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
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static Command commandType;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String FILE_PATH = "data/duke.txt";

    public static void addEventTaskFromInput(String command) {
        try {
            String description = getDeadlineAndEventDescription(command, "/at");
            String time = getDeadlineAndEventTime(command, "/at");
            readEventTaskToArray(description, time);
            printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printUnknownError();
        }
    }

    public static void addDeadlineTaskFromInput(String command) {
        try {
            String description = getDeadlineAndEventDescription(command, "/by");
            String time = getDeadlineAndEventTime(command, "/by");
            readDeadlineTaskToArray(description, time);
            printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            System.out.println("ERROR");
        }
    }

    public static void addTodoTaskFromInput(String command) {
        try {
            String description = getTodoDescription(command);
            readTodoTaskToArray(description);
            printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            System.out.println("ERROR");
        }
    }

    public static void readDeadlineTaskToArray (String description, String time) {
        Task t = new Deadline(description, time);
        tasks.add(t);
    }

    public static void readEventTaskToArray (String description, String time) {
        Task t = new Event(description, time);
        tasks.add(t);
    }

    public static void readTodoTaskToArray (String description) {
        Task t = new Todo(description);
        tasks.add(t);
    }

    public static void loadTasks() {
        File directory = new File("data");
        File f = new File(FILE_PATH);

        if (directory.mkdir()) {
            System.out.println("A directory has just been created: data");
        } else {
            System.out.println("saving directory: " + FILE_PATH);
        }

        try {
            Scanner s = new Scanner(f);
            try {
                readDataFromFile(s);
            } catch (IndexOutOfBoundsException e) {
                ErrorMessage.printOutOfBoundsErrorMessage();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Initialize saving file...");
        }
    }

    public static void readDataFromFile(Scanner s) throws IndexOutOfBoundsException {
        while (s.hasNext()) {
            String[] readings = s.nextLine().split("\\|");

            for (int i = 0; i < readings.length; i++) {
                readings[i] = readings[i].trim();
            }

            switch(readings[0]) {
            case "T":
                readTodoTaskToArray(readings[2]);
                break;
            case "D":
                readDeadlineTaskToArray(readings[2], readings[3]);
                break;
            case "E":
                readEventTaskToArray(readings[2], readings[3]);
                break;
            default:
                throw new IndexOutOfBoundsException();
            }

            if (readings[1].equals("true")) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
    }

    public static void saveTasks() {
        String textToAdd = "";

        for (Task t : tasks) {
            if (t instanceof Todo) {
                textToAdd = textToAdd.concat("T | " + t.isDone() + " | " + t.getDescription()
                        + System.lineSeparator());
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

    public static void writeToFile(String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToWrite);
        fw.close();
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
        case "delete":
            commandType = Command.DELETE;
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
        int taskCount = tasks.size();

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
        int taskCount = tasks.size();

        System.out.println("-----------------------------------------");
        System.out.println("!bot:");

        if (taskCount == 0) {
            System.out.println("There is no task in the list for now.");
            System.out.println("-----------------------------------------");
            return;
        }

        for (int i = 1; i < taskCount + 1; i = i + 1) {
            System.out.printf("%d. %s\n", i, tasks.get(i-1).toString());
        }

        System.out.println("-----------------------------------------");
    }

    public static void printDeleteMessage(Task t) {
        int taskCount = tasks.size();
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Noted. I've removed this tasks");
        System.out.println("  " + t.toString());
        if (taskCount <= 1) {
            System.out.println("Now you have " + taskCount + " task in the list");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list");
        }
        System.out.println("-----------------------------------------");
    }

    public static void deleteTask (String command) {
        try {
            int taskNumber = Integer.parseInt(command.substring(7)) - 1;
            Task t = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            printDeleteMessage(t);
        } catch (NumberFormatException e) {
            ErrorMessage.printNumberFormatErrorMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsErrorMessage();
        }
    }

    public static void setTaskAsDone (String command) {
        try {
            int taskNumber = Integer.parseInt(command.substring(5)) - 1;
            tasks.get(taskNumber).setDone();
            printSetTaskDoneMessage(tasks.get(taskNumber));
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
                addTodoTaskFromInput(command);
                break;
            case DEADLINE:
                addDeadlineTaskFromInput(command);
                break;
            case EVENT:
                addEventTaskFromInput(command);
                break;
            case DELETE:
                deleteTask(command);
                break;
            default:
                break;
            }
            command = getInput();
        }
    }

    public static void main (String[] args) {
        loadTasks();
        printWelcomeMessage();
        executeCommands();
        printGoodbyeMessage();
        saveTasks();
    }

}
