package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.dbhandler.ItemDTO;

import java.util.ArrayList;

public class Receipt
{
    private ArrayList<ItemDTO> boughtItems;
    private double totalPrice;
    private double VAT;
    private double change;

    /**
     * Constructor to create an object of <code>Receipt</code>.
     * @param saleDTO The given <code>SaleDTO</code> to create a receipt from.
     */
    public Receipt(SaleDTO saleDTO)
    {
        boughtItems = saleDTO.getItems();
        totalPrice = saleDTO.getTotalPrice();
        VAT = saleDTO.getVAT();
        change = saleDTO.getChange();
    }

    /**
     * Custom toString method to display the receipt correctly.
     * @return String of all relevant data in correct structure.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================================\n");
        sb.append("-------------------------Receipt-----------------------\n");
        for (ItemDTO item : boughtItems)
        {
            sb.append("- " + item.getName() + " - " + item.getQuantity() + "x - " + item.getPrice() * item.getQuantity() + ":-\n");
        }
        sb.append("Total price: " + totalPrice + "\n");
        sb.append("VAT: " + VAT + "\n");
        sb.append("Change: " + change + "\n");

        return sb.toString();
    }
}