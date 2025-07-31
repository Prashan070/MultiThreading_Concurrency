package ThreadCre.DifferentWayThreadCreation;


import java.util.concurrent.*;

public class ExecutorExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "result from the background thread";
        };


        Future<String> future = executorService.submit(task);

        System.out.println("Main thread doing something else...");

        String result = future.get();

        System.out.println("Received: " + result);


        executorService.shutdown();
        System.out.println("Main method complete");

    }
}
