import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void todoTest() {
        assertEquals("[T][✗]Testing Duke todo", new ToDos("Testing Duke todo").toString());
    }
    

}