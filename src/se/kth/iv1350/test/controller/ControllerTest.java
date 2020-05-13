package se.kth.iv1350.test.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.dbhandler.ItemDTO;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleDTO;

import static junit.framework.TestCase.fail;

public class ControllerTest
{
    private Controller contr;
    private Sale sale;

    @Before
    public void setUp() throws Exception
    {
        contr = new Controller();
    }

    @After
    public void tearDown() throws Exception
    {
        contr = null;
        sale = null;
    }

    @Test
    public void endCurrentSaleNotNull()
    {
        sale = contr.startSale();
        SaleDTO saleDTO = contr.endCurrentSale(sale);
        Assert.assertNotNull("SaleDTO object is null", saleDTO);
    }

    @Test
    // Checks that when we scan same identifer it does not return the same item
    // (It should return two unique items with same values)
    public void scanItemNotDuplicateCheck()
    {
        int itemIdentifier = 5;

        ItemDTO itemDTO = null;
        ItemDTO expectedItemDTO = null;
        try
        {
            itemDTO = contr.scanItem(itemIdentifier);
            expectedItemDTO = contr.scanItem(itemIdentifier);
        }
        catch (Exception ex)
        {
            fail("Item with identifier " + itemIdentifier + " not found.");
        }
        Assert.assertNotEquals(expectedItemDTO, itemDTO);
    }

    @Test
    public void getReceiptNotNull()
    {
        sale = contr.startSale();
        SaleDTO saleDTO = contr.endCurrentSale(sale);
        Receipt receipt = contr.getReceipt(saleDTO);
        Assert.assertNotNull("Receipt object is null", receipt);
    }
}