package se.kth.iv1350.test.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.dbhandler.Inventory;
import se.kth.iv1350.pos.dbhandler.ItemDTO;
import se.kth.iv1350.pos.model.Sale;

import java.util.ArrayList;

public class SaleTest
{
    private Inventory inv;
    private Sale sale;

    @Before
    // Identifier "1" is Chocolate bar with the price of 25
    public void setUp() throws Exception
    {
        inv = new Inventory();
        sale = new Sale();
        sale.uppdateSale(inv.retrieveItemInformation(1));
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getRunningTotalCheck()
    {
        double expectedRT = 25;
        Assert.assertEquals(expectedRT, sale.getRunningTotal(), 0f);
    }

    @Test
    // Knowing the identifier we can check if the item we added is of same type
    public void getItemsCheck()
    {
        ArrayList<ItemDTO> items = sale.getItems();
        ItemDTO itemDTO = items.get(0);
        int expectedIdentifier = 1  ;
        Assert.assertEquals(expectedIdentifier, itemDTO.getIdentifier());
    }

    @Test
    // By adding an item we know that the ArrayLists will be different
    // Makes sure we dont copy references
    public void updateSale()
    {
        ArrayList<ItemDTO> oldItems = sale.getItems();
        try
        {
            sale.uppdateSale(inv.retrieveItemInformation(2));
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        ArrayList<ItemDTO> newItems = sale.getItems();
        Assert.assertNotSame(oldItems, newItems);
    }
}