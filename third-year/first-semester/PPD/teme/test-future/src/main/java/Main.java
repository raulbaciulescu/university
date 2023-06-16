import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    /**
     * Executor service asigneaza task ul la thread pool
     *
     *
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<String> myFuture = Executors.newFixedThreadPool(1)
                .submit(() -> {
                    Thread.sleep(5000);
                    return "Hello";
                });

        while (!myFuture.isDone()) {
            System.out.println("Waiting...");
            Thread.sleep(1000);
        }

        System.out.println(myFuture.get());
    }
}
