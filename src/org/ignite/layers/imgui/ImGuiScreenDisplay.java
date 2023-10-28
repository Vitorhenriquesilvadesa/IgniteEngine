package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;
import org.ignite.core.app.Application;

public class ImGuiScreenDisplay extends ImGuiElement{

    public ImGuiScreenDisplay(float posX, float posY) {
        super(posX, posY);
    }

    @Override
    public void renderElement() {

        Application app = Application.getInstance();

        ImGui.setNextWindowBgAlpha(0);
        ImGui.setNextWindowPos(300, 0);
        ImGui.setNextWindowSize(app.getWindow().getWidth() - 300, app.getWindow().getHeight());
        ImGui.begin("Screen Container", ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoTitleBar);

        ImGui.end();
    }
}
