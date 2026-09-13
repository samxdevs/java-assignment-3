// Q5: Thread naming and priority.
// Creates three threads named "Worker-1", "Worker-2" and "Worker-3" with different priorities.
// Each thread prints messages so we can see the order they run in.
//
// NOTE: Priority is only a HINT to the operating system. A higher priority thread is
// not guaranteed to run first, so the output order can change each time you run it.

class WorkerThread extends Thread {

    WorkerThread(String name) {
        super(name); // gives the thread its name
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            // getName() and getPriority() come from the Thread class
            System.out.println(getName() + " (priority " + getPriority() + ") -> message " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
            }
        }
        System.out.println(getName() + " finished.");
    }
}

public class Q05_NamingAndPriority {
    public static void main(String[] args) {
        WorkerThread worker1 = new WorkerThread("Worker-1");
        WorkerThread worker2 = new WorkerThread("Worker-2");
        WorkerThread worker3 = new WorkerThread("Worker-3");

        // Priorities go from 1 (MIN_PRIORITY) to 10 (MAX_PRIORITY). The default is 5 (NORM_PRIORITY).
        worker1.setPriority(Thread.MIN_PRIORITY);  // 1
        worker2.setPriority(Thread.NORM_PRIORITY); // 5
        worker3.setPriority(Thread.MAX_PRIORITY);  // 10

        System.out.println(worker1.getName() + " priority = " + worker1.getPriority());
        System.out.println(worker2.getName() + " priority = " + worker2.getPriority());
        System.out.println(worker3.getName() + " priority = " + worker3.getPriority());
        System.out.println("Starting threads...\n");

        worker1.start();
        worker2.start();
        worker3.start();
    }
}
