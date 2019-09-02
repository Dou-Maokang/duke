public class ToDos extends Task{

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.getStatus())
            return "[T]" + "[✓]" + super.description;
        else
            return "[T]" + "[✗]" + super.description;
    }

    public static void main(String[] args) {}
}
