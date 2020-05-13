package se.kth.iv1350.test.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleDTO;

public class ReceiptTest
{
    private Receipt receipt;

    @After
    public void tearDown() throws Exception
    {
        receipt = null;
    }

    @Test
    public void toStringCheck()
    {
        Sale sale = new Sale();
        SaleDTO saleDTO = new SaleDTO(sale);
        receipt = new Receipt(saleDTO);
        Assert.assertTrue("No items were present", !receipt.toString().isEmpty());
        Assert.assertTrue("Total price did not print", receipt.toString().contains(Double.toString(saleDTO.getTotalPrice())));
        Assert.assertTrue("VAT did not print", receipt.toString().contains(Double.toString(saleDTO.getVAT())));
        Assert.assertTrue("Change did not print", receipt.toString().contains(Double.toString(saleDTO.getChange())));
    }
}