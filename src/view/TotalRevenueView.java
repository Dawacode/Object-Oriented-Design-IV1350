package view;

import model.ReceiptDTO;
import model.SaleObserver;

public class TotalRevenueView implements SaleObserver {

    private int totalRevenue = 0;

    @Override
    public void updateTotalRevenue(int totalRevenue) {
        this.totalRevenue += totalRevenue;
        System.out.println("Total revenue: " + this.totalRevenue + " SEK");
    }

    }




