import java.util.List;

public class MyThread extends Thread {
    private final int id;
    private final int start;
    private final int end;
    private final int n;
    private final int m;
    private final int N;
    private final int M;
    private int[] vector;
    private int[][] result, kernel;

    public MyThread(int id, int start, int end, int N, int M, int n, int m, int[] vector, int[][] kernel, int[][] result) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.n = n;
        this.m = m;
        this.N = N;
        this.M = M;
        this.vector = vector;
        this.kernel = kernel;
        this.result = result;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            int value = 0;
            for (int k =  - n / 2; k <= n / 2; k++)
                for (int l = - m / 2; l <= m / 2; l++) {
                    int ii = i / N - k;
                    int jj = i % N - l;
                    if (ii < 0)
                        ii = 0;
                    if (jj < 0)
                        jj = 0;
                    if (ii >= M)
                        ii = M - 1;
                    if (jj >= N)
                        jj = N - 1;
                    value += vector[ii * N + jj] * kernel[k + n / 2][l + n / 2];
                }

            result[i / N][i % N] = value;
        }
    }
}
