import java.util.concurrent.*;


public class Main2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> stringFuture = executorService.submit(callableDemo);
        System.out.println("before");
        System.out.println("before");
        System.out.println("before");
        System.out.println("before");
        System.out.println("before");
        System.out.println("before");
        System.out.println("before");
        System.out.println(stringFuture.get());
        System.out.println("after");
        System.out.println("after");
        System.out.println("after");
        System.out.println("after");
        System.out.println("after");



        executorService.shutdown();
    }
}
