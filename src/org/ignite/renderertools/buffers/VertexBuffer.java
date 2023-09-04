package org.ignite.renderertools.buffers;

import org.ignite.core.macros.Destructible;
import org.ignite.renderertools.buffers.glbuffers.GLVertexBuffer;
import org.ignite.renderertools.renderer.Renderer;
import org.ignite.renderertools.renderer.RendererAPI;

import static org.ignite.core.macros.Macros.*;

public interface VertexBuffer extends Destructible {

    public void bind();

    public void unbind();

    public void setLayout(BufferLayout layout);

    public BufferLayout getLayout();

    public static VertexBuffer create(float[] vertices) {

        switch (Renderer.getAPI()) {
            case RendererAPI.NONE: {
                _assert("RendererAPI::NONE is currently not supported.", false);
                return null;
            }

            case RendererAPI.OPENGL: {
                return new GLVertexBuffer(vertices);
            }
        }

        _assert("Unknown renderer API.", false);
        return null;
    }

}
