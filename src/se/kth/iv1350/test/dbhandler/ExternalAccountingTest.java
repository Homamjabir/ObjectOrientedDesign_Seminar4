package se.kth.iv1350.test.dbhandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.dbhandler.ExternalAccountingSystem;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleDTO;

import java.util.ArrayList;

public class ExternalAccountingTest
{
    private ExternalAccountingSystem eAS;
    private SaleDTO saleDTO;

    @Before
    public void setUp() throws Exception
    {
        Controller contr = new Controller();
        eAS = new ExternalAccountingSystem();
        Sale sale = contr.startSale();
        saleDTO = contr.endCurrentSale(sale);
    }

    @After
    public void tearDown() throws Exception
    {
        eAS = null;
        saleDTO = null;
    }

    @Test
    // We know that after storing new log we should have two different lists
    public void addLog()
    {
        ArrayList<SaleDTO> oldLogs = eAS.getLogs();
        eAS.addLog(saleDTO);
        ArrayList<SaleDTO> newLogs = eAS.getLogs();
        Assert.assertNotSame(oldLogs, newLogs);
    }
}