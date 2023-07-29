package org.test;

import org.ignite.core.app.Application;
import org.ignite.core.macros.Macros;
import org.ignite.events.KeyCode;
import org.ignite.platform.windows.WindowsInput;

public class Sandbox extends Application {

    public void start() {
        Macros.DEBUG = false;
        addTickEvent(this::testMethod, "test");
    }

    public void update() {
        callTickEvent("test", WindowsInput.isKeyPressed(KeyCode.TAB));
    }

    public void testMethod() {
        System.out.println("testMethod.");
    }
}