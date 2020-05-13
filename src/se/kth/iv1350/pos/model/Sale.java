package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.dbhandler.ItemDTO;
import se.kth.iv1350.pos.model.Receipt;

import java.util.ArrayList;
import java.util.Scanner;

public class Sale
{
    private ArrayList<ItemDTO> items;
    private double runningTotal;
    private double totalAmount;
    private double change;
    private double vat;
    private int amountOfMoney;
    private Scanner scanner = new Scanner(System.in);

    public Sale() {

        this.items = new ArrayList<ItemDTO>();
    }

    /**
     * @return runningTotal amoun
     */
    public double getRunningTotal()
    {
        return runningTotal;
    }

    /**
     * @return ArrayList of items
     */
    public ArrayList<ItemDTO> getItems()
    {
        ArrayList<ItemDTO> itemsToReturn = new ArrayList<>();
        for (ItemDTO i : this.items)
        {
            itemsToReturn.add(i);
        }
        return itemsToReturn;
    }

    /**
     * Uppdates sale with the given item, handles duplicates of items.
     * @param item ItemDTO to be added
     */
    public void uppdateSale(ItemDTO item) {
        if(item.getIdentifier() == 0)
            return;
        if(checkDuplicate(item))
            items.add(updateItemQuantity(item));
        else
            items.add(item);

        updateTotalPrice(item);

    }

    double getVat()
    {
        return vat;
    }

    void setChange(double amount)
    {
        change = amount;
    }

    double getChange()
    {
        return change;
    }

    private ItemDTO updateItemQuantity(ItemDTO item)
    {
        ItemDTO newItem = new ItemDTO(items.get(findItemIndex(item)));
        items.remove(findItemIndex(item));
        newItem.setQuantity(newItem.getQuantity() + 1);
        return new ItemDTO(newItem);
    }

    private int findItemIndex(ItemDTO item)
    {
        int pos = 0;
        for (ItemDTO i : items)
        {
            if(identifierMatch(i.getIdentifier(), item.getIdentifier()))
                break;
            else
                pos++;
        }

        return pos;
    }

    private boolean checkDuplicate(ItemDTO item)
    {
        for (ItemDTO i : items)
        {
            if(identifierMatch(i.getIdentifier(), item.getIdentifier()))
            {
                return true;
            }
        }
        return false;
    }

    private boolean identifierMatch(long x1, long x2)
    {
        return (x1 == x2);
    }

    private void updateTotalPrice(ItemDTO item)
    {
        runningTotal += item.getPrice();
    }

    void calculateVAT()
    {
        float VAT = 0f;

        for (ItemDTO itemDTO : items)
        {
            VAT += (itemDTO.getVAT() * itemDTO.getPrice() * itemDTO.getQuantity());
        }

        this.vat = VAT;
    }


}