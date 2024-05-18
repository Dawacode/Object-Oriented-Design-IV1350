/*
 * Sale class represents a sales transaction.
 */
package model;

import exceptions.DataBaseException;
import exceptions.ItemException;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import interfaces.SaleObserver;

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
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Constructor for the Sale class.
     * Initializes a new sale with default values and external systems.
     */
    public Sale() {
        currentTotalPrice = 0;
        currentVAT = 0.0;
        itemList = new ArrayList<>();
        externalInventorySystem = ExternalInventorySystem.getInventory();
        externalAccountingSystem = ExternalAccountingSystem.getAccount();
        saleDTO = new SaleDTO(LocalTime.now(), 0, 0, itemList);
    }

    /**
     * Adds an observer to the list of sale observers.
     * @param ob The SaleObserver to be added.
     */
    public void addObs(SaleObserver ob){
        saleObservers.add(ob);
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
     * @throws DataBaseException if there is an error accessing the database.
     * @throws ItemException if there is an error related to the item.
     */
    public ItemDTO itemExists(int ID, int quantity) throws DataBaseException, ItemException {
        ItemDTO unknownItem= new ItemDTO(0,ID,0,quantity);
        ItemDTO foundItem = new ItemMatch().matcher(unknownItem,itemList);
        if (foundItem != null) {
            updateExistingItem(foundItem, quantity);
        } else {
            foundItem = fetchItemFromExternalSystem(unknownItem, quantity);
        }
        return foundItem;
    }

    /**
     * Updates the quantity, total price, and VAT for an existing item in the sale.
     * @param item The ItemDTO object to update.
     * @param quantity The quantity to add to the existing quantity.
     */
    private void updateExistingItem(ItemDTO item, int quantity) {
        int newQuantity = item.getQuantity() + quantity;
        ItemDTO updatedItem = new ItemDTO(item.getPrice(), item.getID(), item.getVAT(), newQuantity);
        itemList.remove(item);
        itemList.add(updatedItem);
        currentTotalPrice += updatedItem.getPrice() * quantity;
        currentVAT += updatedItem.getVAT() * updatedItem.getPrice() * quantity;

        updateSale();
    }

    /**
     * Fetches an item from the external inventory system.
     * @param item The item to fetch.
     * @param quantity The quantity of the item to fetch.
     * @return The ItemDTO object representing the fetched item.
     * @throws DataBaseException if there is an error accessing the database.
     * @throws ItemException if there is an error related to the item.
     */
    private ItemDTO fetchItemFromExternalSystem(ItemDTO item, int quantity) throws DataBaseException, ItemException {
        ItemDTO itemFound = new SystemMatch().matcher(item,externalInventorySystem.getFakeExternalInventorySystem());
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
        notifyObservers(receiptDTO.getTotalPrice());

        return receiptDTO;
    }

    /**
     * Notifies all registered observers with the total revenue.
     * @param totalRevenue The total revenue to notify the observers with.
     */
    private void notifyObservers(int totalRevenue) {
        for (SaleObserver observer : saleObservers) {
            observer.updateTotalRevenue(totalRevenue);
        }
    }
}
