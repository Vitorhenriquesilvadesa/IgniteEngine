package org.test;

import org.ignite.core.app.Application;

public class Main {
    public static void main(String[] args) {
        Application.init();
        Application.setInstance(new Sandbox());
        Application app = Application.getInstance();
        app.run();
    }
}