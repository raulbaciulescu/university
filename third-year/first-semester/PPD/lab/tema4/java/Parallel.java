import util.Constants;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Parallel {
    private MyQueue queue;
    private final MyLinkedList myLinkedList;

    public Parallel() {
        myLinkedList = new MyLinkedList();
        queue = new MyQueue();
    }

    public void run(OutputStream outputStream) {
        try {
            createThreads(outputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createThreads(OutputStream outputStream) throws InterruptedException, IOException {
        Producer producer = new Producer(queue);
        producer.start();

        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < Constants.p; i++) {
            consumers.add(new Consumer(queue, myLinkedList));
            consumers.get(i).start();
        }

        producer.join();
        for (Consumer consumer : consumers)
            consumer.join();

        myLinkedList.printList(outputStream);
    }
}
