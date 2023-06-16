package generator;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    private final Queue<Node> queue;
    private int notFinished;

    public MyQueue() {
        this.notFinished = 5;
        queue = new LinkedList<>();
    }

    synchronized public void add(Node node) throws InterruptedException {
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

    synchronized public void insertNull() {
        notFinished--;
        if (notFinished == 0) {
            for (int i = 0; i < 3; i++)
                queue.add(null);
        }
    }
}
