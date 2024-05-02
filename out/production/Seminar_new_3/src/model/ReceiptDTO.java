package model;

import java.time.LocalTime;
import java.util.List;
/*
 * Represents a receipt for a sale transaction, containing details such as sale time,
 * total price, total VAT, amount paid, change, and the list of items purchased.
 */
public class ReceiptDTO {

    // The time when the sale occurred
    private LocalTime saleTime;

    // The total price of the sale
    private final int totalPrice;

    // The total VAT of the sale
    private final double totalVAT;

    // The amount paid by the customer
    private final int paid;

    // The change returned to the customer
    private final int change;

    // The list of items included in the sale
    private final List<ItemDTO> itemList;

    /*
     * Constructs a new ReceiptDTO object with the provided sale details.
     * @param saleTime The time when the sale occurred.
     * @param totalPrice The total price of the sale.
     * @param totalVAT The total VAT of the sale.
     * @param paid The amount paid by the customer.
     * @param change The change returned to the customer.
     * @param itemList The list of items included in the sale.
     */
    public ReceiptDTO(LocalTime saleTime, int totalPrice, double totalVAT, int paid, int change, List<ItemDTO> itemList) {
        this.saleTime = saleTime;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.paid = paid;
        this.change = change;
        this.itemList = itemList;
    }

    /*
     * Retrieves the time when the sale occurred.
     * @return The sale time.
     */
    public LocalTime getSaleTime() {
        return this.saleTime;
    }

    /*
     * Retrieves the total price of the sale.
     * @return The total price.
     */
    public int getTotalPrice() {
        return this.totalPrice;
    }

    /*
     * Retrieves the total VAT of the sale.
     * @return The total VAT.
     */
    public double getTotalVAT() {
        return this.totalVAT;
    }

    /*
     * Retrieves the amount paid by the customer.
     * @return The amount paid.
     */
    public int getPaid() {
        return this.paid;
    }

    /*
     * Retrieves the change returned to the customer.
     * @return The change.
     */
    public int getChange() {
        return this.change;
    }

    /*
     * Retrieves the list of items included in the sale.
     * @return The list of items.
     */
    public List<ItemDTO> getItemList() {
        return this.itemList;
    }
}
