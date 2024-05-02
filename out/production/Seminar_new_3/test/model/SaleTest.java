package model;
import controller.Controller;
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
    void setUp() {
        sale = new Sale();
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
    void testItemExists_ItemAlreadyInSale() {
        // Add an item to the sale list
        ItemDTO existingItem = new ItemDTO(10, 1, 0.2, 1);
        sale.getSaleDTO().getItemList().add(existingItem);

        // Call itemExists method to update the existing item's quantity
        ItemDTO updatedItem = sale.itemExists(1, 2);

        // Check if the quantity is updated correctly
        assertEquals(3, updatedItem.getQuantity(), "Quantity should be updated");
    }

    @Test
    void testItemExists_ItemNotInSale() {
        // Call itemExists method to add a new item to the sale list
        ItemDTO newItem = sale.itemExists(2, 3);

        // Check if the new item is added to the sale list
        assertTrue(sale.getSaleDTO().getItemList().contains(newItem), "New item should be added to the sale list");
    }

    @Test
    void testReceivePayment() {
        // Create items and add them to the sale
        ItemDTO item1 = new ItemDTO(10, 1, 0.2, 1);
        ItemDTO item2 = new ItemDTO(20, 2, 0.1, 1);
        sale.getSaleDTO().getItemList().add(item1);
        sale.getSaleDTO().getItemList().add(item2);
        sale.getSaleDTO().setTotalPrice(10+20); // this is due to the fact that the items in the list


        // Call receivePayment method
        ReceiptDTO receipt = sale.receivePayment(50);

        // Check if the receipt is correctly calculated
        assertEquals(50, receipt.getPaid(), "Amount paid should be correct");
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
        sale.getSaleDTO().setTotalPrice(50);
        ReceiptDTO receiptDTO = sale.receivePayment(amountPaid);

        // Check if the change is negative
        int change = receiptDTO.getChange();
        assertTrue(change < 0, "Change should be negative indicating insufficient payment");
    }





}