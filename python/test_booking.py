from booking import (
    BookingService,
    InventoryService,
    TicketingService,
    NotificationService,
    EventBus,
)


def test_happy():
    event_bus = EventBus()
    inventory = InventoryService(event_bus=event_bus, capacity=10)
    ticketing = TicketingService(event_bus=event_bus)
    booking = BookingService(event_bus=event_bus)
    # notification_service = NotificationService(event_bus=event_bus)

    booking.book(num_seats=2)

    assert inventory.capacity == 8


def test_on_full_capacity():
    event_bus = EventBus()
    inventory = InventoryService(event_bus=event_bus, capacity=2)
    ticketing = TicketingService(event_bus=event_bus)
    booking = BookingService(event_bus=event_bus)
    # notification_service = NotificationService(event_bus=event_bus)

    booking.book(num_seats=3)

    assert inventory.capacity == 2
