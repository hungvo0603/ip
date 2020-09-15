package duke.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    protected static int numberOfTask = 0;

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

    public static int getNumberOfTask() {
        return numberOfTask;
    }

    public static void incrementNumberOfTask() {
        numberOfTask++;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[\u2713]" : "[\u2718]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
