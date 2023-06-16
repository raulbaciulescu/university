public class SecondThread extends Thread{
    private final int id;
    private final int p;
    private double[] a, b, c;

    public SecondThread(int id, int p, double[] a, double[] b, double[] c) {
        this.id = id;
        this.p = p;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = id; i < c.length; i += p)
            c[i] = Math.sqrt(a[i] * a[i] * a[i] + b[i] * b[i] * b[i]);
    }
}
