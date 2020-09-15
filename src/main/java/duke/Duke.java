package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {

    private static Command commandType;
    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void addDeadlineTask (Task[] tasks, String command, String delimiter) {
        try {
            String description = getDeadlineAndEventDescription(command, delimiter);
            String time = getDeadlineAndEventTime(command, delimiter);
            int taskCount = Task.getNumberOfTask();
            Task t = new Deadline(description, time);
            tasks[taskCount] = t;
            printAddMessage(tasks[taskCount]);
            Task.incrementNumberOfTask();
        } catch (DukeException e) {
            ErrorMessage.printDeadlineSyntaxCommandMessage(command);
        }
    }

    public static void addEventTask (Task[] tasks, String command, String delimiter) {
        try {
            String description = getDeadlineAndEventDescription(command, delimiter);
            String time = getDeadlineAndEventTime(command, delimiter);
            int taskCount = Task.getNumberOfTask();
            Task t = new Event(description, time);
            tasks[taskCount] = t;
            printAddMessage(tasks[taskCount]);
            Task.incrementNumberOfTask();
        } catch (DukeException e) {
            ErrorMessage.printEventSyntaxCommandMessage(command);
        }
    }

    public static void addTodoTask (String command, Task[] tasks) {
        try {
            String description = getTodoDescription(command);
            Task t = new Todo(description);
            int taskCount = Task.getNumberOfTask();
            tasks[taskCount] = t;
            printAddMessage(tasks[taskCount]);
            Task.incrementNumberOfTask();
        } catch (DukeException e) {
            ErrorMessage.printTodoSyntaxCommandMessage(command);
        }
    }

    public static String getDeadlineAndEventTime (String command, String delimiter) throws DukeException {
        String[] descriptions = command.split(delimiter, 2);
        if (descriptions.length <= 1) {
            throw new DukeException();
        } else if (descriptions[1].trim().length() == 0) {
            throw new DukeException();
        }

        return descriptions[1].trim();
    }

    public static String getDeadlineAndEventDescription (String command, String delimiter) throws DukeException {
        String[] words = command.split(" ", 2);
        if (words.length <= 1) {
            throw new DukeException();
        } else if (words[1].trim().length() == 0) {
            throw new DukeException();
        } else if (!words[1].trim().contains(delimiter)) {
            throw new DukeException();
        }

        String description = words[1].split(delimiter, 2)[0].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }

        return description;
    }

    public static String getTodoDescription (String command) throws DukeException {
        String[] slicedCommand = command.split(" ", 2);
        if (slicedCommand.length == 1) {
            throw new DukeException();
        } else if (slicedCommand[1].trim().length() == 0) {
            throw new DukeException();
        }

        return slicedCommand[1].trim();
    }

    public static String getInput() {
        System.out.print("Hung: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            ErrorMessage.printNullCommandErrorMessage();
            System.out.print("Hung: ");
            inputLine = SCANNER.nextLine();
        }

        try {
            getCommandType(inputLine);
        } catch (DukeException e) {
            ErrorMessage.printCommandTypeErrorMessage();
        }

        return inputLine;
    }

    public static void getCommandType (String command) throws DukeException {
        String[] slicedInput = command.split(" ", 2);

        switch (slicedInput[0].toLowerCase().trim()) {
        case "list":
            commandType = Command.LIST;
            break;
        case "bye":
            commandType = Command.BYE;
            break;
        case "todo":
            commandType = Command.TODO;
            break;
        case "deadline":
            commandType = Command.DEADLINE;
            break;
        case "event":
            commandType = Command.EVENT;
            break;
        case "done":
            commandType = Command.DONE;
            break;
        default:
            commandType = Command.ERROR;
            throw new DukeException();
        }
    }

    public static void printWelcomeMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Hello! I'm !bot\nWhat are your orders?");
        System.out.println("-----------------------------------------");
    }

    public static void printGoodbyeMessage() {
        System.out.println("-----------------------------------------");
        System.out.print("!bot: ");
        System.out.println("~~Dead is like a wind, always by my side~~");
        System.out.println("-----------------------------------------");
    }

    public static void printAddMessage (Task task) {
        int taskCount = Task.getNumberOfTask() + 1;
        System.out.println("-----------------------------------------");
        System.out.println("!bot:\nAdded: " + task.toString());
        if (taskCount <= 1) {
            System.out.println("You now have " + taskCount + " task in the list.");
        } else {
            System.out.println("You now have " + taskCount + " tasks in the list.");
        }
        System.out.println("-----------------------------------------");
    }

    public static void printSetTaskDoneMessage (Task task) {
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Nice! I've mark this task as done:");
        System.out.printf(" %s\n", task.toString());
        System.out.println("-----------------------------------------");
    }

    public static void printTaskList (Task[] tasks) {
        int taskCount = Task.getNumberOfTask();
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");

        if (taskCount == 0) {
            System.out.println("There is no task in the list for now.");
            System.out.println("-----------------------------------------");
            return;
        }

        for (int i = 1; i < taskCount + 1; i = i + 1) {
            System.out.printf("%d. %s\n", i, tasks[i - 1].toString());
        }
        System.out.println("-----------------------------------------");
    }

    public static void setTaskAsDone (String command, Task[] tasks) {
        try {
            int taskNumber = Integer.parseInt(command.substring(5)) - 1;
            tasks[taskNumber].setDone();
            printSetTaskDoneMessage(tasks[taskNumber]);
        } catch (NumberFormatException e) {
            ErrorMessage.printNumberFormatErrorMessage();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsErrorMessage();
        }
    }

    public static void executeCommands (Task[] tasks) {
        String command;
        command = getInput();

        while (commandType != Command.BYE) {
            switch (commandType) {
            case LIST:
                printTaskList(tasks);
                break;
            case DONE:
                setTaskAsDone(command, tasks);
                break;
            case TODO:
                addTodoTask(command, tasks);
                break;
            case DEADLINE:
                addDeadlineTask(tasks, command, "/by");
                break;
            case EVENT:
                addEventTask(tasks, command, "/at");
                break;
            default:
                break;
            }
            command = getInput();
        }
    }

    public static void main (String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        printWelcomeMessage();
        executeCommands(tasks);
        printGoodbyeMessage();
    }

}
