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

import org.ignite.annotations.Define;

/**
 * The `WindowProps` class represents the properties of a window.
 * It includes the window's title, width, and height.
 */
@Define("IGNITE_API")
public class WindowProps {

    private String title;
    private int width;
    private int height;

    /**
     * Constructs a new `WindowProps` instance with the given title, width, and
     * height.
     *
     * @param title  The title of the window.
     * @param width  The width of the window.
     * @param height The height of the window.
     */
    public WindowProps(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a new `WindowProps` instance with default values.
     * The default title is "Ignite Engine", width is 1280, and height is 720.
     */
    public WindowProps() {
        this.title = "Ignite Engine";
        this.width = 1920;
        this.height = 1080;
    }

    /**
     * Gets the title of the window.
     *
     * @return The title of the window.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the width of the window.
     *
     * @return The width of the window.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the window.
     *
     * @param width The new width of the window.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the window.
     *
     * @return The height of the window.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the window.
     *
     * @param height The new height of the window.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns a string representation of the `WindowProps` object.
     *
     * @return A string representation of the `WindowProps` object.
     */
    @Override
    public String toString() {
        return "WindowProps: " + this.title + "(" + this.width + ", " + this.height + ")";
    }
}
