public class ToDos extends Task{

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        if (this.getStatus())
            return "[T]" + "[✓]" + super.description;
        else
            return "[T]" + "[✗]" + super.description;
    }

    @Override
    public ToDos markAsDone() {
        return new ToDos(this.description, true);
    }

    public static void main(String[] args) {}
}
