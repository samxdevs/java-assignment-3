// Q1: Create a thread by extending the Thread class.
// The thread prints even numbers from 2 to 20 with a 500 ms delay between each number.

// Step 1: Make a class that extends Thread.
class EvenNumberThread extends Thread {

    // Step 2: Override run(). Whatever is inside run() is what the thread does.
    @Override
    public void run() {
        for (int i = 2; i <= 20; i += 2) {
            System.out.println("Even number: " + i);
            try {
                Thread.sleep(500); // pause this thread for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
        }
        System.out.println("Done printing even numbers.");
    }
}

public class Q01_EvenNumbersThread {
    public static void main(String[] args) {
        // Step 3: Create an object of our thread class.
        EvenNumberThread evenThread = new EvenNumberThread();

        // Step 4: Call start(), NOT run().
        // start() creates a new thread, and that new thread calls run().
        evenThread.start();
    }
}
