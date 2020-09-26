package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;

import java.util.Scanner;

public enum Command {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    DONE,
    BYE,
    DELETE,
    ERROR;

    public static Command commandType;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getCommand() {
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
}
