// Q24: Using the java.util package - Date and Calendar.
// Shows the current date and time in a few different ways.

import java.util.Calendar;
import java.util.Date;

public class Q24_DateAndCalendar {
    public static void main(String[] args) {
        // ---------- Date ----------
        // new Date() holds the current date and time.
        Date now = new Date();
        System.out.println("Using Date:");
        System.out.println("  Current date and time: " + now);

        // ---------- Calendar ----------
        // Calendar.getInstance() gives a calendar set to the current date and time.
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // months start at 0 (January = 0), so add 1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24-hour format
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String dayName = dayNames[calendar.get(Calendar.DAY_OF_WEEK) - 1]; // Sunday = 1

        System.out.println("\nUsing Calendar:");
        System.out.println("  Date : " + day + "/" + month + "/" + year);
        // %02d prints a number with at least 2 digits, e.g. 7 -> 07
        System.out.printf("  Time : %02d:%02d:%02d%n", hour, minute, second);
        System.out.println("  Day  : " + dayName);

        // Calendar can also do date maths, e.g. the date 7 days from today.
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println("\nDate after 7 days: " + calendar.getTime());
    }
}
