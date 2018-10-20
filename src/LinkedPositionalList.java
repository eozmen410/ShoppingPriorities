public class LinkedPositionalList<E> implements PositionalList<E> {

    private class Node<E> implements Position<E> {

        private E element;
        private Node previous;
        private Node next;

        public Node(E e, Node p, Node n) {
            element = e;
            previous = p;
            next = n;
        }

        public E getElement() {
            if(next==null) {
                throw new IllegalStateException("Position no longer valid.");
            }
                return element;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }

        public void setElement(E e){
            element = e;
        }

        public void setPrevious(Node prev){
            previous=prev;
        }

        public void setNext(Node n) {
            next=n;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size;

    public LinkedPositionalList(){
        header = new Node(null, null,null);
        trailer = new Node (null,header,null);
        header.setNext(trailer);
        size = 0;
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid position");
        }
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null)
            throw new IllegalArgumentException("Position is no longer in the list"); return node;
    }

    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public Position<E> first() {
        return header.getNext();
    }

    public Position<E> last() {
        return trailer.getPrevious();
    }

    public Position<E> before(Position p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrevious());
    }

    public Position<E> after(Position p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrevious(newest);
        size++;
        return newest;
    }

    public Position<E> addFirst(E e) throws IllegalArgumentException {
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e) throws IllegalArgumentException {
        return addBetween(e, trailer.getPrevious(), trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrevious(), node.getNext());
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrevious();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrevious(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null); //
        node.setPrevious(null);
        node.setNext(null);
        return answer;
    }


}