package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.SaleObserver;

public class TotalRevenueView implements SaleObserver
{
    @Override
    public void newEndedSale(double saleTotalRunning)
    {
        displayTotalRevenue(saleTotalRunning);
    }

    private void displayTotalRevenue(double totalRevenue)
    {
        System.out.println("###############################################\n" +
                "OBSERVER: Total revenue of all sales: " + totalRevenue
                + "\n###############################################");
    }
}
