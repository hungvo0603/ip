package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;

import java.util.Scanner;

/**
 * An enumeration for commands and methods dealing with commands
 */
public enum Command {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    DONE,
    FIND,
    BYE,
    DELETE,
    ERROR;

    public static Command commandType;
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Get input command from the user
     *
     * @return command that user input
     */
    public static String getCommand() {
        System.out.print("!man: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            ErrorMessage.printNullCommandErrorMessage();
            System.out.print("!man: ");
            inputLine = SCANNER.nextLine();
        }

        try {
            getCommandType(inputLine);
        } catch (DukeException e) {
            ErrorMessage.printCommandTypeErrorMessage();
        }

        return inputLine;
    }

    /**
     * Get command type of the command
     * Including: list, bye, deadline, event, done, delete, find, todo
     *
     * @param command the line that the user input
     * @throws DukeException when the command doesn't fall into any type
     */
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
        case "find":
            commandType = Command.FIND;
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
