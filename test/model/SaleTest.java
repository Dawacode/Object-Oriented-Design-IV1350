package model;
import controller.Controller;
import exceptions.DataBaseException;
import exceptions.ItemException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private Controller controller;
    private List<ItemDTO> itemList;
    private ItemDTO itemToTest;
    private Sale sale;

    @BeforeEach
    void setUp() throws DataBaseException, ItemException {
        sale = new Sale();
        // Add items to the sale using the itemExists method
        sale.itemExists(1, 1); // Add item with ID 1 and quantity 1
        sale.itemExists(2, 2); // Add item with ID 2 and quantity 2
        sale.itemExists(3, 1); // Add item with ID 3 and quantity 1
        // You can add more items as needed
    }

    @Test
    void testEndSale() {
        // Call endSale method
        SaleDTO saleDTO = sale.endSale();

        // Check if the saleDTO returned is the same as the one in the sale object
        assertSame(sale.getSaleDTO(), saleDTO, "SaleDTO should be returned");
    }

    @Test
    void endSale_ReturnsSaleDTO() {
        // Call the method
        SaleDTO saleDTO = sale.endSale();

        // Check if the returned SaleDTO is not null
        assertNotNull(saleDTO);
    }


    @Test
    void testItemExists_ItemAlreadyInSale() throws DataBaseException, ItemException {
        // Add an item to the sale list
        ItemDTO existingItem = new ItemDTO(10, 1, 0.2, 1);
        sale.getSaleDTO().getItemList().add(existingItem);

        // Call itemExists method to update the existing item's quantity
        ItemDTO updatedItem = sale.itemExists(1, 2);

        // Check if the quantity is updated correctly
        assertEquals(3, updatedItem.getQuantity(), "Quantity should be updated");
    }

    @Test
    void testItemExists_ItemNotInSale() throws DataBaseException, ItemException {
        // Call itemExists method to add a new item to the sale list
        ItemDTO newItem = sale.itemExists(2, 3);

        // Check if the new item is added to the sale list
        assertTrue(sale.getSaleDTO().getItemList().contains(newItem), "New item should be added to the sale list");
    }

    @Test
    void testReceivePayment() {

        // Call receivePayment method
        ReceiptDTO receipt = sale.receivePayment(100);

        // Check if the receipt is correctly calculated
        assertEquals(100, receipt.getPaid(), "Amount paid should be correct");
        assertEquals(20, receipt.getChange(), "Change should be correct");
    }

    @Test
    void receivePayment_ValidAmount_ReturnsReceiptDTO() {
        // Prepare test data
        int payment = 100;

        // Call the method
        ReceiptDTO receiptDTO = sale.receivePayment(payment);

        // Check if the returned ReceiptDTO is not null
        assertNotNull(receiptDTO);
    }



    @Test
    void receivePayment_InsufficientPayment_ReturnsNegativeChange() {
        // Call receivePayment with an amount less than the total price
        int amountPaid = 40; // Assuming the customer paid 40 but the total price is 50
        ReceiptDTO receiptDTO = sale.receivePayment(amountPaid);

        // Check if the change is negative
        int change = receiptDTO.getChange();
        assertTrue(change < 0, "Change should be negative indicating insufficient payment");
    }





}