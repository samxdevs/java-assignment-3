// Q17: Buffered I/O - the Q15 and Q16 programs, rewritten with BufferedWriter and BufferedReader.
//
// Why buffered? FileWriter/FileReader talk to the disk for almost every character.
// A buffer collects many characters in memory first and then reads/writes them in one go,
// which is much faster. BufferedReader also gives us readLine() to read a whole line.
//
// Step 1: The user types lines, and BufferedWriter writes them to example.txt.
// Step 2: BufferedReader reads example.txt back line by line and prints it.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Q17_BufferedIO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "example.txt";

        // ---------- Step 1: write with BufferedWriter ----------
        System.out.println("Type some lines to save in " + fileName + ".");
        System.out.println("Press Enter on an empty line to finish.");

        // BufferedWriter wraps a FileWriter.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            while (true) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                writer.write(line);
                writer.newLine(); // adds a line break
            }
            System.out.println("Saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error while writing: " + e.getMessage());
        }

        // ---------- Step 2: read with BufferedReader ----------
        System.out.println("\n----- Reading " + fileName + " -----");

        // BufferedReader wraps a FileReader.
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            // readLine() returns a full line, or null when the file ends.
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error while reading: " + e.getMessage());
        }

        scanner.close();
    }
}
