package duke.task;

import duke.constants.Constants;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.ui.TextUI;
import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * Adds a new event task from user's command to the list.
     *
     * @param command should be in the structure: event description /at YYYY-MM-DD HHMM
     */
    public static void addEventTaskFromInput(String command) {
        try {
            String description = Parser.getDeadlineAndEventDescription(command, "/at");
            String atTime = Parser.getDeadlineAndEventDateAndTime(command, "/at");
            LocalDate date = Parser.getDeadlineAndEventDate(atTime);
            LocalTime time = Parser.getDeadlineAndEventTime(atTime);
            writeEventTaskToList(description, date, time);
            TextUI.printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printEventSyntaxCommandMessage(command);
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            ErrorMessage.printDateTimeFormatErrorMessage();
        }
    }

    /**
     * Adds a new deadline task from user's command to the list.
     *
     * @param command should be in the structure: deadline description /by YYYY-MM-DD HHMM
     */
    public static void addDeadlineTaskFromInput(String command) {
        try {
            String description = Parser.getDeadlineAndEventDescription(command, "/by");
            String byTime = Parser.getDeadlineAndEventDateAndTime(command, "/by");
            LocalDate date = Parser.getDeadlineAndEventDate(byTime);
            LocalTime time = Parser.getDeadlineAndEventTime(byTime);
            writeDeadlineTaskToList(description, date, time);
            TextUI.printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printDeadlineSyntaxCommandMessage(command);
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            ErrorMessage.printDateTimeFormatErrorMessage();
        }
    }

    /**
     * Adds a new to-do task from user's command to the list.
     *
     * @param command should be in the structure: todo description
     */
    public static void addTodoTaskFromInput(String command) {
        try {
            String description = Parser.getTodoAndFindDescription(command);
            writeTodoTaskToList(description);
            TextUI.printAddMessage(tasks.get(tasks.size() - 1));
        } catch (DukeException e) {
            ErrorMessage.printTodoSyntaxCommandMessage(command);
        }
    }

    /**
     * Appends the tasks list with a new Deadline tasks
     *
     * @param description Deadline title
     * @param date Deadline date
     * @param time Deadline time
     */
    public static void writeDeadlineTaskToList(String description, LocalDate date, LocalTime time) {
        Task t = new Deadline(description, date, time);
        tasks.add(t);
    }

    public static void writeEventTaskToList(String description, LocalDate date, LocalTime time) {
        Task t = new Event(description, date, time);
        tasks.add(t);
    }

    /**
     * Appends the tasks list with a new Todo tasks
     *
     * @param description to-do description
     */
    public static void writeTodoTaskToList(String description) {
        Task t = new Todo(description);
        tasks.add(t);
    }

    /**
     * Deletes a task at a specific position
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
     * Sets a task as completed at a specific position
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
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsErrorMessage();
        } catch (DukeException e) {
            ErrorMessage.printTaskAlreadySetDone();
        }
    }

    /**
     * Prints all the tasks in the list
     * Prints a notification if there is no task in the list
     */
    public static void printTaskList() {
        int taskCount = tasks.size();

        System.out.println(Constants.LINE_DIVIDER);
        System.out.println("!bot:");

        if (taskCount == 0) {
            System.out.println(Constants.NO_TASK_MESSAGE);
            System.out.println(Constants.LINE_DIVIDER);
            return;
        }

        for (int i = 1; i < taskCount + 1; i = i + 1) {
            System.out.printf("%d. %s\n", i, tasks.get(i-1).toString());
        }

        System.out.println(Constants.LINE_DIVIDER);
    }

    /**
     * Prints all task found by keywords
     *
     * @param command string containing command type and keyword
     */
    public static void printFoundTaskByKeyword(String command) {
        try {
            String word = Parser.getTodoAndFindDescription(command);
            ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                    .filter((s) -> s.getDescription().contains(word))
                    .collect(Collectors.toList());
            printNumberOfMatchingTasks(filteredTaskList.size());
            for (int i = 1; i <= filteredTaskList.size(); i = i + 1) {
                System.out.printf(" %d. %s\n", i, filteredTaskList.get(i-1).toString());
            }
            System.out.println(Constants.LINE_DIVIDER);
        } catch (DukeException e) {
            ErrorMessage.printFindSyntaxCommandMessage(command);
        }
    }

    /**
     * Prints all tasks found on a specific date
     *
     * @param command string containing command type and date
     */
    public static void printTasksOnThisDate(String command) {
        try {
            String[] words = command.split(" ", 2);
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].trim();
            }
            LocalDate date = LocalDate.parse(words[1], DateTimeFormatter.ofPattern(Constants.INPUT_DATE_FORMAT));
            ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                    .filter((s) -> ((s instanceof Deadline) && (((Deadline) s).getDate().compareTo(date) == 0)) ||
                            ((s instanceof Event) && (((Event) s).getDate().compareTo(date) == 0)))
                    .collect(Collectors.toList());
            printNumberOfMatchingTasks(filteredTaskList.size());
            for (int i = 1; i <= filteredTaskList.size(); i = i + 1) {
                System.out.printf(" %d. %s\n", i, filteredTaskList.get(i-1).toString());
            }
            System.out.println(Constants.LINE_DIVIDER);
        } catch (IndexOutOfBoundsException e) {
            ErrorMessage.printDateNotProvidedErrorMessage();
        } catch (DateTimeParseException e) {
           ErrorMessage.printDateFormatErrorMessage();
        }
    }

    private static void printNumberOfMatchingTasks(int size) {
        System.out.println(Constants.LINE_DIVIDER);
        if (size == 0) {
            System.out.println(Constants.NO_TASK_FOUND);
        } else if (size == 1) {
            System.out.println(Constants.ONE_TASK_FOUND);
        } else {
            System.out.printf("!bot: There are %d matching tasks in your list\n", size);
        }
    }
}
