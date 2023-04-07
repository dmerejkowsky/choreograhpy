package info.dmerej;

public class Booking {
    private final Inventory inventory;
    private final Ticketing ticketing;

    public Booking() {
        inventory = new Inventory(10);
        ticketing = new Ticketing();
    }

    public void book(int numberOfSeats) {
        System.out.println("Booking requested");
        boolean ok = inventory.tryDecrementCapacity(numberOfSeats);
        if (!ok) {
            return;
        }
        ticketing.printTicket(numberOfSeats);
    }
}
