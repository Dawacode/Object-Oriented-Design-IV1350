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
    private static final ExternalInventorySystem INSTANCE = new ExternalInventorySystem(new ArrayList<>());

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

    public List<ItemDTO> getFakeExternalInventorySystem() {
        return fakeExternalInventorySystem;
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



}
