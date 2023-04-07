package info.dmerej;

public class Orchestrator {
    private final Booking booking;
    private final Inventory inventory;
    private final Ticketing ticketing;
    private final Notification notification;

    public Orchestrator(Booking booking, Inventory inventory, Ticketing ticketing, Notification notification) {
        this.booking = booking;
        this.inventory = inventory;
        this.ticketing = ticketing;
        this.notification = notification;
    }

    public void run(int numberOfSeats) {
        booking.book(numberOfSeats);
        boolean ok = inventory.tryDecrementCapacity(numberOfSeats);
        if (!ok) {
            notification.send("Booking failed, not enough seats :(");
            return;
        }
        ticketing.printTicket(numberOfSeats);
    }
}
