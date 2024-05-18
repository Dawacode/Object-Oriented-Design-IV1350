package interfaces;

import exceptions.DataBaseException;
import exceptions.ItemException;
import model.ItemDTO;

import java.util.List;

public interface Match {

     ItemDTO matcher(ItemDTO item, List<ItemDTO> itemList ) throws DataBaseException, ItemException;

}
