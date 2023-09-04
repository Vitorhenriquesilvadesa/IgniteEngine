package org.ignite.renderertools.buffers.glbuffers;

import static org.lwjgl.opengl.GL15.*;

import org.ignite.renderertools.buffers.IndexBuffer;

public class GLIndexBuffer implements IndexBuffer {

    private int rendererID;
    private int count;

    public GLIndexBuffer(int[] indices) {

        this.rendererID = glGenBuffers();
        this.count = indices.length;

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.rendererID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
    }

    @Override
    public void destroy() {
        glDeleteBuffers(this.rendererID);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.rendererID);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
