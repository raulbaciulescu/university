package generator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
    private final Integer service;
    private Integer id;
    private Node nextNode;
    private final Lock lock;

    //id serviciu
    public Node(Integer id, Integer service, Node nextNode) {
        this.service = service;
        this.id = id;
        this.nextNode = nextNode;
        this.lock = new ReentrantLock();
    }

    public int getService() {
        return service;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "exponent=" + service +
                ", value=" + id +
                '}';
    }

    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }
}
