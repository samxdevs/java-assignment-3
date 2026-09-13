// Q8: Deadlock example with Dining Philosophers.
// Two philosophers sit at a table with two chopsticks between them.
// Each philosopher needs BOTH chopsticks to eat.
//
// Philosopher 1 picks up Chopstick-1, then waits for Chopstick-2.
// Philosopher 2 picks up Chopstick-2, then waits for Chopstick-1.
// Each one is waiting for the chopstick the other is holding -> nobody can eat -> DEADLOCK.

class Philosopher extends Thread {
    private String firstChopstick;
    private String secondChopstick;

    Philosopher(String name, String firstChopstick, String secondChopstick) {
        super(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    @Override
    public void run() {
        // A synchronized block "locks" an object. Here the chopstick objects are the locks.
        synchronized (firstChopstick) {
            System.out.println(getName() + " picked up " + firstChopstick);

            try {
                Thread.sleep(500); // give the other philosopher time to grab the other chopstick
            } catch (InterruptedException e) {
                return;
            }

            System.out.println(getName() + " is waiting for " + secondChopstick + "...");
            synchronized (secondChopstick) {
                // We never reach this line, because of the deadlock.
                System.out.println(getName() + " is eating.");
            }
        }
    }
}

public class Q08_DiningPhilosophersDeadlock {
    public static void main(String[] args) throws InterruptedException {
        // new String(...) makes two separate objects to use as locks.
        String chopstick1 = new String("Chopstick-1");
        String chopstick2 = new String("Chopstick-2");

        // They pick up the chopsticks in OPPOSITE order. This is what causes the deadlock.
        Philosopher p1 = new Philosopher("Philosopher-1", chopstick1, chopstick2);
        Philosopher p2 = new Philosopher("Philosopher-2", chopstick2, chopstick1);

        // Daemon threads, so the program can still exit after we show the deadlock.
        p1.setDaemon(true);
        p2.setDaemon(true);

        p1.start();
        p2.start();

        // Wait 3 seconds, then check whether the philosophers are stuck.
        Thread.sleep(3000);

        if (p1.getState() == Thread.State.BLOCKED && p2.getState() == Thread.State.BLOCKED) {
            System.out.println("\nDEADLOCK! Both philosophers are BLOCKED, each waiting for the other's chopstick.");
            System.out.println("Fix idea: make everyone pick up the chopsticks in the SAME order.");
        } else {
            System.out.println("\nNo deadlock this time.");
        }
    }
}
