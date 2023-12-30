package org.ignite.editor.ui;

public class VerticalExpanded extends Container {
    public VerticalExpanded(float x, float y, float width, Widget child) {
        super(x, y, width, UIManager.getScreenHeight(), child);
    }

    public VerticalExpanded(float x, float y, float width) {
        super(x, y, width, UIManager.getScreenHeight() - y, null);
    }

    @Override
    public void update() {
        this.height = UIManager.getScreenHeight();
    }
}
