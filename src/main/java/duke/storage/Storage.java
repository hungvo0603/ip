package duke.storage;

import duke.Duke;
import duke.exception.ErrorMessage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static void readDataFromFile (Scanner s) throws IndexOutOfBoundsException {
        while (s.hasNext()) {
            String[] readings = s.nextLine().split("\\|");

            for (int i = 0; i < readings.length; i++) {
                readings[i] = readings[i].trim();
            }

            switch(readings[0]) {
            case "T":
                TaskList.writeTodoTaskToList(readings[2]);
                break;
            case "D":
                TaskList.writeDeadlineTaskToList(readings[2], readings[3]);
                break;
            case "E":
                TaskList.writeEventTaskToList(readings[2], readings[3]);
                break;
            default:
                throw new IndexOutOfBoundsException();
            }

            if (readings[1].equals("true")) {
                TaskList.tasks.get(TaskList.getNumberOfTasks() - 1).setDone();
            }
        }
    }

    public static void loadTasks() {
        File directory = new File("data");
        File f = new File(Duke.FILE_PATH);

        if (directory.mkdir()) {
            System.out.println("A directory has just been created: data");
        } else {
            System.out.println("saving directory: " + Duke.FILE_PATH);
        }

        try {
            Scanner s = new Scanner(f);
            try {
                readDataFromFile(s);
            } catch (IndexOutOfBoundsException e) {
                ErrorMessage.printOutOfBoundsErrorMessage();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Initialize saving file...");
        }
    }

    public static void saveTasks() {
        String textToAdd = "";

        for (Task t : TaskList.tasks) {
            if (t instanceof Todo) {
                textToAdd = textToAdd.concat("T | " + t.isDone() + " | " + t.getDescription()
                        + System.lineSeparator());
            } else if (t instanceof Deadline) {
                textToAdd = textToAdd.concat("D | " + t.isDone() + " | " + t.getDescription()
                        + " | " + ((Deadline) t).getDeadlineTime() + System.lineSeparator());
            } else if (t instanceof Event) {
                textToAdd = textToAdd.concat("E | " + t.isDone() + " | " + t.getDescription()
                        + " | " + ((Event) t).getEventTime() + System.lineSeparator());
            } else {
                System.out.println("ERROR");
            }
        }
        try {
            writeToFile(textToAdd);
        } catch (IOException e) {
            System.out.println("CANNOT WRITE TO FILE");
        }
    }

    public static void writeToFile (String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(Duke.FILE_PATH);
        fw.write(textToWrite);
        fw.close();
    }

}
