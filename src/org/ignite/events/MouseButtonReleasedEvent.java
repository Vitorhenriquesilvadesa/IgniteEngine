package org.ignite.events;

public class MouseButtonReleasedEvent extends MouseButtonEvent {

    public MouseButtonReleasedEvent(int mouseButtonCode) {
        super(mouseButtonCode);
    }

    @Override
    public int getType() {
        return EventType.MouseButtonReleased;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "MouseButtonReleasedEvent: " + super.getMouseButtonCode();

    }

}
