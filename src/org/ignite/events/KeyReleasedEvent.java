package org.ignite.events;

public class KeyReleasedEvent extends KeyEvent {

    public KeyReleasedEvent(int keyCode) {
        super(keyCode);
    }

    @Override
    public int getType() {
        return EventType.KeyReleased;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + super.getKeyCode();
    }

}
