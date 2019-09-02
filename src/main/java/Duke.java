import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        String[] userTask = new String[100];
        int taskNum = 0;
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < taskNum; i++) {
                    System.out.printf("%d. %s\n", i + 1, userTask[i]);
                }
            }
            else {
                userTask[taskNum] = userInput;
                taskNum++;
                System.out.println("added: " + userInput);
            }
            System.out.println("----------------------");
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------");

    }
}
