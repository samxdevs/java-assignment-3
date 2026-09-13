// Q16: Writing to a file using a character stream (FileWriter).
// The user types a sentence, and the program writes it to example.txt.

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Q16_WriteFileCharacterStream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text to write into example.txt: ");
        String text = scanner.nextLine();

        // FileWriter writes characters directly, so no need to convert to bytes.
        try (FileWriter writer = new FileWriter("example.txt")) {
            writer.write(text);
            System.out.println("Text written to example.txt");
        } catch (IOException e) {
            System.out.println("Error while writing the file: " + e.getMessage());
        }

        scanner.close();
    }
}
