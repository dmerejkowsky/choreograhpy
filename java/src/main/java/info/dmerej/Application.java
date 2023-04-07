package info.dmerej;

public class Application {
    public static void main(String[] args) {
        var bus = new MessageBus();

        var booking = new Booking(bus);
        var inventory = new Inventory(bus, 10);
        var ticketing = new Ticketing(bus);
        var notification = new Notification(bus);

        booking.book(3);
        booking.book(8);
    }
}
