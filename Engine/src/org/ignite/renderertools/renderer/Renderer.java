package org.ignite.renderertools.renderer;

import org.ignite.core.app.Application;
import org.ignite.editor.ui.Color;
import org.ignite.mathf.Vector3;
import org.ignite.renderertools.components.Sprite;
import org.ignite.general.macros.memory.SharedPointer;
import org.ignite.mathf.Vector2;
import org.ignite.platform.general.Window;
import org.ignite.renderertools.buffers.general.*;
import org.ignite.renderertools.components.Texture;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private List<SharedPointer<VertexArray>> vertexArrays;

    public Renderer() {
        this.vertexArrays = new ArrayList<>();
    }

    public void beginScene() {
        vertexArrays.clear();
    }

    public void endScene() {
        for (SharedPointer<VertexArray> vertexArray : vertexArrays) {
            vertexArray.getReference().bind();
            RenderCommand.drawIndexed(vertexArray);
        }
    }

    public void submit(SharedPointer<VertexArray> vertexArray) {
        vertexArrays.add(vertexArray);
    }

    public void drawSprite(Sprite sprite) {
        Texture texture = sprite.texture;

        float width = sprite.width;
        float height = sprite.height;

        Vector2 position = new Vector2(sprite.transform.position.x, sprite.transform.position.y);

        BufferLayout squareLayout = new BufferLayout(new BufferElement(ShaderDataType.Float3, "a_Position"));

        SharedPointer<VertexBuffer> squareVB = new SharedPointer<>();
        squareVB.reset(VertexBuffer.create(getSquareVertices(position, width, height)));
        SharedPointer<VertexArray> vertexArray = new SharedPointer<>();
        vertexArray.reset(VertexArray.create());
        squareVB.getReference().setLayout(squareLayout);
        SharedPointer<IndexBuffer> indexBuffer = new SharedPointer<>();
        indexBuffer.reset(IndexBuffer.create(new int[]{0, 1, 2, 2, 3, 0}));
        vertexArray.getReference().addVertexBuffer(squareVB);
        vertexArray.getReference().setIndexBuffer(indexBuffer);

        submit(vertexArray);
    }


    public void drawQuad(Vector2 position, float width, float height, Color color) {

        float[] square_vertices = getSquareVertices(position, width, height);

        BufferLayout squareLayout = new BufferLayout(new BufferElement(ShaderDataType.Float3, "a_Position"));

        SharedPointer<VertexBuffer> squareVB = new SharedPointer<>();
        squareVB.reset(VertexBuffer.create(square_vertices));
        SharedPointer<VertexArray> vertexArray = new SharedPointer<>();
        vertexArray.reset(VertexArray.create());
        squareVB.getReference().setLayout(squareLayout);
        SharedPointer<IndexBuffer> indexBuffer = new SharedPointer<>();
        indexBuffer.reset(IndexBuffer.create(new int[]{0, 1, 2, 2, 3, 0}));
        vertexArray.getReference().addVertexBuffer(squareVB);
        vertexArray.getReference().setIndexBuffer(indexBuffer);
        submit(vertexArray);
    }

    private static float[] getSquareVertices(Vector2 position, float width, float height) {
        final Window window = Application.getInstance().getWindow();
        final Vector2 screenDim = new Vector2(window.getWidth(), window.getHeight());
        final float aspectRatio = screenDim.x / screenDim.y;

        final float normalizedX = (2.0f * position.x) / screenDim.x - 1.0f;
        final float normalizedY = 1.0f - (2.0f * position.y) / screenDim.y;
        final float normalizedWidth = (2.0f * width) / screenDim.x;
        final float normalizedHeight = (2.0f * height) / screenDim.y;

        return new float[]{
                normalizedX, normalizedY, 0.0f,
                normalizedX + normalizedWidth, normalizedY, 0.0f,
                normalizedX + normalizedWidth, normalizedY + normalizedHeight, 0.0f,
                normalizedX, normalizedY + normalizedHeight, 0.0f
        };
    }

    public RendererAPI.API getAPI() {
        return RendererAPI.getAPI();
    }
}
