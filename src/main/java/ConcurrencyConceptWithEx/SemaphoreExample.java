package ConcurrencyConceptWithEx;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    static Semaphore parkingLot = new Semaphore(3);


    public static void main(String[] args) {


        for (int i = 1; i <= 6; i++) {

            final int car = i;
            Thread t1 = new Thread(() -> {
                try {
                    System.out.println("Car " + car + " trying to park...");
                    parkingLot.acquire(); // get a permit
                    System.out.println("Car " + car + " parked.");
                    Thread.sleep(2000); // simulate time parked
                    System.out.println("Car " + car + " leaving...");
                    parkingLot.release(); // release the permit
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            t1.start();

        }


    }
}
