package org.ignite.editor.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DockingContainer extends StatelessWidget {

    List<Widget> children;
    float width;
    float height;
    float x;
    float y;
    protected int flags;
    protected boolean isDraggable = false;
    private BoxDecoration decoration = new BoxDecoration(
            new Color(30, 30, 30, 255),
            new Color(30, 30, 30, 255),
            new Color(0, 0, 0, 0),
            0, 0, null);

    private GlobalKey key;

    public DockingContainer(float x, float y, float width, float height, Widget... children) {
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.flags = ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings;
        this.key = new GlobalKey(this);
    }

    @Override
    public void render() {

        update();

        ImGui.setNextWindowSize(width, height);

        ImGui.setNextWindowPos(x, y);

        ImGuiStyle style = ImGui.getStyle();

        style.setColor(ImGuiCol.WindowBg, this.decoration.getColor().red, this.decoration.getColor().green,
                this.decoration.getColor().blue, this.decoration.getColor().alpha);

        style.setColor(ImGuiCol.Border, this.decoration.getBorderColor().red, this.decoration.getBorderColor().green,
                this.decoration.getBorderColor().blue, this.decoration.getBorderColor().alpha);

        style.setWindowBorderSize(this.decoration.getBorderWidth());

        if (ImGui.begin("" + super.getId(), this.flags)) {

            for (Widget child : children) {
                child.render();
            }

            ImGui.end();
        }
    }

    public void update() {

    }

    public DockingContainer decoration(BoxDecoration decoration) {
        this.decoration = decoration;
        return this;
    }

    public DockingContainer children(Widget... children) {
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
        return this;
    }

    public DockingContainer children(List<Widget> children) {
        this.children = children;
        return this;
    }
}
