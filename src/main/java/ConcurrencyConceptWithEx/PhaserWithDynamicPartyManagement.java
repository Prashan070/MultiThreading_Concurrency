package ConcurrencyConceptWithEx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserWithDynamicPartyManagement {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Phaser phaser = new Phaser(1);

        // Friends 1–3
        for (int i = 1; i <= 3; i++) {

            final int friendId = i;

            phaser.register();

            executor.submit(() -> {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }



                System.out.println("🚶 Friend " + friendId + " reached Stop 1");


                phaser.arriveAndAwaitAdvance();

                if (friendId == 2) {
                    System.out.println("❌ Friend " + friendId + " is tired and leaves");
                    phaser.arriveAndDeregister(); // Leaves early
                    return;
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("🚶 Friend " + friendId + " reached Stop 2");
                phaser.arriveAndAwaitAdvance();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("🏁 Friend " + friendId + " finished the trek!");
                phaser.arriveAndDeregister();
            });
        }

        // MAIN thread at Stop 1
        System.out.println("Main also reached stop 1");
       phaser.arriveAndAwaitAdvance();
        //System.out.println("🧑 Main also reached Stop 1");

        // Register Friend 4 BEFORE entering Stop 2
        phaser.register(); // New friend is joining Stop 2
        executor.submit(() -> {
            System.out.println("👋 New Friend 4 joined at Stop 2");
            phaser.arriveAndAwaitAdvance(); // Sync at Stop 2
            System.out.println("🏁 Friend 4 finished the trek!");
            phaser.arriveAndDeregister();
        });

        // MAIN thread at Stop 2
        phaser.arriveAndAwaitAdvance();
        System.out.println("🧑 Main also reached Stop 2");

        // MAIN thread done
        phaser.arriveAndDeregister();

        executor.shutdown();
    }
}
