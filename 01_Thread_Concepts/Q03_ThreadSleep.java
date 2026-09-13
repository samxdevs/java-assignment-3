// Q3: Thread sleep method.
// Thread 1 prints "Thread 1" every 1 second.
// Thread 2 prints "Thread 2" every 2 seconds.
// Both run at the same time. Each thread prints a fixed number of times so the program ends.

class MessagePrinter extends Thread {
    private String message;
    private int delayInMillis;
    private int times;

    MessagePrinter(String message, int delayInMillis, int times) {
        this.message = message;
        this.delayInMillis = delayInMillis;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 1; i <= times; i++) {
            System.out.println(message);
            try {
                Thread.sleep(delayInMillis); // sleep() pauses only THIS thread, not the other one
            } catch (InterruptedException e) {
                System.out.println(message + " was interrupted.");
            }
        }
    }
}

public class Q03_ThreadSleep {
    public static void main(String[] args) {
        // Both threads run for about 10 seconds in total.
        MessagePrinter thread1 = new MessagePrinter("Thread 1", 1000, 10); // every 1 second, 10 times
        MessagePrinter thread2 = new MessagePrinter("Thread 2", 2000, 5);  // every 2 seconds, 5 times

        thread1.start();
        thread2.start();
    }
}
