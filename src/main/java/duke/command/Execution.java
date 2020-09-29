package duke.command;

import duke.task.TaskList;

public class Execution {

    /**
     * Continuously get commands from the user and execute them
     */
    public static void executeCommands () {
        String command;
        command = Command.getCommand();

        while (Command.commandType != Command.BYE) {
            switch (Command.commandType) {
            case LIST:
                TaskList.printTaskList();
                break;
            case DONE:
                TaskList.setTaskAsDone(command);
                break;
            case TODO:
                TaskList.addTodoTaskFromInput(command);
                break;
            case DEADLINE:
                TaskList.addDeadlineTaskFromInput(command);
                break;
            case EVENT:
                TaskList.addEventTaskFromInput(command);
                break;
            case FIND:
                TaskList.printFoundTask(command);
                break;
            case ON:
                TaskList.printTasksOnThisDate(command);
                break;
            case DELETE:
                TaskList.deleteTask(command);
                break;
            default:
                break;
            }
            command = Command.getCommand();
        }
    }

}
