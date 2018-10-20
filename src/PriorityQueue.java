import java.util.Comparator;

public class PriorityQueue<K, V> {

    public class PQEntry<K, V> {
        private K key;
        private V value;

        public PQEntry(K myKey, V myValue) {
            key = myKey;
            value = myValue;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K newKey) {
            key = newKey;
        }

        public void setValue(V newValue) {
            value = newValue;
        }
    }

    private Comparator<K> kComparator;
    private LinkedPositionalList<PQEntry<K,V>> list = new LinkedPositionalList<>();

    //can use a custom comparator in case the key is a different object
    public PriorityQueue(Comparator<K> comp) {
        kComparator = comp;
    }

    public PriorityQueue() {
        this(new MaxPriorityComparator<>());
    }

    public int compare(PQEntry<K, V> a, PQEntry<K, V> b) {
        return kComparator.compare(a.getKey(),b.getKey());
    }

    private boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (kComparator.compare(key,key)==0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public PQEntry<K, V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        PQEntry<K, V> newest = new PQEntry<>(key, value);
        if (list.isEmpty()) {
            list.addFirst(newest);
        } else{
            Position<PQEntry<K,V>> walk = list.last(); //walk back to insert in order of keys
            while (walk!=null && compare(newest, walk.getElement())>0){
                walk = list.before(walk);
            }
            if (walk == null) {
                list.addFirst(newest);
            } else {
                list.addAfter(walk, newest);
            }
        }
        return newest;
    }

    public PQEntry<K,V> min() {
        if(list.isEmpty()){
            return null;
        }
        return list.first().getElement();
    }

    public PQEntry<K,V> removeMin() {
        if(list.isEmpty()){
            return null;
        }
        return list.remove(list.first());
    }





}
