package org.ignite.editor.ui;

import imgui.ImGui;
import org.ignite.editor.layers.imgui.OnPressedCallback;

public class CascadeMenuItem extends MenuItem {

    Widget child;
    String title;
    OnPressedCallback onPressed;
    GlobalKey key;

    public CascadeMenuItem(String title, Widget child, OnPressedCallback onPressed) {
        this.child = child;
        this.title = title;
        this.onPressed = onPressed;
        this.key = new GlobalKey(this);
    }

    public CascadeMenuItem(String title, OnPressedCallback onPressed) {
        this.title = title;
        this.onPressed = onPressed;
        this.key = new GlobalKey(this);
    }

    public CascadeMenuItem(String title) {
        this.title = title;
        this.key = new GlobalKey(this);
    }

    public CascadeMenuItem onPressed(OnPressedCallback onPressed) {
        this.onPressed = onPressed;
        return this;
    }

    @Override
    public void render() {

        if (ImGui.menuItem(this.title)) {
            if (this.onPressed != null) {
                this.onPressed.onPressed();
            }
            if (child != null) {
                child.render();
            }
        }
    }
}
