import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public abstract class Worker {
    protected int[][] matrix;
    protected int[][] kernel;
    protected int[][] result;
    protected int N;
    protected int M;
    protected int n;
    protected int m;

    protected abstract void doOperation();

    public void process(InputStream inputStream, OutputStream outputStream) throws IOException {
        readFromFile(inputStream);
        doOperation();
        //writeToFile(outputStream);
    }

    public void readFromFile(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        N = scanner.nextInt();
        M = scanner.nextInt();
        matrix = new int[M][N];

        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                matrix[i][j] = scanner.nextInt();

        n = scanner.nextInt();
        m = scanner.nextInt();
        kernel = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                kernel[i][j] = scanner.nextInt();
    }

    private void writeToFile(OutputStream outputStream) throws IOException {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                outputStream.write((result[i][j] + " ").getBytes());
            }
            outputStream.write("\n".getBytes());
        }
    }
}
