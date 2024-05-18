package model;

import exceptions.DataBaseException;
import exceptions.ItemException;
import interfaces.Match;

import java.util.List;

/**
 * SystemMatch class is responsible for matching items within a given list based on their ID and quantity.
 * It implements the Match interface and provides the matcher method to find and update items.
 */
public class SystemMatch implements Match {

    /**
     * Matches an item within the given list based on the provided item's ID.
     * If the item is found and the database is running, it updates the item's quantity.
     * Throws exceptions if the database is not running or if the item is not found.
     *
     * @param ID The ItemDTO containing the ID and quantity of the item to match.
     * @param list The list of ItemDTOs to search through.
     * @return The matched and updated ItemDTO if found.
     * @throws DataBaseException if the database is not running.
     * @throws ItemException if the item does not exist in the list.
     */
    @Override
    public ItemDTO matcher(ItemDTO ID, List<ItemDTO> list) throws DataBaseException, ItemException {
        for (ItemDTO item : list) {
            if (dataBaseNotRunning(ID.getID())) {
                throw new DataBaseException("Database is not running");
            }

            if (itemMatchesID(item, ID.getID())) {
                return updateItemQuantity(item, ID.getQuantity());
            }
        }
        throw new ItemException("Item does not exist");
    }

    /**
     * Updates the quantity of the provided item.
     *
     * @param item The ItemDTO to update.
     * @param quantity The new quantity to set for the item.
     * @return A new ItemDTO object with the updated quantity.
     */
    private ItemDTO updateItemQuantity(ItemDTO item, int quantity) {
        return new ItemDTO(item.getPrice(), item.getID(), item.getVAT(), quantity);
    }

    /**
     * Checks if the provided ID indicates that the database is not running.
     *
     * @param ID The ID to check.
     * @return True if the ID indicates the database is not running, otherwise false.
     */
    private boolean dataBaseNotRunning(int ID) {
        return ID == 0;
    }

    /**
     * Checks if the given item matches the specified ID.
     *
     * @param item The ItemDTO to check.
     * @param ID The ID to match against the item's ID.
     * @return True if the item's ID matches the specified ID, otherwise false.
     */
    private boolean itemMatchesID(ItemDTO item, int ID) {
        return item.getID() == ID;
    }
}
