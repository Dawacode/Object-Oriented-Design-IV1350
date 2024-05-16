package exception;

import exceptions.DataBaseException;
import exceptions.ItemException;
import integration.ExternalInventorySystem;
import model.ItemDTO;
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
        List<ItemDTO> fakeInventory = new ArrayList<>();
        inventorySystem = new ExternalInventorySystem(fakeInventory);
    }

    @Test
    void testFetchItemDatabaseException() {
        assertThrows(DataBaseException.class, () -> {
            inventorySystem.fetchItem(1, 0);
        });
    }

    @Test
    void testFetchItemItemException() {
        assertThrows(ItemException.class, () -> {
            inventorySystem.fetchItem(1, 999); // assuming 999 is an ID that does not exist
        });
    }

    @Test
    void testFetchItemSuccess() throws ItemException, DataBaseException {
        ItemDTO item = inventorySystem.fetchItem(10, 1);
        assertEquals(1, item.getID());
        assertEquals(10, item.getQuantity());
    }
}
