package org.ignite.renderer.buffers;

import org.ignite.renderer.Renderer;
import org.ignite.renderer.RendererAPI;
import org.ignite.renderer.buffers.glbuffers.GLIndexBuffer;
import static org.ignite.core.macros.Macros.*;

public interface IndexBuffer {

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
