package duke.task;

public class Event extends Task {

    protected String eventTime;

    /**
     * Construct an Event task inheriting from Task class
     *
     * @param description Event description
     * @param eventTime Event time
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    /**
     * Override {@link package.task#toString()}
     *
     * @return task type, done status, description and time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
