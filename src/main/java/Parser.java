import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Parser {

    private Scanner input = new Scanner(System.in);
    private String userInput;
    private static SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hhmm");
    private static SimpleDateFormat rt = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");

    public Parser() {
        userInput = input.nextLine();
    }

    public String getNewLine() {
        return userInput = input.nextLine();
    }

    public String getCommand() {
        return this.userInput.contains(" ") ? this.userInput.split(" ", 2)[0] : this.userInput;
    }

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

    public String getSearchKeyword() {
        return userInput.split(" ", 2)[1];
    }

    public String getTodoTask() {
        return userInput.split(" ", 2)[1];
    }

    public String getDeadlineDescription() {
        String subStr = userInput.split(" ", 2)[1];
        return subStr.split("/by")[0];
    }

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

    public String getEventDescription() {
        String subStr = userInput.split(" ", 2)[1];
        return subStr.split("/at")[0];
    }

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
