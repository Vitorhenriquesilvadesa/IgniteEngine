package org.ignite.renderertools.buffers.general;

import org.ignite.renderertools.buffers.openglbuffers.GLVertexArray;
import org.ignite.renderertools.renderer.RendererAPI;

import static org.ignite.general.macros.debug.Macros.*;

import java.util.List;

import org.ignite.general.macros.debug.Destructible;
import org.ignite.general.macros.memory.SharedPointer;

public interface VertexArray extends Destructible {

    public void bind();

    public void unbind();

    public void addVertexBuffer(SharedPointer<VertexBuffer> vertexBuffer);

    public void setIndexBuffer(SharedPointer<IndexBuffer> indexBuffer);

    public SharedPointer<IndexBuffer> getIndexBuffer();

    public List<SharedPointer<VertexBuffer>> getVertexBuffers();

    public static VertexArray create() {
         return switch (RendererAPI.getAPI()) {

            case None -> {
                _assert("RendererAPI::NONE is currently not supported.", false);
                yield null;
            }

            case OpenGL -> {
                yield new GLVertexArray();
            }

            default -> {
                _assert("Unknown renderer API.", false);
                yield null;
            }
        };
    }
}
