package org.ignite.editor.ui;

import imgui.ImFont;
import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;

public class Text extends Widget {
    private String text;
    private TextStyle style;
    private GlobalKey key;

    public Text(String text) {
        this.text = text;
        this.style = TextStyle.DEFAULT;
        this.key = new GlobalKey(this);
    }

    public Text(String text, TextStyle style) {
        this.text = text;
        this.style = style;
        this.key = new GlobalKey(this);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextStyle getStyle() {
        return style;
    }

    public Text style(TextStyle style) {
        this.style = style;
        return this;
    }

    @Override
    public void render() {
        ImFont font = FontFamily.getFont(this.style.fontFamily+this.style.fontSize);
        ImGui.pushFont(font);
        ImGuiStyle style = ImGui.getStyle();
        style.setColor(ImGuiCol.Text, this.style.color.red, this.style.color.green,
                this.style.color.blue, this.style.color.alpha);
        ImGui.text(text);
        ImGui.popFont();

    }

    public Text text(String text){
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "Text" + this.getId() + ": text -> " + this.text;
    }
}
