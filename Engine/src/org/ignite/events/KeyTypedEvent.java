package org.ignite.events;

public class KeyTypedEvent extends KeyEvent {

    public KeyTypedEvent(int keyCode) {
        super(keyCode);
    }

    @Override
    public int getType() {
        return EventType.KeyTyped;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getName() + ": " + this.getKeyCode();
    }

}
