package org.ignite.renderertools.renderer;

import org.ignite.core.macros.memory.SharedPointer;
import org.ignite.mathf.Vector4;
import org.ignite.renderertools.buffers.general.VertexArray;

public abstract class Renderer {

    public static void beginScene(){

    }

    public static void endScene(){

    }

    public static void submit(SharedPointer<VertexArray> vertexArray){
        vertexArray.getReference().bind();
        RenderCommand.drawIndexed(vertexArray);
    }

    public static RendererAPI.API getAPI() { return RendererAPI.getAPI(); }
}
