package org.ignite.engine;

import org.ignite.editor.ui.Color;
import org.ignite.general.log.LogLevel;
import org.ignite.general.log.Logger;
import org.ignite.mathf.Vector2;
import org.ignite.mathf.Vector3;
import org.ignite.renderertools.cameras.Camera;
import org.ignite.renderertools.cameras.OrthographicCamera;
import org.ignite.renderertools.components.Sprite;
import org.ignite.renderertools.renderer.Renderer;
import org.ignite.renderertools.shader.SpriteShader;
import org.ignite.system.debbuging.DebugConsole;

import static org.ignite.core.app.Application.ClientLog;

public final class Engine {

    private static Renderer renderer;
    private static Camera camera;
    private static Sprite sprite;

    public static void init() {
        ClientLog.info("Initializing Engine...");
        DebugConsole.sendInfoMessage("Initializing Engine...", "Engine");
        renderer = new Renderer();
        camera = new OrthographicCamera();
        sprite = new Sprite("C:/Users/vitor/OneDrive/Área de Trabalho/Pixel art/Guns/EnergyGun/EnergyGun.png",
                new Vector3(500f, 500f, 0f));
    }

    public static void update() {
        renderer.beginScene();
        sprite.render();
        renderer.endScene();
    }

    public static void renderSprite(Sprite sprite){
        renderer.drawSprite(sprite);
    }
}
