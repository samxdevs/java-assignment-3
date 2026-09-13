// Q22: RandomAccessFile - read and write at specific positions in a file.
// 1. Write "Hello World! Welcome to Java." at the beginning of the file.
// 2. Jump to position 6 and overwrite "World" with "Earth".
// 3. Jump to different positions and read from there.
//
// Positions start at 0, just like array indexes:
//   H e l l o   W o r l d !
//   0 1 2 3 4 5 6 7 8 9 ...

import java.io.IOException;
import java.io.RandomAccessFile;

public class Q22_RandomAccessFileDemo {
    public static void main(String[] args) {
        String fileName = "random_access.txt";

        // "rw" = open the file for reading AND writing (it is created if missing).
        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            file.setLength(0); // clear old content so every run starts fresh

            // ---------- 1. Write at the beginning ----------
            file.seek(0); // move the file pointer to position 0
            file.writeBytes("Hello World! Welcome to Java.");
            System.out.println("After first write : " + readWholeFile(file));

            // ---------- 2. Overwrite part of it ----------
            file.seek(6); // "World" starts at position 6
            file.writeBytes("Earth");
            System.out.println("After overwrite   : " + readWholeFile(file));

            // ---------- 3. Read from a specific position ----------
            file.seek(13); // "Welcome" starts at position 13
            byte[] word = new byte[7];
            file.read(word);
            System.out.println("7 bytes from pos 13: " + new String(word));
            System.out.println("File pointer is now at position " + file.getFilePointer());

            // ---------- 4. Add text at the end ----------
            file.seek(file.length()); // jump to the end of the file
            file.writeBytes(" Have fun!");
            System.out.println("After adding at end: " + readWholeFile(file));
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    // Reads the whole file from position 0 and returns it as a String.
    static String readWholeFile(RandomAccessFile file) throws IOException {
        file.seek(0);
        byte[] data = new byte[(int) file.length()];
        file.readFully(data);
        return new String(data);
    }
}
