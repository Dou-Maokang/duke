public class Deadline extends Task{

    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.getStatus())
            return ("[D]" + "[✓]" + super.description + " (by: " + deadline + ")");
        else
            return ("[D]" + "[✗]" + super.description + " (by: " + deadline + ")");
    }

    public static void main(String[] args) {}
}
