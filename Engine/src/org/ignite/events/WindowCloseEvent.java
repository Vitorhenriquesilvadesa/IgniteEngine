package org.ignite.events;

public class WindowCloseEvent extends Event {

    @Override
    public int getType() {
        return EventType.WindowClose;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.EventCategoryApplication;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
