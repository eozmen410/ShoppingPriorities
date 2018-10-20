import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class FileReader {

    private Scanner reader;
    private File input;

    public FileReader(String name) throws FileNotFoundException {
        input = new File(name);
        reader = new Scanner(input);
    }

    public PositionalList<ShoppingItem> readShoppingList() throws InputMismatchException {
        PositionalList<ShoppingItem> readData = new LinkedPositionalList<>();
        while (reader.hasNext()) {
            ShoppingItem item = new ShoppingItem(reader.next(), reader.nextInt());
            readData.addLast(item);
        }
        return readData;
    }

    public ArrayList<ItemPriority> readPriorities() {
        ArrayList<ItemPriority> readData = new ArrayList<>();
        while (reader.hasNext()) {
            readData.add(new ItemPriority(reader.next(), reader.nextInt()));
        }
        return readData;
    }



}