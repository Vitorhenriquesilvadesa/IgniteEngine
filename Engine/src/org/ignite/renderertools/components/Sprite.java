package org.ignite.renderertools.components;

import org.ignite.engine.Engine;
import org.ignite.mathf.Transform;
import org.ignite.mathf.Vector3;
import org.ignite.renderertools.shader.Shader;
import org.ignite.renderertools.shader.SpriteShader;
import org.ignite.system.debbuging.DebugConsole;

import static org.ignite.core.app.Application.ClientLog;

public class Sprite {

    public static Shader shader = new SpriteShader("renderertools/shaders/sprite_vertex.glsl",
            "renderertools/shaders/sprite_fragment.glsl");

    public Transform transform;
    public Texture texture;

    public int width, height;

    public Sprite(String texturePath){
        transform = new Transform();
        texture = Texture.create(texturePath);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        ClientLog.info("Sprite created with texture: " + texturePath + " and size: " + width + "x" + height);
    }

    public Sprite(String texturePath, Vector3 position){
        transform = new Transform();
        transform.position = position;
        texture = Texture.create(texturePath);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public void render(){
        shader.bind();
        shader.setUniformMat4f("u_MVP", transform.transformationMatrix());
        shader.setUniformMat4f("u_MVP", transform.transformationMatrix());
        texture.bind();
        Engine.renderSprite(this);
        texture.unbind();
        shader.unbind();
    }
}
