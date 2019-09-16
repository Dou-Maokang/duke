import java.util.*;
import java.text.*;

public class Duke {
    private static TaskList taskList= new TaskList();
    private static SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hhmm");
    private static SimpleDateFormat rt = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");


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


        Storage.readData(taskList);

        while (!userInput.equals("bye")) {
            String[] words = userInput.split(" ", 2);

            if (userInput.equals("list")) {
                taskList.getList();

            } else if (words[0].equals("done")) {
                int num = Integer.parseInt(words[1]) - 1;
                if (taskList.finishTask(num) == -1) {
                    userInput = input.nextLine();
                    continue;
                }
                else {
                    System.out.printf("Nice! I've marked this task as done:\n" +
                            "%s\n", taskList.getTask(num));
                }

            } else if (words[0].equals("delete")) {
                int num = Integer.parseInt(words[1]) - 1;
                if (taskList.deleteTask(num) == -1) {
                    userInput = input.nextLine();
                    continue;
                }


            } else if (words[0].equals("find")) {
                taskList.findTask(words[1]);

            } else {

                if (words[0].equals("todo")) {
                    taskList.addTask(new ToDos(words[1]));
                } else if (words[0].equals("deadline")) {
                    String[] holder = words[1].split(" /by", 2);
                    if (holder.length < 2) {
                        System.out.println(DukeException.invalidInput());
                        System.out.println("----------------------");
                        userInput = input.nextLine();
                        continue;
                    }
                    try {
                        taskList.addTask(new Deadline(holder[0], ft.parse(holder[1])));
                    } catch (ParseException e) {
                        System.out.println("OOPS!!! Please enter time in right format: dd/MM/yyyy hhmm");
                        userInput = input.nextLine();
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The index of task is out of range.");
                        userInput = input.nextLine();
                        continue;
                    }
                } else if (words[0].equals("event")) {
                    String[] holder = words[1].split(" /at", 2);
                    if (holder.length < 2) {
                        System.out.println(DukeException.invalidInput());
                        System.out.println("----------------------");
                        userInput = input.nextLine();
                        continue;
                    }
                    try {
                        taskList.addTask(new Event(holder[0], ft.parse(holder[1])));
                    } catch (ParseException e) {
                        System.out.println("OOPS!!! Please enter time in right format: dd/MM/yyyy hhmm");
                        userInput = input.nextLine();
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The index of task is out of range.");
                        userInput = input.nextLine();
                        continue;
                    }
                } else {
                    System.out.println(DukeException.unknownInput());
                    System.out.println("----------------------");
                    userInput = input.nextLine();
                    continue;
                }
                System.out.println("Got it. I've added this task:\n" + taskList.getTask(taskList.countTask() - 1));
                System.out.printf("Now you have %d %s in the list.\n", taskList.countTask(),
                        ((taskList.countTask() > 1) ? "tasks" : "task"));
            }
            System.out.println("----------------------");
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------");
        Storage.saveData(taskList);
    }


}
