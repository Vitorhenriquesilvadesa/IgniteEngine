package org.ignite.editor.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiWindowFlags;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL45.*;

public class Image extends Widget {
    private float posX, posY;
    private float width, height;
    private int textureID = -1;

    public Image(float posX, float posY, float width, float height, String imagePath) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

        try {
            ByteBuffer image;
            IntBuffer w = BufferUtils.createIntBuffer(1);
            IntBuffer h = BufferUtils.createIntBuffer(1);
            IntBuffer comp = BufferUtils.createIntBuffer(1);

            STBImage.stbi_set_flip_vertically_on_load(false);

            image = STBImage.stbi_load(imagePath, w, h, comp, 4);
            if (image != null) {
                int id = glGenTextures();
                glBindTexture(GL_TEXTURE_2D, id);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(0), h.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
                glGenerateMipmap(GL_TEXTURE_2D);
                STBImage.stbi_image_free(image);
                glBindTexture(GL_TEXTURE_2D, 0);
                textureID = id;
            } else {
                throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        ImGui.setNextWindowPos(posX, posY);
        ImGui.setNextWindowSize(width+8, height+8);

        ImGuiStyle style = ImGui.getStyle();
        style.setColor(ImGuiCol.Border, 0, 0, 0, 0);
        style.setColor(ImGuiCol.WindowBg, 0, 0, 0, 0);

        if (ImGui.begin("Image", ImGuiWindowFlags.NoDecoration |
                ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse)) {
            if (textureID != -1) {
                ImGui.image(textureID, width, height);
            }

            ImGui.end();
        }
    }
}
