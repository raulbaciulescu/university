import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadMemory extends Thread {
    private final int id;
    private final int startI;
    private final int startJ;
    private final int endI;
    private final int endJ;
    private final int n;
    private final int m;
    private final int N;
    private final int M;
    private int bufferSize;
    private int[][] buffer;
    private int[][] kernel;
    private int[][] matrix;
    private CyclicBarrier barrier;
    private int startBufferI, startBufferJ, endBufferI, endBufferJ;
    ReentrantLock lock;

    public ThreadMemory(int id, int startI, int endI, int startJ, int endJ,
                        int N, int M, int n, int m, int[][] matrix, int[][] kernel, CyclicBarrier barrier, ReentrantLock lock) {
        this.id = id;
        this.startI = startI;
        this.startJ = startJ;
        this.endI = endI;
        this.endJ = endJ;
        this.n = n;
        this.m = m;
        this.N = N;
        this.M = M;
        this.kernel = kernel;
        this.matrix = matrix;
        this.barrier = barrier;
        this.lock = lock;
        buffer = new int[n * 2 + endJ - startI + 10][N];
    }

    @Override
    public void run() {
        saveInBuffer();
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        int iBuff = startBufferI;
        for (int i = startI; i <= endI; i++) {
            for (int j = 0; j < N; j++) {
                int value = 0;
                boolean okk = false;
                for (int k = -n / 2; k <= n / 2; k++)
                    for (int l = -m / 2; l <= m / 2; l++) {
                        if ((i == startI && j >= startJ) || (i > startI && i < endI) || (i == endI && j < endJ)) {
                            okk = true;
                            int ii = iBuff - k;
                            int jj = j - l;
                            if (jj < 0)
                                jj = 0;
                            if (jj >= N)
                                jj = N - 1;
                            value += buffer[ii][jj] * kernel[k + n / 2][l + n / 2];
                        }
                    }
                if (okk)
                    matrix[i][j] = value;
            }
            iBuff++;
        }
    }

    private void saveInBuffer() {
        bufferSize = 0;
        for (int i = startI - n / 2; i <= endI + n / 2; i++) {
            for (int j = 0; j < N; j++) {
                int ii = i;
                if (ii >= M)
                    ii = M - 1;
                if (ii < 0)
                    ii = 0;
                buffer[bufferSize][j] = matrix[ii][j];
                if (i == startI) {
                    startBufferI = bufferSize;
                }
                if (i == endI) {
                    endBufferI = bufferSize;
                }
            }
            bufferSize++;
        }
        startBufferJ = startJ;
        endBufferJ = endJ;
    }
}
