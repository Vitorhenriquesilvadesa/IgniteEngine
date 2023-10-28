package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import imgui.internal.ImGuiWindow;

import static org.ignite.core.app.Application.ClientLog;

public class ImGuiFileDialog extends ImGuiElement {

    private float windowPosX = 0f;
    private float windowPosY = 0f;
    private boolean isDraggingWindow = false;
    private float mouseXOffset = 0f;
    private float mouseYOffset = 0f;

    public ImGuiFileDialog() {
        super(0f, 0f);
    }

    @Override
    public void renderElement() {
        ImGui.setNextWindowSize(300, 24);
        ImGui.setNextWindowPos(0f, 0f);

        if (ImGui.begin("Cascade Menu", ImGuiWindowFlags.AlwaysAutoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoResize | ImGuiWindowFlags.MenuBar | ImGuiWindowFlags.NoMove)) {
            if (ImGui.beginMenuBar()) {
                if (ImGui.beginMenu("File")) {
                    if (ImGui.menuItem("Save", "Ctrl + S")) {
                        ClientLog.info("Save button clicked!");
                    }
                    if (ImGui.menuItem("Open", "Ctrl + O")) {
                        ClientLog.info("Open button clicked!");
                    }
                    if (ImGui.menuItem("Open Recent", "Ctrl + R")) {
                        ClientLog.info("Open Recent button clicked!");
                    }
                    ImGui.endMenu();
                }
                if (ImGui.beginMenu("Theme")) {
                    if (ImGui.menuItem("One Dark")) {
                        ImGuiLayer.theme = ImGuiTheme.getTheme(ImGuiThemes.OneDark);
                    }
                    if (ImGui.menuItem("Dracula")) {
                        ImGuiLayer.theme = ImGuiTheme.getTheme(ImGuiThemes.Dracula);
                    }
                    ImGui.endMenu();
                }
            }
            ImGui.endMenuBar();
        }
        ImGui.end();

    }
}
