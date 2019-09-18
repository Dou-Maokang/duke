package duke;

import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDos;
import duke.ui.Ui;

import java.text.*;

public class Duke {
    private static TaskList taskList= new TaskList();
    private static SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hhmm");
    private static SimpleDateFormat rt = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");


    public static void main(String[] args) {
        Ui.printLogo();
        Storage.readData(taskList);

        Parser parser = new Parser();

        while (!parser.getCommand().equals("bye")) {
            switch (parser.getCommand().toLowerCase()) {
                case "list":
                    taskList.getList();
                    break;
                case "done":
                    if (taskList.finishTask(parser.getTaskNum()) == -1) {
                        parser.getNewLine();
                        continue;
                    } else {
                        System.out.printf("Nice! I've marked this task as done:\n" +
                                "%s\n", taskList.getTask(parser.getTaskNum()));
                    }
                    break;
                case "delete":
                    if (taskList.deleteTask(parser.getTaskNum()) == -1) {
                        parser.getNewLine();
                        continue;
                    }
                    break;
                case "find":
                    taskList.findTask(parser.getSearchKeyword());
                    break;
                case "todo":
                    taskList.addTask(new ToDos(parser.getTodoTask()));
                    System.out.println("Got it. I've added this task:\n" + taskList.getTask(taskList.countTask() - 1));
                    System.out.printf("Now you have %d %s in the list.\n", taskList.countTask(),
                            ((taskList.countTask() > 1) ? "tasks" : "duke/task"));

                    break;
                case "deadline":
                    if (parser.getDeadlineDescription() == null || parser.getDeadlineTime() == null) {
                        System.out.println(DukeException.invalidInput());
                        parser.getNewLine();
                        continue;
                    } else {
                        taskList.addTask(new Deadline(parser.getDeadlineDescription(), parser.getDeadlineTime()));
                        System.out.println("Got it. I've added this task:\n" + taskList.getTask(taskList.countTask() - 1));
                        System.out.printf("Now you have %d %s in the list.\n", taskList.countTask(),
                                ((taskList.countTask() > 1) ? "tasks" : "duke/task"));
                    }
                    break;
                case "event":
                    if (parser.getEventDescription() == null || parser.getEventTime() == null) {
                        System.out.println(DukeException.invalidInput());
                        parser.getNewLine();
                        continue;
                    } else {
                        taskList.addTask(new Event(parser.getEventDescription(), parser.getEventTime()));
                        System.out.println("Got it. I've added this task:\n" + taskList.getTask(taskList.countTask() - 1));
                        System.out.printf("Now you have %d %s in the list.\n", taskList.countTask(),
                                ((taskList.countTask() > 1) ? "tasks" : "duke/task"));
                    }

                    break;
                default:
                    System.out.println(DukeException.unknownInput());
                    Ui.drawLine();
                    parser.getNewLine();
                    continue;
            }


            Ui.drawLine();
            parser.getNewLine();
            }
        Ui.sayGoodbye();
        Ui.drawLine();
        Storage.saveData(taskList);
    }


}
