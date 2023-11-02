import org.ignite.core.app.Application;
import org.ignite.editor.layers.Layer;
import org.ignite.editor.layers.LayerStack;
import org.ignite.editor.layers.ignui.IgnGuiLayer;
import org.ignite.editor.layers.imgui.ImGuiLayer;
import org.ignite.editor.ui.Color;
import org.ignite.engine.Engine;
import org.ignite.events.Event;
import org.ignite.events.EventDispatcher;
import org.ignite.events.EventType;
import org.ignite.mathf.Vector2;
import org.ignite.mathf.Vector3;
import org.ignite.platform.general.Input;
import org.ignite.renderertools.components.Sprite;
import org.ignite.renderertools.renderer.RenderCommand;

public class Sandbox extends Application {

    /** Stack of layers managed by the application. */
    private LayerStack layerStack = new LayerStack();
    protected ImGuiLayer ignLayer;
    public void start(){
        this.ignLayer = new IgnGuiLayer();
        pushOverlay(ignLayer);


    }

    public void update(){

        Engine.update();

        for (Layer layer : this.layerStack) {
            layer.onUpdate();
        }

        ImGuiLayer.begin();

        for (Layer layer : this.layerStack) {
            layer.onImGuiRender();
        }

        ImGuiLayer.end();
    }

    public void onEvent(Event e){
        EventDispatcher dispatcher = new EventDispatcher(e);
        dispatcher.dispatch(EventType.WindowClose, this::onWindowClose);
        ClientLog.debug(e);

        for (Layer layer : this.layerStack) {

            layer.onEvent(e);
            if (e.isHandled()) {
                break;
            }
        }
    }

    /**
     * Adds a new overlay layer to the top of the layer stack.
     * It calls the `onAttach` method of the overlay layer after it has been added.
     *
     * @param layer The overlay layer to be added.
     */
    protected void pushOverlay(Layer layer) {
        this.layerStack.pushOverlay(layer);
        layer.onAttach();
    }

    /**
     * Adds a new layer to the top of the layer stack.
     * It calls the `onAttach` method of the layer after it has been added.
     *
     * @param layer The layer to be added.
     */
    protected void pushLayer(Layer layer) {
        this.layerStack.pushLayer(layer);
        layer.onAttach();
    }


}
