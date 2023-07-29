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

package org.ignite.platform.windows;

import org.ignite.core.app.Application;
import org.ignite.core.macros.Pair;
import org.ignite.platform.general.Input;

import static org.lwjgl.glfw.GLFW.*;

public class WindowsInput extends Input {

    /**
     * Initializes the WindowsInput instance.
     * This method should be called once at the application's startup to set the
     * input instance.
     */
    public static void init() {
        instance = new WindowsInput();
    }

    /**
     * Checks whether a keyboard key is currently pressed or being held down.
     *
     * @param keycode The key code of the keyboard key to check.
     * @return True if the key is pressed or being held down, false otherwise.
     */
    @Override
    protected boolean isKeyPressedImpl(int keycode) {
        long window = Application.getInstance().getWindow().getNativeWindow();
        int state = glfwGetKey(window, keycode);
        return state == GLFW_PRESS || state == GLFW_REPEAT;
    }

    /**
     * Checks whether a mouse button is currently pressed.
     *
     * @param button The mouse button code to check.
     * @return True if the mouse button is pressed, false otherwise.
     */
    @Override
    protected boolean isMouseButtonPressedImpl(int button) {
        long window = Application.getInstance().getWindow().getNativeWindow();
        int state = glfwGetMouseButton(window, button);
        return state == GLFW_PRESS;
    }

    /**
     * Gets the current X position of the mouse cursor relative to the window.
     *
     * @return The X position of the mouse cursor.
     */
    @Override
    protected float getMouseXImpl() {
        long window = Application.getInstance().getWindow().getNativeWindow();
        double[] xPos = new double[1];
        double[] yPos = new double[1];

        glfwGetCursorPos(window, xPos, yPos);

        return (float) xPos[0];
    }

    /**
     * Gets the current Y position of the mouse cursor relative to the window.
     *
     * @return The Y position of the mouse cursor.
     */
    @Override
    protected float getMouseYImpl() {
        long window = Application.getInstance().getWindow().getNativeWindow();
        double[] xPos = new double[1];
        double[] yPos = new double[1];

        glfwGetCursorPos(window, xPos, yPos);

        return (float) yPos[0];
    }

    /**
     * Gets the current position of the mouse cursor relative to the window.
     *
     * @return A Pair object representing the X and Y coordinates of the mouse
     *         cursor.
     */
    @Override
    protected Pair<Float, Float> getMousePositionImpl() {
        long window = Application.getInstance().getWindow().getNativeWindow();
        double[] xPos = new double[1];
        double[] yPos = new double[1];

        glfwGetCursorPos(window, xPos, yPos);

        return new Pair<Float, Float>((float) xPos[0], (float) yPos[0]);
    }
}
