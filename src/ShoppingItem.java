public class ShoppingItem implements Comparable<ShoppingItem> {
    private String nameOfItem;
    private double priceOfItem;

    /**
     * Constructor for a shopping item, takes the price and name of the item as arguments.
     * @param name
     * @param price
     */
    public ShoppingItem(String name, int price) {
        nameOfItem = name;
        priceOfItem = price;
    }

    /**
     * Accessor method to get the price of an item.
     * @return price of the item (double)
     */
    public double getPrice() {
        return priceOfItem;
    }

    /**
     * Accessor method to get the name of an item.
     * @return name of a product (String)
     */
    public String getName() {
        return nameOfItem;
    }

    /**
     * Compare to method to sort shopping items based on price.
     * @param otherItem
     * @return integers 0, 1 or -1 to sort items based on price
     */
    public int compareTo(ShoppingItem otherItem) {
        if(this.priceOfItem<otherItem.priceOfItem)
            return -1;
        else if(this.priceOfItem>otherItem.priceOfItem)
            return 1;
        return 0;
    }

    public String toString() {
        return nameOfItem + " --  $" + priceOfItem;
    }
}
