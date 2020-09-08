import java.util.Scanner;

public class Duke {

    private static Command commandType;
    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void addDeadlineTask(String command, Task[] tasks, String delimiter) {
        String[] words = command.split(" ", 2);
        String[] deadline = command.split(delimiter);

        try {
            String description = words[1].split(delimiter, 2)[0].trim();
            String time = deadline[1].trim();
            int taskCount = Task.getNumberOfTask();
            Task t = new Deadline(description, time);
            tasks[taskCount] = t;
            printAddMessage(tasks[taskCount]);
            Task.incrementNumberOfTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot init Deadline task");
        }
    }

    public static void addEventTask(String command, Task[] tasks, String delimiter) {
        String[] words = command.split(" ", 2);
        String[] event = command.split(delimiter);
        try {
            String description = words[1].split(delimiter, 2)[0].trim();
            String time = event[1].trim();
            int taskCount = Task.getNumberOfTask();
            Task t = new Event(description, time);
            tasks[taskCount] = t;
            printAddMessage(tasks[taskCount]);
            Task.incrementNumberOfTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot init Event task");
        }
    }

    public static void addTodoTask(String command, Task[] tasks) {
        String[] slicedCommand = command.split(" ", 2);
        try {
            Task t = new Todo(slicedCommand[1]);
            int taskCount = Task.getNumberOfTask();
            tasks[taskCount] = t;
            printAddMessage(tasks[taskCount]);
            Task.incrementNumberOfTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot init Todo task");
        }
    }

    public static String getInput() {
        System.out.print("Hung: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            printNullCommandMessage();
            System.out.print("Hung: ");
            inputLine = SCANNER.nextLine();
        }

        getCommandType(inputLine);

        return inputLine;
    }

    public static void getCommandType(String command) {
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
            System.out.println("XXXXXXXX");
        }
    }

    public static void printNullCommandMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: Command cannot be empty! Please try another command!");
        System.out.println("-----------------------------------------");
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

    public static void printErrorMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Your command is not valid!");
        System.out.println("Please enter another command!");
        System.out.println("-----------------------------------------");
    }

    public static void printAddMessage(Task task) {
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

    public static void printSetTaskDoneMessage(Task task) {
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Nice! I've mark this task as done:");
        System.out.printf(" %s\n", task.toString());
        System.out.println("-----------------------------------------");
    }

    public static void printTaskList(Task[] tasks) {
        int taskCount = Task.getNumberOfTask();
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        for (int i = 1; i < taskCount + 1; i = i + 1) {
            System.out.printf("%d. %s\n", i, tasks[i - 1].toString());
        }
        System.out.println("-----------------------------------------");
    }

    public static void setTaskAsDone(String command, Task[] tasks) {
        try {
            int taskNumber = Integer.parseInt(command.substring(5)) - 1;
            tasks[taskNumber].setDone();
            printSetTaskDoneMessage(tasks[taskNumber]);
        } catch (NumberFormatException e) {
            System.out.println("Task number should be an integer");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Task number is not valid");
        }
    }

    public static void executeCommands(Task[] tasks) {
        String command;
        command = getInput();

        while (commandType != Command.BYE) {
            try {
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
                    addDeadlineTask(command, tasks, "/by");
                    break;
                case EVENT:
                    addEventTask(command, tasks, "/at");
                    break;
                default:
                    System.out.println("xxxxx");
                }
            } catch (NullPointerException e) {
                System.out.println("No command type found");
            }

            command = getInput();
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];

        printWelcomeMessage();
        executeCommands(tasks);
        printGoodbyeMessage();
    }

}
