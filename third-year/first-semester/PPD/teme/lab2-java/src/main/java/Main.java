import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A = [1, 2, 3...n]
 * B = [1, 2, 3,..n]
 * C = [2, 4, 6...n + n]
 * c[i] = a[i] + b[i]
 */
public class Main {
    private static final int n = 10000000;
//    private static final double[] a = new double[n];
//    private static final double[] b = new double[n];
//    private static final double[] c = new double[n];
//    private static final double[] t = new double[n];
    private static final int p = 8;


    public static void printVector(double[] x) {
        for (int i = 0; i < x.length; i++)
            System.out.print(x[i] + " ");
        System.out.println();
    }

//    public static void initialize() {
//        Random random = new Random();
//        for (int i = 0; i < n; i++) {
//            a[i] = random.nextInt(900000);
//            b[i] = random.nextInt(900000);
//        }
//    }

    public static void initialize(List<Double> a, List<Double> b) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a.add((double) random.nextInt(900000));
            b.add((double) random.nextInt(900000));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Double> a = new ArrayList<>(n);
        List<Double> b = new ArrayList<>(n);
        List<Double> c = Arrays.asList(new Double[n]);
        List<Double> t = Arrays.asList(new Double[n]);

        initialize(a, b);
        long startTime = System.nanoTime();
//        for (int i = 0; i < a.length; i++)
//            c[i] = Math.sqrt(a[i] * a[i] * a[i] + b[i] * b[i] * b[i]);

        for (int i = 0; i < a.size(); i++)
            c.set(i, Math.sqrt(a.get(i) * a.get(i) * a.get(i) + b.get(i) * b.get(i) * b.get(i)));

        long endTime = System.nanoTime();
        System.out.println("Interval secvential " + (endTime - startTime));
        //printVector(a);
        //printVector(b);
        //printVector(c);

        int intreg = n / p;
        int fract = n % p;
        int start = 0;
        int end = intreg;
        List<ThirdThread> myThreads = new ArrayList<>();

        startTime = System.nanoTime();
        for (int i = 0; i < p; i++) {
            if (fract > 0) {
                end++;
                fract--;
            }

//            myThreads.add(new ThirdThread(i, start, end, a, b, t));
//            myThreads.get(i).start();
            //System.out.println(i + " " + start + " " + end + "\n");
            start = end;
            end += intreg;
        }

//        for (int i = 0; i < p; i++) {
//            myThreads.get(i).join();
//        }
        endTime = System.nanoTime();
        System.out.println("Interval cu thread-uri " + (endTime - startTime));
//        //printVector(t);
//
//        startTime = System.nanoTime();
//        List<SecondThread> myThreads2 = new ArrayList<>();
//        for (int i = 0; i < p; i++) {
//            myThreads2.add(new SecondThread(i, p, a, b, t));
//            myThreads2.get(i).start();
//        }
//
//        for (int i = 0; i < p; i++) {
//            myThreads2.get(i).join();
//        }
//        endTime = System.nanoTime();
//        System.out.println("Interval cu thread-uri " + (endTime - startTime));
//        //printVector(t);
    }
}
