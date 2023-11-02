package org.ignite.editor.ui;

public abstract class State<T extends Widget> {

    protected T widget;
    protected abstract void initState();
    protected abstract void build();
}

