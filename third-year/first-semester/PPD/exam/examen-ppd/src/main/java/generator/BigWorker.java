package generator;

import workers.MyEntity;
import workers.Service1;
import workers.Service2;
import workers.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigWorker {
    private MyQueue queue;
    private MyQueue worker1Queue;
    private MyQueue worker2Queue;
    private MyQueue worker3Queue;
    private MyQueue worker4Queue;
    private final List<Reader> readers;
    private final List<Worker> workers;
    Service1 service1;
    Service2 service2;
    Service2 service3;
    Service2 service4;
    Map<Integer, MyEntity> map;

    public BigWorker() {
        this.readers = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.queue = new MyQueue();
        this.worker1Queue = new MyQueue();
        this.worker2Queue = new MyQueue();
        this.worker3Queue = new MyQueue();
        this.worker4Queue = new MyQueue();
        map = new HashMap<>();
    }

    public void run() {
        try {
            runThreads();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void runThreads() {
        createReaders();
        createWorkers();
        createServices();
        joinReaders();
        joinWorkers();
        help();
        joinServices();
    }

    private void help() {
        try {
            worker1Queue.add(null);
            worker3Queue.add(null);
            worker4Queue.add(null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void joinServices() {
        try {
            service1.join();
            service2.join();
            service3.join();
            service4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void createServices() {
        service1 = new Service1(worker1Queue, map, worker2Queue);
        service1.start();

        service2 = new Service2(worker2Queue, map, 15);
        service2.start();
        service3 = new Service2(worker3Queue, map, 30);
        service3.start();
        service4 = new Service2(worker4Queue, map, 30);
        service4.start();
    }

    private void joinWorkers() {
        workers.forEach((producer -> {
            try {
                producer.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    private void createWorkers() {
        for (int i = 0; i < 3; i++) {
            workers.add(new Worker(queue, worker1Queue, worker3Queue, worker4Queue));
            workers.get(i).start();
        }
    }

    private void joinReaders() {
        readers.forEach((producer -> {
            try {
                producer.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    private void createReaders() {
        for (int i = 0; i < 5; i++) {
            readers.add(new Reader(i, queue));
            readers.get(i).start();
        }
    }
}
