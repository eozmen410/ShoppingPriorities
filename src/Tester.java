import java.io.FileNotFoundException;

public class Tester {

    public static void main (String[] args) throws FileNotFoundException {
        Shopper testShop = new Shopper("ShoppingList.txt","PriorityList.txt");
        testShop.run();
    }
}
