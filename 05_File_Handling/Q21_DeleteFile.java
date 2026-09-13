// Q21: Deleting a file using the File class.
// The user types a file name. The program asks for confirmation before deleting it.
// Tip: create a test file first with Q18 (e.g. notes.txt), then delete it here.

import java.io.File;
import java.util.Scanner;

public class Q21_DeleteFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name to delete: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
        } else if (file.isDirectory()) {
            System.out.println("This is a directory, not a file. This program only deletes files.");
        } else {
            System.out.print("Are you sure you want to delete " + file.getName() + "? (y/n): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("y")) {
                // delete() returns true if the file was deleted.
                if (file.delete()) {
                    System.out.println("File deleted: " + fileName);
                } else {
                    System.out.println("Could not delete the file (it may be open or protected).");
                }
            } else {
                System.out.println("Delete cancelled.");
            }
        }

        scanner.close();
    }
}
