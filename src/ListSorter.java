import java.util.Comparator;

public class ListSorter {
    /**
     * Sorts a given list.
     * @param list
     */
    public static<E extends Comparable<E>> void sort(PositionalList<E> list) {
        Position<E> marker = list.first();
        while (marker!=list.last()){
            Position<E> pivot = list.after(marker);
            E value = pivot.getElement();
            if (value.compareTo(marker.getElement()) > 0) {
                marker = pivot;
            } else {
                Position<E> walk = marker;
                while (walk!=list.first() && list.before(walk).getElement().compareTo(value)<0){
                    walk = list.before(walk);
                }
                list.remove(pivot);
                list.addAfter(walk,value);
            }
        }
    }

    /**
     * This method takes a list as an argument and returns a sorted version of the list
     * leaving the original list unchanged.
     * @param list
     * @return sorted form of the original list
     */
    public static <E extends Comparable<E>> PositionalList<E> sorted(PositionalList<E> list) {
        PositionalList sortedList = new LinkedPositionalList ();
        Position<E> adding = list.first();
        while (adding!=null) {
            Position<E> walk = sortedList.last();
            while (walk != null && adding.getElement().compareTo(walk.getElement()) > 0) {
                walk = list.before(walk);
            }
            if (walk == null) {
                sortedList.addFirst(adding);
            } else {
                sortedList.addAfter(walk, adding);
            }
            adding = list.after(adding);
        }
        return sortedList;
    }
}
