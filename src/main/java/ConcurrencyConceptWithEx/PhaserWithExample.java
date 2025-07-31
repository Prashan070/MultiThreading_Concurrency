package ConcurrencyConceptWithEx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserWithExample {
    public static void main(String[] args) {
        ExecutorService executor= Executors.newFixedThreadPool(3);

        Phaser phaser = new Phaser(1);

        for(int i =1 ; i<=3;  i++) {

            final int friendId=i;

            phaser.register();

            executor.submit(() -> {
                System.out.println("🚶 Friend " + friendId + " reached Stop 1");
                phaser.arriveAndAwaitAdvance(); // wait at stop 1

                System.out.println("🚶 Friend " + friendId + " reached Stop 2");
                phaser.arriveAndAwaitAdvance(); // wait at stop 2

                System.out.println("🏁 Friend " + friendId + " finished the trek!");
                phaser.arriveAndDeregister();
            });

        }
        phaser.arriveAndAwaitAdvance(); // Wait at Stop 1
        System.out.println("🧑 Main also reached Stop 1");

        phaser.arriveAndAwaitAdvance(); // Wait at Stop 2
        System.out.println("🧑 Main also reached Stop 2");

        phaser.arriveAndDeregister(); // Main done

        executor.shutdown();

    }
}
