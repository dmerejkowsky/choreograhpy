package info.dmerej;

public class Inventory {
    private int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
    }

    public boolean tryDecrementCapacity(int numberOfSeats) {
        if (numberOfSeats > capacity) {
            System.out.println("Number of seats requested greater than capacity");
            return false;
        }
        capacity -= numberOfSeats;
        System.out.format("Capacity at %d\n", capacity);
        return true;
    }
}
