package interfaces;

import model.ItemDTO;

import java.util.List;

public interface Match {

     ItemDTO matcher(int item, List<ItemDTO> itemList );

}
