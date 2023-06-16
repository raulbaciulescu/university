package generator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Reader extends Thread {
    private final MyQueue queue;
    private int fileNumber;

    public Reader(int fileNumber, MyQueue queue) {
        this.fileNumber = fileNumber;
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
        InputStream inputStream = new FileInputStream(
                "D:\\Facultate\\PPD\\exam\\examen-ppd\\src\\main\\resources\\input\\in" + fileNumber + ".txt");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            List<String> line = List.of(scanner.nextLine().split(" "));
            createNode(line);
        }

        queue.insertNull();
        System.out.println("producer end");
    }

    private void createNode(List<String> line) throws InterruptedException {
        Node node = new Node(Integer.parseInt(line.get(0)), Integer.parseInt(line.get(1)), null);
        queue.add(node);
    }
}

