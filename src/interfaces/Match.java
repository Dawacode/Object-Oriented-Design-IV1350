package interfaces;

import exceptions.DataBaseException;
import exceptions.ItemException;
import model.ItemDTO;

import java.util.List;

/**
 * The Match interface defines a method for matching items within a list based on specified criteria.
 * Implementing classes are expected to provide the logic for finding and possibly updating items.
 */
public interface Match {

     /**
      * Matches an item within the given list based on the provided item's details.
      * Implementing classes should define the criteria for matching items.
      *
      * @param item The ItemDTO containing the details of the item to match.
      * @param itemList The list of ItemDTOs to search through.
      * @return The matched ItemDTO if found, otherwise null or an updated item depending on the implementation.
      * @throws DataBaseException if there is an error accessing the database.
      * @throws ItemException if there is an error related to the item.
      */
     ItemDTO matcher(ItemDTO item, List<ItemDTO> itemList) throws DataBaseException, ItemException;
}
