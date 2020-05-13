package se.kth.iv1350.test.dbhandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.dbhandler.Printer;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleDTO;

public class PrinterTest
{
    CashRegister cashRegister;
    private Printer printer;
    private Sale sale;
    private SaleDTO saleDTO;

    @Before
    public void setUp() throws Exception
    {
        cashRegister = new CashRegister();
        printer = new Printer();
        sale = cashRegister.createNewSale();
        saleDTO = cashRegister.endCurrentSale(sale);
    }

    @After
    public void tearDown() throws Exception
    {
        cashRegister = null;
        printer = null;
        sale = null;
        saleDTO = null;
    }

    @Test
    // Even when using the same SaleDTO object, two seperate receipts should be created with identical data
    public void printReceiptNotDuplicate()
    {
        Receipt receipt1 = printer.printReceipt(saleDTO);
        Receipt receipt2 = printer.printReceipt(saleDTO);
        Assert.assertNotSame(receipt1, receipt2);
    }
}