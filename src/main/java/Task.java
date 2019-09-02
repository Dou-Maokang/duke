public class Task {
    private final boolean done;
    private final String taskContent;

    public Task(String taskContent) {
        this.taskContent = taskContent;
        this.done = false;
    }

    public Task(String taskContent, boolean done) {
        this.taskContent = taskContent;
        this.done = done;
    }

    public String getTask() {
        return this.taskContent;
    }

    public boolean getStatus() {
        return this.done;
    }

    public Task markAsDone() {
        return new Task(this.taskContent, true);
    }

    public static void main(String[] args) {}
}
