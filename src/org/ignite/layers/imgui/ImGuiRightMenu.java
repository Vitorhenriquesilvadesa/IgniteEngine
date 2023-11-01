package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;
import imgui.internal.ImGuiWindow;
import org.ignite.core.app.Application;

public class ImGuiRightMenu extends ImGuiElement {

    private boolean renderFlag = false;

    public ImGuiRightMenu() {
        super(0f, 0f);
    }

    @Override
    public void renderElement() {

        Application app = Application.getInstance();

        ImGui.setNextWindowSize(300, app.getWindow().getHeight(), ImGuiCond.Once);
        renderFlag = true;


        if (ImGui.begin("Right Menu", ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoTitleBar
                | ImGuiWindowFlags.NoResize)) {

            ImGui.setWindowSize(300, app.getWindow().getHeight());
            ImGui.setWindowPos(app.getWindow().getWidth() - 300, 0);
        }
        ImGui.end();
    }
}
