package MultithreadingConceptWithEx;

/*

Producer produces data and puts it into a shared queue.
Consumer consumes data from the queue.

If the queue is full, the Producer should wait().
If the queue is empty, the Consumer should wait().

use
A simple LinkedList as a queue.
wait() and notify() to handle synchronization.
 */


import java.util.LinkedList;

class SharedQueue {
    private final LinkedList<Integer> queue = new LinkedList<>();
    private final int initalCapacity = 3;

    public synchronized void produce(int val) throws InterruptedException {
        while (queue.size() == initalCapacity) {
            System.out.println("Queue is full. Producer is waiting...");
            wait();
        }
        queue.add(val);
        System.out.println("just produce" + val);
        notify();
    }

    public synchronized int consumer() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty. Consumer is waiting...");
            wait();
        }
        int value = queue.removeFirst();
        System.out.println("Consumed" + value);
        notify();
        return value;
    }

}


public class MultiThreadingExample_Wait {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();

        Thread producer = new Thread(() -> {
            int count = 0;
            try {
                while (true) {
                    sharedQueue.produce(count++);
                    Thread.sleep(500); // Simulate time to produce
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    sharedQueue.consumer();
                    Thread.sleep(1000); // Simulate time to consume
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}