package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;
import org.ignite.core.app.Application;
import org.ignite.core.app.Time;

public class ImGuiFPSDisplay extends ImGuiElement{

    double lastFpsUpdate = 0;

    float currentFPS = 0f;

    public ImGuiFPSDisplay(float posX, float posY) {
        super(posX, posY);
    }

    @Override
    public void renderElement() {
        ImGui.setNextWindowPos(0, Application.getInstance().getWindow().getHeight() - 40);
        ImGui.setNextWindowSize(300, 40);

        ImGui.setNextWindowBgAlpha(255);

        ImGui.begin("FPS", ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoTitleBar);

        double currentTime = Time.time();

        ImGui.text(String.format("FPS: %.2f", currentFPS).replace(",", "."));

        if (currentTime - lastFpsUpdate >= 1.0) {
            currentFPS = 1.0f / Time.deltaTime();
            lastFpsUpdate = currentTime;
        }

        ImGui.end();
    }
}
