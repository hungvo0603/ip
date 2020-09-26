package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TextUI;
import duke.parser.Parser;

import java.util.ArrayList;

public class TaskList {
    public static final ArrayList<Task> tasks = new ArrayList<>();

    public static int getNumberOfTasks() {
        return tasks.size();
    }

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

    public static void addTodoTaskFromInput(String command) {
        try {
            String description = Parser.getTodoDescription(command);
            writeTodoTaskToList(description);
            TextUI.printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printTodoSyntaxCommandMessage(command);
        }
    }

    public static void writeDeadlineTaskToList(String description, String time) {
        Task t = new Deadline(description, time);
        tasks.add(t);
    }

    public static void writeEventTaskToList(String description, String time) {
        Task t = new Event(description, time);
        tasks.add(t);
    }

    public static void writeTodoTaskToList(String description) {
        Task t = new Todo(description);
        tasks.add(t);
    }

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
