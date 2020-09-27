package duke.parser;

import duke.exception.DukeException;

public class Parser {

    public static String getDeadlineAndEventTime(String command, String delimiter) throws DukeException {
        String[] descriptions = command.split(delimiter, 2);
        if (descriptions.length <= 1) {
            throw new DukeException();
        } else if (descriptions[1].trim().length() == 0) {
            throw new DukeException();
        }

        return descriptions[1].trim();
    }

    public static String getDeadlineAndEventDescription(String command, String delimiter) throws DukeException {
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

    public static String getTodoAndFindDescription(String command) throws DukeException {
        String[] slicedCommand = command.split(" ", 2);
        if (slicedCommand.length == 1) {
            throw new DukeException();
        } else if (slicedCommand[1].trim().length() == 0) {
            throw new DukeException();
        }

        return slicedCommand[1].trim();
    }
}
