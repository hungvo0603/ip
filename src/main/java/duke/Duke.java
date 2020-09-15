package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

//import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static Command commandType;
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String FILE_PATH = "data/duke.txt";

    public static void writeToFile(String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToWrite);
        fw.close();
    }

    public static void readFromFile() {
        File directory = new File("data");
        File f = new File(FILE_PATH);

        if (directory.mkdir()) {
            System.out.println("A directory has just been created: data");
        } else {
            System.out.println("saving directory: " + FILE_PATH);
        }

        try {
            Scanner s = new Scanner(f);
            loadTasksFromFile(s);
        } catch (FileNotFoundException e) {
            System.out.println("Initialize saving file...");
        }
    }

    public static void loadTasksFromFile(Scanner s) {
        while (s.hasNext()) {
            String[] readings = s.nextLine().split("\\|");

            for (int i = 0; i < readings.length; i++) {
                readings[i] = readings[i].trim();
            }

            switch(readings[0]) {
            case "T":
                addTodoTask(readings[2]);
                break;
            case "D":
                addDeadlineTask(readings[2], readings[3]);
                break;
            case "E":
                addEventTask(readings[2], readings[3]);
                break;
            default:
                System.out.println("ERROR");
            }

            if (readings[1].equals("true")) {
                tasks[Task.getNumberOfTask() - 1].setDone();
            }
        }
    }

    public static void addDeadlineTask (String description, String time) {
        int taskCount = Task.getNumberOfTask();
        Task t = new Deadline(description, time);
        tasks[taskCount] = t;
        Task.incrementNumberOfTask();
    }

    public static void addEventTask (String description, String time) {
        int taskCount = Task.getNumberOfTask();
        Task t = new Event(description, time);
        tasks[taskCount] = t;
        Task.incrementNumberOfTask();

    }

    public static void addTodoTask (String description) {
        Task t = new Todo(description);
        int taskCount = Task.getNumberOfTask();
        tasks[taskCount] = t;
        Task.incrementNumberOfTask();
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
        int taskCount = Task.getNumberOfTask();
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

    public static void printTaskList () {
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

    public static void setTaskAsDone (String command) {
        try {
            int taskNumber = Integer.parseInt(command.substring(5)) - 1;
            tasks[taskNumber].setDone();
            printSetTaskDoneMessage(tasks[taskNumber]);
        } catch (NumberFormatException e) {
            ErrorMessage.printNumberFormatErrorMessage();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsErrorMessage();
        }
    }

    public static void executeCommands () {
        String command;
        command = getInput();

        while (commandType != Command.BYE) {
            switch (commandType) {
            case LIST:
                printTaskList();
                break;
            case DONE:
                setTaskAsDone(command);
                break;
            case TODO:
                try {
                    String description = getTodoDescription(command);
                    addTodoTask(description);
                    printAddMessage(tasks[Task.getNumberOfTask() - 1]);
                } catch (DukeException e) {
                    System.out.println("ERROR");
                }

                break;
            case DEADLINE:
                try {
                    String description = getDeadlineAndEventDescription(command, "/by");
                    String time = getDeadlineAndEventTime(command, "/by");
                    addDeadlineTask(description, time);
                    printAddMessage(tasks[Task.getNumberOfTask() - 1]);
                } catch (DukeException e) {
                    System.out.println("ERROR");
                }
                break;
            case EVENT:
                try {
                    String description = getDeadlineAndEventDescription(command, "/at");
                    String time = getDeadlineAndEventTime(command, "/at");
                    addEventTask(description, time);
                    printAddMessage(tasks[Task.getNumberOfTask() - 1]);
                } catch (DukeException e) {
                    System.out.println("ERROR");
                }
                break;
            default:
                break;
            }
            command = getInput();
        }
    }

    public static void saveTasks() {
        String textToAdd = "";

        for (int i = 0; i < Task.getNumberOfTask(); i++) {
            Task t = tasks[i];
            if (t instanceof Todo) {
                textToAdd = textToAdd.concat("T | " + t.isDone() + " | " + t.getDescription() + System.lineSeparator());
            } else if (t instanceof Deadline) {
                textToAdd = textToAdd.concat("D | " + t.isDone() + " | " + t.getDescription()
                        + " | " + ((Deadline) t).getDeadlineTime() + System.lineSeparator());
            } else if (t instanceof Event) {
                textToAdd = textToAdd.concat("E | " + t.isDone() + " | " + t.getDescription()
                        + " | " + ((Event) t).getEventTime() + System.lineSeparator());
            } else {
                System.out.println("ERROR");
            }
        }
        try {
            writeToFile(textToAdd);
        } catch (IOException e) {
            System.out.println("CANNOT WRITE TO FILE");
        }
    }

    public static void main (String[] args) {
        readFromFile();
        printWelcomeMessage();
        executeCommands();
        printGoodbyeMessage();
        saveTasks();
    }

}
