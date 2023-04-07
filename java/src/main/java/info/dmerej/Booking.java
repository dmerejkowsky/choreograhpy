package info.dmerej;

public class Booking implements Listener {
    private final MessageBus bus;

    public Booking(MessageBus bus) {
        this.bus = bus;
        this.bus.subscribe(this);
    }

    public void book(int numberOfSeats) {
        System.out.println("Booking requested");
        var seatRequested = new Event("seat_requested", numberOfSeats);
        bus.send(seatRequested);
    }

    @Override
    public void onMessage(Event event) {

    }
}
