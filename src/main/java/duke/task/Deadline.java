package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Deadline (String description, LocalDate date, LocalTime by) {
        super(description);
        this.time = by;
        this.date = date;
    }

    public String getTime() {
        String time = this.time.toString();
        return date + " " + time.substring(0, 2) + time.substring(3);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " +
                time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
