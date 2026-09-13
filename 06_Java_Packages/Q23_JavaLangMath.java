// Q23: Using the java.lang package - Math.random(), Math.abs() and Math.pow().
// java.lang is imported automatically, so we don't need an import line for Math.

import java.util.Scanner;

public class Q23_JavaLangMath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ---------- Math.random() ----------
        // Math.random() gives a decimal number from 0.0 up to (but not including) 1.0.
        double randomDecimal = Math.random();
        // Multiply by 100 and add 1 to get a whole number from 1 to 100.
        int randomNumber = (int) (Math.random() * 100) + 1;
        System.out.println("Math.random()            = " + randomDecimal);
        System.out.println("Random number (1 to 100) = " + randomNumber);

        // ---------- Math.abs() ----------
        // Math.abs() removes the minus sign: abs(-7.5) = 7.5
        System.out.print("\nEnter a number (try a negative one): ");
        double number = scanner.nextDouble();
        System.out.println("Math.abs(" + number + ") = " + Math.abs(number));

        // ---------- Math.pow() ----------
        // Math.pow(base, exponent) = base raised to the power of exponent. pow(2, 3) = 8
        System.out.print("\nEnter the base: ");
        double base = scanner.nextDouble();
        System.out.print("Enter the exponent: ");
        double exponent = scanner.nextDouble();
        System.out.println("Math.pow(" + base + ", " + exponent + ") = " + Math.pow(base, exponent));

        scanner.close();
    }
}
