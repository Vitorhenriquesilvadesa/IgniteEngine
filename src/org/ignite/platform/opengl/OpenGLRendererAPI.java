package org.ignite.platform.opengl;

import org.ignite.core.macros.memory.SharedPointer;
import org.ignite.mathf.Vector4;
import org.ignite.renderertools.buffers.general.VertexArray;
import org.ignite.renderertools.renderer.RendererAPI;
import static org.lwjgl.opengl.GL45.*;

public class OpenGLRendererAPI extends RendererAPI {

    @Override
    public void setClearColor(Vector4 color) {
        glClearColor(color.x, color.y, color.z, color.w);
    }

    @Override
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void drawIndexed(SharedPointer<VertexArray> vertexArray) {
        glDrawElements(GL_TRIANGLES, vertexArray.getReference().getIndexBuffer().getReference().getCount(), GL_UNSIGNED_INT, 0);
    }
}
