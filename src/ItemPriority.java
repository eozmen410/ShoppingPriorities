public class ItemPriority {

    private String itemName;
    private int key;

    public ItemPriority(String name, int priority) {
        itemName = name;
        key = priority;
    }

    public String getItemName() {
        return itemName;
    }

    public int getKey() {
        return key;
    }
}
