package org.ignite.events;

public class MouseMovedEvent extends Event {

    private float mouseX, mouseY;

    public MouseMovedEvent(float x, float y) {
        this.mouseX = x;
        this.mouseY = y;
    }

    @Override
    public int getType() {
        return EventType.MouseMoved;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.EventCategoryMouse | EventCategory.EventCategoryInput;
    }

    @Override
    public String toString() {
        return "MouseMovedEvent: " + this.mouseX + ", " + this.mouseY;
    }

    public float getMouseX() {
        return mouseX;
    }

    public float getMouseY() {
        return mouseY;
    }
}
