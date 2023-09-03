package org.ignite.renderer.buffers.glbuffers;

import org.ignite.renderer.buffers.VertexBuffer;
import static org.lwjgl.opengl.GL15.*;

public class GLVertexBuffer implements VertexBuffer {

    private int rendererID;

    public GLVertexBuffer(float[] vertices) {

        this.rendererID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, this.rendererID);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
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
