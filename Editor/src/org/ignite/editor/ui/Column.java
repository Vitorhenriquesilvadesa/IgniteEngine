package org.ignite.editor.ui;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Column extends StatelessWidget {

    public List<Widget> children;

    public Column(Widget... children) {
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public void render() {

        if (ImGui.begin("" + this.getId(), ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoResize |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings |
                ImGuiWindowFlags.NoScrollbar)) {

            for (Widget child : children) {
                child.render();
            }

            ImGui.end();
        }
    }
}
