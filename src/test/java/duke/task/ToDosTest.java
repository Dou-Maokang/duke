package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDosTest {
    @Test
    void todosTest() {
        Task task = new ToDos("Testing Duke todo");
        assertEquals("[T][✗]Testing Duke todo", task.toString());
    }
}
