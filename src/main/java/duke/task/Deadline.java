package duke.task;

public class Deadline extends Task {

    protected String deadlineTime;

    public Deadline (String description, String by) {
        super(description);
        this.deadlineTime = by;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
    }
}
