package duke.dukeexception;

/**
 * Represents an error occurred when duke is performing an action.
 */
public class DukeException {

    /**
     * Creates an exception with the specified message.
     */
    public static String unknownInput() {
        return "I am sorry, but I do not understand what this means.";
    }

    /**
     * Creates an exception with the specified message.
     */
    public static String invalidInput() {
        return "I am sorry, but your input is invalid.";
    }

}
