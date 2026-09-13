// Q13: Reading a file using a byte stream (FileInputStream).
// The user types a file name, and the program prints the file's contents.
// Try it with "sample.txt", which is in this folder.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Q13_ReadFileByteStream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name to read (e.g. sample.txt): ");
        String fileName = scanner.nextLine();

        // try-with-resources: the stream is closed automatically at the end.
        try (FileInputStream fis = new FileInputStream(fileName)) {
            System.out.println("\n----- File contents -----");

            int byteValue;
            // read() returns one byte at a time, or -1 when the file ends.
            while ((byteValue = fis.read()) != -1) {
                System.out.print((char) byteValue); // turn the byte into a character
            }

            System.out.println("\n----- End of file -----");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.out.println("Check the name, and run the program from the folder that has the file.");
        } catch (IOException e) {
            System.out.println("Error while reading the file: " + e.getMessage());
        }

        scanner.close();
    }
}
