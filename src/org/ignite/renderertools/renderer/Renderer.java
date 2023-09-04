package org.ignite.renderertools.renderer;

public class Renderer {

    private static int rendererAPI = RendererAPI.OPENGL;

    public static int getAPI() {
        return Renderer.rendererAPI;
    }
}
