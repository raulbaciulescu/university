package workers;

import generator.MyQueue;
import generator.Node;

import java.util.Map;
import java.util.Random;

public class Service2 extends Thread {
    private final MyQueue queue;
    private final Map<Integer, MyEntity> map;
    private final Random random;
    private int timeout;

    public Service2(MyQueue queue, Map<Integer, MyEntity> map, int timeout) {
        this.queue = queue;
        this.map = map;
        random = new Random();
        this.timeout = timeout;
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
                System.out.println("pop service 2");
                System.out.println("consumer " + node);
                if (node == null)
                    break;

                if (!map.containsKey(node.getId())) {
                    map.put(node.getId(), new MyEntity(0, "0"));
                }
                else {
                    if (map.get(node.getId()).getCode() == 0) {
                        map.get(node.getId()).setHistory(map.get(node.getId()).getHistory() + node.getService());
                        sleep(timeout);
                        map.get(node.getId()).setCode(0);
                    }
                    else {
                        queue.add(node);
                    }
                }
            }
        }
    }
}



