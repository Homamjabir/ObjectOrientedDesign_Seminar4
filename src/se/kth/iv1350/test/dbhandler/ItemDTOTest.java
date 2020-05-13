package se.kth.iv1350.test.dbhandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.dbhandler.Inventory;
import se.kth.iv1350.pos.dbhandler.ItemDTO;

public class ItemDTOTest
{
    private Inventory inv;
    private ItemDTO itemDTO;

    @Before
    public void setUp() throws Exception
    {
        inv = new Inventory();
        itemDTO = inv.retrieveItemInformation(1);
    }

    @After
    public void tearDown() throws Exception
    {
        inv = null;
        itemDTO = null;
    }

    @Test
    public void getNAME()
    {
        String expectedName = "Chocolate bar";
        Assert.assertEquals(expectedName, itemDTO.getName());
    }

    @Test
    public void getPRICE()
    {
        double expectedPrice = 25;
        Assert.assertEquals(expectedPrice, itemDTO.getPrice(), 0);
    }

    @Test
    public void getIDENTIFIER()
    {
        int expectedIfentifier = 1;
        Assert.assertEquals(expectedIfentifier, itemDTO.getIdentifier());
    }

    @Test
    public void getVAT()
    {
        double expectedVAT = 0.06;
        Assert.assertEquals(expectedVAT, itemDTO.getVAT(), 0);
    }

    @Test
    public void getQUANTITY()
    {
        int expectedQuantity = 1;
        Assert.assertEquals(expectedQuantity, itemDTO.getQuantity());
    }
}