package se.kth.iv1350.pos.dbhandler;

import se.kth.iv1350.pos.model.SaleDTO;
import java.util.ArrayList;

public class ExternalAccountingSystem
{
    private static ArrayList<SaleDTO> saleLogs;

    /**
     * Creates an instance of ExternalAccounting
     */
    public ExternalAccountingSystem()
    {
        saleLogs = new ArrayList<SaleDTO>();
    }

    /**
     * Stores a new sales log in the external accounting system.
     * @param saleDTO The given <code>SaleDTO</code> to store in the external accounting system.
     */
    public void addLog(SaleDTO saleDTO)
    {
        saleLogs.add(saleDTO);
    }

    /**
     * Retrieve all stored sales logs
     */
    public ArrayList<SaleDTO> getLogs()
    {
        ArrayList<SaleDTO> saleLogsToReturn = new ArrayList<>();
        for (SaleDTO saleDTO : saleLogs)
            saleLogsToReturn.add(saleDTO);
        return saleLogsToReturn;
    }
}