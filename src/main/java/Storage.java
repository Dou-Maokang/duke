import java.io.*;
import java.text.SimpleDateFormat;

public class Storage {

    private static File dukeText = new File("dukeTaskList.txt");
    private static InputStream is;
    private static SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hhmm");
    private static SimpleDateFormat rt = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");

    /***<p>
     * read the data stored in hard disk to taskList</p>
     * @param taskList the array list stores all tasks
     */
    public static void readData(TaskList taskList) {
        try {
            is = new FileInputStream(dukeText);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            while (line != null) {

                String[] words = line.split(";");

                if (words[0].equals("todo")) {
                    taskList.addTask(new ToDos(words[2]));
                    if (words[1].equals("1"))
                        taskList.finishTask(taskList.countTask() - 1);
                }
                else if (words[0].equals("deadline")) {
                    taskList.addTask(new Deadline(words[2], rt.parse(words[3])));
                    if (words[1].equals("1"))
                        taskList.finishTask(taskList.countTask() - 1);
                }
                else if (words[0].equals("event")) {
                    taskList.addTask(new Event(words[2], rt.parse(words[3])));
                    if (words[1].equals("1"))
                        taskList.finishTask(taskList.countTask() - 1);
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
    public static void saveData(TaskList taskList) {
        String output = "";
        int isDone;
        int indexOfDescriptionFront;
        int indexOfDescriptionBack;


        for (int i = 0; i < taskList.countTask();i++) {
            String taskStr = taskList.getTask(i).toString();



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
