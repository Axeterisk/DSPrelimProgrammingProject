import java.util.NoSuchElementException;

/**
 * A personal implementation of a SinglyLinked List
 * @param <E> Holds elements of this parameterized type
 */
public class MySinglyLinkedList<E> implements MyList<E> {

    /**
     * Inner private Node class
     * @param <E>
     */



    // Instance variables
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    /**
     * Construct an empty list
     */
    public MySinglyLinkedList() { }

    /**
     * Returns the size of the list
     * @return number of nodes containing elements
     */
    @Override
    public int getSize() { return size; }

    /**
     * Tests if the list is empty
     * @return is the list empty?
     */
    private boolean isEmpty() { return getSize() == 0; }

    /**
     * Inserts an element at the end of the list
     * @param data element to be inserted
     * @throws ListOverflowException
     */
    public void insert(E data) throws ListOverflowException {
        Node<E> temp = new Node<>(data);

        if (isEmpty()) head = temp;
        else tail.setNext(temp);
        tail = temp;
        size++;

        if (getSize() == Integer.MAX_VALUE) throw new ListOverflowException("List is full.");
    }

    /**
     * Retrieves the element passed as an argument from the list
     * @param data element to be retrieved
     * @return element passed as an argument
     * @throws NoSuchElementException if element is not in the list
     */
    public E getElement(E data) throws NoSuchElementException {
        if (isEmpty()) return null;
        Node<E> current = head;
        while (true) {
            if (current.getData().equals(data)) return current.getData();
            if (current.getNext() != null) current = current.getNext();
            else throw new NoSuchElementException(data + " not found in list.");
        }

    }

    /**
     * Delete the passed argument contained in the list
     * @param data element to be deleted from the list
     * @return if argument was deleted
     */
    public boolean delete(E data) {
        if (isEmpty()) return false;
        Node<E> current = head;

        if (data == head.getData()) {
            current = head.getNext();
            head = current;

            --size;
            return true;
        } else {
            Node<E> prevNode = head;

            while (prevNode.getNext() != null) {

                if (prevNode.getNext().getData().equals(data)) {
                    current = prevNode.getNext();

                    prevNode.setNext(current.getNext());
                    current = null;

                    --size;
                    return true;
                } else {
                    prevNode = prevNode.getNext();
                }
            }

            return false;
        }
    }

    /**
     * Checks if the passed argument exists in the list
     * @param data element to look up
     * @return existence of element in the list
     */
    @Override
    public boolean search(E data) {
        if (isEmpty()) return false;

        Node<E> current = head;
        do {
            if (current.getData().equals(data)) return true;
            current = current.getNext();

        } while (current != null);
        return false;

    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }
}
