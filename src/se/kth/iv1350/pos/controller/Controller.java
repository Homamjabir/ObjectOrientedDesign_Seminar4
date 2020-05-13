package se.kth.iv1350.pos.controller;


import se.kth.iv1350.pos.dbhandler.ItemDTO;
import se.kth.iv1350.pos.model.*;

public class Controller
{
    private CashRegister cashRegister;

    /**
     * Constructor to create the Controller object
     */
    public Controller() {
        this.cashRegister = new CashRegister();

    }

    /**
     * Calls createNewSale in cashRegister to create a new sale object
     * @return
     */
    public Sale startSale() {
        return cashRegister.createNewSale();
    }

    /**
     * Calls scanIdentifier in cashRegister to scan a specific itemIdentifier
     * @param itemIdentifier Identifier of an item
     * @return ItemDTO of the found item
     */
    public ItemDTO scanItem(int itemIdentifier) throws Exception {
        try {
            return cashRegister.scanIdentifier(itemIdentifier);
        }
        catch (DatabaseFailureException ex) {
            throw new DatabaseFailureException();
        }
        catch (Exception ex) {
            throw new InvalidIdentifierException();
        }
    }

    /**
     * Calls createReceipt in cashRegister to create a Receipt object
     * @param saleDTO Sale information that will be included in the receipt
     * @return Receipt from the SaleDTO
     */
    public Receipt getReceipt(SaleDTO saleDTO) {
        return cashRegister.createReceipt(saleDTO);
    }

    /**
     * Ends a given sale by calling enCurrentSale in cashRegister
     * @param currentSale The current sale
     * @return SaleDTO from Sale
     */
    public SaleDTO endCurrentSale(Sale currentSale) {
        return cashRegister.endCurrentSale(currentSale);
    }

    public void createObserver(SaleObserver saleObserver) {
        cashRegister.addSaleObserver(saleObserver);
    }

}
