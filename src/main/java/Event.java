import java.util.Date;

public class Event extends Task {

    private Date time;

    public Event(String description, Date time) {
        super(description);
        this.time = time;
    }

    public Event(String description, Date time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        if (this.getStatus())
            return "[E]" + "[v]" + super.description + " (at:" + time + ")" ;
        else
            return "[E]" + "[x]" + super.description + " (at:" + time + ")" ;
    }

    @Override
    public Event markAsDone() {
        return new Event(this.description, this.time,true);
    }

    public static void main(String[] args) {}
}
