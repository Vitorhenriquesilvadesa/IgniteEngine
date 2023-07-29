package org.ignite.events;

public abstract class MouseButtonEvent extends Event {

    private int mouseButtonCode;

    public MouseButtonEvent(int mouseButtonCode) {
        this.mouseButtonCode = mouseButtonCode;
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.EventCategoryMouse | EventCategory.EventCategoryInput
                | EventCategory.EventCategoryMouseButton;
    }

    public int getMouseButtonCode() {
        return mouseButtonCode;
    }
}
