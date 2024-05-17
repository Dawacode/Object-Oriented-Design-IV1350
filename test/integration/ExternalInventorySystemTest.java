package integration;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.DataBaseException;
import exceptions.ItemException;
import model.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ExternalInventorySystemTest {
    private ExternalInventorySystem inventorySystem;
    private List<ItemDTO> fakeExternalInventorySystem;

    @BeforeEach
    void setUp() {
        fakeExternalInventorySystem = new ArrayList<>();
        inventorySystem = ExternalInventorySystem.getInventory();
    }

    @Test
    void testFetchItem_ItemExists() throws DataBaseException, ItemException {
        // Add some sample items to the inventory system

        // Attempt to fetch an item that exists in the inventory
        ItemDTO fetchedItem = inventorySystem.fetchItem(20, 2);

        // Assert that the fetched item is not null
        assertNotNull(fetchedItem);
        // Assert that the fetched item's ID matches the expected ID
        assertEquals(2, fetchedItem.getID());
    }

    @Test
    void testFetchItem_ItemDoesNotExist() throws DataBaseException, ItemException {
        // Attempt to fetch an item that does not exist in the inventory
        ItemDTO fetchedItem = inventorySystem.fetchItem(1, 100);

        // Assert that the fetched item is null (indicating it doesn't exist)
        assertNull(fetchedItem);
    }
}
