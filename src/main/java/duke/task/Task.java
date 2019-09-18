package duke.task;


/**
 * Represents a task that stores description and boolean that indicates the task as completed.
 */
public class Task {
    protected final boolean isDone;
    protected final String description;
    protected String type;


    /**
     * Create a task with description and status.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Create a task with description and status.
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Extracting a task content into readable string.
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        if (this.getStatus())
            return "[v]" + this.description;
        else
            return "[x]" + this.description;
    }

    /**
     * Get the status of the task.
     * @return The status of the task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Change the status of the task to done.
     * @return The new task with isDone = True.
     */
    public Task markAsDone() {
        return new Task(this.description, true);
    }

    public static void main(String[] args) {}
}
