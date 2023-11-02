package org.ignite.platform.opengl;

import org.ignite.core.app.Application;
import org.ignite.editor.ui.Color;
import org.ignite.general.macros.memory.SharedPointer;
import org.ignite.mathf.Vector2;
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

    @Override
    public void drawQuad(Vector2 position, float width, float height, Color color) {

        Vector2 screenDim = new Vector2(Application.getInstance().getWindow().getWidth(), Application.getInstance().getWindow().getHeight());
        float normalizedX = (2.0f * position.x) / screenDim.x - 1.0f;
        float normalizedY = 1.0f - (2.0f * position.y) / screenDim.y;
        float normalizedWidth = (2.0f * width) / screenDim.x;
        float normalizedHeight = -(2.0f * height) / screenDim.y;

        glBegin(GL_QUADS);
        glColor4f(color.red, color.green, color.blue, color.alpha);
        glVertex2f(normalizedX, normalizedY);
        glVertex2f(normalizedX + normalizedWidth, normalizedY);
        glVertex2f(normalizedX + normalizedWidth, normalizedY + normalizedHeight);
        glVertex2f(normalizedX, normalizedY + normalizedHeight);
        glEnd();
    }

}
