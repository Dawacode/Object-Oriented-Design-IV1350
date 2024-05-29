package controller;
import model.ItemDTO;
import model.ReceiptDTO;
import model.SaleDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private List<ItemDTO> itemList;
    private ItemDTO itemToTest;

    @BeforeEach
    void setUp()  {
        controller = new Controller();
        controller.startSale();
        // Use the search method to add items to the SaleDTO
        controller.searchForItem(1, 1); // Add item with ID 1 and quantity 1
        controller.searchForItem(2, 2); // Add item with ID 2 and quantity 2
        controller.searchForItem(3, 1); // Add another item with ID 3 and quantity 1
        // You can add more items as needed
    }

    @Test
    public void testSearchForItem()  {

    int expectedItemID = 1;
    int result1 = controller.searchForItem(1,1).getID();
        assertEquals(expectedItemID,result1,"The ID of the item that is found is not the one that we where searching for");
    }

    @Test
    void searchForItem_ReturnsNonNullItemDTO()  {
        ItemDTO item = controller.searchForItem(1, 1); // Example ID and quantity

        assertNotNull(item);
    }

    @Test
    public void testPay() { // we want to test that the returned receipt gives us the correct change;
        ReceiptDTO receipt = controller.pay(100);
        int expectedChange ;
        expectedChange = 20;
        int actualChange = receipt.getChange();
        assertEquals(expectedChange,actualChange,"receipt gives customer the wrong change ");
    }

    @Test
    public void testPay_notNegative() { // we want to test that the returned receipt gives us the correct change;
        int payment = 100; // Example positive payment amount
        ReceiptDTO receipt = controller.pay(payment);

        assertNotNull(receipt);
        assertEquals(payment, receipt.getPaid());
    }

    @Test
    void testPay_notNull() {
        // Test paying for the sale
        ReceiptDTO receipt = controller.pay(100);
        assertNotNull(receipt); // Ensure receipt is not null
        // You can add more assertions to verify receipt properties if needed
    }

    @Test
    public void testEndSale() {
        // End the sale
        SaleDTO saleDTO = controller.endSale();

        // Ensure that the returned SaleDTO is valid
        assertNotNull(saleDTO);

        // Verify that the sale details match the expected values
        List<ItemDTO> expectedItemList = new ArrayList<>();
        expectedItemList.add(new ItemDTO(10, 1, 0.25, 1));
        expectedItemList.add(new ItemDTO(20, 2, 0.12, 2));
        expectedItemList.add(new ItemDTO(30, 3, 0.06, 1));

        // Compare the sizes of the lists
        assertEquals(expectedItemList.size(), saleDTO.getItemList().size(), "Item lists have different sizes");

        // Compare each item in the lists by iterating through them
        for (int i = 0; i < expectedItemList.size(); i++) {
            ItemDTO expectedItem = expectedItemList.get(i);
            ItemDTO actualItem = saleDTO.getItemList().get(i);
            assertEquals(expectedItem.getID(), actualItem.getID(), "Item IDs do not match at index " + i);
            assertEquals(expectedItem.getQuantity(), actualItem.getQuantity(), "Item quantities do not match at index " + i);
            assertEquals(expectedItem.getPrice(), actualItem.getPrice(), "Item prices do not match at index " + i);
            assertEquals(expectedItem.getVAT(), actualItem.getVAT(), "Item VATs do not match at index " + i);
        }

        // Verify other properties as needed
        assertEquals(80, saleDTO.getTotalPrice(), "Incorrect total price");
        assertEquals(9.1, saleDTO.getTotalVAT(), "Incorrect total VAT");
    }

    @Test
    void endSale_ReturnsNonNullSaleDTO() {
        SaleDTO saleDTO = controller.endSale();

        assertNotNull(saleDTO);
    }


}