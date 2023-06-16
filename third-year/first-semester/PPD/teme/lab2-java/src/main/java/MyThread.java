

public class MyThread extends Thread {
    private final int id;
    private final int start;
    private final int end;
    private double[] a, b, c;

    public MyThread(int id, int start, int end, double[] a, double[] b, double[] c) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++)
            c[i] = Math.sqrt(a[i] * a[i] * a[i] + b[i] * b[i] * b[i]);
    }
}
