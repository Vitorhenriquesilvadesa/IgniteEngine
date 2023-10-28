package org.ignite.layers.imgui;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import org.ignite.core.app.Application;
import static org.lwjgl.opengl.GL45.*;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;

public class ImGuiTopMenu extends ImGuiElement {

    public ImGuiTopMenu() {
        super(300, 0);
    }

    @Override
    public void renderElement() {
        Application app = Application.getInstance();

        ImGui.setNextWindowSize(app.getWindow().getWidth() - 600, 50);
        ImGui.setNextWindowPos(300, 0);

        if (ImGui.begin("Top Menu", ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoDocking)) {

            ImGui.end();
        }
    }
}
