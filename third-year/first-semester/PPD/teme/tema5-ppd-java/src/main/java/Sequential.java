import util.Constants;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Sequential {
    private final MyLinkedList myLinkedList;

    public Sequential() {
        myLinkedList = new MyLinkedList();
    }

    public void run(OutputStream outputStream) {
        try {
            process(outputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void process(OutputStream outputStream) throws IOException {
        for (int i = 0; i < Constants.NUMBER_OF_POLYNOMIALS; i++) {
            InputStream inputStream = new FileInputStream("D:\\Facultate\\PPD\\teme\\tema4-ppd-java\\src\\main\\resources\\input\\polinom" + i + ".txt");
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                List<String> line = List.of(scanner.nextLine().split(" "));
                Node node = new Node(Integer.parseInt(line.get(0)), Integer.parseInt(line.get(1)), null);
                myLinkedList.add(node);
            }
        }

        myLinkedList.printList(outputStream);
    }
}
