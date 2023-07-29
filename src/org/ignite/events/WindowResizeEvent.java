package org.ignite.events;

public class WindowResizeEvent extends Event {

    private int width, height;

    public WindowResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.width + ", " + this.height + ")";
    }

    @Override
    public int getType() {
        return EventType.WindowResize;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.EventCategoryApplication;
    }

}
