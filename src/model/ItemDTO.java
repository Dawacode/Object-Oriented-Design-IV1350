package model;
/**
 * Represents a Data Transfer Object (DTO) for an item.
 */
public class ItemDTO {

    // The price of the item
    private final int price;

    // The ID of the item
    private final int ID;

    // The VAT (Value Added Tax) of the item
    private final double VAT;

    // The quantity of the item
    private final int quantity;

    /**
     * Constructs an ItemDTO object with the provided price, ID, VAT, and quantity.
     * @param price The price of the item.
     * @param ID The ID of the item.
     * @param VAT The VAT of the item.
     * @param quantity The quantity of the item.
     */
    public ItemDTO(int price, int ID, double VAT, int quantity) {
        this.price = price;
        this.ID = ID;
        this.VAT = VAT;
        this.quantity = quantity;
    }

    /**
     * Retrieves the price of the item.
     * @return The price of the item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Retrieves the ID of the item.
     * @return The ID of the item.
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Retrieves the VAT of the item.
     * @return The VAT of the item.
     */
    public double getVAT() {
        return this.VAT;
    }

    /**
     * Retrieves the quantity of the item.
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return this.quantity;
    }

}
