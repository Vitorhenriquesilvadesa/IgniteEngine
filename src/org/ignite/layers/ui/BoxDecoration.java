package org.ignite.layers.ui;

import javax.swing.*;

public class BoxDecoration {
    private Color color;
    private Color borderColor;
    private float borderRadius;
    private float borderWidth;
    private Image backgroundImage;
    private Color activeColor;
    private Color hoverColor;

    public BoxDecoration(Color color, Color borderColor, Color activeColor, float borderRadius, float borderWidth, Image backgroundImage) {
        this.color = color;
        this.borderColor = borderColor;
        this.borderRadius = borderRadius;
        this.borderWidth = borderWidth;
        this.backgroundImage = backgroundImage;
        this.activeColor = activeColor;
    }

    public BoxDecoration(){
        this.color = Colors.grey;
        this.borderColor = Colors.grey;
        this.activeColor = Colors.grey;
        this.borderRadius = 0;
        this.borderWidth = 0;
        this.backgroundImage = null;
    }

    public Color getColor() {
        return color;
    }

    public BoxDecoration color(Color color) {
        this.color = color;
        return this;
    }


    public Color getBorderColor() {
        return borderColor;
    }

    public BoxDecoration borderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Color getActiveColor(){
        return activeColor;
    }

    public float getBorderRadius() {
        return borderRadius;
    }

    public BoxDecoration borderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public BoxDecoration borderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public BoxDecoration activeColor(Color color) {
        this.activeColor = color;
        return this;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void backgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public BoxDecoration hoverColor(Color color){
        this.hoverColor = color;
        return this;
    }

    public Color getHoverColor(){
        return this.hoverColor;
    }
}
