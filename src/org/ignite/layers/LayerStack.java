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

import static org.ignite.core.macros.debug.Macros.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ignite.core.macros.memory.Pointer;
import org.ignite.core.macros.memory.RawPointer;

/**
 * The `LayerStack` class represents a stack of layers in the Ignite Engine.
 * It allows adding, removing, and iterating through layers in the stack.
 */
public class LayerStack implements Iterable<Layer> {

    private List<Layer> layers;

    /**
     * Constructs a new `LayerStack` object.
     */
    public LayerStack() {
        this.layers = new ArrayList<Layer>();
    }

    /**
     * Adds a layer to the top of the layer stack.
     *
     * @param layer The layer to push onto the stack.
     */
    public void pushLayer(Layer layer) {
        this.layers.add(0, layer);
    }

    /**
     * Adds an overlay layer to the top of the layer stack.
     *
     * @param overlay The overlay layer to push onto the stack.
     */
    public void pushOverlay(Layer overlay) {
        this.layers.add(overlay);
    }

    /**
     * Removes a layer from the layer stack.
     *
     * @param layer The layer to remove from the stack.
     */
    public void popLayer(Layer layer) {
        int index = this.layers.indexOf(layer);
        if (index != this.layers.size()) {
            this.layers.remove(layer);
        }
    }

    /**
     * Removes an overlay layer from the layer stack.
     *
     * @param overlay The overlay layer to remove from the stack.
     */
    public void popOverlay(Layer overlay) {
        int index = this.layers.indexOf(overlay);
        if (index != this.layers.size()) {
            this.layers.remove(overlay);
        }
    }

    /**
     * Returns the bottom layer of the stack.
     *
     * @return The bottom layer of the stack.
     */
    public Layer begin() {
        return this.layers.get(0);
    }

    /**
     * Returns the top overlay layer of the stack.
     *
     * @return The top overlay layer of the stack.
     */
    public Layer end() {
        return this.layers.get(this.layers.size() - 1);
    }

    /**
     * Returns the number of layers in the stack.
     *
     * @return The number of layers in the stack.
     */
    public int size() {
        return this.layers.size();
    }

    /**
     * Returns the layer at the specified index in the stack.
     *
     * @param index The index of the layer to retrieve.
     * @return The layer at the specified index in the stack.
     */
    public Layer get(int index) {
        return this.layers.get(index);
    }

    /**
     * Removes all layers from the stack and deletes them using the `_delete` macro.
     */
    public void clear() {
        for (Layer l : this.layers) {
            Pointer<Layer> ref = new RawPointer<Layer>(l);
            _delete(ref);
        }
    }

    @Override
    public Iterator<Layer> iterator() {
        return this.layers.iterator();
    }
}
