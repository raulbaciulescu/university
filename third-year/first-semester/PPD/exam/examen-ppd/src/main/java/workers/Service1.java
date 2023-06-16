package workers;

import generator.MyQueue;
import generator.Node;

import java.util.Map;
import java.util.Random;

public class Service1 extends Thread {
    private final MyQueue queue;
    private final MyQueue queue2;
    private final Map<Integer, MyEntity> map;
    private final Random random;

    public Service1(MyQueue queue, Map<Integer, MyEntity> map, MyQueue queue2) {
        this.queue = queue;
        this.map = map;
        random = new Random();
        this.queue2 =queue2;
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
                System.out.println("pop service 1");
                System.out.println("consumer " + node);
                if (node == null)
                    break;

                if (!map.containsKey(node.getId())) {
                    map.put(node.getId(), new MyEntity(0, "0"));
                }
                else {
                    if (map.get(node.getId()).getCode() == 0) {
                        map.get(node.getId()).setHistory(map.get(node.getId()).getHistory() + node.getService());
                        sleep(25);
                        map.get(node.getId()).setCode(0);
                        if (random.nextInt(10) == 1) {
                            queue2.add(new Node(node.getId(), 2, null));
                        }
                    }
                    else {
                        queue.add(node);
                    }
                }
            }
        }
        queue2.add(null);
    }
}


