from dataclasses import dataclass


class EventBus:
    def __init__(self):
        self.subscribers = []

    def subscribe(self, subscriber):
        self.subscribers.append(subscriber)

    def send(self, message):
        for subscriber in self.subscribers:
            subscriber.on_message(message)


class BookingService:
    def __init__(self, event_bus):
        self.event_bus = event_bus
        self.event_bus.subscribe(self)

    def on_message(self, message):
        pass

    def book(self, num_seats):
        print("Booking", num_seats, "seats")
        self.event_bus.send({"ticket_booked": num_seats})


class NotificationService:
    def __init__(self, *, event_bus):
        self.event_bus = event_bus
        event_bus.subscribe(self)

    def on_message(self, message):
        num_seats = message.get("booking_confirmed")
        if num_seats:
            print("Notify success")

        capacity_exceeded = message.get("capacity_exceeded")
        if capacity_exceeded:
            print("Notify failure")


class InventoryService:
    def __init__(self, *, event_bus, capacity):
        self.event_bus = event_bus
        event_bus.subscribe(self)
        self.capacity = capacity

    def on_message(self, message):
        num_seats = message.get("ticket_booked")
        if not num_seats:
            return
        if num_seats > self.capacity:
            print("Capacity exceeded")
            self.event_bus.send({"capacity_exceeded": True})
        else:
            self.capacity -= num_seats
            print("Capacity:", self.capacity)
            self.event_bus.send({"capacity_reduced": self.capacity})
            self.event_bus.send({"booking_confirmed": num_seats})


class TicketingService:
    def __init__(self, event_bus):
        self.event_bus = event_bus
        self.event_bus.subscribe(self)

    def on_message(self, message):
        num_seats = message.get("booking_confirmed")
        if num_seats:
            print("Printing ticket with", num_seats, "seats")
