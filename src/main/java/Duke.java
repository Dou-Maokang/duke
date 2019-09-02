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

        Task[] userTask = new Task[100];
        int taskNum = 0;
        while (!userInput.equals("bye")) {
            String[] words = userInput.split(" ", 2);

            if (userInput.equals("list")) {
                for (int i = 0; i < taskNum; i++)
                    System.out.printf("%d. %s\n", i + 1, userTask[i]);
            } else if (words[0].equals("done")) {
                int num = Integer.parseInt(words[1]) - 1;
                userTask[num] = userTask[num].markAsDone();
                System.out.printf("Nice! I've marked this task as done:\n" +
                        "%s\n", userTask[num]);
            } else {

                if (words[0].equals("todo")) {
                    userTask[taskNum] = new ToDos(words[1]);
                } else if (words[0].equals("deadline")) {
                    String[] holder = words[1].split(" /by", 2);
                    userTask[taskNum] = new Deadline(holder[0], holder[1]);
                } else if (words[0].equals("event")) {
                    String[] holder = words[1].split(" /at", 2);
                    userTask[taskNum] = new Event(holder[0], holder[1]);
                } else {
                    userTask[taskNum] = new Task(userInput);
                }

                // userTask[taskNum] = new Task(userInput);
                System.out.println("Got it. I've added this task:\n" + userTask[taskNum]);
                System.out.printf("Now you have %d %s in the list.\n", taskNum + 1,
                        ((taskNum >= 1)? "tasks" : "task"));
                taskNum++;
            }
            System.out.println("----------------------");
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------");
    }
}
