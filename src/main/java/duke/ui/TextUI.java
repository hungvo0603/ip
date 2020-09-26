package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

public class TextUI {

    public static void printWelcomeMessage() {
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Hello! I'm !bot\nWhat are your orders?");
        System.out.println("-----------------------------------------");
    }

    public static void printGoodbyeMessage() {
        System.out.println("-----------------------------------------");
        System.out.print("!bot: ");
        System.out.println("~~Dead is like a wind, always by my side~~");
        System.out.println("-----------------------------------------");
    }

    public static void printSetTaskDoneMessage (Task task) {
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Nice! I've mark this task as done:");
        System.out.printf(" %s\n", task.toString());
        System.out.println("-----------------------------------------");
    }

    public static void printAddMessage (Task task) {
        int taskCount = TaskList.getNumberOfTasks();

        System.out.println("-----------------------------------------");
        System.out.println("!bot:\nAdded: " + task.toString());
        if (taskCount <= 1) {
            System.out.println("You now have " + taskCount + " task in the list.");
        } else {
            System.out.println("You now have " + taskCount + " tasks in the list.");
        }
        System.out.println("-----------------------------------------");
    }

    public static void printDeleteMessage(Task t) {
        int taskCount = TaskList.getNumberOfTasks();
        System.out.println("-----------------------------------------");
        System.out.println("!bot:");
        System.out.println("Noted. I've removed this tasks");
        System.out.println("  " + t.toString());
        if (taskCount <= 1) {
            System.out.println("Now you have " + taskCount + " task in the list");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list");
        }
        System.out.println("-----------------------------------------");
    }

}
