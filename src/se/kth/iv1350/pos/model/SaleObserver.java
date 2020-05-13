package se.kth.iv1350.pos.model;

public interface SaleObserver
{
    /**
     * Called when a sale has ended which means there has been a change in total revenue.
     * @param totalRevenue The total revenue.
     */
    void newEndedSale(double totalRevenue);
}
