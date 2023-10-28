package org.ignite.layers.imgui;

public abstract class ImGuiElement {

    public float posX;
    public float posY;

    public ImGuiElement(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void renderElement();
}
