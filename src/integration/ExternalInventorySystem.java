package integration;

// Import necessary classes and exceptions
import exceptions.DataBaseException;
import exceptions.ItemException;
import model.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an external inventory system for managing items.
 */
public class ExternalInventorySystem {

    // List to simulate the external inventory system's storage of items
    private final List<ItemDTO> fakeExternalInventorySystem;

    // Singleton instance of the ExternalInventorySystem
    private static ExternalInventorySystem INSTANCE = new ExternalInventorySystem(new ArrayList<>());

    /**
     * Constructs a new ExternalInventorySystem object with the provided list of items.
     * Initializes the inventory system by adding items to the store.
     *
     * @param fakeExternalInventorySystem The list to store simulated items.
     */
    private ExternalInventorySystem(List<ItemDTO> fakeExternalInventorySystem) {
        this.fakeExternalInventorySystem = fakeExternalInventorySystem;
        addItemsToStore();
    }

    public static ExternalInventorySystem getInventory(){
        return INSTANCE;
    }



    /**
     * Adds items to the simulated inventory store.
     * Initializes the store with predefined items.
     */
    private void addItemsToStore() {
        fakeExternalInventorySystem.add(new ItemDTO(100, 1, 0.25, 100));
        fakeExternalInventorySystem.add(new ItemDTO(20, 2, 0.12, 100));
        fakeExternalInventorySystem.add(new ItemDTO(30, 3, 0.06, 100));
        fakeExternalInventorySystem.add(new ItemDTO(40, 4, 0.25, 100));
    }

    /**
     * Fetches an item from the inventory system based on its ID and quantity.
     *
     * @param quantity The quantity of the item.
     * @param ID The ID of the item.
     * @return The found item if available, null otherwise.
     * @throws ItemException if the item does not exist.
     * @throws DataBaseException if the database is not running.
     */
    public ItemDTO fetchItem(int quantity, int ID) throws ItemException, DataBaseException {
        for (ItemDTO item : fakeExternalInventorySystem) {
            if (dataBaseNotRunning(ID)) {
                throw new DataBaseException("Database is not running");
            }

            if (itemMatchesID(item, ID)) {
                return updateItemQuantity(item, quantity);
            }
        }
        throw new ItemException("Item does not exist");
    }

    /**
     * Checks if the provided item matches the given ID.
     *
     * @param item The item to check.
     * @param ID The ID to match against.
     * @return True if the item's ID matches the given ID, otherwise false.
     */
    private boolean itemMatchesID(ItemDTO item, int ID) {
        return item.getID() == ID;
    }

    /**
     * Checks if the provided ID indicates that the database is not running.
     *
     * @param ID The ID that illustrates that the database is not running.
     * @return True if the provided ID indicates the database is not running, otherwise false.
     */
    private boolean dataBaseNotRunning(int ID) {
        return ID == 0;
    }

    /**
     * Updates the quantity of the provided item.
     *
     * @param item The item to update.
     * @param quantity The new quantity of the item.
     * @return A new ItemDTO object with the updated quantity.
     */
    private ItemDTO updateItemQuantity(ItemDTO item, int quantity) {
        return new ItemDTO(item.getPrice(), item.getID(), item.getVAT(), quantity);
    }
}
