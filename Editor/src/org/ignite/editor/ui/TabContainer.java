package org.ignite.editor.ui;

import imgui.ImFont;
import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabContainer extends StatelessWidget {

    public int width;
    public int height;
    public int x;
    public int y;
    public String title;
    public List<Widget> children;
    public TextStyle style;
    public BoxDecoration decoration;

    public TabContainer(int x, int y, int width, int height, String title, List<Widget> children) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        this.children = children;
        this.style = new TextStyle();
        this.decoration = new BoxDecoration();
    }

    public TabContainer(int x, int y, int width, int height, String title, Widget... children) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
        this.style = new TextStyle();
        this.decoration = new BoxDecoration();
    }

    @Override
    public void render() {

        ImFont font = FontFamily.getFont(this.style.fontFamily+this.style.fontSize);
        ImGui.pushFont(font);
        ImGuiStyle style = ImGui.getStyle();
        style.setColor(ImGuiCol.Text, this.style.color.red, this.style.color.green,
                this.style.color.blue, this.style.color.alpha);
        style.setWindowBorderSize(this.decoration.getBorderWidth());
        style.setColor(ImGuiCol.WindowBg, this.decoration.getColor().red, this.decoration.getColor().green,
                this.decoration.getColor().blue, this.decoration.getColor().alpha);
        style.setWindowRounding(this.decoration.getBorderRadius());
        style.setColor(ImGuiCol.TabActive, decoration.getActiveColor().red, decoration.getActiveColor().green,
                decoration.getActiveColor().blue, decoration.getActiveColor().alpha);
        style.setColor(ImGuiCol.TabHovered, decoration.getHoverColor().red, decoration.getHoverColor().green,
                decoration.getHoverColor().blue, decoration.getHoverColor().alpha);

        if(ImGui.begin(title, ImGuiWindowFlags.NoCollapse |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoSavedSettings))
        {
            ImGui.setWindowSize(width, height, ImGuiCond.Once);
            ImGui.setWindowPos(x, y, ImGuiCond.Once);
            if(ImGui.beginTabBar(this.title)) {
                for (Widget child : children) {
                    child.render();
                }
                ImGui.endTabBar();
            }
            ImGui.end();
        }

        ImGui.popFont();
    }

    public TabContainer decoration(BoxDecoration decoration){
        this.decoration = decoration;
        return this;
    }
}
