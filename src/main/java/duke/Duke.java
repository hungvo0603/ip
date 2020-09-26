package duke;

import duke.command.Execution;
import duke.storage.Storage;
import duke.ui.TextUI;

public class Duke {
    
    public static void main (String[] args) {
        Storage.loadTasks();
        TextUI.printWelcomeMessage();
        Execution.executeCommands();
        TextUI.printGoodbyeMessage();
        Storage.saveTasks();
    }

}
