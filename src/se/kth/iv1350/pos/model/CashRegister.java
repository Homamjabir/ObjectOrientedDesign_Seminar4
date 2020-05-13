package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.dbhandler.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Scanner;

public class CashRegister
{
    private Inventory inventory;
    private Sale sale;
    private Printer printer;
    private double totalInCashRegister;
    private ExternalAccountingSystem eAS;
    private double amountPresent;
    private double amountNeeded;
    private Scanner scanner;
    final static Logger logger = Logger.getAnonymousLogger();
    ArrayList<SaleObserver> saleObservers = new ArrayList<SaleObserver>();

    /**
     * Constructor for the class CashRegister. Creates an instance with necessary initializations
     */

    public CashRegister()
    {
        inventory = new Inventory();
        sale = new Sale();
        printer = new Printer();
        scanner = new Scanner(System.in);
        eAS = new ExternalAccountingSystem();
    }

    /**
     * Searches for Item in Inventory by checking the identifiers and updated the sale price
     * @param itemIdentifier Identifier which is unique to each type of item
     * @return ItemDTO of Item with matching identifiers
     */

    public ItemDTO scanIdentifier(int itemIdentifier) throws Exception
    {

        try
        {
            boolean isIdentifierReal = inventory.checkIdentifier(itemIdentifier);
            if (isIdentifierReal)
            {
                ItemDTO foundItem = inventory.retrieveItemInformation(itemIdentifier);
                updateAmountNeeded(foundItem.getPrice());
                return foundItem;
            }
        }
        catch (DatabaseFailureException ex)
        {
            System.out.println("DEVELOPER LOG:");
            logger.log(Level.SEVERE, "an exception was thrown", ex);
            throw new DatabaseFailureException();
        }
        catch (Exception ex)
        {
            System.out.println("DEVELOPER LOG:");
            logger.log(Level.SEVERE, "an exception was thrown", ex);
            throw new InvalidIdentifierException();
        }
        return null;
    }

    /**
     * @return New Sale object
     */
    public Sale createNewSale() {
        return new Sale();
    }

    /**
     * Creates a Receipt object from the saleDTO argument
     * @param saleDTO Sale information
     * @return Receipt object
     */
    public Receipt createReceipt(SaleDTO saleDTO) {
        return printer.printReceipt(saleDTO);
    }

    /**
     * Ends the given Sale
     * @param currentSale The Sale that will be ended
     * @return SaleDTO from the Sale argument
     */
    public SaleDTO endCurrentSale(Sale currentSale) {
        while(amountNeeded > amountPresent) {
            System.out.println("Amount needed: " + (amountNeeded - amountPresent));
            System.out.print("Enter customer payment: ");
            updateAmountPresent(scanner.nextDouble());
        }
        System.out.println();

        if(amountPresent > amountNeeded)
            currentSale.setChange(calculateChange());

        totalInCashRegister += amountNeeded;
        currentSale.calculateVAT();

        SaleDTO finalSaleDTO = new SaleDTO(currentSale);

        eAS.addLog(finalSaleDTO);

        notifySaleObserver();
        return finalSaleDTO;

    }

    private void updateAmountPresent(double amount) {
        amountPresent += amount;
    }


    private void updateAmountNeeded(double amount) {
        amountNeeded += amount;
    }

    private double calculateChange() {
        return amountPresent - amountNeeded;
    }

    public void addSaleObserver(SaleObserver saleObserver) {
        saleObservers.add(saleObserver);
    }

    public void notifySaleObserver() {
        for (SaleObserver saleObserver : saleObservers)
            saleObserver.newEndedSale(totalInCashRegister);
    }
}