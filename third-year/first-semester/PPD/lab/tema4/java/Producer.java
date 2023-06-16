import util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Producer extends Thread {
    private final MyQueue queue;

    public Producer(MyQueue queue) {
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

    private void produce() throws FileNotFoundException {
        for (int i = 1; i <= Constants.numberOfPolynomials; i++) {
            InputStream inputStream = new FileInputStream("D:\\Facultate\\PPD\\teme\\tema4-ppd-java\\src\\main\\resources\\input\\polinom" + i + ".txt");
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                List<String> line = List.of(scanner.nextLine().split(" "));
                createNode(line);
            }
        }

        produceFinals();
    }

    private void produceFinals() {
        for (int i = 0; i < Constants.p; i++) {
            queue.add(null);
        }
    }

    private void createNode(List<String> line) {
        Node node = new Node(Integer.parseInt(line.get(0)), Integer.parseInt(line.get(1)), null);
        queue.add(node);
    }
}
