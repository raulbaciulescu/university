import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Node {
    private final Integer exponent;
    private Integer value;
    private Node nextNode;
    private final Lock lock;

    public Node(Integer value, Integer exponent, Node nextNode) {
        this.exponent = exponent;
        this.value = value;
        this.nextNode = nextNode;
        this.lock = new ReentrantLock();
    }

    public int getExponent() {
        return exponent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
                "exponent=" + exponent +
                ", value=" + value +
                '}';
    }

    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }
}
