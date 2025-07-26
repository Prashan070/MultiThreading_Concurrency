package ThreadCre;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThroughExector {
    public static void main(String[] args) {

        ExecutorService ex = Executors.newScheduledThreadPool(2);

        Runnable run = () -> {
            for(int i=0; i<10;i++) {
                System.out.println("Task Executed by " + Thread.currentThread().getName());
            }
        };

       /* Thread t1= new Thread(run);
        t1.start();
        Thread t2= new Thread(run);
        t2.start();*/

        ex.execute(run);
        ex.submit(run);

        ex.shutdown();


    }
}
