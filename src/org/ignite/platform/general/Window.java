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

package org.ignite.platform.general;

import org.ignite.events.*;
import org.ignite.system.meta.Define;

/**
 * The `Window` interface defines the common methods and properties for a
 * platform window.
 * Concrete window implementations for specific platforms should implement this
 * interface.
 */
@Define("IGNITE_API")
public interface Window {

    /**
     * Updates the window state and processes events.
     * This method should be called once per frame in the main loop.
     */
    public abstract void onUpdate();

    /**
     * Gets the width of the window.
     *
     * @return The width of the window.
     */
    public abstract int getWidth();

    /**
     * Gets the height of the window.
     *
     * @return The height of the window.
     */
    public abstract int getHeight();

    /**
     * Sets the event callback function for the window.
     * The event callback function will be invoked when window events occur.
     *
     * @param callback The event callback function to set.
     */
    public abstract void setEventCallback(EventCallbackFn<Event> callback);

    /**
     * Enables or disables vertical synchronization (VSync) for the window.
     * VSync synchronizes the window's frame updates with the monitor's refresh
     * rate.
     *
     * @param enabled `true` to enable VSync, `false` to disable.
     */
    public abstract void setVSync(boolean enabled);

    /**
     * Checks if vertical synchronization (VSync) is enabled for the window.
     *
     * @return `true` if VSync is enabled, `false` otherwise.
     */
    public abstract boolean isVSync();

    /**
     * Gets the native handle of the window.
     * The native handle can be used for platform-specific window interactions.
     *
     * @return The native handle of the window.
     */
    public abstract long getNativeWindow();

    // The following methods are intended to be used in concrete window
    // implementations and are commented out in the interface.

    // /**
    // * Creates a new window with the specified properties.
    // *
    // * @param props The properties of the new window.
    // * @return The created window.
    // */
    // public Window create(WindowProps props);

    // /**
    // * Creates a new window with default properties.
    // *
    // * @return The created window.
    // */
    // public Window create();

}
