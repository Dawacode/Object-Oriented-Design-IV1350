package view;

// Import necessary classes from the model package
import interfaces.Logger;
import interfaces.SaleObserver;

/**
 * TotalRevenueView class implements the SaleObserver interface to update and display the total revenue.
 */
public class TotalRevenueView implements SaleObserver {
    private int totalRevenue = 1000; // Initialize total revenue with a starting value of 1000 SEK
    private Logger logger;


    public TotalRevenueView(Logger logger) {
        // Set the logger to be used for logging messages
        this.logger = logger;
    }

    /**
     * This method is called to update the total revenue when a sale occurs.
     *
     * @param totalRevenue The revenue to be added to the total revenue.
     */
    @Override
    public void updateTotalRevenue(int totalRevenue) {
        // Add the passed revenue to the current total revenue
        this.totalRevenue += totalRevenue;
        // Call a method to log the updated total revenue
        callLogger(this.totalRevenue);
        // Print the updated total revenue to the console
        System.out.println("Total revenue for all sales: " + this.totalRevenue + " SEK");
    }

    /**
     * Logs the total revenue using the logger.
     *
     * @param msgNo The total revenue amount to be logged.
     */
    private void callLogger(int msgNo) {
        // Log the total revenue message
        logger.log("Total revenue is " + msgNo + " SEK");
    }

    /**
     * Sets the logger to be used for logging messages.
     *
     * @param logger The logger instance to be set.
     */
    private void setLogger(Logger logger) {
        this.logger = logger;
    }
}
