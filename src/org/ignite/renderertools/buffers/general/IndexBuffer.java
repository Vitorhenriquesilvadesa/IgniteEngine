package org.ignite.renderertools.buffers.general;

import static org.ignite.core.macros.debug.Macros.*;

import org.ignite.core.macros.debug.Destructible;
import org.ignite.renderertools.buffers.openglbuffers.GLIndexBuffer;
import org.ignite.renderertools.renderer.RendererAPI;

public interface IndexBuffer extends Destructible {

    public void bind();

    public void unbind();

    public void destroy();

    public int getCount();

    public static IndexBuffer create(int[] indices) {

        return switch (RendererAPI.getAPI()) {
            case None -> {
                _assert("RendererAPI::NONE is currently not supported.", false);
                yield null;
            }
            case OpenGL -> { yield new GLIndexBuffer(indices); }
            default -> {
                _assert("Unknown renderer API.", false);
                yield null;
            }
        };
    }
}
