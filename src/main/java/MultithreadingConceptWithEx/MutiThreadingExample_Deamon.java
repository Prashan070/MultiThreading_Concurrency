package MultithreadingConceptWithEx;


public class MutiThreadingExample_Deamon {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }

        });

        Thread t2 = new Thread(()->{
            System.out.println("thread two");
        });


        t1.setDaemon(true);

        t1.start();;
        t2.start();

        System.out.println("Main thread completed");

    }


}
