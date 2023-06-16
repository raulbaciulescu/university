import util.Constants;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Parallel {
    private MyQueue queue;
    private final MyLinkedList myLinkedList;
    private final List<Producer> producers;
    private final List<Consumer> consumers;


    public Parallel() {
        myLinkedList = new MyLinkedList();
        producers = new ArrayList<>();
        consumers = new ArrayList<>();
    }

    public void run(OutputStream outputStream) {
        try {
            runThreads(outputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void runThreads(OutputStream outputStream) throws IOException {
        queue = new MyQueue();
        createProducers();
        createConsumers();

        joinProducers();
        joinConsumers();

        myLinkedList.printList(outputStream);
    }

    private void createProducers() {
        int intreg = Constants.NUMBER_OF_POLYNOMIALS / Constants.numberOfProducers;
        int fract = Constants.NUMBER_OF_POLYNOMIALS % Constants.numberOfProducers;
        int start = 0;
        int end = intreg;

        for (int i = 0; i < Constants.numberOfProducers; i++) {
            if (fract > 0) {
                end++;
                fract--;
            }

            producers.add(new Producer(queue, start, end));
            producers.get(i).start();
            start = end;
            end += intreg;
        }
    }

    private void createConsumers() {
        for (int i = 0; i < Constants.numberOfConsumers; i++) {
            consumers.add(new Consumer(queue, myLinkedList));
            consumers.get(i).start();
        }
    }

    private void joinProducers() {
        producers.forEach((producer -> {
            try {
                producer.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    private void joinConsumers() {
        consumers.forEach((consumer -> {
            try {
                consumer.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
