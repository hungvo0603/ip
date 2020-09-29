package duke.ui;

import duke.constants.Constants;
import duke.task.Task;
import duke.task.TaskList;

public class TextUI {

    public static void printWelcomeMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.GREETING_MESSAGE);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printGoodbyeMessage() {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.GOODBYE_MESSAGE);
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printSetTaskDoneMessage(Task task) {
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.MARK_AS_DONE_MESSAGE);
        System.out.printf(" %s\n", task.toString());
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printAddMessage(Task task) {
        int taskCount = TaskList.getNumberOfTasks();
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println("!bot:\nAdded: " + task.toString());
        System.out.println(getTaskCountMessage(taskCount));
        System.out.println(Constants.LINE_DIVIDER);
    }

    public static void printDeleteMessage(Task t) {
        int taskCount = TaskList.getNumberOfTasks();
        System.out.println(Constants.LINE_DIVIDER);
        System.out.println(Constants.REMOVE_TASK_MESSAGE);
        System.out.println("  " + t.toString());
        System.out.println(getTaskCountMessage(taskCount));
        System.out.println(Constants.LINE_DIVIDER);
    }

    /**
     * Gets string stating current number of task
     *
     * @param taskCount number of task count
     * @return String representing to total number of tasks
     */
    private static String getTaskCountMessage(int taskCount) {
        return (taskCount <= 1) ?
                "You now have " + taskCount + " task in the list" :
                "You now have " + taskCount + " tasks in the list";
    }

}
