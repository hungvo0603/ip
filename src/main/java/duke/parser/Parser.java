package duke.parser;

import duke.exception.DukeException;

public class Parser {

    /**
     * Slice the command to get time of Deadline and Event tasks
     *
     * @param command Command of deadline and event to be sliced
     * @param delimiter /at for Event task, /by for Deadline task
     * @return time of Deadline or Event task
     * @throws DukeException When time is missing or when it is poorly constructed
     */
    public static String getDeadlineAndEventTime (String command, String delimiter) throws DukeException {
        String[] descriptions = command.split(delimiter, 2);
        if (descriptions.length <= 1) {
            throw new DukeException();
        } else if (descriptions[1].trim().length() == 0) {
            throw new DukeException();
        }

        return descriptions[1].trim();
    }

    /**
     * Slice the command to get description of Deadline and Event tasks
     *
     * @param command Command of deadline and event to be sliced
     * @param delimiter /at for Event task, /by for Deadline task
     * @return time of Deadline or Event task
     * @throws DukeException When description is missing or command is poorly constructed
     */
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

    /**
     * Slice the command to get a Todo task description
     *
     * @param command command to be sliced
     * @return String illustrate to-do description
     * @throws DukeException When command is poorly constructed
     */
    public static String getTodoDescription (String command) throws DukeException {
        String[] slicedCommand = command.split(" ", 2);
        if (slicedCommand.length == 1) {
            throw new DukeException();
        } else if (slicedCommand[1].trim().length() == 0) {
            throw new DukeException();
        }

        return slicedCommand[1].trim();
    }
}
