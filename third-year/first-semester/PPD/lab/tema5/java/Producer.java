import util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Producer extends Thread {
    private final MyQueue queue;
    private final int start;
    private final int end;

    public Producer(MyQueue queue, int start, int end) {
        this.start = start;
        this.end = end;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void produce() throws FileNotFoundException, InterruptedException {
        System.out.println("producer start");
        System.out.println(start + " " + end);
        for (int i = start; i < end; i++) {
            InputStream inputStream = new FileInputStream("D:\\Facultate\\PPD\\teme\\tema4-ppd-java\\src\\main\\resources\\input\\polinom" + i + ".txt");
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                List<String> line = List.of(scanner.nextLine().split(" "));
                createNode(line);
            }
        }
        queue.insertNull();
        System.out.println("producer end");
    }

    private void createNode(List<String> line) throws InterruptedException {
        Node node = new Node(Integer.parseInt(line.get(0)), Integer.parseInt(line.get(1)), null);
        queue.add(node);
        System.out.println("producer " + node);
    }
}
