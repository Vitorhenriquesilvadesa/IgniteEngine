package org.ignite.renderertools.components.openglcomponents;

import org.ignite.renderertools.components.Texture;
import org.ignite.system.debbuging.DebugColor;
import org.ignite.system.debbuging.DebugConsole;
import org.ignite.system.debbuging.DebugLevel;
import org.ignite.system.debbuging.DebugMessage;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL.getCapabilities;
import static org.lwjgl.opengl.GL45.*;

public class OpenGLTexture extends Texture {
    public OpenGLTexture(String filepath) {
        super(filepath);

        try {
            ByteBuffer image;
            IntBuffer w = BufferUtils.createIntBuffer(1);
            IntBuffer h = BufferUtils.createIntBuffer(1);
            IntBuffer comp = BufferUtils.createIntBuffer(1);

            STBImage.stbi_set_flip_vertically_on_load(false);

            image = STBImage.stbi_load(getPath(), w, h, comp, 4);
            setWidth(w.get(0));
            setHeight(h.get(0));

            if (image != null) {
                int id = glGenTextures();
                glBindTexture(GL_TEXTURE_2D, id);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(0), h.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
                glGenerateMipmap(GL_TEXTURE_2D);
                STBImage.stbi_image_free(image);
                glBindTexture(GL_TEXTURE_2D, 0);
                setTextureID(id);
            } else {
//                throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
                DebugConsole.sendMessage(new DebugMessage("Failed to load image: " + STBImage.stbi_failure_reason(), DebugColor.RED,
                        DebugLevel.ERROR, "OpenGL Texture"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, getTextureID());
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void cleanup() {
        glDeleteTextures(getTextureID());
    }

    private void checkForGLError() {
        long error;
        while ((error = getCapabilities().glGetError) != GL_NO_ERROR) {
            System.err.println("OpenGL error: " + error);
        }
    }
}
