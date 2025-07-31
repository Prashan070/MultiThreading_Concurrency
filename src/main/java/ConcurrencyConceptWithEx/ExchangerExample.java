package ConcurrencyConceptWithEx;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        Thread friend1 = new Thread(() -> {
            String letter = "Letter from Friend 1";

            try {
                System.out.println("Friend 1 is at the post office with: " + letter);
                String received = exchanger.exchange(letter);
                System.out.println("Friend 1 received: " + received);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread friend2 = new Thread(() -> {
            String letter = "Letter from Friend 2";

            try {
                System.out.println("Friend 2 is at the post office with: " + letter);
                Thread.sleep(5000); // simulate delay
                String received = exchanger.exchange(letter);
                System.out.println("Friend 2 received: " + received);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        friend1.start();
        friend2.start();
    }
}
