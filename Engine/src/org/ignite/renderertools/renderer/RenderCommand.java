package org.ignite.renderertools.renderer;

import org.ignite.editor.ui.Color;
import org.ignite.general.macros.memory.RawPointer;
import org.ignite.general.macros.memory.SharedPointer;
import org.ignite.mathf.Vector2;
import org.ignite.mathf.Vector4;
import org.ignite.platform.opengl.OpenGLRendererAPI;
import org.ignite.renderertools.buffers.general.VertexArray;

public class RenderCommand {

    public static void drawIndexed(SharedPointer<VertexArray> vertexArray){
        rendererAPI.getReference().drawIndexed(vertexArray);
    }
    
    public static void setClearColor(Vector4 color){
        rendererAPI.getReference().setClearColor(color);
    }

    public static void clear(){
        rendererAPI.getReference().clear();
    }

    private final static RawPointer<RendererAPI> rendererAPI = new RawPointer<>(new OpenGLRendererAPI());
}
