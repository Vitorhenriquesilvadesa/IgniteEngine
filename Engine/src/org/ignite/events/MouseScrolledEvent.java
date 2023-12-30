package org.ignite.events;

public class MouseScrolledEvent extends Event {

    private float XOffset, YOffset;

    public MouseScrolledEvent(float XOffset, float YOffset) {
        this.XOffset = XOffset;
        this.YOffset = YOffset;
    }

    @Override
    public int getType() {
        return EventType.MouseScrolled;
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
        return "MouseScrolledEvent: " + this.XOffset + ", " + this.YOffset;
    }

    public float getXOffset() {
        return XOffset;
    }

    public float getYOffset() {
        return YOffset;
    }
}
