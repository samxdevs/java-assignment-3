// Q9 (part 2): Two threads print numbers 1 to 20 alternately using wait() and notify().
// The "Odd" thread prints 1, 3, 5, ...
// The "Even" thread prints 2, 4, 6, ...
// Each thread prints one number, wakes the other thread, and then waits for its next turn.

class NumberPrinter {
    private int number = 1;       // the next number to print (shared by both threads)
    private final int MAX = 20;

    // isOddThread = true for the odd thread, false for the even thread.
    public synchronized void printNumbers(boolean isOddThread) {
        while (number <= MAX) {
            boolean numberIsOdd = (number % 2 == 1);

            if (numberIsOdd == isOddThread) {
                // It is my turn: print, move to the next number, wake the other thread.
                System.out.println(Thread.currentThread().getName() + " : " + number);
                number++;
                notify();
            } else {
                // Not my turn: wait until the other thread notifies me.
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
        notify(); // we are finished; wake the other thread so it can finish too
    }
}

public class Q09b_OddEvenPrinter {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter(); // one shared object

        Thread oddThread = new Thread(() -> printer.printNumbers(true), "Odd");
        Thread evenThread = new Thread(() -> printer.printNumbers(false), "Even");

        oddThread.start();
        evenThread.start();
    }
}
