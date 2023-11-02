package org.ignite.editor.ui;

public abstract class Key<T> {
    public int id;
    public T value;

    public Key(int id, T value){
        this.id = id;
        this.value = value;
    }
}
