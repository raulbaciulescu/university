import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {
    public static int p;

    /**
     * compare 2 files
     */
    public static long compareFiles(Path path1, Path path2) throws IOException {
        try (BufferedReader bf1 = Files.newBufferedReader(path1);
             BufferedReader bf2 = Files.newBufferedReader(path2)) {

            long lineNumber = 1;
            String line1 = "", line2 = "";
            while ((line1 = bf1.readLine()) != null) {
                line2 = bf2.readLine();
                if (line2 == null || !line1.equals(line2)) {
                    return lineNumber;
                }
                lineNumber++;
            }
            if (bf2.readLine() == null) {
                return -1;
            }
            else {
                return lineNumber;
            }
        }
    }

    /**
     * generate a matrix with dimensions: M * N
     * @param N number of cols
     * @param M number of rows
     * @return a random matrix
     */
    public static int[][] generateMatrix(int N, int M) {
        Random random = new Random();
        int[][] a = new int[M][N];

        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = random.nextInt(900000);

        return a;
    }

    /**
     * write 2 matrix in a file
     */
    public static void writeToFile(int[][] a, int[][] kernel, int N, int M, int n, int m) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/main/resources/date.txt"), StandardCharsets.UTF_8))) {
            writer.write(N + " " + M + "\n");
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++)
                    writer.write(a[i][j] + " ");
                writer.write("\n");
            }

            writer.write(n + " " + m + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    writer.write(kernel[i][j] + " ");
                writer.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream input = new FileInputStream("D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\date.txt");
        long startTime = System.nanoTime();
        p = 6;
    //    p = Integer.parseInt(args[0]);
        int N = 10 , M = 10 , n = 3, m = 3;
        int[][] matrix = generateMatrix(N, M);
        int[][] kernel = generateMatrix(n, m);
        writeToFile(matrix, kernel, N, M, n, m);

//        OutputStream outputParallel = new FileOutputStream("D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\parallel.out");
//        Parallel parallel = new Parallel();
//        parallel.process(input, outputParallel);

//        OutputStream outputSequential = new FileOutputStream("D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\sequential.out");
//        Sequential sequential = new Sequential();
//        sequential.process(input, outputSequential);

        OutputStream outputParallel = new FileOutputStream("D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\parallel.out");
        ParallelMemory parallel = new ParallelMemory();
        parallel.process(input, outputParallel);


        //System.out.println(compareFiles(Path.of("src/main/resources/sequential.out"), Path.of("src/main/resources/parallel.out")));
        long endTime = System.nanoTime();
        System.out.println((double)(endTime - startTime)/1E6);//ms
    }
}
