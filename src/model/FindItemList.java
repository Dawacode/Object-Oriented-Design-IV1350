package model;

import interfaces.Match;

import java.util.List;

 public class FindItemList implements Match {
     //   FindItemList(){}

    @Override
    public ItemDTO matcher(int ID, List<ItemDTO> itemList) {
        for (ItemDTO item : itemList) {
            if (item.getID() == ID) {
                return item;
            }
        }
        return null;
    }
}
