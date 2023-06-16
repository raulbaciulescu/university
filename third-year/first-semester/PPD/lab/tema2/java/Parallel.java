import java.util.ArrayList;
import java.util.List;

public class Parallel extends Worker {
    private int[] vector;
//    private int p = 8;

    @Override
    protected void doOperation() {
        transformInArray();

        result = new int[M][N];
        int length = M * N;
        int intreg = length / Main.p;
        int fract = length % Main.p;
        int start = 0;
        int end = intreg;
        List<MyThread> myThreads = new ArrayList<>();

        for (int i = 0; i < Main.p; i++) {
            if (fract > 0) {
                end++;
                fract--;
            }

            myThreads.add(new MyThread(i, start, end, N, M, n, m, vector, kernel, result));
            myThreads.get(i).start();
            start = end;
            end += intreg;
        }

        for (int i = 0; i < Main.p; i++) {
            try {
                myThreads.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void transformInArray() {
        int k = 0;
        vector = new int[N * M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                vector[k++] = matrix[i][j];
        }
    }
}
