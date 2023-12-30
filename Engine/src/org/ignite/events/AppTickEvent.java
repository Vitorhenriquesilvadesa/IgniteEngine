package org.ignite.events;

public class AppTickEvent extends Event {

    @Override
    public int getType() {
        return EventType.AppTick;
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
        return null;
    }

}
