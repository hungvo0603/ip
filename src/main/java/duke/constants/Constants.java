package duke.constants;

public class Constants {

    public static final String LINE_DIVIDER = "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|";

    public static final String INPUT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy";
    public static final String OUTPUT_TIME_FORMAT = "hh:mm a";

    public static final String TICK_ICON = "[/]";
    public static final String CROSS_ICON = "[x]";

    public static final String BOT_INVALID_COMMAND = "!bot: invalid command: ";
    public static final String SAMPLE_DEADLINE_COMMAND = "sample: deadline deadlineDescription /by DD/MM/YYYY HHMM";
    public static final String SAMPLE_EVENT_COMMAND = "sample: event eventDescription /at DD/MM/YYYY HHMM";
    public static final String SAMPLE_TODO_COMMAND = "sample: todo todoDescription";
    public static final String SAMPLE_FIND_COMMAND = "sample: find grass";
    public static final String COMMAND_LIST = "commands: todo, deadline, event, done, list, find, on, delete, bye";

    public static final String GREETING_MESSAGE = "!bot:\nHello! I'm !bot\nWhat are your orders?";
    public static final String GOODBYE_MESSAGE = "!bot: ~~Dead is like a wind, always by my side~~";
    public static final String MARK_AS_DONE_MESSAGE = "!bot: Nice! I've mark this task as done:";
    public static final String REMOVE_TASK_MESSAGE = "!bot: Noted. I've removed this tasks";
    public static final String CREATE_DIRECTORY_MESSAGE = "!bot: A directory has just been created: data";
    public static final String CREATE_FILE_MESSAGE = "!bot: initialize saving file...";
    public static final String NO_TASK_MESSAGE = "There is no task in the list for now.";
    public static final String ALREADY_DONE_MESSAGE = "!bot: this task is already set as done before.";

    public static final String NO_TASK_FOUND = "!bot: No matching task found in your list";
    public static final String ONE_TASK_FOUND = "!bot: There is one matching task in your list";

    public static final String NULL_COMMAND_ERROR = "!bot: command cannot be empty! Please try another command!";
    public static final String ERROR = "!bot: ERROR";
    public static final String INVALID_TASK_NUMBER_ERROR = "Task number is not valid";
    public static final String TASK_NUMBER_FORMAT_ERROR = "Task number should be an integer";
    public static final String NO_DATE_PROVIDED_ERROR = "!bot: date is not provided";
    public static final String DATE_FORMAT_ERROR = "!bot: date format is poorly constructed";;
    public static final String DATETIME_FORMAT_ERROR = "!bot: date/time format is invalid";
    public static final String PARSE_DATETIME_ERROR = "!bot: unable to parse date or time";
    public static final String INVALID_INPUT_ERROR = "!bot: invalid input";
    public static final String WRITE_TO_FILE_ERROR = "!bot: CANNOT WRITE TO FILE";
}
