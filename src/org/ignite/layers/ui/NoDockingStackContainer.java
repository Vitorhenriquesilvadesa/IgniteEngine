package org.ignite.layers.ui;

import imgui.flag.ImGuiWindowFlags;
import org.jetbrains.annotations.NotNull;

public class NoDockingStackContainer extends StackContainer {
    public NoDockingStackContainer(float x, float y, float width, float height, @NotNull Widget... children) {
        super(x, y, width, height, children);
        this.flags = ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings | ImGuiWindowFlags.NoDocking;
    }
}
