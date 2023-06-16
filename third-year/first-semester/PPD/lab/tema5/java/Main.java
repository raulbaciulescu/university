import generator.PolynomialGenerator;
import util.Constants;
import util.MyComparator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
//       System.out.println(MyComparator.compareFiles(
//               Path.of("src/main/resources/output/out.txt"),
//               Path.of("src/main/resources/output/out-parallel.txt")));
        Constants.p = Integer.parseInt(args[0]);
        Constants.numberOfProducers = 2;
        Constants.numberOfConsumers = Constants.p - Constants.numberOfProducers;
        //Constants.p = 4;
     //   PolynomialGenerator.generatePolynomials();
        doSequential();
       // doParallel();
    }

    private static void doParallel() throws FileNotFoundException {
        long startTime = System.nanoTime();

        Parallel parallel = new Parallel();
        OutputStream outputStream = new FileOutputStream("D:\\Facultate\\PPD\\teme\\tema5-ppd-java\\src\\main\\resources\\output\\out-parallel.txt");
        parallel.run(outputStream);

        long endTime = System.nanoTime();
        System.out.println((double)(endTime - startTime) / 1E6);
    }

    private static void doSequential() throws FileNotFoundException {
        long startTime = System.nanoTime();

        Sequential sequential = new Sequential();
        OutputStream outputStream = new FileOutputStream("D:\\Facultate\\PPD\\teme\\tema5-ppd-java\\src\\main\\resources\\output\\out.txt");
        sequential.run(outputStream);

        long endTime = System.nanoTime();
        System.out.println((double)(endTime - startTime) / 1E6);
    }
}
