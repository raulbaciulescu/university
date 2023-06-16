import java.io.IOException;
import java.io.OutputStream;

public class MyLinkedList {
    private Node first;

    public MyLinkedList() {
        first = null;
    }

    public void printList(OutputStream outputStream) throws IOException {
        Node current = first;
        while (current != null) {
            outputStream.write(("exponent: " + current.getExponent() + ", value: " + current.getValue() + "\n").getBytes());
            current = current.getNextNode();
        }
    }

    public void add(Node node) {
        if (first == null || node.getExponent() > first.getExponent()) {
            node.setNextNode(first);
            first = node;
            return;
        }
        Node prev = null;
        Node current = first;
        current.lock();
        while (current.getNextNode() != null && current.getNextNode().getExponent() >= node.getExponent()) {
            if (prev != null) {
                prev.unlock();
            }
            prev = current;
            current = current.getNextNode();
            current.lock();
        }

        if (current.getExponent() == node.getExponent()) {
            current.setValue(current.getValue() + node.getValue());
            if (current.getValue() == 0) {
                if (prev == null) {
                    first = current.getNextNode();
                } else {
                    prev.setNextNode(current.getNextNode());
                }
            }
        } else {
            node.setNextNode(current.getNextNode());
            current.setNextNode(node);
        }

        if (prev != null) {
            prev.unlock();
        }
        current.unlock();
    }
}
