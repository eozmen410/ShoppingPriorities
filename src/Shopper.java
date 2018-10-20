import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Shopper {

    private FileReader shopReader;
    private FileReader priorityReader;
    private double budget;
    private PositionalList<ShoppingItem> shoppingList;
    private ArrayList<ItemPriority> tempPriority;
    private PriorityQueue<Integer, ShoppingItem> priorityQueue;


    public Shopper (String shoplist, String priorityList) throws FileNotFoundException {
        shopReader = new FileReader(shoplist);
        priorityReader = new FileReader(priorityList);
        priorityQueue = new PriorityQueue<>();
    }

    public void run () throws FileNotFoundException {
        shoppingList = shopReader.readShoppingList();
        tempPriority = priorityReader.readPriorities();
        askBudget();
        constructPQ();
        printOutput();
    }

    private void askBudget() throws InputMismatchException {
        System.out.println("Enter budget size in dollars");
        Scanner keyboard = new Scanner(System.in);
        budget = keyboard.nextDouble();
    }

    private ShoppingItem findItem(String productName) {
        Position<ShoppingItem> item =  shoppingList.first();
        for (int i=0; i<shoppingList.size(); i++) {
            if (item.getElement().getName().equals(productName)){
                return item.getElement();
            }
            item = shoppingList.after(item);
        }
        return null;
    }

    private void constructPQ () {
        for(int i=0; i<tempPriority.size(); i++){
            ShoppingItem item = findItem(tempPriority.get(i).getItemName());
            int key = tempPriority.get(i).getKey();
            priorityQueue.insert(key,item);
        }
    }

    private void printOutput() throws FileNotFoundException {
        PrintWriter outputLogger = new PrintWriter("output.txt");
        while (budget>priorityQueue.min().getValue().getPrice()){
            ShoppingItem buy = priorityQueue.removeMin().getValue();
            budget = budget - buy.getPrice();
            outputLogger.println(buy.toString());
        }
        outputLogger.close();
    }


}
