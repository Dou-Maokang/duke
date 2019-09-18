package duke.parser;

import duke.dukeexception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

/**
 * Represents a parser that breaks down user input into commands.
 */
public class Parser {

    private Scanner input = new Scanner(System.in);
    private String userInput;
    private static SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hhmm");
    private static SimpleDateFormat rt = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");

    /**
     * Get the user input.
     */
    public Parser() {
        userInput = input.nextLine();
    }

    /**
     * Get the following line of user input.
     * @return the user input String.
     */
    public String getNewLine() {
        return userInput = input.nextLine();
    }

    /**
     * Get user command.
     * @return the command type.
     */
    public String getCommand() {
        return this.userInput.contains(" ") ? this.userInput.split(" ", 2)[0] : this.userInput;
    }


    /**
     * Get the task number in the task list.
     * @return the number of the task.
     */
    public int getTaskNum() {
        int num;
        try {
            num = Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println(DukeException.invalidInput());
            return -1;
        };
        return num;
    }

    /**
     * Get the researched keyword.
     * @return the keyword.
     */
    public String getSearchKeyword() {
        return userInput.split(" ", 2)[1];
    }

    /**
     * Get the description for ToDos.
     * @return return the task description.
     */
    public String getTodoTask() {
        return userInput.split(" ", 2)[1];
    }

    /**
     * Get the description for Deadline.
     * @return return the task description.
     */
    public String getDeadlineDescription() {
        String subStr = userInput.split(" ", 2)[1];
        return subStr.split("/by")[0];
    }

    /**
     * Get the date and time for Deadline.
     * @return return the task date and time.
     */
    public Date getDeadlineTime() {
        String subStr = userInput.split(" ", 2)[1];
        Date time = null;
        try {
             time = ft.parse(subStr.split("/by")[1]);
        } catch(ParseException e) {
            System.out.println("OOPS!!! Please enter time in right format: dd/MM/yyyy hhmm");
        }
        return time;
    }

    /**
     * Get the description for Event.
     * @return return the task description.
     */
    public String getEventDescription() {
        String subStr = userInput.split(" ", 2)[1];
        return subStr.split("/at")[0];
    }

    /**
     * Get the date and time for Event.
     * @return return the task date and time.
     */
    public Date getEventTime() {
        String subStr = userInput.split(" ", 2)[1];
        Date time = null;
        try {
            time = ft.parse(subStr.split("/at")[1]);
        } catch(ParseException e) {
            System.out.println("OOPS!!! Please enter time in right format: dd/MM/yyyy hhmm");
        }
        return time;
    }
}
