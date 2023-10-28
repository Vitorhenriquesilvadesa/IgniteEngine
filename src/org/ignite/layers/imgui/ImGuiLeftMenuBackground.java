package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;
import imgui.internal.ImGuiWindow;
import org.ignite.core.app.Application;

public class ImGuiLeftMenuBackground extends ImGuiElement{

    public ImGuiLeftMenuBackground() {
        super(0, 0);
    }

    @Override
    public void renderElement() {
        ImGui.setNextWindowPos(0, 24);
        ImGui.setNextWindowSize(300, Application.getInstance().getWindow().getHeight() - 64);
        ImGui.setNextWindowBgAlpha(255);
        ImGui.begin("Object Properties", ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize |
                ImGuiWindowFlags.NoCollapse);
        ImGui.end();
    }
}
