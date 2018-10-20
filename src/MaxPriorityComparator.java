import java.util.Comparator;

public class MaxPriorityComparator<E> implements Comparator<E> {
    public int compare(E a, E b) throws ClassCastException {
        return -1 * ((Comparable<E>)a).compareTo(b);
    }
}
