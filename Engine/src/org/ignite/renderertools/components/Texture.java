package org.ignite.renderertools.components;

import org.ignite.renderertools.components.openglcomponents.OpenGLTexture;
import org.ignite.renderertools.renderer.RendererAPI;

public abstract class Texture {
    private int textureID;
    private int width;
    private int height;

    private String path;

    public Texture(String path) {
        this.path = path;
    }

    public static Texture create(String path) {
        return switch (RendererAPI.getAPI()) {
            case None -> null;
            case OpenGL -> new OpenGLTexture(path);
            default -> throw new UnsupportedOperationException("Unknown API");
        };
    }

    public abstract void bind();
    public abstract void unbind();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public int getTextureID() {
        return textureID;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
