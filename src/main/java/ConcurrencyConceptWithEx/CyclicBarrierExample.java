package ConcurrencyConceptWithEx;

import java.util.concurrent.*;

public class CyclicBarrierExample {

    static CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("All joined. Let's start the call!");
    });

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            int empId = i;

            executor.submit(() -> {
                System.out.println("👨‍💼 Employee " + empId + " is joining...");

                try {
                    Thread.sleep(2000 + empId * 1000); // simulate delay
                    System.out.println("Employee " + empId + " reached.");
                    barrier.await(); // wait at barrier
                    System.out.println("📞 Employee " + empId + " is now in the call.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
