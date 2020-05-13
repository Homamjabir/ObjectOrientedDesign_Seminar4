package se.kth.iv1350.test.model;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import se.kth.iv1350.pos.dbhandler.ItemDTO;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleDTO;

import java.util.ArrayList;

import static junit.framework.TestCase.fail;

public class CashRegisterTest
{
    private CashRegister cashRegister;
    private ItemDTO itemDTO;
    private Sale sale;
    private SaleDTO saleDTO;
    private Receipt receipt;

    @Before
    public void setUp() throws Exception
    {
        cashRegister = new CashRegister();
    }

    @After
    public void tearDown() throws Exception
    {
        cashRegister = null;
        itemDTO = null;
        sale = null;
        saleDTO = null;
        receipt = null;
    }

    @Test
    public void scanIdentifierNotNull()
    {

        try
        {
            Assert.assertNotNull("scanIdentifier return is null", cashRegister.scanIdentifier(1));
        }
        catch (Exception ex)
        {
            fail("Item with identifier 1000 not found.");
        }
    }

    @Test
    public void scanIdentifierCorrectType()
    {
        try
        {
            itemDTO = cashRegister.scanIdentifier(1);
        }
        catch (Exception ex)
        {
            fail("Item with identifier 1000 not found.");
        }
        Assert.assertThat("scanIdentifier return is not of type ItemDTO", itemDTO, CoreMatchers.isA(ItemDTO.class));
    }

    @Test
    // Checks that when we scan same identifer it does not return the same item
    // (It should return two unique items with same values)
    public void scanIdentifierNotDuplicateCheck()
    {
        ItemDTO itemDTO2 = null;
        try
        {
            itemDTO = cashRegister.scanIdentifier(1);
            itemDTO2 = cashRegister.scanIdentifier(1);
        }
        catch (Exception ex)
        {
            fail("Item with identifier 1 not found.");
        }
        Assert.assertNotEquals(itemDTO, itemDTO2);
    }

    @Test
    public void createNewSaleNotNull()
    {
        sale = cashRegister.createNewSale();
        Assert.assertNotNull("Sale object is null", sale);
    }

    @Test
    public void createNewSaleCorrectType()
    {
        sale = cashRegister.createNewSale();
       Assert.assertThat("Sale object is not of type Sale", sale, CoreMatchers.isA(Sale.class));

    }

    @Test
    // Check how the program handles an empty sale (no scanned or added items)
    public void endCurrentSaleEmptySale()
    {
        sale = cashRegister.createNewSale();
        saleDTO = cashRegister.endCurrentSale(sale);
        ArrayList<ItemDTO> expectedItems = new ArrayList<ItemDTO>();
        double expectedVAT = 0f;
        double expectedTotalPrice = 0f;
        double expectedChange = 0f;
        Assert.assertNotNull("SaleDTO object is null", saleDTO);
        Assert.assertEquals("ArrayList is empty", expectedItems, saleDTO.getItems());
        System.out.println("Checking if the VAT is equal to zero...");
        Assert.assertEquals(expectedVAT, saleDTO.getVAT(), 0f);
        System.out.println("Checking if the total price is equal to zero...");
        Assert.assertEquals(expectedTotalPrice, saleDTO.getTotalPrice(), 0f);
        System.out.println("Checking if the change is equal to zero...");
        Assert.assertEquals(expectedChange, saleDTO.getChange(), 0f);
    }

    @Test
    public void endCurrentSaleNotNull()
    {
        sale = cashRegister.createNewSale();
        saleDTO = cashRegister.endCurrentSale(sale);
        Assert.assertNotNull("Object of SaleDTO is null", saleDTO);
    }

    @Test
    public void endCurrentSaleCorrectType()
    {
        sale = cashRegister.createNewSale();
        saleDTO = cashRegister.endCurrentSale(sale);
        Assert.assertThat("SaleDTO object is not of type SaleDTO", saleDTO, CoreMatchers.isA(SaleDTO.class));
    }

    @Test
    public void createReceiptEmptySaleDTO()
    {
        sale = cashRegister.createNewSale();
        saleDTO = cashRegister.endCurrentSale(sale);
        receipt = cashRegister.createReceipt(saleDTO);
        Assert.assertNotNull("Receipt is null", receipt);
        Assert.assertThat("Receipt object is not of type Receipt", receipt, CoreMatchers.isA(Receipt.class));
    }
}