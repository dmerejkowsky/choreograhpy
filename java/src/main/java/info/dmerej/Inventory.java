package info.dmerej;

public class Inventory implements Listener {
    private final MessageBus bus;
    private int capacity;


    public Inventory(MessageBus bus, int capacity) {
        this.capacity = capacity;
        this.bus = bus;
        this.bus.subscribe(this);
    }

    public void decrementCapacity(int numberOfSeats) {
        if (numberOfSeats > capacity) {
            System.out.println("Number of seats requested greater than capacity");
            var event = new Event("capacity_exceeded", capacity);
            this.bus.send(event);
            return;
        }
        capacity -= numberOfSeats;
        this.bus.send(new Event("seats_booked", numberOfSeats));
        this.bus.send(new Event("capacity_updated", capacity));
        System.out.format("Capacity at %d\n", capacity);
    }

    @Override
    public void onMessage(Event event) {
        if (event.name().equals("seat_requested")) {
            decrementCapacity(event.value());
        }
    }
}
