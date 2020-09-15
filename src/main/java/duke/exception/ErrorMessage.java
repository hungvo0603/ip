package duke.exception;

public class ErrorMessage {

    public static void printDeadlineSyntaxCommandMessage(String command) {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: invalid command: " + command);
        System.out.println("sample: deadline deadlineDescription /by deadlineTime");
        System.out.println("-----------------------------------------");
    }

    public static void printEventSyntaxCommandMessage(String command) {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: invalid command: " + command);
        System.out.println("sample: event eventDescription /at eventTime");
        System.out.println("-----------------------------------------");
    }

    public static void printTodoSyntaxCommandMessage(String command) {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: invalid command: " + command);
        System.out.println("sample: todo todoDescription");
        System.out.println("-----------------------------------------");
    }

    public static void printCommandTypeErrorMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: invalid command type");
        System.out.println("commands: todo, event, deadline, done, list, bye");
        System.out.println("-----------------------------------------");
    }

    public static void printNullCommandErrorMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: command cannot be empty! Please try another command!");
        System.out.println("-----------------------------------------");
    }

    public static void printOutOfBoundsErrorMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("Task number is not valid");
        System.out.println("-----------------------------------------");
    }

    public static void printNumberFormatErrorMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: task number should be an integer");
        System.out.println("-----------------------------------------");
    }

    public static void printUnknownError() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot: Something went wrong. Please try again!!");
        System.out.println("-----------------------------------------");
    }
}
