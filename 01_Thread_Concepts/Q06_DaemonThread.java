// Q6: Daemon threads.
// A daemon thread prints "Auto-Save in progress..." every 3 seconds, forever.
// The main thread does a (simulated) file processing task.
// When main finishes, the JVM exits and the daemon thread is stopped automatically.

class AutoSaveThread extends Thread {
    @Override
    public void run() {
        // This loop never ends on its own. It only stops because it is a daemon thread.
        while (true) {
            System.out.println("   [Auto-Save in progress...]");
            try {
                Thread.sleep(3000); // every 3 seconds
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class Q06_DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        AutoSaveThread autoSave = new AutoSaveThread();

        // setDaemon(true) must be called BEFORE start().
        autoSave.setDaemon(true);
        autoSave.start();

        // Main thread: pretend to process a file line by line.
        String[] fileLines = {
            "Line 1: Name, Age, City",
            "Line 2: Asha, 21, Kolkata",
            "Line 3: Rahul, 22, Delhi",
            "Line 4: Priya, 20, Mumbai",
            "Line 5: Arjun, 23, Chennai"
        };

        System.out.println("Main thread: starting file processing...");
        for (String line : fileLines) {
            System.out.println("Processing -> " + line);
            Thread.sleep(2000); // pretend each line takes 2 seconds to process
        }
        System.out.println("Main thread: file processing complete.");
        System.out.println("Main is ending, so the daemon auto-save thread stops too.");
    }
}
