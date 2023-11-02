package org.ignite.events;

public class AppUpdate extends Event {

    @Override
    public int getType() {
        return EventType.AppUpdate;
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
