import generator.BigWorker;
import generator.Generator;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Generator.generate();
        BigWorker bigWorker = new BigWorker();
        bigWorker.run();
        long endTime = System.nanoTime();
        System.out.println((double)(endTime - startTime) / 1E6);
    }
}
