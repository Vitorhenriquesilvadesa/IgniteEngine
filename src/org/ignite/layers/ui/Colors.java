package org.ignite.layers.ui;

public class Colors {
    public static final Color transparent = new Color(0, 0, 0, 0);
    public static final Color black = new Color(0, 0, 0, 255);
    public static final Color white = new Color(255, 255, 255, 255);
    public static final Color red = new Color(255, 0, 0, 255);
    public static final Color pink = new Color(255, 192, 203, 255);
    public static final Color purple = new Color(128, 0, 128, 255);
    public static final Color deepPurple = new Color(103, 58, 183, 255);
    public static final Color indigo = new Color(63, 81, 181, 255);
    public static final Color blue = new Color(0, 0, 255, 255);
    public static final Color lightBlue = new Color(173, 216, 230, 255);
    public static final Color cyan = new Color(0, 255, 255, 255);
    public static final Color teal = new Color(0, 128, 128, 255);
    public static final Color green = new Color(0, 128, 0, 255);
    public static final Color lightGreen = new Color(144, 238, 144, 255);
    public static final Color lime = new Color(0, 255, 0, 255);
    public static final Color yellow = new Color(255, 255, 0, 255);
    public static final Color amber = new Color(255, 191, 0, 255);
    public static final Color orange = new Color(255, 165, 0, 255);
    public static final Color deepOrange = new Color(255, 87, 34, 255);
    public static final Color brown = new Color(165, 42, 42, 255);
    public static final Color grey = new Color(128, 128, 128, 255);
    public static final Color blueGrey = new Color(96, 125, 139, 255);

    public static Color fromRGBA(int red, int green, int blue, int alpha) {
        return new Color(red, green, blue, alpha);
    }
}
