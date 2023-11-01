package org.ignite.layers.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;

import static org.ignite.core.app.Application.ClientLog;

public class Container extends StatelessWidget {

    Widget child;
    float width;
    float height;
    float x;
    float y;
    protected int flags;
    protected boolean isDraggable = false;
    private GlobalKey key;
    private BoxDecoration decoration = new BoxDecoration(
            new Color(30, 30, 30, 255),
            new Color(30, 30, 30, 255),
            new Color(30, 30, 30, 0),
            0, 0, null);

    public Container(float x, float y, float width, float height, Widget child) {
        this.child = child;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.flags = ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings;
        this.key = new GlobalKey(this);
    }

    public Container(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.flags = ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoTitleBar |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoSavedSettings;
        this.key = new GlobalKey(this);
    }

    public void build() {
    }

    @Override
    public void render() {

        update();

        ImGui.setNextWindowSize(width, height);

        if (!isDraggable) {
            ImGui.setNextWindowPos(x, y);
        }

        ImGuiStyle style = ImGui.getStyle();

        style.setColor(ImGuiCol.WindowBg, this.decoration.getColor().red, this.decoration.getColor().green,
                this.decoration.getColor().blue, this.decoration.getColor().alpha);

        style.setColor(ImGuiCol.Border, this.decoration.getBorderColor().red, this.decoration.getBorderColor().green,
                this.decoration.getBorderColor().blue, this.decoration.getBorderColor().alpha);

        style.setWindowRounding(this.decoration.getBorderRadius());

        if (ImGui.begin("" + this.hashCode(), this.flags)) {

            if (child != null) {
                child.render();
            }

            ImGui.end();
        }
    }

    public void update() {
    }

    public Container decoration(BoxDecoration decoration) {
        this.decoration = decoration;
        return this;
    }
}
