package workers;

import generator.MyQueue;
import generator.Node;

import java.util.HashMap;
import java.util.Map;

public class Worker extends Thread {
    private final MyQueue queue;
    private final MyQueue workerQueue1;
    private final MyQueue workerQueue3;
    private final MyQueue workerQueue4;

    public Worker(MyQueue queue, MyQueue workerQueue1, MyQueue workerQueue3, MyQueue workerQueue4) {
        this.queue = queue;
        this.workerQueue1 = workerQueue1;
        this.workerQueue3 = workerQueue3;
        this.workerQueue4 = workerQueue4;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consume() throws InterruptedException {
        Node node;
        while (true) {
            if (queue.size() > 0) {
                node = queue.pop();
                System.out.println("consumer " + node);
                if (node == null)
                    break;

                if (node.getService() == 1) {
                    workerQueue1.add(node);
                }
                if (node.getService() == 3) {
                    workerQueue3.add(node);
                }
                if (node.getService() == 4) {
                    workerQueue4.add(node);
                }
            }
        }
        System.out.println("finished worker");
    }
}
