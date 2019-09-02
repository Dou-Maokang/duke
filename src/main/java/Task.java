public class Task {
    protected final boolean isDone;
    protected final String description;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getTask() {
        if (this.getStatus())
            return "[✓]" + this.description;
        else
            return "[✗]" + this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public Task markAsDone() {
        return new Task(this.description, true);
    }

    public static void main(String[] args) {}
}
