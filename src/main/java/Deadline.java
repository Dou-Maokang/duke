import java.util.Date;

public class Deadline extends Task{

    private Date deadline;

    public Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, Date deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.getStatus())
            return ("[D]" + "[✓]" + super.description + " (by:" + deadline + ")");
        else
            return ("[D]" + "[✗]" + super.description + " (by:" + deadline + ")");
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(this.description, this.deadline,true);
    }

    public static void main(String[] args) {}
}
