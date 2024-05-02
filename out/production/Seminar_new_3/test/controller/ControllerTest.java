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
    void setUp() {
        controller = new Controller();
        controller.startSale();
        itemToTest = new ItemDTO(10, 1, 0.25, 1);
        itemList = new ArrayList<>();
        itemList.add(itemToTest);

        // we create a saleDTO with an item which we can run tests
        SaleDTO sale = controller.getSaleDTO();
        sale.setTime(LocalTime.MIN);
        sale.setItemList(itemList);
        sale.setTotalPrice(10);
        sale.setTotalVAT(0.25);
    }



    @AfterEach
    void tearDown() {
    controller.getSaleDTO().setItemList(null);
    }

    @Test
    public void testSearchForItem() {

    int expectedItemID = 1;
    int result1 = controller.searchForItem(1,1).getID();
        assertEquals(expectedItemID,result1,"The ID of the item that is found is not the one that we where searching for");
    }

    @Test
    void searchForItem_ReturnsNonNullItemDTO() {
        ItemDTO item = controller.searchForItem(1, 1); // Example ID and quantity

        assertNotNull(item);
    }

    @Test
    public void testPay() { // we want to test that the returned receipt gives us the correct change;
        ReceiptDTO receipt = controller.pay(70);
        int expectedChange ;
        expectedChange = 60;
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

        SaleDTO saleDTO = controller.endSale();

        assertNotNull(saleDTO); // Ensure that the returned SaleDTO is valid

        assertEquals(LocalTime.MIN, saleDTO.getSaleTime(), "Incorrect sale time");
        assertEquals(itemList, saleDTO.getItemList(), "Incorrect item list");
        assertEquals(10, saleDTO.getTotalPrice(), "Incorrect total price");
        assertEquals(0.25, saleDTO.getTotalVAT(), "Incorrect total VAT");
    }

    @Test
    void endSale_ReturnsNonNullSaleDTO() {
        SaleDTO saleDTO = controller.endSale();

        assertNotNull(saleDTO);
    }


}