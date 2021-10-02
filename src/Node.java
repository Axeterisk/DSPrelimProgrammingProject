public class Node<E> {
    private E data; // contains the actual element to be stored
    private Node<E> next;   // a reference to the next node

    /**
     * Constructor for the Node class
     * @param data element to be stored within a node
     */
    public Node(E data) {
        this.data = data;
    }

    /**
     * Returns the element stored in the node
     * @return stored element
     */
    public E getData() { return data; }


    /**
     * Set the reference to the next node
     * @param next the next node
     */
    public void setNext(Node<E> next) { this.next = next; }

    /** Returns a reference to the next node
     * @return the next node
     */
    public Node<E> getNext() { return next; }
}