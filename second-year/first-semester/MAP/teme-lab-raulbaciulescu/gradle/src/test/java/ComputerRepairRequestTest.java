import model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerRepairRequestTest {
    @Test
    @DisplayName("First test")
    public void testExample() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();
        assertEquals("", computerRepairRequest.getOwnerName());
        assertEquals("", computerRepairRequest.getOwnerAddress());
    }

    @Test
    @DisplayName("Second test")
    public void testExample2() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();
        assertEquals(2,2, "Numerele trebuie sa fie egale!");
    }
      
}
