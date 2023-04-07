package info.dmerej;

public class Application {
    public static void main(String[] args) {
        var booking = new Booking();
        Inventory inventory = new Inventory(10);
        Ticketing ticketing = new Ticketing();
        Notification notification = new Notification();
        Orchestrator orchestrator = new Orchestrator(booking, inventory, ticketing, notification);
        orchestrator.run(3);
        orchestrator.run(8);
    }
}
