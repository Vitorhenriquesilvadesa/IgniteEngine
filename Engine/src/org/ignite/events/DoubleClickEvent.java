package org.ignite.events;

public class DoubleClickEvent extends Event {

    @Override
    public int getType() {
        return EventType.DoubleClick;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.EventCategoryInput | EventCategory.EventCategoryApplication
                | EventCategory.EventCategoryMouseButton | EventCategory.EventCategoryMouse;
    }

    @Override
    public String toString() {
        return "Double Click Mouse Event";
    }

}
