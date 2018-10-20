public class DoublyLinkedList<E> {

    private class Node {
        private E element;
        private Node previous;
        private Node next;

        public Node(E e, Node p, Node n) {
            element = e;
            previous = p;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }

        public void setPrevious(Node p) {
            previous = p;
        }

        public void setNext(Node n) {
            next = n;
        }

    } //end of nested node class

    private Node header;
    private Node trailer;
    private int size;

    public DoublyLinkedList() {
        header = new Node(null, null, null);
        trailer = new Node(null,header,null);
        header.setNext(trailer);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return header.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return trailer.getPrevious().getElement();
    }

    private void addBetween (E e, Node predecessor, Node successor) {
        Node newest = new Node(e, predecessor,successor);
        predecessor.setNext(newest);
        predecessor.setPrevious(newest);
        size++;
    }

    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }

    public void addLast(E e) {
        addBetween(e, trailer.getPrevious(), trailer);
    }

    private E remove (Node node) {
        Node predecessor = node.getPrevious();
        Node successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrevious(predecessor);
        size--;
        return node.getElement();
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return remove(header.getNext());
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(trailer.getPrevious());
    }

}
