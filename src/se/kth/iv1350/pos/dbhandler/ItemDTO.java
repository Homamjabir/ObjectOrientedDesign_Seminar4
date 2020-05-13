package se.kth.iv1350.pos.dbhandler;

public class ItemDTO
{
    int identifier;
    String name;
    double VAT;
    double price;
    int quantity;

    /**
     * Constructor to create an ItemDTO from given values.
     * @param name Name of item
     * @param identfier Identifier of item
     * @param VAT VAT of item
     * @param price Price of item
     * @param quantity quantity of item
     */
    public ItemDTO(String name, int identfier, double VAT, double price, int quantity) {
        this.identifier = identfier;
        this.name = name;
        this.VAT = VAT;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructor to create ItemDTO from Item
     * @param item instance of ItemDTO
     */
    public ItemDTO(ItemDTO item) {
        this.identifier = item.identifier;
        this.name = item.name;
        this.VAT = item.VAT;
        this.price = item.price;
        this.quantity = item.quantity;
    }

    /**
     * @return Identifier of current ItemDTO
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @return Name of current ItemDTO
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return VAT of current ItemDTO
     */
    public double getVAT()
    {
        return VAT;
    }

    /**
     * @return Price of current ItemDTO
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @return Quantity of current ItemDTO
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Sets quantity of item
     * @param newQuantity New quantity
     */
    public void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }
}
