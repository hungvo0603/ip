package duke.constants;

public class Constants {
    public static final String DATE_FORMAT = "MMM dd yyyy";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String TICK_ICON = "[\u2713]";
    public static final String CROSS_ICON = "[\u2718]";
    public static final String LINE_DIVIDER = "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|";
    public static final String BOT_INVALID_COMMAND = "!bot: invalid command: ";
    public static final String SAMPLE_DEADLINE_COMMAND = "sample: deadline deadlineDescription /by YYYY-MM-DD HHMM";
    public static final String SAMPLE_EVENT_COMMAND = "sample: event eventDescription /at YYYY-MM-DD HHMM";
    public static final String SAMPLE_TODO_COMMAND = "sample: todo todoDescription";
    public static final String SAMPLE_FIND_COMMAND = "sample: find grass";
    public static final String COMMAND_LIST = "commands: todo, event, deadline, done, list, find, delete, bye";
    public static final String NULL_COMMAND_ERROR = "!bot: command cannot be empty! Please try another command!";
    public static final String INVALID_TASK_NUMBER_ERROR = "Task number is not valid";
    public static final String TASK_NUMBER_FORMAT_ERROR = "Task number should be an integer";
    public static final String GREETING_MESSAGE = "!bot:\nHello! I'm !bot\nWhat are your orders?";
    public static final String GOODBYE_MESSAGE = "!bot: ~~Dead is like a wind, always by my side~~";
    public static final String MARK_AS_DONE_MESSAGE = "!bot: Nice! I've mark this task as done:";
    public static final String REMOVE_TASK_MESSAGE = "!bot: Noted. I've removed this tasks";
    public static final String BOT_INVALID_INPUT = "!bot: invalid input";
    public static final String CANNOT_WRITE_TO_FILE = "!bot: CANNOT WRITE TO FILE";
    public static final String ERROR = "!bot: ERROR";
    public static final String CREATE_DIRECTORY_MESSAGE = "!bot: A directory has just been created: data";
    public static final String CREATE_FILE_MESSAGE = "!bot: initialize saving file...";
    public static final String PARSE_DATETIME_ERROR_MESSAGE = "!bot: unable to parse date or time";
    public static final String DATETIME_FORMAT_ERROR_MESSAGE = "!bot: date/time format is invalid";
    public static final String NO_TASK_MESSAGE = "There is no task in the list for now.";
    public static final String NO_TASK_FOUND = "!bot: No matching task found in your list";
    public static final String ONE_TASK_FOUND = "!bot: There is one matching task in your list";
}
