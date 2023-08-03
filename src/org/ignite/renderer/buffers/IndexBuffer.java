package org.ignite.renderer.buffers;

public abstract class IndexBuffer {

    public abstract void bind();

    public abstract void unbind();

    public abstract IndexBuffer create(int[] indices);
}
