package duke.command;

import duke.tasklist.TaskList;

public class Execution {

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
