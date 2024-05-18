package model;

import interfaces.Match;

import java.util.List;

/**
 * ItemMatch class is responsible for finding an item within a given list based on the item's ID.
 * It implements the Match interface and provides the matcher method to find items.
 */
public class ItemMatch implements Match {

    /**
     * Matches an item within the given list based on the provided item's ID.
     * If the item is found in the list, it returns the item; otherwise, it returns null.
     *
     * @param searchItem The ItemDTO containing the ID of the item to match.
     * @param itemList The list of ItemDTOs to search through.
     * @return The matched ItemDTO if found, otherwise null.
     */
    @Override
    public ItemDTO matcher(ItemDTO searchItem, List<ItemDTO> itemList) {
        for (ItemDTO item : itemList) {
            if (item.getID() == searchItem.getID()) {
                return item;
            }
        }
        return null;
    }
}
