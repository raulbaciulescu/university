package generator;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private static final Random random = new Random(System.currentTimeMillis());;

    public static void generate() {
        try {
            gen();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //id service
    private static void gen() throws IOException {
        for (int j = 0; j < 5; j++) {
            List<Integer> ids = new ArrayList<>();
            List<Integer> services = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                int id = random.nextInt(10) + 1;
                int service = 0;
                while (service != 2) {
                    service = random.nextInt(4) + 1;
                }
                ids.add(id);
                services.add(service);
            }
            writeToFile(j, ids, services);
        }
    }

    private static void writeToFile(Integer contor, List<Integer> ids, List<Integer> services) throws IOException {
        String fileName = "src/main/resources/input/in" + contor + ".txt";
        CreateFile.create(fileName);
        OutputStream output = new FileOutputStream(fileName);
        for (int i = 0; i < ids.size(); i++) {
            output.write((ids.get(i) + " " + services.get(i) + "\n").getBytes());
        }
    }
}
