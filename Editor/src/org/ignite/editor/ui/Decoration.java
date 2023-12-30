package org.ignite.editor.ui;

public class Decoration {
    private Color color;
    private Color borderColor;
    private double padding;

    public Decoration(Color color, Color borderColor, double padding) {
        this.color = color;
        this.borderColor = borderColor;
        this.padding = padding;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public double getPadding() {
        return padding;
    }

    public void setPadding(double padding) {
        this.padding = padding;
    }
}
