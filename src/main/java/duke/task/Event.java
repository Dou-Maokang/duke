package duke.task;

import java.util.Date;


/**
 * Represents an event that stores description and date/time.
 */
public class Event extends Task {

    private Date time;

    /**
     * Create an event at a specific date and time.
     * @param description The description of the task.
     * @param time The specific date and time of the task.
     */
    public Event(String description, Date time) {
        super(description);
        this.time = time;
    }

    /**
     * Create a finished event at a specific date and time.
     * @param description The description of the task.
     * @param time The specific date and time of the task.
     * @param isDone The status of the task.
     */
    public Event(String description, Date time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Extracting a task content into readable string.
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        if (this.getStatus())
            return "[E]" + "[v]" + super.description + " (at:" + time + ")" ;
        else
            return "[E]" + "[x]" + super.description + " (at:" + time + ")" ;
    }

    /**
     * Change the status of the task to done.
     * @return The new task with isDone = True.
     */
    @Override
    public Event markAsDone() {
        return new Event(this.description, this.time,true);
    }

    public static void main(String[] args) {}
}
