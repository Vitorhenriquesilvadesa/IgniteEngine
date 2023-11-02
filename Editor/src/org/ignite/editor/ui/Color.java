package org.ignite.editor.ui;

public class Color {
    public int red;
    public int green;
    public int blue;
    public int alpha;

    public Color(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return "Color [R: " + red + ", G: " + green + ", B: " + blue + ", A: " + alpha + "]";
    }
}
