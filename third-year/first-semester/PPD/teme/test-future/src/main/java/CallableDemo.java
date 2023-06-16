import java.util.concurrent.Callable;

public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(7000);
        return "hello";
    }
}
