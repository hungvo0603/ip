package duke.task;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.ui.TextUI;
import duke.parser.Parser;

import java.util.ArrayList;

public class TaskList {
    public static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns number of tasks in the user's list
     *
     * @return number of tasks
     */
    public static int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Add a new event task from user's command to the list.
     *
     * @param command should be in the structure: event description /at YYYY-MM-DD HHMM
     */
    public static void addEventTaskFromInput(String command) {
        try {
            String description = Parser.getDeadlineAndEventDescription(command, "/at");
            String time = Parser.getDeadlineAndEventTime(command, "/at");
            writeEventTaskToList(description, time);
            TextUI.printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printEventSyntaxCommandMessage(command);
        }
    }

    /**
     * Add a new deadline task from user's command to the list.
     *
     * @param command should be in the structure: deadline description /by YYYY-MM-DD HHMM
     */
    public static void addDeadlineTaskFromInput(String command) {
        try {
            String description = Parser.getDeadlineAndEventDescription(command, "/by");
            String time = Parser.getDeadlineAndEventTime(command, "/by");
            writeDeadlineTaskToList(description, time);
            TextUI.printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printDeadlineSyntaxCommandMessage(command);
        }
    }

    /**
     * Add a new to-do task from user's command to the list.
     *
     * @param command should be in the structure: todo description
     */
    public static void addTodoTaskFromInput(String command) {
        try {
            String description = Parser.getTodoDescription(command);
            writeTodoTaskToList(description);
            TextUI.printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printTodoSyntaxCommandMessage(command);
        }
    }

    /**
     * Append the tasks list with a new Deadline tasks
     *
     * @param description deadline title
     * @param time deadline time
     */
    public static void writeDeadlineTaskToList(String description, String time) {
        Task t = new Deadline(description, time);
        tasks.add(t);
    }

    /**
     * Append the tasks list with a new Event tasks
     *
     * @param description event title
     * @param time event time
     */
    public static void writeEventTaskToList(String description, String time) {
        Task t = new Event(description, time);
        tasks.add(t);
    }

    /**
     * Append the tasks list with a new Todo tasks
     *
     * @param description to-do description
     */
    public static void writeTodoTaskToList(String description) {
        Task t = new Todo(description);
        tasks.add(t);
    }

    /**
     * Delete a task at a specific position
     *
     * @param command "delete 1" deletes the first task in the list
     */
    public static void deleteTask(String command) {
        try {
            int taskNumber = Integer.parseInt(command.substring(7)) - 1;
            Task t = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            TextUI.printDeleteMessage(t);
        } catch (NumberFormatException e) {
            ErrorMessage.printNumberFormatErrorMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsErrorMessage();
        }
    }

    /**
     * Set a task as completed at a specific position
     *
     * @param command "done 1" sets the first task in the list  as done
     */
    public static void setTaskAsDone(String command) {
        try {
            int taskNumber = Integer.parseInt(command.substring(5)) - 1;
            tasks.get(taskNumber).setDone();
            TextUI.printSetTaskDoneMessage(tasks.get(taskNumber));
        } catch (NumberFormatException e) {
            ErrorMessage.printNumberFormatErrorMessage();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsErrorMessage();
        }
    }

    /**
     * Print all the tasks in the list
     * Print a notification if there is no task in the list
     */
    public static void printTaskList () {
        int taskCount = tasks.size();

        System.out.println("-----------------------------------------");
        System.out.println("!bot:");

        if (taskCount == 0) {
            System.out.println("There is no task in the list for now.");
            System.out.println("-----------------------------------------");
            return;
        }

        for (int i = 1; i < taskCount + 1; i = i + 1) {
            System.out.printf("%d. %s\n", i, tasks.get(i-1).toString());
        }

        System.out.println("-----------------------------------------");
    }
}
