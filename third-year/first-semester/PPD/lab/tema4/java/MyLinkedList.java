import java.io.IOException;
import java.io.OutputStream;

public class MyLinkedList {
    private Node first;

    public MyLinkedList() {
        first = null;
    }

    synchronized public void printList(OutputStream outputStream) throws IOException {
        Node current = first;
        while (current != null) {
            outputStream.write(("exponent: " + current.getExponent() + ", value: " + current.getValue() + "\n").getBytes());
            current = current.getNextNode();
        }
    }

    synchronized public void add(Node node) {
        Node current = first, prev = null;
        boolean found = false;
        while (current != null) {
            if (current.getExponent() == node.getExponent()) {
                current.setValue(current.getValue() + node.getValue());
                found = true;
            }

            if (current.getValue() == 0) {
                if (prev != null && current.getNextNode() != null)
                    prev.setNextNode(current.getNextNode());
                else if (prev == null && current.getNextNode() != null) {
                    first = current.getNextNode();
                } else if (prev != null && current.getNextNode() == null) {
                    prev.setNextNode(null);
                } else if (prev == null && current.getNextNode() == null)
                    first = null;
            }
            prev = current;
            current = current.getNextNode();
        }

        if (!found)
            addNewNode(node);
    }

    private void show() {
        Node current = first;
        while (current != null) {
            System.out.println(current);
            current = current.getNextNode();
        }
        System.out.println();
    }

    synchronized private void addNewNode(Node node) {
        Node current = first;
        if (first == null) {
            first = node;
            return;
        }

        while (current.getNextNode() != null && current.getNextNode().getExponent() > node.getExponent()) {
            current = current.getNextNode();
        }

        if (current.getNextNode() == null && current.getExponent() > node.getExponent()) {
            current.setNextNode(node);
        } else if (current.getExponent() < node.getExponent()) {
            node.setNextNode(current);
            first = node;
        } else {
            node.setNextNode(current.getNextNode());
            current.setNextNode(node);
        }
    }
}
