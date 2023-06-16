

public class Consumer extends Thread {
    private final MyQueue queue;
    private final MyLinkedList myLinkedList;

    public Consumer(MyQueue queue, MyLinkedList myLinkedList) {
        this.queue = queue;
        this.myLinkedList = myLinkedList;
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
                if (node == null)
                    break;
                myLinkedList.add(node);
            }
        }
    }
}
