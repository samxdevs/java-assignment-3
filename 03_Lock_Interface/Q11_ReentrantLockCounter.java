// Q11: Counter with ReentrantLock vs. counter without a lock.
// Several threads increase a counter at the same time.
//   - UnsafeCounter has no lock, so some increments get lost (the result is usually too small).
//   - SafeCounter uses a ReentrantLock, so the result is always correct.

import java.util.concurrent.locks.ReentrantLock;

class UnsafeCounter {
    private int count = 0;

    public void increment() {
        // count++ is really 3 steps: read count, add 1, write count.
        // Two threads can read the same old value, and one increment is lost.
        count++;
    }

    public int getCount() {
        return count;
    }
}

class SafeCounter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // only one thread can pass this point at a time
        try {
            count++;
        } finally {
            lock.unlock(); // always unlock inside finally, even if an error happens
        }
    }

    public int getCount() {
        return count;
    }
}

public class Q11_ReentrantLockCounter {
    static final int THREADS = 4;
    static final int INCREMENTS_PER_THREAD = 100000;

    // Runs the given task in several threads and waits for all of them to finish.
    static void runInThreads(Runnable task) throws InterruptedException {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int expected = THREADS * INCREMENTS_PER_THREAD;

        // 1) Without a lock
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        runInThreads(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                unsafeCounter.increment();
            }
        });

        // 2) With a ReentrantLock
        SafeCounter safeCounter = new SafeCounter();
        runInThreads(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                safeCounter.increment();
            }
        });

        System.out.println("Expected count        : " + expected);
        System.out.println("Without lock (unsafe) : " + unsafeCounter.getCount());
        System.out.println("With ReentrantLock    : " + safeCounter.getCount());
        System.out.println("\nThe unsafe result is usually wrong and changes on every run.");
        System.out.println("The locked result is always " + expected + ".");
    }
}
