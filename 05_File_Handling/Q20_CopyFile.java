// Q20: Copying a file using byte streams (FileInputStream and FileOutputStream).
// The user types the source file and the destination file.
// Try: source = sample.txt, destination = sample_copy.txt

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Q20_CopyFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the source file name: ");
        String source = scanner.nextLine();
        System.out.print("Enter the destination file name: ");
        String destination = scanner.nextLine();

        // Both streams are opened here and closed automatically at the end.
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024]; // copy 1024 bytes at a time (faster than 1 by 1)
            int bytesRead;
            int totalBytes = 0;

            // read(buffer) fills the buffer and returns how many bytes it read, or -1 at the end.
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead); // write only the bytes we actually read
                totalBytes += bytesRead;
            }

            System.out.println("Copied " + totalBytes + " bytes from " + source + " to " + destination);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while copying: " + e.getMessage());
        }

        scanner.close();
    }
}
