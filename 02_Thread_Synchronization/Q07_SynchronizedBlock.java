// Q7: Synchronized block - inventory management.
// Several worker threads take items out of the same stock.
// Only the part that reads and changes the stock is inside a synchronized block.

class Inventory {
    private int stock;

    Inventory(int stock) {
        this.stock = stock;
    }

    public void removeItem(String worker) {
        // Code OUTSIDE the block can run in many threads at the same time.
        System.out.println(worker + " wants to take 1 item.");

        // Code INSIDE the block runs in only one thread at a time.
        // "this" is the lock: every thread must hold this Inventory object's lock to enter.
        synchronized (this) {
            if (stock > 0) {
                stock--;
                System.out.println("  " + worker + " took 1 item. Stock left: " + stock);
            } else {
                System.out.println("  " + worker + " found the stock EMPTY.");
            }
        }
    }

    public synchronized int getStock() {
        return stock;
    }
}

public class Q07_SynchronizedBlock {
    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(10); // product starts with 10 items

        // 4 workers, each tries to take 3 items = 12 tries, but only 10 items exist.
        Thread[] workers = new Thread[4];
        for (int i = 0; i < workers.length; i++) {
            String workerName = "Worker-" + (i + 1);

            // This lambda "() -> { ... }" is a short way to write a Runnable.
            workers[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    inventory.removeItem(workerName);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
        }

        for (Thread worker : workers) {
            worker.start();
        }
        for (Thread worker : workers) {
            worker.join(); // wait for all workers to finish
        }

        System.out.println("\nFinal stock: " + inventory.getStock() + " (never negative)");
    }
}
