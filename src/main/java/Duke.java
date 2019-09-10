import java.util.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;

public class Duke {
    private static File dukeText = new File("dukeTaskList.txt");
    private static InputStream is;
    private static OutputStream os;
    private static ArrayList<Task> taskList = new ArrayList<Task>();
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


        readData(taskList);

        while (!userInput.equals("bye")) {
            String[] words = userInput.split(" ", 2);

            if (userInput.equals("list")) {
                for (int i = 0; i < taskList.size(); i++)
                    System.out.printf("%d. %s\n", i + 1, taskList.get(i));
            } else if (words[0].equals("done")) {
                int num = Integer.parseInt(words[1]) - 1;
                try {
                    taskList.set(num, taskList.get(num).markAsDone());
                    System.out.printf("Nice! I've marked this task as done:\n" +
                            "%s\n", taskList.get(num));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The index of task is out of range.");
                    userInput = input.nextLine();
                    continue;
                }
            } else if (words[0].equals("delete")) {
                int num = Integer.parseInt(words[1]) - 1;
                String taskStr = taskList.get(num).toString();
                try {
                    taskList.remove(num);
                    System.out.printf("Noted. I've removed this task:\n" +
                            "%s\n", taskStr);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The index of task is out of range.");
                    userInput = input.nextLine();
                    continue;
                }
            } else if (words[0].equals("find")) {
                String taskStr = null;
                int num = 1;
                for (int i = 0; i < taskList.size(); i++) {
                    taskStr = taskList.get(i).toString();
                    if (taskStr.contains(words[1])) {
                        System.out.printf("%d. %s\n", num++, taskList.get(i));
                    }
                }
            } else {

                if (words[0].equals("todo")) {
                    taskList.add(new ToDos(words[1]));
                } else if (words[0].equals("deadline")) {
                    String[] holder = words[1].split(" /by", 2);
                    if (holder.length < 2) {
                        System.out.println(DukeException.invalidInput());
                        System.out.println("----------------------");
                        userInput = input.nextLine();
                        continue;
                    }
                    try {
                        taskList.add(new Deadline(holder[0], ft.parse(holder[1])));
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
                        taskList.add(new Event(holder[0], ft.parse(holder[1])));
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

                // userTask[taskNum] = new Task(userInput);

                System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1));
                System.out.printf("Now you have %d %s in the list.\n", taskList.size(),
                        ((taskList.size() > 1) ? "tasks" : "task"));


            }
            System.out.println("----------------------");
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------");
        saveData(taskList);
    }

    /***<p>
     * read the data stored in hard disk to taskList</p>
     * @param taskList the array list stores all tasks
     */
    public static void readData(ArrayList<Task> taskList) {
        try {
            is = new FileInputStream(dukeText);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            while (line != null) {

                String[] words = line.split(";");

                if (words[0].equals("todo")) {
                    taskList.add(new ToDos(words[2]));
                    if (words[1].equals("1"))
                        taskList.set(taskList.size() - 1, taskList.get(taskList.size() - 1).markAsDone());
                }
                else if (words[0].equals("deadline")) {
                    taskList.add(new Deadline(words[2], rt.parse(words[3])));
                    if (words[1].equals("1"))
                        taskList.set(taskList.size() - 1, taskList.get(taskList.size() - 1).markAsDone());
                }
                else if (words[0].equals("event")) {
                    taskList.add(new Event(words[2], rt.parse(words[3])));
                    if (words[1].equals("1"))
                        taskList.set(taskList.size() - 1, taskList.get(taskList.size() - 1).markAsDone());
                }
                line = br.readLine();
            }
            br.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***<p>
     * save the tasks data to the hard disk</p>
     * @param taskList the array list of tasks to be saved
     */
    public static void saveData(ArrayList<Task> taskList) {
        String output = "";
        int isDone;
        int indexOfDescriptionFront;
        int indexOfDescriptionBack;


        for (int i = 0; i < taskList.size();i++) {
            String taskStr = taskList.get(i).toString();



            if (taskStr.contains(("[v]")))
                isDone = 1;
            else
                isDone = 0;

            if (taskStr.contains("[T]")) {
                output += "todo;" + isDone + ";" + taskStr.substring(taskStr.lastIndexOf("]") + 1) + "\n";
            } else if (taskStr.contains("[D]")) {

                indexOfDescriptionFront = taskStr.lastIndexOf("]") + 1;
                indexOfDescriptionBack = taskStr.indexOf("(by");


                output += "deadline;" + isDone + ";" +
                        taskStr.substring(indexOfDescriptionFront, indexOfDescriptionBack - 1) +
                        ";" + taskStr.substring(indexOfDescriptionBack + 4, taskStr.length() - 1) + "\n";
            } else if (taskStr.contains("[E]")) {
                indexOfDescriptionFront = taskStr.lastIndexOf("]") + 1;
                indexOfDescriptionBack = taskStr.indexOf("(at");
                output += "event;" + isDone + ";" +
                        taskStr.substring(indexOfDescriptionFront, indexOfDescriptionBack - 1) +
                        ";" + taskStr.substring(indexOfDescriptionBack + 4, taskStr.length() - 1) + "\n";
            }

        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dukeText));
            bw.write(output);
            bw.close();
        } catch (IOException e) { }
    }
}
