package duke.task;

import java.util.Date;


/**
 * Represents a deadline that stores description and date/time.
 */
public class Deadline extends Task {

    private Date deadline;

    /**
     * Create a task with deadline in format of dd/mm/yyyy time.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Create a finished task with deadline in format of dd/mm/yyyy time.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @param isDone The status of the task.
     */
    public Deadline(String description, Date deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Extracting a task content into readable string.
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        if (this.getStatus())
            return ("[D]" + "[v]" + super.description + " (by:" + deadline + ")");
        else
            return ("[D]" + "[x]" + super.description + " (by:" + deadline + ")");
    }

    /**
     * Change the status of the task to done.
     * @return The new task with isDone = True.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(this.description, this.deadline,true);
    }

    public static void main(String[] args) {}
}
