// Q9 (part 1): Producer-Consumer using wait() and notify().
// The producer puts a number into a shared box. The consumer takes it out.
// The box holds only ONE number at a time:
//   - If the box is full, the producer must wait().
//   - If the box is empty, the consumer must wait().
// After changing the box, each thread calls notify() to wake the other one.

class SharedBox {
    private int data;
    private boolean hasData = false;

    // wait() and notify() can only be called inside synchronized code.
    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) {
            wait(); // box is full -> release the lock and sleep until notified
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // wake up the consumer
    }

    public synchronized int consume() throws InterruptedException {
        while (!hasData) {
            wait(); // box is empty -> release the lock and sleep until notified
        }
        hasData = false;
        System.out.println("   Consumed: " + data);
        notify(); // wake up the producer
        return data;
    }
}

class Producer extends Thread {
    private SharedBox box;

    Producer(SharedBox box) {
        this.box = box;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                box.produce(i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted.");
        }
    }
}

class Consumer extends Thread {
    private SharedBox box;

    Consumer(SharedBox box) {
        this.box = box;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                box.consume();
                Thread.sleep(800); // consumer is slower, so the producer has to wait sometimes
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted.");
        }
    }
}

public class Q09a_ProducerConsumer {
    public static void main(String[] args) {
        SharedBox box = new SharedBox(); // both threads share this one box

        new Producer(box).start();
        new Consumer(box).start();
    }
}
