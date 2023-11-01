package org.ignite.layers.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import org.ignite.system.debbuging.DebugColor;

public class TextStyle {
    public static final TextStyle DEFAULT = new TextStyle();

    public String fontFamily;
    public int fontSize;
    public boolean isBold;
    public boolean isItalic;
    public Color color;

    public TextStyle() {
        this.fontFamily = "JetBrainsMono-VariableFont_wght";
        this.fontSize = 20;
        this.isBold = false;
        this.isItalic = false;
        this.color = Colors.white;
    }

    public TextStyle(String fontFamily, FontSize fontSize) {
        this.fontFamily = fontFamily;
        setFontSize(fontSize);
        this.isBold = false;
        this.isItalic = false;
        this.color = Colors.white;
    }

    public TextStyle(String fontFamily, FontSize fontSize, Color color) {
        this.fontFamily = fontFamily;
        setFontSize(fontSize);
        this.color = color;
    }

    public TextStyle(String fontFamily, FontSize fontSize, DebugColor color) {
        this.fontFamily = fontFamily;
        setFontSize(fontSize);
        setTextColor(color);
    }

    private void setTextColor(DebugColor color) {
        switch (color) {
            case RED -> this.color = new Color(203, 122, 119, 255);
            case BLUE -> this.color = new Color(100, 120, 240, 255);
            case YELLOW -> this.color = new Color(200, 203, 119, 255);
            case WHITE -> this.color = new Color(255, 255, 255, 255);
        }
    }

    private void setFontSize(FontSize fontSize){
        switch (fontSize){
            case XSMALL:
                this.fontSize = 12;
                break;
            case SMALL:
                this.fontSize = 16;
                break;
            case MEDIUM:
                this.fontSize = 20;
                break;
            case LARGE:
                this.fontSize = 24;
                break;
            case XLARGE:
                this.fontSize = 28;
                break;
        }
    }
}
