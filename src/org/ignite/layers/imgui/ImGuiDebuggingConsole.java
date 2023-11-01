package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;
import org.ignite.core.app.Application;
import org.ignite.layers.imgui.ImGuiElement;
import org.ignite.system.debbuging.DebugColor;
import org.ignite.system.debbuging.DebugConsole;
import org.ignite.system.debbuging.DebugMessage;

public class ImGuiDebuggingConsole extends ImGuiElement {
    private enum Tab {
        CONSOLE,
        FILE_EXPLORER
    }

    private Tab currentTab = Tab.CONSOLE;

    public ImGuiDebuggingConsole(float posX, float posY) {
        super(posX, posY);
    }

    @Override
    public void renderElement() {
        Application app = Application.getInstance();

        ImGui.setNextWindowSize(app.getWindow().getWidth() - 600, app.getWindow().getHeight());
        ImGui.setNextWindowPos(300, app.getWindow().getHeight() - 300);

        if (ImGui.begin("Bottom Menu", ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoResize |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoTitleBar)) {

            if (ImGui.beginTabBar("Debugging Console", ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoResize |
                    ImGuiWindowFlags.NoMove)) {

                if (ImGui.beginTabItem("Console")) {
                    currentTab = Tab.CONSOLE;
                    renderConsoleTab();
                    ImGui.endTabItem();
                }

                if (ImGui.beginTabItem("File Explorer")) {
                    currentTab = Tab.FILE_EXPLORER;
                    renderFileExplorerTab();
                    ImGui.endTabItem();
                }

                ImGui.endTabBar();
            }
            ImGui.end();
        }
    }

    private void renderConsoleTab() {
        for (DebugMessage message : DebugConsole.getDebugMessages()) {
            setTextColor(message.getColor());
            ImGui.text(message.getText());
            ImGui.newLine();
        }
        setTextColor(DebugColor.WHITE);
    }

    private void renderFileExplorerTab() {
        ImGui.text("File Explorer Content Goes Here");
        // Add your file explorer logic here
    }

    private void setTextColor(DebugColor color) {
        ImGuiStyle style = ImGui.getStyle();
        switch (color) {
            case RED -> style.setColor(ImGuiCol.Text, 203, 122, 119, 255);
            case BLUE -> style.setColor(ImGuiCol.Text, 100, 120, 240, 255);
            case YELLOW -> style.setColor(ImGuiCol.Text, 200, 203, 119, 255);
            case WHITE -> style.setColor(ImGuiCol.Text, 255, 255, 255, 255);
        }
    }
}
