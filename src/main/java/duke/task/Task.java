package duke.task;

import duke.constants.Constants;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Construct a new Task object
     * By default, done status is set to false
     *
     * @param description Task description
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? Constants.TICK_ICON : Constants.CROSS_ICON);
    }

    /**
     * Override toString method of Object class
     *
     * @return status icon and task description
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
