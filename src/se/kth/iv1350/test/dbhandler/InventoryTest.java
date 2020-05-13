package se.kth.iv1350.test.dbhandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.dbhandler.Inventory;

import static junit.framework.TestCase.fail;

public class InventoryTest
{
    Inventory inv;

    @Before
    public void setUp() throws Exception
    {
        inv = new Inventory();
    }

    @After
    public void tearDown() throws Exception
    {
        inv = null;
    }

    @Test
    public void checkIdentifierWhenTrue()
    {
        // Identifier "1" exist, expected result is true
        try
        {
            boolean actualResult = inv.checkIdentifier(1);
            Assert.assertTrue(actualResult);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }


}