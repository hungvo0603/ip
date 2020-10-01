package duke.storage;

import duke.constants.Constants;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {

    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Creates the path when the path is missing
     * Loads the data from file if file is already created
     */
    public static void loadTasks() {
        File directory = new File("data");
        File f = new File(FILE_PATH);

        if (directory.mkdir()) {
            System.out.println(Constants.CREATE_DIRECTORY_MESSAGE);
        } else {
            System.out.println("!bot: saving directory: " + FILE_PATH);
        }

        try {
            Scanner s = new Scanner(f);
            try {
                readDataFromFile(s);
            } catch (IndexOutOfBoundsException e) {
                ErrorMessage.printOutOfBoundsErrorMessage();
            } catch (DukeException e) {
                System.out.println(Constants.PARSE_DATETIME_ERROR);
            }
        } catch (FileNotFoundException e) {
            System.out.println(Constants.CREATE_FILE_MESSAGE);
        }
    }

    /**
     * Takes in the data from a storage file line by line
     * Proceeds data and put them into the list
     *
     * @param s Global scanner
     * @throws IndexOutOfBoundsException On error data in file
     */
    public static void readDataFromFile (Scanner s) throws IndexOutOfBoundsException, DukeException {
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
                try {
                    LocalDate deadlineDate = Parser.getDeadlineAndEventDate(readings[3]);
                    LocalTime deadlineTime = Parser.getDeadlineAndEventTime(readings[3]);
                    TaskList.writeDeadlineTaskToList(readings[2], deadlineDate, deadlineTime);
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    ErrorMessage.printInvalidInputMessage();
                }
                break;
            case "E":
                try {
                    LocalDate eventDate = Parser.getDeadlineAndEventDate(readings[3]);
                    LocalTime eventTime = Parser.getDeadlineAndEventTime(readings[3]);
                    TaskList.writeEventTaskToList(readings[2], eventDate, eventTime);
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    ErrorMessage.printInvalidInputMessage();
                }
                break;
            default:
                throw new IndexOutOfBoundsException();
            }

            if (readings[1].equals("true")) {
                TaskList.tasks.get(TaskList.getNumberOfTasks() - 1).setDone();
            }
        }
    }

    /**
     * Constructs a string to be written in storage file when the user stops the program
     */
    public static void saveTasks() {
        String textToAdd = "";

        for (Task t : TaskList.tasks) {
            if (t instanceof Todo) {
                textToAdd = textToAdd.concat("T | " + t.isDone() + " | " + t.getDescription()
                        + System.lineSeparator());
            } else if (t instanceof Deadline) {
                textToAdd = textToAdd.concat("D | " + t.isDone() + " | " + t.getDescription()
                        + " | " + ((Deadline) t).getDeadlineTime() +  System.lineSeparator());
            } else if (t instanceof Event) {
                textToAdd = textToAdd.concat("E | " + t.isDone() + " | " + t.getDescription()
                        + " | " + ((Event) t).getEventTime() + System.lineSeparator());
            } else {
                System.out.println(Constants.ERROR);
            }
        }

        try {
            writeToFile(textToAdd);
        } catch (IOException e) {
            System.out.println(Constants.WRITE_TO_FILE_ERROR);
        }
    }

    /**
     * Overwrites data in storage file by the string prepared by {@link package.storage#saveTasks()}
     *
     * @param textToWrite String to overwrite the storage file
     * @throws IOException When file cannot be modified
     */
    private static void writeToFile (String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToWrite);
        fw.close();
    }

}
