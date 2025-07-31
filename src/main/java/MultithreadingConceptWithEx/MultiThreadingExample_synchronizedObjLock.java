package MultithreadingConceptWithEx;

class Printer {
    public static synchronized void display(int num) {
        System.out.println("Inside syncronized blOCK " + num);
    }
}

public class MultiThreadingExample_synchronizedObjLock {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                Printer.display(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 6; i < 10; i++) {
                Printer.display(i);
            }
        });

        t1.start();
        t2.start();

    }
}
