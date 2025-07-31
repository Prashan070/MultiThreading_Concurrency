package MultithreadingConceptWithEx;

public class MultiThreadingExample_Join {
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            System.out.println("Inside: " + Thread.currentThread().getName());
            System.out.println("Priority: " + Thread.currentThread().getName() + Thread.currentThread().getPriority());
        });

        Thread t2 = new Thread(()->{
            System.out.println("Inside: " + Thread.currentThread().getName());
            System.out.println("Priority: " +Thread.currentThread().getName()+ Thread.currentThread().getPriority());
        });

        t1.setName("ZoroThread");
        t2.setName("LuffyThread");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();


    }
}
