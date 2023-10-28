package org.ignite.renderertools.buffers.general;

import static org.ignite.core.macros.debug.Macros.*;

import org.ignite.core.macros.debug.Destructible;
import org.ignite.renderertools.buffers.openglbuffers.GLVertexBuffer;
import org.ignite.renderertools.renderer.RendererAPI;

public interface VertexBuffer extends Destructible {

    public void bind();

    public void unbind();

    public void setLayout(BufferLayout layout);

    public BufferLayout getLayout();

    public static VertexBuffer create(float[] vertices) {

        return switch (RendererAPI.getAPI()) {

            case None -> {
                _assert("RendererAPI::NONE is currently not supported.", false);
                yield null;
            }

            case OpenGL -> {
                yield new GLVertexBuffer(vertices);
            }

            default -> {
                _assert("Unknown renderer API.", false);
                yield null;
            }
        };
    }
}
