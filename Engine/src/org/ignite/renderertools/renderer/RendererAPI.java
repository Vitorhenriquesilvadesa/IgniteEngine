package org.ignite.renderertools.renderer;


import org.ignite.editor.ui.Color;
import org.ignite.general.macros.memory.SharedPointer;
import org.ignite.mathf.Vector2;
import org.ignite.mathf.Vector4;
import org.ignite.renderertools.buffers.general.VertexArray;

import static org.ignite.renderertools.renderer.RendererAPI.API.*;

public abstract class RendererAPI {

    public abstract void setClearColor(Vector4 color);
    public abstract void clear();

    public abstract void drawIndexed(SharedPointer<VertexArray> vertexArray);

    public abstract void drawQuad(Vector2 position, float width, float height, Color color);

    private static final API API = OpenGL;

    public static API getAPI() {
        return API;
    }

    public enum API {
        None, OpenGL
    }
}