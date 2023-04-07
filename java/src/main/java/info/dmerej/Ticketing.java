package info.dmerej;

public class Ticketing implements Listener {
    private final MessageBus bus;

    public Ticketing(MessageBus bus) {
        this.bus = bus;
        this.bus.subscribe(this);
    }

    public void printTicket(int numberOfSeats) {
        System.out.format("Printing tickets for %d seats\n", numberOfSeats);
    }

    @Override
    public void onMessage(Event event) {
        if (event.name().equals("seats_booked")) {
            int numberOfSeats = event.value();
            printTicket(numberOfSeats);
        }
    }
}
