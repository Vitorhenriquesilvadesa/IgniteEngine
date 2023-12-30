package org.ignite.renderertools.buffers.openglbuffers;

import static org.lwjgl.opengl.GL15.*;

import org.ignite.renderertools.buffers.general.BufferLayout;
import org.ignite.renderertools.buffers.general.VertexBuffer;

public class GLVertexBuffer implements VertexBuffer {

    private int rendererID;
    private BufferLayout layout;

    public GLVertexBuffer(float[] vertices) {

        this.rendererID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, this.rendererID);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    @Override
    public void setLayout(BufferLayout layout) {
        this.layout = layout;
    }

    @Override
    public BufferLayout getLayout() {
        return this.layout;
    }

    @Override
    public void destroy() {
        glDeleteBuffers(this.rendererID);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.rendererID);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
}
