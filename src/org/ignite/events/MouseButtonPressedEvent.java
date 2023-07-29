package org.ignite.events;

public class MouseButtonPressedEvent extends MouseButtonEvent {

    public MouseButtonPressedEvent(int mouseButtonCode) {
        super(mouseButtonCode);
    }

    @Override
    public int getType() {
        return EventType.MouseButtonPressed;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "MouseButtonPressedEvent: " + super.getMouseButtonCode();
    }

}
