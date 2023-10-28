package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import org.ignite.core.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ImGuiParentWindow extends ImGuiElement {
    private List<ImGuiElement> childElements;

    public ImGuiParentWindow(float posX, float posY) {
        super(posX, posY);
        childElements = new ArrayList<>();
    }

    public void addChildElement(ImGuiElement element) {
        childElements.add(element);
    }

    @Override
    public void renderElement() {
        for (ImGuiElement element : childElements) {
            element.renderElement();
        }
    }
}
