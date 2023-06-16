import util.Constants;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    private final Queue<Node> queue;
    private int notFinished;
    public MyQueue() {
        this.notFinished = Constants.numberOfProducers;
        queue = new LinkedList<>();
    }

    synchronized public void add(Node node) throws InterruptedException {
        while (queue.size() == Constants.SIZE_OF_QUEUE)
            wait();

        queue.add(node);
        notifyAll();
    }

    synchronized public Node pop() throws InterruptedException {
        while (queue.isEmpty())
            wait();

        Node node = queue.remove();
        notifyAll();
        return node;
    }

    synchronized public Integer size() {
        return queue.size();
    }

    synchronized public void insertNull() {
        notFinished--;
        if (notFinished == 0) {
            for (int i = 0; i < Constants.numberOfConsumers; i++)
                queue.add(null);
        }
    }
}
