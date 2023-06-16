import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    private final Queue<Node> queue;

    public MyQueue() {
        queue = new LinkedList<>();
    }

    synchronized public void add(Node node) {
        queue.add(node);
        notifyAll();
    }

    synchronized public Node pop() throws InterruptedException {
        while (queue.isEmpty())
            wait();

        return queue.remove();
    }

    synchronized public Integer size() {
        return queue.size();
    }
}
