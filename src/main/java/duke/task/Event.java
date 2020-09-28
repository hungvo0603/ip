package duke.task;

import duke.constants.Constants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalTime time;
    protected LocalDate date;

    /**
     * Construct an Event task inheriting from Task class
     *
     * @param description Event description
     * @param date Event date
     * @param eventTime Event time
     */
    public Event(String description, LocalDate date, LocalTime eventTime) {
        super(description);
        this.date = date;
        this.time = eventTime;
    }

    public String getTime() {
        String time = this.time.toString();
        return date.format(DateTimeFormatter.ofPattern(Constants.INPUT_DATE_FORMAT))
                + " " + time.substring(0, 2) + time.substring(3);
    }

    /**
     * Override {@link package.task#toString()}
     *
     * @return task type, done status, description and time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern(Constants.OUTPUT_DATE_FORMAT)) + " " +
                time.format(DateTimeFormatter.ofPattern(Constants.OUTPUT_TIME_FORMAT)) + ")";
    }
}
