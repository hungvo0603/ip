package duke.parser;

import duke.constants.Constants;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Slices the command to get date and time of Deadline and Event tasks
     *
     * @param command Command of deadline and event to be sliced
     * @param delimiter /at for Event task, /by for Deadline task
     * @return date and time of Deadline or Event task
     * @throws DukeException When the command is poorly constructed
     */
    public static String getDeadlineAndEventDateAndTime(String command, String delimiter) throws DukeException {
        String[] descriptions = command.split(delimiter, 2);
        if (descriptions.length <= 1) {
            throw new DukeException();
        } else if (descriptions[1].trim().length() == 0) {
            throw new DukeException();
        }

        return descriptions[1].trim();
    }

    /**
     * Gets date for Deadline and Event tasks
     *
     * @param dateAndTime String contains date and time
     * @return LocalDate of the date
     * @throws DukeException When command is poorly constructed
     * @throws DateTimeParseException when string of date cannot be parsed to LocalDate type
     */
    public static LocalDate getDeadlineAndEventDate(String dateAndTime) throws DukeException, DateTimeParseException {
        String[] str = dateAndTime.split(" ");
        if (str.length != 2) {
            throw new DukeException();
        }
        return LocalDate.parse(str[0], DateTimeFormatter.ofPattern(Constants.INPUT_DATE_FORMAT));
    }

    /**
     * Gets time for Deadline and Event tasks
     * @param dateAndTime String contains date and time
     * @return LocalTime of time in hour:minute
     * @throws DukeException When the command is poorly constructed
     * @throws StringIndexOutOfBoundsException When date or time is missing
     */
    public static LocalTime getDeadlineAndEventTime(String dateAndTime) throws DukeException, StringIndexOutOfBoundsException {
        String[] str = dateAndTime.split(" ");
        if (str.length != 2) {
            throw new DukeException();
        }

        try {
            int time = Integer.parseInt(str[1]);
            if (time < 0 || time > 2359) {
                throw new DukeException();
            }
        } catch (NumberFormatException e) {
            ErrorMessage.printNumberFormatErrorMessage();
        }

        String hour = str[1].substring(0, 2);
        String minute = str[1].substring(2, 4);
        String time = hour + ":" + minute;
        return LocalTime.parse(time);
    }

    /**
     * Slices the command to get description of Deadline and Event tasks
     *
     * @param command Command of deadline and event to be sliced
     * @param delimiter /at for Event task, /by for Deadline task
     * @return description of Deadline or Event task
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
     * Slices the command to get a todo task description
     *
     * @param command command to be sliced
     * @return String illustrate to-do description
     * @throws DukeException When command is poorly constructed
     */
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
