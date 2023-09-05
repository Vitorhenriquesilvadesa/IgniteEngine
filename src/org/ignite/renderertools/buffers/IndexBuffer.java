package org.ignite.renderertools.buffers;

import static org.ignite.core.macros.debug.Macros.*;

import org.ignite.core.macros.debug.Destructible;
import org.ignite.renderertools.buffers.openglbuffers.GLIndexBuffer;
import org.ignite.renderertools.renderer.Renderer;
import org.ignite.renderertools.renderer.RendererAPI;

public interface IndexBuffer extends Destructible {

    public void bind();

    public void unbind();

    public void destroy();

    public int getCount();

    public static IndexBuffer create(int[] indices) {
        switch (Renderer.getAPI()) {
            case RendererAPI.NONE: {
                _assert("RendererAPI::NONE is currently not supported.", false);
                return null;
            }

            case RendererAPI.OPENGL: {
                return new GLIndexBuffer(indices);
            }
        }

        _assert("Unknown renderer API.", false);
        return null;
    }
}
