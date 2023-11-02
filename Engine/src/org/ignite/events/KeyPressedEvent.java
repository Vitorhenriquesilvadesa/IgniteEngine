package org.ignite.events;

public class KeyPressedEvent extends KeyEvent {

    private boolean isRepeat;

    public KeyPressedEvent(int keyCode, boolean isRepeat) {
        super(keyCode);
        this.isRepeat = isRepeat;
    }

    @Override
    public int getType() {
        return EventType.KeyPressed;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + super.getKeyCode() + "(repeating = " + this.isRepeat + ")";
    }

    public boolean getIsRepeat() {
        return this.isRepeat;
    }
}
