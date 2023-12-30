package org.ignite.editor.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;
import org.ignite.core.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullScreenContainer extends StatelessWidget {

    List<Widget> children;
    protected int flags;
    protected boolean isDraggable = false;
    Application app = Application.getInstance();
    private BoxDecoration decoration = new BoxDecoration(
            new Color(30, 30, 30, 255),
            new Color(30, 30, 30, 255),
            new Color(30, 30, 30, 0),
            0, 0, null);
    private GlobalKey key;

    public FullScreenContainer(Widget... children) {
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
        this.flags = ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings;
        this.key = new GlobalKey(this);
    }

    public FullScreenContainer(BoxDecoration decoration, Widget... children) {
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
        this.flags = ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings;
        this.decoration = decoration;
        this.key = new GlobalKey(this);
    }

    @Override
    public void render() {

        update();

        ImGuiStyle style = ImGui.getStyle();

        style.setColor(ImGuiCol.WindowBg, 0, 0, 0, 0);
            for (Widget child : children) {
                child.render();
            }
    }

    public void update() {
    }

    public FullScreenContainer decoration(BoxDecoration decoration) {
        this.decoration = decoration;
        return this;
    }
}
