package duke.task;

public class Todo extends Task {

    /**
     * Constructs a new Todo object inheriting from Task object
     *
     * @param description To-do description
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Overrides toString method from Task class
     *
     * @return task icon and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
