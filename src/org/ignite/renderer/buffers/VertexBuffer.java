package org.ignite.renderer.buffers;

public abstract class VertexBuffer {

    public abstract void bind();

    public abstract void unbind();

    public abstract VertexBuffer create(float[] vertices);

}
