package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Data Transfer Object (DTO) for a sale.
 */
public class SaleDTO {

    // The time at which the sale occurred
    private LocalTime saleTime;

    // The total price of the sale
    private int totalPrice;

    // The total VAT (Value Added Tax) of the sale
    private double totalVAT;

    // The list of items included in the sale
    private List<ItemDTO> itemList;

    /**
     * Constructs a SaleDTO object with the provided sale time, total price, total VAT, and item list.
     * @param saleTime The time at which the sale occurred.
     * @param totalPrice The total price of the sale.
     * @param totalVAT The total VAT of the sale.
     * @param itemList The list of items included in the sale.
     */
    public SaleDTO(LocalTime saleTime, int totalPrice, double totalVAT, List<ItemDTO> itemList) {
        this.saleTime = saleTime;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.itemList = itemList;
    }

    /**
     * Retrieves the sale time.
     * @return The time at which the sale occurred.
     */
    public LocalTime getSaleTime() {
        return this.saleTime;
    }

    /**
     * Retrieves the total price of the sale.
     * @return The total price of the sale.
     */
    public int getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Retrieves the list of items included in the sale.
     * @return The list of items included in the sale.
     */
    public List<ItemDTO> getItemList() {
        return this.itemList;
    }

    /**
     * Retrieves the total VAT of the sale.
     * @return The total VAT of the sale.
     */
    public double getTotalVAT() {
        return this.totalVAT;
    }

    /**
     * Sets the sale time.
     * @param time The time at which the sale occurred.
     */
    public void setTime(LocalTime time) {
        this.saleTime = time;
    }

    /**
     * Sets the list of items included in the sale.
     * @param itemList The list of items included in the sale.
     */
    public void setItemList(List<ItemDTO> itemList) {
        this.itemList = itemList;
    }

    /**
     * Sets the total price of the sale.
     * @param newTotalPrice The new total price of the sale.
     */
    public void setTotalPrice(int newTotalPrice) {
        this.totalPrice = newTotalPrice;
    }

    /**
     * Sets the total VAT of the sale.
     * @param totalVAT The total VAT of the sale.
     */
    public void setTotalVAT(double totalVAT) {
        this.totalVAT = totalVAT;
    }
}
