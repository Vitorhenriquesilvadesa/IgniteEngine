package org.ignite.layers.ui;

import java.util.Stack;

public final class Navigator {

    private static Stack<Screen> screens = new Stack<>();
    public static void render(){
        screens.peek().render();
    }

    public static void pushScreen(Screen screen){
        screens.push(screen);
    }

    public static void popScreen(){
        screens.pop();
    }

    public static void pushReplacementScreen(Screen screen){
        screens.pop();
        screens.push(screen);
    }
}
