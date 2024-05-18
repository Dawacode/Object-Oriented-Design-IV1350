package interfaces;

/**
 * SaleObserver interface provides a method to be implemented by observers
 * that need to be notified about changes in total revenue.
 */
public interface SaleObserver {
    /**
     * This method is called to update the total revenue.
     *
     * @param totalRevenue The new total revenue to be updated.
     */
    void updateTotalRevenue(int totalRevenue);
}
