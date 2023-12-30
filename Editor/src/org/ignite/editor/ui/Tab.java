package org.ignite.editor.ui;

import imgui.ImFont;
import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;

import java.util.ArrayList;
import java.util.List;

public class Tab extends StatelessWidget{

    public int width;
    public int height;
    public int x;
    public int y;
    public String title;
    public List<Widget> children;
    public TextStyle style;

    public Tab(int x, int y, int width, int height, String title, List<Widget> children) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        this.children = children;
        this.style = new TextStyle();
    }

    public Tab(int x, int y, int width, int height, String title, Widget... children) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        this.children = new ArrayList<>();
        this.children.addAll(List.of(children));
        this.style = new TextStyle();
    }

    public Tab(int x, int y, int width, int height, String title) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        this.style = new TextStyle();
    }

    @Override
    public void render() {

        ImFont font = FontFamily.getFont(this.style.fontFamily+this.style.fontSize);
        ImGui.pushFont(font);
        ImGuiStyle style = ImGui.getStyle();
        style.setColor(ImGuiCol.Text, this.style.color.red, this.style.color.green,
                this.style.color.blue, this.style.color.alpha);

        if(ImGui.beginTabItem(title))
        {
            if(this.children != null) {
                for (Widget child : children) {
                    child.render();
                }
            }
            ImGui.endTabItem();
        }

        ImGui.popFont();
    }
}
