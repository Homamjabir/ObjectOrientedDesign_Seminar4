package se.kth.iv1350.test.dbhandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.dbhandler.Inventory;
import se.kth.iv1350.pos.model.DatabaseFailureException;
import se.kth.iv1350.pos.model.InvalidIdentifierException;

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

    // Here we expect an exception to be thrown since identifier "100" does not exist
    @Test
    public void checkIdentifierWhenFalse() throws InvalidIdentifierException
    {
        try
        {
            inv.checkIdentifier(100);
            fail("Identifier found a match which it shouldn't...");
        }
        catch (Exception ex)
        {
            Assert.assertEquals(ex.getClass(), InvalidIdentifierException.class);
        }
    }


    // Here we test the simulated database failure
    @Test
    public void databeaseFailureTest() throws DatabaseFailureException
    {
        try
        {
            inv = new Inventory();
            inv.checkIdentifier(0);
        }
        catch (Exception ex)
        {
            Assert.assertEquals(ex.getClass(), DatabaseFailureException.class);
        }
    }


}