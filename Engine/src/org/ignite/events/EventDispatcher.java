package org.ignite.events;

public class EventDispatcher {

    private Event event;

    public EventDispatcher(Event event) {
        this.event = event;
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> boolean dispatch(int eventType, EventFn<T> func) {
        if (this.event.getType() == eventType) {
            this.event.setHandled(this.event.isHandled() | func.apply((T) this.event));
            return true;
        }
        return false;
    }

    public String stringIn() {
        return this.event.toString();
    }
}
