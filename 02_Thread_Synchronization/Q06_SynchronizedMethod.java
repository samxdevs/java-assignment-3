// Q6: Synchronized method - ticket booking system.
// Many users (threads) try to book tickets at the same time.
// bookTicket() is synchronized, so only ONE user can book at a time.
// This stops two users from buying the same last tickets (overselling).

class TicketCounter {
    private int availableTickets;

    TicketCounter(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    // "synchronized" = only one thread can be inside this method at a time.
    // Other threads wait until the current thread leaves the method.
    public synchronized void bookTicket(String user, int ticketsWanted) {
        System.out.println(user + " is trying to book " + ticketsWanted + " ticket(s)...");

        if (ticketsWanted <= availableTickets) {
            try {
                Thread.sleep(500); // pretend payment takes some time
            } catch (InterruptedException e) {
                System.out.println(user + " was interrupted.");
            }
            availableTickets = availableTickets - ticketsWanted;
            System.out.println("  SUCCESS: " + user + " booked " + ticketsWanted
                    + " ticket(s). Tickets left: " + availableTickets);
        } else {
            System.out.println("  SORRY: " + user + " could not book. Only "
                    + availableTickets + " ticket(s) left.");
        }
    }

    public synchronized int getAvailableTickets() {
        return availableTickets;
    }
}

class BookingUser extends Thread {
    private TicketCounter counter;
    private int ticketsWanted;

    BookingUser(String name, TicketCounter counter, int ticketsWanted) {
        super(name);
        this.counter = counter;
        this.ticketsWanted = ticketsWanted;
    }

    @Override
    public void run() {
        counter.bookTicket(getName(), ticketsWanted);
    }
}

public class Q06_SynchronizedMethod {
    public static void main(String[] args) throws InterruptedException {
        // All users share the SAME counter object, with 10 tickets.
        TicketCounter counter = new TicketCounter(10);

        BookingUser[] users = {
            new BookingUser("User-1", counter, 3),
            new BookingUser("User-2", counter, 4),
            new BookingUser("User-3", counter, 2),
            new BookingUser("User-4", counter, 3),
            new BookingUser("User-5", counter, 1)
        };

        // Start all users at (almost) the same time.
        for (BookingUser user : users) {
            user.start();
        }

        // join() makes main wait until each user thread is finished.
        for (BookingUser user : users) {
            user.join();
        }

        System.out.println("\nBooking closed. Tickets left: " + counter.getAvailableTickets());
        System.out.println("Tickets never go below 0, so there is no overselling.");
    }
}
