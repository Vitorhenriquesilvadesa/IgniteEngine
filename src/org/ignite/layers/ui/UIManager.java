package org.ignite.layers.ui;

import org.ignite.core.app.Application;

import java.util.HashMap;
import java.util.Map;

public final class UIManager {
    public static void init(){
        FontFamily.init();
    }

    public static int getScreenWidth(){
        return Application.getInstance().getWindow().getWidth();
    }

    public static int getScreenHeight(){
        return Application.getInstance().getWindow().getHeight();
    }
}
