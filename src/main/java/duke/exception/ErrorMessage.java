package duke.exception;

import duke.constants.Constants;

public class ErrorMessage {

    public static void printDeadlineSyntaxCommandMessage(String command) {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.BOT_INVALID_COMMAND + command);
        System.out.println(Constants.SAMPLE_DEADLINE_COMMAND);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printEventSyntaxCommandMessage(String command) {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.BOT_INVALID_COMMAND + command);
        System.out.println(Constants.SAMPLE_EVENT_COMMAND);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printTodoSyntaxCommandMessage(String command) {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.BOT_INVALID_COMMAND + command);
        System.out.println(Constants.SAMPLE_TODO_COMMAND);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printFindSyntaxCommandMessage(String command) {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.BOT_INVALID_COMMAND + command);
        System.out.println(Constants.SAMPLE_FIND_COMMAND);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printInvalidInputMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.BOT_INVALID_INPUT);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printDateTimeFormatErrorMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.DATETIME_FORMAT_ERROR_MESSAGE);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printCommandTypeErrorMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.BOT_INVALID_COMMAND);
        System.out.println(Constants.COMMAND_LIST);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printNullCommandErrorMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.NULL_COMMAND_ERROR);
        System.out.println(Constants.LINE_DIVIDER);

    }

    public static void printOutOfBoundsErrorMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.INVALID_TASK_NUMBER_ERROR);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printNumberFormatErrorMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.TASK_NUMBER_FORMAT_ERROR);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printDateNotProvidedErrorMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.NO_DATE_PROVIDED);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printDateFormatErrorMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.DATE_FORMAT_ERROR);
        System.out.println(Constants.LINE_DIVIDER);
    }

}
