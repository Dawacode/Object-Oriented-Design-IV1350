package exception;

import exceptions.DataBaseException;
import exceptions.ItemException;
import integration.ExternalInventorySystem;
import model.ItemDTO;
import model.SystemMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    private ExternalInventorySystem inventorySystem;

    @BeforeEach
    void setUp() {
        inventorySystem = ExternalInventorySystem.getInventory();
    }

    @Test
    void testFetchItemDatabaseException() {
        ItemDTO item=new ItemDTO(0,0,0,1);
        assertThrows(DataBaseException.class, () -> {
            new SystemMatch().matcher(item,inventorySystem.getFakeExternalInventorySystem());
        });
    }

    @Test
    void testFetchItemItemException() {
        ItemDTO item=new ItemDTO(0,999,0,1);
        assertThrows(ItemException.class, () -> {
            new SystemMatch().matcher(item,inventorySystem.getFakeExternalInventorySystem()); // assuming 999 is an ID that does not exist
        });
    }

    @Test
    void testFetchItemSuccess() throws ItemException, DataBaseException {
        ItemDTO fetchItem=new ItemDTO(0,1,0,10);
        ItemDTO item = new SystemMatch().matcher(fetchItem,inventorySystem.getFakeExternalInventorySystem());
        assertEquals(1, item.getID());
        assertEquals(10, item.getQuantity());
    }
}
