package model;

import exceptions.DataBaseException;
import exceptions.ItemException;
import interfaces.Match;

import java.util.List;

public class SystemMatch implements Match {

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


    private ItemDTO updateItemQuantity(ItemDTO item, int quantity) {
        return new ItemDTO(item.getPrice(), item.getID(), item.getVAT(), quantity);
    }
    private boolean dataBaseNotRunning(int ID) {
        return ID == 0;
    }


    private boolean itemMatchesID(ItemDTO item, int ID) {
        return item.getID() == ID;
    }




}
