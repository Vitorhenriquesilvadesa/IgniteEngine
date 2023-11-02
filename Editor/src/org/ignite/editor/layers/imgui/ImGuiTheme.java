package org.ignite.editor.layers.imgui;

public interface ImGuiTheme {

    public void applyTheme();

    public static ImGuiTheme getTheme(ImGuiThemes theme) {
        switch (theme) {
            case OneDark -> {
                return new ImOneDarkTheme();
            }

            case Dracula -> {
                return new ImDraculaTheme();
            }

        }
        throw new UnsupportedOperationException("Unknown theme");
    }
}