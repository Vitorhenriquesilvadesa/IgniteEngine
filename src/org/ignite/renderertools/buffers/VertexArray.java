package org.ignite.renderertools.buffers;

import org.ignite.renderertools.buffers.openglbuffers.GLVertexArray;
import org.ignite.renderertools.renderer.Renderer;
import org.ignite.renderertools.renderer.RendererAPI;

import static org.ignite.core.macros.debug.Macros.*;

import java.util.List;

import org.ignite.core.macros.debug.Destructible;
import org.ignite.core.macros.memory.SharedPointer;

public interface VertexArray extends Destructible {

    public void bind();

    public void unbind();

    public void addVertexBuffer(SharedPointer<VertexBuffer> vertexBuffer);

    public void setIndexBuffer(SharedPointer<IndexBuffer> indexBuffer);

    public SharedPointer<IndexBuffer> getIndexBuffer();

    public List<SharedPointer<VertexBuffer>> getVertexBuffers();

    public static VertexArray create() {
        switch (Renderer.getAPI()) {
            case RendererAPI.NONE: {
                _assert("RendererAPI::NONE is currently not supported.", false);
                return null;
            }

            case RendererAPI.OPENGL: {
                return new GLVertexArray();
            }
        }

        _assert("Unknown renderer API.", false);
        return null;
    }
}
