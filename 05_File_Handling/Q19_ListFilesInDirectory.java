// Q19: Listing all files in a directory chosen by the user.
// Handles problems like a wrong path, a path that is a file, or no permission.

import java.io.File;
import java.util.Scanner;

public class Q19_ListFilesInDirectory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a directory path (use . for the current folder): ");
        String path = scanner.nextLine();

        File directory = new File(path);

        try {
            if (!directory.exists()) {
                System.out.println("Error: the path does not exist -> " + path);
            } else if (!directory.isDirectory()) {
                System.out.println("Error: this is a file, not a directory -> " + path);
            } else {
                // listFiles() returns everything inside the directory.
                // It returns null if the directory can't be read (for example, no permission).
                File[] files = directory.listFiles();

                if (files == null) {
                    System.out.println("Error: could not read the directory (check permissions).");
                } else if (files.length == 0) {
                    System.out.println("The directory is empty.");
                } else {
                    System.out.println("\nContents of " + directory.getAbsolutePath() + ":");
                    for (File f : files) {
                        if (f.isDirectory()) {
                            System.out.println("  [DIR]  " + f.getName());
                        } else {
                            System.out.println("  [FILE] " + f.getName() + " (" + f.length() + " bytes)");
                        }
                    }
                    System.out.println("\nTotal items: " + files.length);
                }
            }
        } catch (SecurityException e) {
            // Thrown if a security manager blocks access to the directory.
            System.out.println("Access denied: " + e.getMessage());
        }

        scanner.close();
    }
}
