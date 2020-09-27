package duke.parser;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {


    public static String getDeadlineAndEventDateAndTime(String command, String delimiter) throws DukeException {
        String[] descriptions = command.split(delimiter, 2);
        if (descriptions.length <= 1) {
            throw new DukeException();
        } else if (descriptions[1].trim().length() == 0) {
            throw new DukeException();
        }

        return descriptions[1].trim();
    }

    public static LocalDate getDeadlineAndEventDate(String dateAndTime) throws DukeException, DateTimeParseException {
        String[] str = dateAndTime.split(" ");
        if (str.length != 2) {
            throw new DukeException();
        }
        return LocalDate.parse(str[0]);
    }
    
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
