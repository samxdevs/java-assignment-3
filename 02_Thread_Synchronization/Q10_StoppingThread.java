// Q10: Stopping a thread gracefully.
// A thread simulates a file download by printing "Downloading chunk X".
// The loop keeps going only while the flag "running" is true.
// When the user presses Enter, main sets the flag to false and the download stops cleanly.
// (Never use the old Thread.stop() method. It is unsafe and removed from new Java versions.)

import java.util.Scanner;

class DownloadTask extends Thread {
    // "volatile" makes sure the download thread sees the new value as soon as main changes it.
    private volatile boolean running = true;

    public void stopDownload() {
        running = false;
    }

    @Override
    public void run() {
        int totalChunks = 20;
        int chunk = 1;

        while (running && chunk <= totalChunks) {
            System.out.println("Downloading chunk " + chunk + " of " + totalChunks);
            chunk++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }

        if (chunk > totalChunks) {
            System.out.println("Download complete! (Press Enter to exit)");
        } else {
            System.out.println("Download stopped by user after " + (chunk - 1) + " chunk(s).");
        }
    }
}

public class Q10_StoppingThread {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        DownloadTask download = new DownloadTask();
        System.out.println("Download started. Press Enter at any time to stop it.\n");
        download.start();

        scanner.nextLine();       // main waits here until the user presses Enter
        download.stopDownload();  // set the flag to false
        download.join();          // wait for the download thread to finish its current step

        System.out.println("Program finished.");
        scanner.close();
    }
}
