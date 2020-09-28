package duke.task;

import duke.constants.Constants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Construct an Deadline task inheriting from Task class
     *
     * @param description Deadline description
     * @param date Deadline date
     * @param by Deadline time
     */
    public Deadline (String description, LocalDate date, LocalTime by) {
        super(description);
        this.time = by;
        this.date = date;
    }

    public String getTime() {
        String time = this.time.toString();
        return date.format(DateTimeFormatter.ofPattern(Constants.INPUT_DATE_FORMAT)) +
                " " + time.substring(0, 2) + time.substring(3);
    }

    /**
     * Override {@link package.task#toString()}
     *
     * @return task type, done status, description and time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern(Constants.OUTPUT_DATE_FORMAT)) + " " +
                time.format(DateTimeFormatter.ofPattern(Constants.OUTPUT_TIME_FORMAT)) + ")";
    }
}
