import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelMemory extends Worker {
    private final CyclicBarrier barrier;
    ReentrantLock lock ;
    public ParallelMemory() {
        lock = new ReentrantLock();
        barrier = new CyclicBarrier(Main.p);
    }

    @Override
    protected void doOperation() {
        // k = i * N + j
        // i = k / N
        // j = k / M
        int length = M * N;
        int intreg = length / Main.p - 1;
        int fract = length % Main.p;
        System.out.println(fract);
        int start = 0;
        int end = intreg;
        List<ThreadMemory> threads = new ArrayList<>();

        for (int i = 0; i < Main.p; i++) {
            if (fract > 0) {
                end++;
                fract--;
            }

            int startI = start / N;
            int startJ = start % N;
            int endI = end / N;
            int endJ = end % N + 1;
            if (endJ == 0)
                endJ = N;

            threads.add(new ThreadMemory(i, startI, endI, startJ, endJ, N, M, n, m, matrix, kernel, barrier, lock));
            threads.get(i).start();
            end++;
            start = end;
            end += intreg;
        }

        for (int i = 0; i < Main.p; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
