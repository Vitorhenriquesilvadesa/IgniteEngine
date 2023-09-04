package org.ignite.renderertools.buffers.glbuffers;

import java.util.ArrayList;
import java.util.List;
import org.ignite.core.macros.memory.SharedPointer;
import org.ignite.renderertools.buffers.BufferElement;
import org.ignite.renderertools.buffers.BufferLayout;
import org.ignite.renderertools.buffers.IndexBuffer;
import org.ignite.renderertools.buffers.ShaderDataType;
import org.ignite.renderertools.buffers.VertexArray;
import org.ignite.renderertools.buffers.VertexBuffer;
import static org.ignite.renderertools.buffers.DataTypeConverter.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL45.*;

public class GLVertexArray implements VertexArray {

    private List<SharedPointer<VertexBuffer>> vertexBuffers;
    private SharedPointer<IndexBuffer> indexBuffer;
    private int rendererID;

    public GLVertexArray() {
        this.vertexBuffers = new ArrayList<SharedPointer<VertexBuffer>>();
        this.indexBuffer = new SharedPointer<IndexBuffer>();
        this.rendererID = glCreateVertexArrays();

    }

    @Override
    public void bind() {
        glBindVertexArray(this.rendererID);
    }

    @Override
    public void unbind() {
        glBindVertexArray(0);
    }

    @Override
    public void addVertexBuffer(SharedPointer<VertexBuffer> vertexBuffer) {
        glBindVertexArray(this.rendererID);
        vertexBuffer.getReference().bind();

        int index = 0;
        final BufferLayout layout = vertexBuffer.getReference().getLayout();

        for (BufferElement element : layout) {

            glEnableVertexAttribArray(index);

            glVertexAttribPointer(index,
                    element.getComponentCount(),
                    shaderDataTypeToOpenGLBaseType(ShaderDataType.Float),
                    element.normalized,
                    layout.getStride(),
                    element.offset);

            index++;
        }

        this.vertexBuffers.add(vertexBuffer);
    }

    @Override
    public void setIndexBuffer(SharedPointer<IndexBuffer> indexBuffer) {
        glBindVertexArray(this.rendererID);
        indexBuffer.getReference().bind();
        this.indexBuffer = indexBuffer;
    }

    @Override
    public void destroy() {
        glDeleteVertexArrays(this.rendererID);
    }

    @Override
    public SharedPointer<IndexBuffer> getIndexBuffer() {
        return this.indexBuffer;
    }

    @Override
    public List<SharedPointer<VertexBuffer>> getVertexBuffers() {
        return this.vertexBuffers;
    }
}
