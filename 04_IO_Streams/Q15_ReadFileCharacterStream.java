// Q15: Reading a file using a character stream (FileReader).
// The user types a file name, and the program prints the file's contents.
// Byte streams read bytes; character streams read characters, which is better for text.

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Q15_ReadFileCharacterStream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name to read (e.g. sample.txt): ");
        String fileName = scanner.nextLine();

        try (FileReader reader = new FileReader(fileName)) {
            System.out.println("\n----- File contents -----");

            int ch;
            // read() returns one character at a time, or -1 when the file ends.
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
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
