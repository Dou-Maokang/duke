package duke.task;


/**
 * Represents a to-do that stores description.
 */
public class ToDos extends Task {

    /**
     * Create a task with description and status.
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Create a task with description and status.
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Extracting a task content into readable string.
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        if (this.getStatus())
            return "[T]" + "[v]" + super.description;
        else
            return "[T]" + "[x]" + super.description;
    }

    /**
     * Change the status of the task to done.
     * @return The new task with isDone = True.
     */
    @Override
    public ToDos markAsDone() {
        return new ToDos(this.description, true);
    }

    public static void main(String[] args) {}
}
