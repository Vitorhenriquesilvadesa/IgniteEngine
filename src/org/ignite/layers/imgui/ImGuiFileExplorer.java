package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import org.ignite.core.app.Application;

public class ImGuiFileExplorer extends ImGuiElement {

    public ImGuiFileExplorer() {
        super(0, 0);
    }

    @Override
    public void renderElement() {

        Application app = Application.getInstance();

        ImGui.setNextWindowSize(app.getWindow().getWidth() - 600, 300);
        ImGui.setNextWindowPos(300, app.getWindow().getHeight() - 300);
        if(ImGui.begin("File Explorer", ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoResize)){

            ImGui.end();
        }
    }
}
