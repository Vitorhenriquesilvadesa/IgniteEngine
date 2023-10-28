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

import org.ignite.events.Event;
import org.ignite.annotations.Define;

/**
 * The `Layer` class represents a layer in the Ignite Engine's layer stack.
 * Layers can be used to organize and modularize different parts of the
 * application.
 */
@Define("IGNITE_API")
public abstract class Layer {

    private final String debugName;

    /**
     * Constructs a new `Layer` with the given debug name.
     *
     * @param name The debug name of the layer.
     */
    public Layer(String name) {
        this.debugName = name;
    }

    /**
     * Constructs a new `Layer` with the default debug name "Layer".
     */
    public Layer() {
        this.debugName = "Layer";
    }

    /**
     * Called when the layer is attached to the layer stack.
     * This method is intended to perform any necessary initialization for the
     * layer.
     */
    public abstract void onAttach();

    /**
     * Called when the layer is detached from the layer stack.
     * This method is intended to perform any cleanup or resource release
     * operations.
     */
    public abstract void onDetach();

    /**
     * Called on every frame update to update the layer's logic and state.
     */
    public abstract void onUpdate();

    /**
     * Called when an event is dispatched to the layer.
     * Layers can handle events based on their own requirements.
     *
     * @param e The event that was dispatched to the layer.
     */
    public abstract void onEvent(Event e);

    public abstract void onImGuiRender();

    public abstract void begin();

    public abstract void end();

    /**
     * Gets the debug name of the layer.
     *
     * @return The debug name of the layer.
     */
    public String getName() {
        return this.debugName;
    }
}
