package se.kth.iv1350.pos.dbhandler;

import se.kth.iv1350.pos.model.DatabaseFailureException;
import se.kth.iv1350.pos.model.InvalidIdentifierException;

import java.util.ArrayList;

public class Inventory
{

    private ArrayList<ItemDTO> items;

    /**
     * Constructor to create an instance of Inventory
     */
    public Inventory()
    {
        items = new ArrayList<>();

        items.add(new ItemDTO("INVALID ITEM", 0,0,0,0));
        items.add(new ItemDTO("Chocolate bar",1,0.06,25,0));
        items.add(new ItemDTO("Milk",         2,0.06,10,0));
        items.add(new ItemDTO("Cheese",       3,0.12,90,0));
        items.add(new ItemDTO("Dozen eggs",   4,0.12,30,0));
        items.add(new ItemDTO("Breed loaf",   5,0.25,15,0));

    }

    /**
     * Checks if itemIdentifier in argument matches any identifier in the inventory
     * Throws DatabaseFailureException if itemIdentifer is equal to 0 and
     * InvalidIdentifierException if the identifier does not exist
     * @param itemIdentifier Identifier of item to check
     * @return True if identifier is found in inventory, false otherwise
     */
    public boolean checkIdentifier(int itemIdentifier) throws Exception
    {
        if(itemIdentifier == 0)
            throw new DatabaseFailureException("Database failure");

        for(ItemDTO item : items)
        {
            if(item.getIdentifier() == itemIdentifier) { return true; }
        }
        throw new InvalidIdentifierException("Invalid identifier");
    }

    /**
     * Looks for an item with the same identifier.
     * @param itemIdentifier Identifier to retrieve item of
     * @return return ItemDTO of given identifier
     */
    public ItemDTO retrieveItemInformation(int itemIdentifier) throws InvalidIdentifierException
    {
        for(ItemDTO item : items)
            if(item.getIdentifier() == itemIdentifier)
            {
                ItemDTO newItem = new ItemDTO(item);
                newItem.quantity = 1;
                return newItem;
            }
        throw new InvalidIdentifierException("Invalid identifier, no item found");
    }
}

