// Q2: Create a thread by implementing the Runnable interface.
// The thread takes the string "MULTITHREADING" and prints its characters in reverse, one by one.

// Step 1: Make a class that implements Runnable.
class ReversePrinter implements Runnable {
    private String text;

    ReversePrinter(String text) {
        this.text = text;
    }

    // Step 2: Put the thread's work inside run().
    @Override
    public void run() {
        // Start from the last character and go back to the first one.
        for (int i = text.length() - 1; i >= 0; i--) {
            System.out.println(text.charAt(i));
            try {
                Thread.sleep(300); // small delay so we can see characters appear one by one
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
        }
    }
}

public class Q02_ReverseStringRunnable {
    public static void main(String[] args) {
        // Step 3: Create the Runnable object (the "task").
        ReversePrinter task = new ReversePrinter("MULTITHREADING");

        // Step 4: Give the task to a Thread object and start it.
        Thread thread = new Thread(task);
        thread.start();
    }
}
