/**
 * Ignite Engine - A powerful game engine in Java.
 *
 * @license MIT License
 *
 * @author Creator: Lord Vtko
 * @version 1.0.0
 * @since 2023-07-28
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

package org.ignite.layers;

import static org.ignite.core.app.Application.ClientLog;

import org.ignite.events.Event;

import imgui.ImGui;
import org.ignite.system.meta.Define;

/**
 * The `ExampleLayer` class is an implementation of the `Layer` abstract class.
 * It serves as an example of how to create a custom layer for the Ignite
 * Engine.
 */

@Define("IGNITE_API")
public class ExampleLayer extends Layer {

    @Override
    public void onAttach() {
        // Perform any necessary initialization when the layer is attached.
    }

    @Override
    public void onDetach() {
        // Perform any cleanup or resource release operations when the layer is
        // detached.
    }

    @Override
    public void onUpdate() {
        // Called on every frame update to update the logic and state of the layer.
        // In this example, we log a debug message to indicate the update.
        ClientLog.debug("ExampleLayer::Update");
    }

    @Override
    public void onEvent(Event e) {
        // Called when an event is dispatched to the layer.
        // In this example, we log the event with a trace level message.
        ClientLog.debug(e);
    }

    @Override
    public void onImGuiRender() {
        ImGui.begin("Test");
        ImGui.text("Hello World");
        ImGui.end();
    }

    @Override
    public void begin() {

    }

    @Override
    public void end() {

    }

}
