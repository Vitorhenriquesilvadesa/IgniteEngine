package org.ignite.editor.ui;

import imgui.flag.ImGuiWindowFlags;

public class DraggableContainer extends Container {
    public DraggableContainer(float x, float y, float width, float height, Widget child) {
        super(x, y, width, height, child);
        this.flags = ImGuiWindowFlags.NoResize |
                ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings | ImGuiWindowFlags.NoTitleBar;
        this.isDraggable = true;
    }
}
