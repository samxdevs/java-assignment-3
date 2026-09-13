// Q18: File existence check.
// The user types a file name. If the file exists, we show some details about it.
// If it doesn't exist, we create it using the File class.

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Q18_FileExistenceCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name to check (e.g. notes.txt): ");
        String fileName = scanner.nextLine();

        // A File object is just a path. It does not create anything by itself.
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("The file already exists.");
            System.out.println("Full path : " + file.getAbsolutePath());
            System.out.println("Size      : " + file.length() + " bytes");
        } else {
            System.out.println("The file does not exist. Creating it...");
            try {
                // createNewFile() creates an empty file. It returns true if it worked.
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Could not create the file.");
                }
            } catch (IOException e) {
                // For example, when the folder in the path doesn't exist.
                System.out.println("Error creating the file: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
