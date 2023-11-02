package org.ignite.editor.ui;

import static org.ignite.editor.Editor.EditorLogger;

public abstract class Widget {

    private int id;
    public abstract void render();

    public Widget(){
        this.id = this.hashCode();
        EditorLogger.trace(this);
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + " " + this.getId();
    }
}
