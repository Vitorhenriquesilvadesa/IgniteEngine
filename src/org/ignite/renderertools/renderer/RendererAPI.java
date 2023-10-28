package org.ignite.renderertools.renderer;


import org.ignite.core.macros.memory.SharedPointer;
import org.ignite.mathf.Vector4;
import org.ignite.renderertools.buffers.general.VertexArray;

import static org.ignite.renderertools.renderer.RendererAPI.API.*;

public abstract class RendererAPI {

    public abstract void setClearColor(Vector4 color);
    public abstract void clear();

    public abstract void drawIndexed(SharedPointer<VertexArray> vertexArray);

    private static final API API = OpenGL;

    public static API getAPI() {
        return API;
    }

    public enum API {
        None, OpenGL
    }
}