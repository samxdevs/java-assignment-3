// Q14: Writing to a file using a byte stream (FileOutputStream).
// Writes the string "Java I/O Streams Example" to a file named output.txt.

import java.io.FileOutputStream;
import java.io.IOException;

public class Q14_WriteFileByteStream {
    public static void main(String[] args) {
        String text = "Java I/O Streams Example";

        // If output.txt doesn't exist, it is created. If it exists, it is overwritten.
        try (FileOutputStream fos = new FileOutputStream("output.txt")) {
            // A byte stream writes bytes, so we turn the String into a byte array first.
            byte[] bytes = text.getBytes();
            fos.write(bytes);

            System.out.println("Wrote \"" + text + "\" to output.txt");
        } catch (IOException e) {
            System.out.println("Error while writing the file: " + e.getMessage());
        }
    }
}
