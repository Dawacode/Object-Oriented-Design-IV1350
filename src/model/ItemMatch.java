package model;

import interfaces.Match;

import java.util.List;

 public class ItemMatch implements Match {
     //   FindItemList(){}

    @Override
    public ItemDTO matcher(ItemDTO ID, List<ItemDTO> itemList) {
        for (ItemDTO item : itemList) {
            if (item.getID() == ID.getID()) {
                return item;
            }
        }
        return null;
    }
}
