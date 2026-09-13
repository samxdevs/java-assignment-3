// Q25: Regular expressions with java.util.regex - check if an email address is valid.
// The user types email addresses, and the program says whether each one is valid.
// Type "exit" to quit.

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q25_EmailValidationRegex {
    public static void main(String[] args) {
        // What this pattern means, piece by piece:
        //   ^                 start of the text
        //   [A-Za-z0-9._%+-]+ one or more letters, digits or . _ % + -   (the name part)
        //   @                 exactly one @
        //   [A-Za-z0-9.-]+    one or more letters, digits, dots or hyphens (the domain, e.g. gmail)
        //   \\.               a dot (written \\. because . alone means "any character")
        //   [A-Za-z]{2,}      at least 2 letters (e.g. com, in, org)
        //   $                 end of the text
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Pattern.compile() turns the regex text into a Pattern object (do this once).
        Pattern pattern = Pattern.compile(emailRegex);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter an email address (or type exit): ");
            String email = scanner.nextLine().trim();

            if (email.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            // A Matcher checks one piece of text against the pattern.
            Matcher matcher = pattern.matcher(email);

            // matches() is true only if the WHOLE text fits the pattern.
            if (matcher.matches()) {
                System.out.println("VALID email: " + email);
            } else {
                System.out.println("INVALID email: " + email);
            }
        }

        scanner.close();
    }
}
