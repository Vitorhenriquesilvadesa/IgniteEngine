package org.ignite.editor.ui;

import org.ignite.core.app.Application;

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
