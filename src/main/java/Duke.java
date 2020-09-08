import java.util.Scanner;

public class Duke {

    private static Command commandType;
    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void addDeadlineTask(String command, Task[] tasks, String delimiter) {
        String[] words = command.split(" ", 2);
        String[] deadline = command.split(delimiter);
        String description = words[1].split(delimiter, 2)[0].trim();
        String time = deadline[1].trim();
        Task t = new Deadline(description, time);
        tasks[Task.getNumberOfTask()] = t;
        Task.incrementNumberOfTask();
    }

    public static void addEventTask(String command, Task[] tasks, String delimiter) {
        String[] words = command.split(" ", 2);
        String[] event = command.split(delimiter);
        String description = words[1].split(delimiter, 2)[0].trim();
        String time = event[1].trim();
        Task t = new Event(description, time);
        tasks[Task.getNumberOfTask()] = t;
        Task.incrementNumberOfTask();
    }

    public static void addTodoTask(String command, Task[] tasks) {
        String description = command.split(" ", 2)[1];
        Task t = new Todo(description);
        tasks[Task.getNumberOfTask()] = t;
        Task.incrementNumberOfTask();
    }

    public static String getInput() {
        System.out.print("Hung: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }

        return inputLine;
    }

    public static void printWelcomeMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!Bot:");
        System.out.println("Hello! I'm !Bot\nWhat are your orders?");
        System.out.println("-----------------------------------------");
    }

    public static void printGoodbyeMessage() {
        System.out.println("-----------------------------------------");
        System.out.print("!Bot: ");
        System.out.println("~~Dead is like a wind, always by my side~~");
        System.out.println("-----------------------------------------");
    }

    public static void printErrorMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!Bot:");
        System.out.println("Your command is not valid!");
        System.out.println("Please enter another command!");
        System.out.println("-----------------------------------------");
    }

    public static void printAddMessage(Task task) {
        int taskCount = Task.getNumberOfTask();
        System.out.println("-----------------------------------------");
        System.out.println("!Bot:\nAdded: " + task.toString());
        if (taskCount == 1) {
            System.out.println("You now have " + taskCount + " task in the list.");
        } else {
            System.out.println("You now have " + taskCount + " tasks in the list.");
        }
        System.out.println("-----------------------------------------");
    }

    public static void printSetTaskDoneMessage(Task task) {
        System.out.println("-----------------------------------------");
        System.out.println("!Bot:");
        System.out.println("Nice! I've mark this task as done:");
        System.out.printf(" %s\n", task.toString());
        System.out.println("-----------------------------------------");
    }

    public static void printTaskList(Task[] tasks) {
        int taskCount = Task.getNumberOfTask();
        System.out.println("-----------------------------------------");
        System.out.println("!Bot:");
        for (int i = 1; i < taskCount + 1; i = i + 1) {
            System.out.printf("%d. %s\n", i, tasks[i - 1].toString());
        }
        System.out.println("-----------------------------------------");
    }

    public static void setTaskAsDone(String command, Task[] tasks) {
        int taskNumber = Integer.parseInt(command.substring(5)) - 1;
        int taskCount = Task.getNumberOfTask();
        if ((taskNumber >= 0) && (taskNumber < taskCount)) {
            tasks[taskNumber].setDone();
            printSetTaskDoneMessage(tasks[taskNumber]);
        } else {
            printErrorMessage();
        }
    }

    public static void executeCommands(Task[] tasks) {
        String command;
        command = getInput();
        getCommandType(command.trim());
        while (commandType != Command.BYE) {
            if (commandType == Command.LIST) {
                printTaskList(tasks);
            } else if (commandType == Command.DONE) {
                setTaskAsDone(command, tasks);
            } else if (commandType == Command.TODO) {
                addTodoTask(command, tasks);
                printAddMessage(tasks[Task.getNumberOfTask() - 1]);
            } else if (commandType == Command.DEADLINE){
                addDeadlineTask(command, tasks, "/by");
                printAddMessage(tasks[Task.getNumberOfTask() - 1]);
            } else if (commandType == Command.EVENT){
                addEventTask(command, tasks, "/at");
                printAddMessage(tasks[Task.getNumberOfTask() - 1]);
            } else {
                printErrorMessage();
            }

            command = getInput();
            getCommandType(command);
        }
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
            commandType = Command.ERROR;
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];

        printWelcomeMessage();
        executeCommands(tasks);
        printGoodbyeMessage();
    }

}
