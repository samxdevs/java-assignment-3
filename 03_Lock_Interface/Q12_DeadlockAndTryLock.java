// Q12: Deadlock with two locks, then fixing it with tryLock() and a timeout.
//
// PART 1 (deadlock):
//   Thread-A locks lock1, then wants lock2.
//   Thread-B locks lock2, then wants lock1.
//   lock() waits forever, so both threads get stuck -> DEADLOCK.
//
// PART 2 (fix):
//   tryLock(1, SECONDS) waits at most 1 second. If the thread can't get the second lock,
//   it releases the first lock, waits a little, and tries again. Nobody is stuck forever.

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Q12_DeadlockAndTryLock {

    // ---------------- PART 1: causes a deadlock ----------------
    static void lockInOrder(String name, Lock first, Lock second) {
        first.lock();
        try {
            System.out.println(name + " got its first lock");
            sleep(100); // give the other thread time to take the other lock
            System.out.println(name + " is waiting for its second lock...");
            second.lock(); // waits forever, because the other thread holds it
            try {
                System.out.println(name + " got both locks (this never prints)");
            } finally {
                second.unlock();
            }
        } finally {
            first.unlock();
        }
    }

    // ---------------- PART 2: fixed with tryLock() ----------------
    static void tryLockInOrder(String name, Lock first, Lock second) {
        boolean workDone = false;

        while (!workDone) {
            try {
                if (first.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(name + " got its first lock");
                        sleep(100);

                        if (second.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                System.out.println(name + " got BOTH locks and finished its work.");
                                workDone = true;
                            } finally {
                                second.unlock();
                            }
                        } else {
                            System.out.println(name + " could not get its second lock -> releasing and retrying");
                        }
                    } finally {
                        first.unlock(); // release the first lock so the other thread can continue
                    }
                }
            } catch (InterruptedException e) {
                return;
            }

            if (!workDone) {
                // Wait a random short time so both threads don't retry at exactly the same moment.
                sleep((long) (Math.random() * 500));
            }
        }
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // ---------- PART 1 ----------
        System.out.println("===== PART 1: Deadlock using lock() =====");
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        // Opposite order: A takes lock1 then lock2, B takes lock2 then lock1.
        Thread a = new Thread(() -> lockInOrder("Thread-A", lock1, lock2));
        Thread b = new Thread(() -> lockInOrder("Thread-B", lock2, lock1));

        // Daemon threads, so the stuck threads don't stop the program from ending.
        a.setDaemon(true);
        b.setDaemon(true);
        a.start();
        b.start();

        Thread.sleep(2000);
        if (a.isAlive() && b.isAlive()) {
            System.out.println("DEADLOCK! Both threads are still stuck after 2 seconds.\n");
        }

        // ---------- PART 2 ----------
        System.out.println("===== PART 2: Fixed using tryLock() with timeout =====");
        // New locks, because the stuck threads from part 1 still hold the old ones.
        Lock newLock1 = new ReentrantLock();
        Lock newLock2 = new ReentrantLock();

        Thread c = new Thread(() -> tryLockInOrder("Thread-C", newLock1, newLock2));
        Thread d = new Thread(() -> tryLockInOrder("Thread-D", newLock2, newLock1));
        c.start();
        d.start();
        c.join();
        d.join();

        System.out.println("No deadlock: both threads finished.");
    }
}
