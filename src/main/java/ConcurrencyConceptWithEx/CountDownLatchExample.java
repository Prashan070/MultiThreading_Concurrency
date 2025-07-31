package ConcurrencyConceptWithEx;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 3; i++) {
            final int friendId = i;
            Thread t1 = new Thread(() -> {
                System.out.println("Friend " + friendId + " started  the journey");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Friend "+ friendId + " reached the ditiation");
                latch.countDown();

            });
            t1.start();
        }
        System.out.println("still waiting");
        latch.await();

        System.out.println("Lets start the trip");

    }
}
