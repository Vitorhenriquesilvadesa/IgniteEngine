package org.ignite.events;

public abstract class Event {

    private boolean handled = false;

    public abstract int getType();

    public abstract String getName();

    public abstract int getCategoryFlags();

    @Override
    public abstract String toString();

    public boolean isInCategory(int category) {
        return (getCategoryFlags() & category) != 0;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }
}
