package info.dmerej;

public class Notification implements Listener {
    private final MessageBus bus;

    public Notification(MessageBus bus) {
        this.bus = bus;
        this.bus.subscribe(this);
    }

    public void send(String message) {
        System.out.println(message);
    }

    @Override
    public void onMessage(Event event) {
        if (event.name().equals("capacity_exceeded")) {
            send("Booking failed, not enough seats :(");
        }
    }
}
