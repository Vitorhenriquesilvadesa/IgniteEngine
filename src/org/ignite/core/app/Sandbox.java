package org.ignite.core.app;

import org.ignite.core.app.Application;
import org.ignite.events.KeyCode;
import org.ignite.events.MouseButton;
import org.ignite.platform.windows.WindowsInput;

public class Sandbox extends Application {

    int clickNumber = 0;
    long firstClick;
    long secondClick;

    public void start() {
        disableEventExceptions();
        addTickEvent(this::sayHello, "say hello");
        addTriggerEvent(this::sayOlaMundo, "say ola mundo");
        addTriggerEvent(this::doubleClick, "double dlick");
    }

    public void update() {
        callTickEvent("say hello", WindowsInput.isKeyPressed(KeyCode.SPACE));
        callTriggerEvent("say ola mundo", WindowsInput.isKeyPressed(KeyCode.TAB));
        callTriggerEvent("double click", WindowsInput.isMouseButtonPressed(MouseButton.BUTTON_LEFT));
    }

    public void sayHello() {
        ClientLog.info("Hello!");
    }

    public void sayOlaMundo() {
        ClientLog.info("Ola mundo");
    }

    public void doubleClick() {

        if (this.clickNumber == 0) {
            firstClick = System.currentTimeMillis();
        } else {
            secondClick = System.currentTimeMillis();
        }

        this.clickNumber++;

        if (clickNumber > 1) {

            if (secondClick - firstClick < 500) {
                ClientLog.trace("Double Click");

            }
            firstClick = System.currentTimeMillis();
            secondClick = System.currentTimeMillis();
            clickNumber = 0;
        }

        if (System.currentTimeMillis() - firstClick >= 500) {
            firstClick = System.currentTimeMillis();
            secondClick = System.currentTimeMillis();
            clickNumber = 0;
        }
    }
}