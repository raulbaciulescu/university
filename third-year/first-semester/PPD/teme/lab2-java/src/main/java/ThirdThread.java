import java.util.List;

public class ThirdThread extends Thread{
    private final int id;
    private final int start;
    private final int end;
    private List<Double> a, b, c;

    public ThirdThread(int id, int start, int end,  List<Double> a, List<Double> b, List<Double> c) {
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
            c.set(i, Math.sqrt(a.get(i) * a.get(i) * a.get(i) + b.get(i) * b.get(i) * b.get(i)));
    }
}
