import java.util.Scanner;

public class MenuItem<E> extends MySinglyLinkedList<E> {
    private Node<E> current = null;
    private String label;

    MenuItem(String label) {
        this.label = label;
    }

    @Override
    public String toString() {

        if (super.getSize() == 0) {
            current = null;
            return null;
        }

        current = super.getHead();
        StringBuilder menu = new StringBuilder("[ ");
        do {
            menu.append(current.getData());
            if (current.getNext() != null) menu.append(", ");
            current = current.getNext();
        } while (current != null || getSize() == 1);
        menu.append(" ]");
        return menu.toString();
    }

    @Override
    public void insert(E data) throws ListOverflowException {
        super.insert(data);
    }


    public String getLabel() {
        return label;
    }

    @Override
    public Node<E> getHead() {
        return super.getHead();
    }

}
