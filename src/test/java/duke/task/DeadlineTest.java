package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void deadlineTest() {
        Task task = new Deadline("Testing Duke deadline");
        assertEquals("[D][✗]Testing Duke deadline", task.toString());
    }
}
