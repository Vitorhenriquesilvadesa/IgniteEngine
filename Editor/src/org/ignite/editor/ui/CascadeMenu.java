package org.ignite.editor.ui;

import imgui.ImFont;
import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CascadeMenu extends MenuItem {

    List<Widget> items = new ArrayList<>();
    private String title;
    private TextStyle style;
    private GlobalKey key;
    private int x;
    private int y;
    private int width;
    private int textWidth;
    private int height;
    private BoxDecoration decoration;

    public CascadeMenu(int x, int y, int width, int height, int textWidth, String title, Widget... items) {
        this.title = title;
        this.items.addAll(Arrays.asList(items));
        this.style = new TextStyle();
        this.key = new GlobalKey(this);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textWidth = textWidth;
        this.decoration = new BoxDecoration();
    }

    public CascadeMenu(int x, int y, int width, int height, int textWidth, String title, TextStyle style, Widget... items) {
        this.title = title;
        this.items.addAll(Arrays.asList(items));
        this.style = style;
        this.key = new GlobalKey(this);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textWidth = textWidth;
        this.decoration = new BoxDecoration();
    }

    @Override
    public void render() {
        ImFont font = FontFamily.getFont(this.style.fontFamily + this.style.fontSize);
        ImGui.pushFont(font);
        ImGui.setNextWindowPos(x, y);
        ImGui.setNextWindowSize(width, height);

        ImGuiStyle style = ImGui.getStyle();

        style.setColor(ImGuiCol.WindowBg, this.decoration.getColor().red, this.decoration.getColor().green,
                this.decoration.getColor().blue, this.decoration.getColor().alpha);

        style.setColor(ImGuiCol.Border, this.decoration.getBorderColor().red, this.decoration.getBorderColor().green,
                this.decoration.getBorderColor().blue, this.decoration.getBorderColor().alpha);

        if (ImGui.begin(title, ImGuiWindowFlags.MenuBar | ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoMove |
                ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoDocking | ImGuiWindowFlags.NoResize)) {

            if (ImGui.beginMenuBar()) {
                if (ImGui.beginMenu(this.title)) {
                    for (Widget item : items) {
                        item.render();
                    }
                    ImGui.endMenu();
                }
                ImGui.endMenuBar();
            }

            ImGui.end();
        }

        ImGui.popFont();
    }

    public CascadeMenu decoration(BoxDecoration decoration) {
        this.decoration = decoration;
        return this;
    }
}
