package org.ignite.layers.ui;

import imgui.ImFont;

import java.util.HashMap;
import java.util.Map;

public final class FontFamily {

    private static Map<String, ImFont> fonts = new HashMap<>();

    public static ImFont getFont(String font){
        return fonts.get(font);
    }

    public static void addFont(String fontName, ImFont font){
        fonts.put(fontName, font);
    }

    public static void init(){
        TTFFontFiles.fetchFonts();
    }
}
