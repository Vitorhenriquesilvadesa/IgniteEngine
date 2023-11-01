package org.ignite.layers.ui;

public abstract class State<T extends Widget> {

    protected T widget;
    protected abstract void initState();
    protected abstract void build();
}

