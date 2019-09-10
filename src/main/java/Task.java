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

    @Override
    public String toString() {
        if (this.getStatus())
            return "[v]" + this.description;
        else
            return "[x]" + this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public Task markAsDone() {
        return new Task(this.description, true);
    }

    public static void main(String[] args) {}
}
