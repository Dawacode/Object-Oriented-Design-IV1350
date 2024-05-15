package view;

import model.Logger;
import model.ReceiptDTO;
import model.SaleObserver;

public class TotalRevenueView implements SaleObserver {
    private int totalRevenue=1000;
    @Override
    public void updateTotalRevenue(int totalRevenue) {
        setLogger(new TotalRevenueFileOutput());
        this.totalRevenue += totalRevenue;
        anyMethod(this.totalRevenue);
        System.out.println("Total revenue for all sales: " + this.totalRevenue + " SEK");
    }
    private Logger logger;

    public void anyMethod(int msgNo) {
         logger.log("Important message number " + msgNo);
    }

    public void setLogger(Logger logger) {
        this.logger=logger;
    }

}




