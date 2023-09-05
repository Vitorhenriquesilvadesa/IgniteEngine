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

import org.ignite.core.macros.debug.Pair;

/**
 * The `Input` abstract class provides an interface for handling input events
 * such as key presses and mouse events.
 * Subclasses of `Input` should implement the methods to interact with the
 * specific input system.
 */
public abstract class Input {

    protected static Input instance;

    /**
     * Checks if the given keycode is currently pressed.
     *
     * @param keycode The keycode of the key to check.
     * @return `true` if the key is pressed, otherwise `false`.
     */
    public static boolean isKeyPressed(int keycode) {
        return instance.isKeyPressedImpl(keycode);
    }

    /**
     * Checks if the given mouse button is currently pressed.
     *
     * @param button The button to check (e.g., GLFW_MOUSE_BUTTON_LEFT).
     * @return `true` if the button is pressed, otherwise `false`.
     */
    public static boolean isMouseButtonPressed(int button) {
        return instance.isMouseButtonPressedImpl(button);
    }

    /**
     * Gets the current mouse cursor's X-coordinate.
     *
     * @return The X-coordinate of the mouse cursor.
     */
    public static float getMouseX() {
        return instance.getMouseXImpl();
    }

    /**
     * Gets the current mouse cursor's Y-coordinate.
     *
     * @return The Y-coordinate of the mouse cursor.
     */
    public static float getMouseY() {
        return instance.getMouseYImpl();
    }

    /**
     * Gets the current mouse cursor position as a `Pair` object containing X and Y
     * coordinates.
     *
     * @return The current mouse cursor position.
     */
    public static Pair<Float, Float> getMousePosition() {
        return instance.getMousePositionImpl();
    }

    /**
     * Checks if the given keycode is currently pressed (implementation-specific).
     *
     * @param keycode The keycode of the key to check.
     * @return `true` if the key is pressed, otherwise `false`.
     */
    protected abstract boolean isKeyPressedImpl(int keycode);

    /**
     * Checks if the given mouse button is currently pressed
     * (implementation-specific).
     *
     * @param button The button to check (e.g., GLFW_MOUSE_BUTTON_LEFT).
     * @return `true` if the button is pressed, otherwise `false`.
     */
    protected abstract boolean isMouseButtonPressedImpl(int button);

    /**
     * Gets the current mouse cursor's X-coordinate (implementation-specific).
     *
     * @return The X-coordinate of the mouse cursor.
     */
    protected abstract float getMouseXImpl();

    /**
     * Gets the current mouse cursor's Y-coordinate (implementation-specific).
     *
     * @return The Y-coordinate of the mouse cursor.
     */
    protected abstract float getMouseYImpl();

    /**
     * Gets the current mouse cursor position as a `Pair` object containing X and Y
     * coordinates (implementation-specific).
     *
     * @return The current mouse cursor position.
     */
    protected abstract Pair<Float, Float> getMousePositionImpl();
}
