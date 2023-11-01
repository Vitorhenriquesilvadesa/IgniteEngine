package org.ignite.layers.ui;

import static org.ignite.core.app.Application.ClientLog;

public abstract class Widget {

    private int id;
    public abstract void render();

    public Widget(){
        this.id = this.hashCode();
        ClientLog.trace(this);
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + " " + this.getId();
    }
}
