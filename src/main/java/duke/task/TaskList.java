package duke.task;

import java.util.ArrayList;

/**
 * Represents a task list that stores a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates an empty task list using an array list.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Creates an updated task list with the specified array list.
     *
     * @param taskList The updated array list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     * Get the whole list of tasks.
     */
    public void getList() {
        if (taskList.size() == 0) {
            System.out.println("The current list is empty!");
        } else {
            for (int i = 0; i < taskList.size(); i++)
                System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

    /**
     * Get the number of tasks in the list.
     * @return The number of tasks.
     */
    public int countTask() {
        return taskList.size();
    }

    /**
     * Get a specific task in the list.
     * @param num The number of the target task.
     * @return The target task.
     */
    public Task getTask(int num) {
        return taskList.get(num);
    }

    /**
     * Add a new task into the task list.
     * @param newTask The new task.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Mark a task as done.
     * @param num The number of the target task in the list.
     * @return The new task marked as done.
     */
    public int finishTask(int num) {
        try {
            if (taskList.get(num).isDone) {
                System.out.println("This task is already finished!");
                return -1;
            }
            else {
                taskList.set(num, taskList.get(num).markAsDone());
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of task is out of range.");
            return -1;
        }
        return 1;
    }

    /**
     * Delete a task in the list.
     * @param num The number of the target task in the list.
     * @return An integer indicating whether the deleting is successful.
     */
    public int deleteTask(int num) {
        try {
            String taskStr = taskList.get(num).toString();
            taskList.remove(num);
            System.out.printf("Noted. I've removed this task:\n" +
                    "%s\n", taskStr);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of task is out of range.");
            return -1;
        }
        return 1;
    }

    /**
     * Find a specific task in the list based on the keyword given.
     * @param keyword The user given keyword.
     */
    public void findTask(String keyword) {
        String taskStr;
        int num = 1;
        for (int i = 0; i < taskList.size(); i++) {
            taskStr = taskList.get(i).toString();
            if (taskStr.contains(keyword)) {
                System.out.printf("%d. %s\n", num++, taskList.get(i));
            }
        }
    }


}
