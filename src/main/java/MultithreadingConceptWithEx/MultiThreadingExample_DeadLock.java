package MultithreadingConceptWithEx;

public class MultiThreadingExample_DeadLock {

    static final Object lock1 = new Object();
    static final Object lock2 = new Object();


    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {

                System.out.println("Thread 1: Holding lock1...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                System.out.println("Thread 1: Waiting for lock2...");

                synchronized (lock2) {
                    System.out.println("Thread 1: Got lock2 too!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock2...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                System.out.println("Thread 2: Waiting for lock1...");
                synchronized (lock1) {
                    System.out.println("Thread 2: Got lock1 too!");
                }
            }

        });


        t1.start();
        t2.start();

    }
}
