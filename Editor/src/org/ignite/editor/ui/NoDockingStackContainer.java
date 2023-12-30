package org.ignite.editor.ui;

import imgui.flag.ImGuiWindowFlags;

public class NoDockingStackContainer extends DockingContainer {
    public NoDockingStackContainer(float x, float y, float width, float height, Widget... children) {
        super(x, y, width, height, children);
        this.flags = ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings | ImGuiWindowFlags.NoDocking;
    }
}
