package org.ignite.events;

public abstract class KeyEvent extends Event {

    private int keyCode;

    public KeyEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public int getCategoryFlags() {
        return EventCategory.EventCategoryKeyboard | EventCategory.EventCategoryInput;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
