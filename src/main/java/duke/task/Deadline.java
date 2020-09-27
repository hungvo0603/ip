package duke.task;

public class Deadline extends Task {

    protected String deadlineTime;

    /**
     * Construct an Deadline task inheriting from Task class
     *
     * @param description Deadline description
     * @param by Deadline time
     */
    public Deadline (String description, String by) {
        super(description);
        this.deadlineTime = by;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Override {@link package.task#toString()}
     *
     * @return task type, done status, description and time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
    }
}
