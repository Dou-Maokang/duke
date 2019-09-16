import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void getList() {
        for (int i = 0; i < taskList.size(); i++)
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
    }

    public int countTask() {
        return taskList.size();
    }

    public Task getTask(int num) {
        return taskList.get(num);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public int finishTask(int num) {
        try {
            taskList.set(num, taskList.get(num).markAsDone());
            System.out.printf("Nice! I've marked this task as done:\n" +
                    "%s\n", taskList.get(num));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of task is out of range.");
            return -1;
        }
        return 1;
    }

    public int deleteTask(int num) {
        String taskStr = taskList.get(num).toString();
        try {
            taskList.remove(num);
            System.out.printf("Noted. I've removed this task:\n" +
                    "%s\n", taskStr);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of task is out of range.");
            return -1;
        }
        return 1;
    }

    public void findTask(String keyword) {
        String taskStr = null;
        int num = 1;
        for (int i = 0; i < taskList.size(); i++) {
            taskStr = taskList.get(i).toString();
            if (taskStr.contains(keyword)) {
                System.out.printf("%d. %s\n", num++, taskList.get(i));
            }
        }
    }






}
