package info.dmerej;

import java.util.ArrayList;
import java.util.List;

public class MessageBus {
    private final List<Listener> listeners = new ArrayList<Listener>();

    public void subscribe(Listener l) {
        this.listeners.add(l);
    }

    public void send(Event event) {
        for (Listener l : listeners) {
            l.onMessage(event);
        }
    }
}
