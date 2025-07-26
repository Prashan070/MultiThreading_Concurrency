import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {

        Semaphore parkingLot = new Semaphore(3);

        ExecutorService executor = Executors.newFixedThreadPool(6);

        Runnable park = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " trying to park");
                parkingLot.acquire();
                System.out.println(Thread.currentThread().getName() + " parked.");
                Thread.sleep(2000); // simulate time parked
                System.out.println(Thread.currentThread().getName() + " leaving...");
                parkingLot.release(); // release the permit

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        for (int i = 0; i <= 6; i++) {
            int carNumber = i;
            executor.submit(() -> {
                Thread.currentThread().setName("Car" + carNumber);
                park.run();
            });
        }

        executor.shutdown();  // move here!
    }
}
