// Q4: Thread sleep with multiple threads (countdown timer).
// One thread counts down from 10 to 1 (1 second delay).
// Another thread prints "Tick..." every half second at the same time.

class CountdownThread extends Thread {
    @Override
    public void run() {
        for (int i = 10; i >= 1; i--) {
            System.out.println("Countdown: " + i);
            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
                System.out.println("Countdown interrupted.");
            }
        }
        System.out.println("Time's up!");
    }
}

class TickThread extends Thread {
    private Thread countdown;

    // We pass the countdown thread in, so the tick thread knows when to stop.
    TickThread(Thread countdown) {
        this.countdown = countdown;
    }

    @Override
    public void run() {
        // isAlive() is true while the countdown thread is still running.
        while (countdown.isAlive()) {
            System.out.println("   Tick...");
            try {
                Thread.sleep(500); // half a second
            } catch (InterruptedException e) {
                System.out.println("Tick interrupted.");
            }
        }
    }
}

public class Q04_CountdownTimer {
    public static void main(String[] args) {
        CountdownThread countdown = new CountdownThread();
        TickThread ticker = new TickThread(countdown);

        countdown.start();
        ticker.start();
    }
}
