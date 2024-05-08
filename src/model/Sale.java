/*
 * Sale class represents a sales transaction.
 */
package model;

import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private ReceiptDTO receiptDTO;
    private SaleDTO saleDTO;
    private int currentTotalPrice;
    private double currentVAT;
    private ExternalInventorySystem externalInventorySystem;
    private ExternalAccountingSystem externalAccountingSystem;

    private List<ItemDTO> itemList;
    private List<ItemDTO> externalList;
    private List<ReceiptDTO> accountingList;

    /**
     * Constructor for the Sale class.
     */
    public Sale() {
        currentTotalPrice = 0;
        currentVAT = 0.0;
        itemList = new ArrayList<>();
        externalInventorySystem = new ExternalInventorySystem(externalList = new ArrayList<>());
        externalAccountingSystem = new ExternalAccountingSystem(accountingList = new ArrayList<>());
        saleDTO = new SaleDTO(LocalTime.now(), 0, 0, itemList);

    }

    /**
     * Updates the SaleDTO object with the current total price and VAT.
     */
    private void updateSale() {
        saleDTO = new SaleDTO(saleDTO.getSaleTime(), currentTotalPrice, currentVAT, itemList);
    }

    /**
     * Retrieves the SaleDTO object representing the current sale.
     * @return The SaleDTO object representing the current sale.
     */
    public SaleDTO getSaleDTO() {
        return saleDTO;
    }

    /**
     * Checks if an item with the given ID and quantity exists in the sale.
     * If found, updates the quantity and calculates total price and VAT.
     * If not found, fetches the item from the external inventory system.
     * @param ID The ID of the item to search for.
     * @param quantity The quantity of the item.
     * @return The ItemDTO object representing the found or fetched item.
     */
    public ItemDTO itemExists(int ID, int quantity) {
        ItemDTO item = findItemByID(ID);
        if (item != null) {
            updateExistingItem(item, quantity);
        } else {
            item = fetchItemFromExternalSystem(ID, quantity);
        }
        return item;
    }

    /**
     * Searches for an item in the sale by its ID.
     * @param ID The ID of the item to search for.
     * @return The ItemDTO object if found, otherwise null.
     */
    private ItemDTO findItemByID(int ID) {
        for (ItemDTO item : itemList) {
            if (item.getID() == ID) {
                return item;
            }
        }
        return null;
    }

    /**
     * Updates the quantity, total price, and VAT for an existing item in the sale.
     * @param item The ItemDTO object to update.
     * @param quantity The quantity to add to the existing quantity.
     */
    private void updateExistingItem(ItemDTO item, int quantity) {
        int newQuantity = item.getQuantity() + quantity;

        // Create a new ItemDTO object with the updated quantity
        ItemDTO updatedItem = new ItemDTO(item.getPrice(), item.getID(), item.getVAT(), newQuantity);

        // Replace the existing item in the itemList with the updated item
        itemList.remove(item);
        itemList.add(updatedItem);

        // Update currentTotalPrice and currentVAT based on the new quantity
        currentTotalPrice += updatedItem.getPrice() * quantity;
        currentVAT += updatedItem.getVAT() * updatedItem.getPrice() * quantity;

        // Update the SaleDTO object
        updateSale();
    }

    /**
     * Fetches an item from the external inventory system.
     * @param ID The ID of the item to fetch.
     * @param quantity The quantity of the item to fetch.
     * @return The ItemDTO object representing the fetched item.
     */
    private ItemDTO fetchItemFromExternalSystem(int ID, int quantity) {
        ItemDTO itemFound = externalInventorySystem.fetchItem(quantity, ID);
        itemList.add(itemFound);
        currentTotalPrice += itemFound.getPrice() * quantity;
        currentVAT += itemFound.getVAT() * itemFound.getPrice() * quantity;
        updateSale();
        return itemFound;
    }

    /**
     * Ends the current sale and retrieves the SaleDTO object.
     * @return The SaleDTO object representing the ended sale.
     */
    public SaleDTO endSale() {
        return this.saleDTO;
    }

    /**
     * Processes the payment for the current sale and returns the receipt.
     * @param amountPaid The amount paid by the customer.
     * @return The ReceiptDTO object representing the receipt for the sale.
     */
    public ReceiptDTO receivePayment(int amountPaid) {
        int change = amountPaid - saleDTO.getTotalPrice();
        receiptDTO = new ReceiptDTO(saleDTO.getSaleTime(), currentTotalPrice, currentVAT, amountPaid, change, itemList);
        externalAccountingSystem.update(receiptDTO);
        return receiptDTO;
    }

}
