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

import static org.ignite.core.app.Application.ClientLog;
import static org.ignite.general.macros.debug.Macros.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.HashMap;
import java.util.Map;

import org.ignite.platform.general.GraphicsContext;
import org.ignite.platform.opengl.OpenGLContext;
import org.ignite.annotations.Define;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.ignite.events.*;
import org.ignite.platform.general.EventCallbackFn;
import org.ignite.platform.general.Window;
import org.ignite.platform.general.WindowProps;

/**
 * The `WindowsWindow` class implements the `Window` interface and provides
 * functionality for creating and managing a GLFW window on Windows.
 * It enables event handling and OpenGL context management for the window.
 */

@Define("IGNITE_API")
public class WindowsWindow implements Window {

    /**
     * The `WindowData` class holds data related to a GLFW window, such as title,
     * width, and height.
     * It also contains the event callback function for the window.
     */
    public class WindowData {

        String title;
        int width;
        int height;
        static EventCallbackFn<Event> eventCallbackFn;

        /**
         * Constructs a new `WindowData` instance with the given title, width, and
         * height.
         *
         * @param title  The title of the window.
         * @param width  The width of the window.
         * @param height The height of the window.
         */
        public WindowData(String title, int width, int height) {
            this.title = title;
            this.width = width;
            this.height = height;
        }

        /**
         * Sets the event callback function for the window.
         *
         * @param callback The event callback function to set.
         */
        public void setEventCallbackFn(EventCallbackFn<Event> callback) {
            WindowData.eventCallbackFn = callback;
        }

        /**
         * Calls the event callback function with the given event.
         *
         * @param e The event to pass to the event callback function.
         */
        public void eventCallback(Event e) {
            WindowData.eventCallbackFn.apply(e);
        }
    }

    /**
     * The `ErrorCallback` class implements the GLFWErrorCallbackI interface to
     * handle GLFW error callbacks.
     * It prints the error message to the `ClientLog` when an error occurs.
     */
    public class ErrorCallback implements GLFWErrorCallbackI {
        @Override
        public void invoke(int error, long description) {
            String errorMessage = getDescription(description);

            ClientLog.error(errorMessage);
        }

        private String getDescription(long description) {
            return GLFWErrorCallback.getDescription(description);
        }
    }

    private String title;
    private boolean VSync;
    private static boolean GLFWInitialized;
    private long window;
    private GraphicsContext context;
    private WindowData data;
    private Map<Long, WindowData> windowDataMap = new HashMap<>();

    /**
     * Constructs a new `WindowsWindow` instance with the given window properties.
     *
     * @param props The window properties for the new window.
     */
    public WindowsWindow(WindowProps props) {
        this.init(props);
    }

    private void init(WindowProps props) {

        this.data = new WindowData(title, props.getWidth(), props.getHeight());
        this.data.title = props.getTitle();
        windowDataMap.put(window, data);

        ClientLog.info("Creating window: " + props);

        if (!WindowsWindow.GLFWInitialized) {

            boolean success = glfwInit();
            _assert("Could not initialize GLFW!", success);
            glfwSetErrorCallback(new ErrorCallback());
        }

        this.window = glfwCreateWindow(props.getWidth(), props.getHeight(), props.getTitle(), NULL, NULL);

        this.context = new OpenGLContext(this.window);

        this.context.init();

        glfwDefaultWindowHints();
        createCapabilities();
        setVSync(true);

        glfwMaximizeWindow(window);

        int[] widthA = new int[1];
        int[] heightA = new int[1];
        glfwGetFramebufferSize(window, widthA, heightA);

        this.data = new WindowData(title, widthA[0], heightA[0]);
        windowDataMap.replace(window, new WindowData(title, widthA[0], heightA[0]));
        glfwSetWindowAttrib(window, GLFW_RESIZABLE, GLFW_FALSE);

        /**
         * Set of GLFW Callbacks, like WindowResize, WindowClose, Keyboard, Keys and
         * Mouse functions
         */
        glfwSetWindowSizeCallback(this.window, (long window, int width, int height) -> {
            windowDataMap.replace(window, new WindowData(title, width, height));
            this.data = new WindowData(title, width, height);
            WindowResizeEvent e = new WindowResizeEvent(width, height);
            this.data.eventCallback(e);
        });

        glfwSetWindowCloseCallback(this.window, (long window) -> {
            windowDataMap.replace(window, new WindowData(title, this.data.width, this.data.height));
            this.data = new WindowData(title, this.data.width, this.data.height);
            WindowCloseEvent e = new WindowCloseEvent();
            this.data.eventCallback(e);
        });

        glfwSetKeyCallback(this.window, (long window, int key, int scancode, int action, int mods) -> {

            windowDataMap.replace(window, new WindowData(title, this.data.width, this.data.height));
            this.data = new WindowData(title, this.data.width, this.data.height);

            switch (action) {

                case GLFW_PRESS: {
                    KeyPressedEvent e = new KeyPressedEvent(key, false);
                    this.data.eventCallback(e);
                    break;
                }

                case GLFW_RELEASE: {
                    KeyReleasedEvent e = new KeyReleasedEvent(key);
                    this.data.eventCallback(e);
                    break;
                }

                case GLFW_REPEAT: {
                    KeyPressedEvent e = new KeyPressedEvent(key, true);
                    this.data.eventCallback(e);
                    break;
                }
            }
        });

        glfwSetCharCallback(this.window, (long window, int keycode) -> {
            windowDataMap.replace(window, new WindowData(title, this.data.width, this.data.height));
            this.data = new WindowData(title, this.data.width, this.data.height);
            KeyTypedEvent e = new KeyTypedEvent(keycode);
            this.data.eventCallback(e);
        });

        glfwSetMouseButtonCallback(this.window, (long window, int button, int action, int mods) -> {

            windowDataMap.replace(window, new WindowData(title, this.data.width, this.data.height));
            this.data = new WindowData(title, this.data.width, this.data.height);

            switch (action) {

                case GLFW_PRESS: {
                    MouseButtonPressedEvent e = new MouseButtonPressedEvent(button);
                    this.data.eventCallback(e);
                    break;
                }

                case GLFW_RELEASE: {
                    MouseButtonReleasedEvent e = new MouseButtonReleasedEvent(button);
                    this.data.eventCallback(e);
                    break;
                }
            }
        });

        glfwSetScrollCallback(this.window, (long window, double xOffset, double yOffset) -> {
            windowDataMap.replace(window, new WindowData(title, this.data.width, this.data.height));
            this.data = new WindowData(title, this.data.width, this.data.height);

            MouseScrolledEvent e = new MouseScrolledEvent((float) xOffset, (float) yOffset);
            this.data.eventCallback(e);
        });

        glfwSetCursorPosCallback(this.window, (long window, double xPos, double yPos) -> {
            windowDataMap.replace(window, new WindowData(title, this.data.width, this.data.height));
            this.data = new WindowData(title, this.data.width, this.data.height);

            MouseMovedEvent e = new MouseMovedEvent((float) xPos, (float) yPos);
            this.data.eventCallback(e);
        });
    }

    /**
     * Shuts down and destroys the window.
     */
    public void shutdown() {
        glfwDestroyWindow(this.window);
    }

    @Override
    public void onUpdate() {
        glfwPollEvents();
        this.context.swapBuffers();
    }

    @Override
    public int getWidth() {
        return this.data.width;
    }

    @Override
    public int getHeight() {
        return this.data.height;
    }

    @Override
    public void setEventCallback(EventCallbackFn<Event> callback) {
        WindowData.eventCallbackFn = callback;
    }

    @Override
    public void setVSync(boolean enabled) {
        if (enabled) {
            glfwSwapInterval(1);
        } else {
            glfwSwapInterval(0);
        }

        this.VSync = enabled;
    }

    @Override
    public boolean isVSync() {
        return this.VSync;
    }

    /**
     * Creates a new `WindowsWindow` instance with the given window properties.
     *
     * @param props The window properties for the new window.
     * @return The newly created `WindowsWindow` instance.
     */
    public static Window create(WindowProps props) {
        return new WindowsWindow(props);
    }

    /**
     * Creates a new `WindowsWindow` instance with default window properties.
     *
     * @return The newly created `WindowsWindow` instance with default window
     *         properties.
     */
    public static Window create() {
        return new WindowsWindow(new WindowProps());
    }

    public String getTitle() {
        return this.title;
    }

    public EventCallbackFn<Event> getEventCallback() {
        return WindowData.eventCallbackFn;
    }

    public WindowProps getData() {
        return new WindowProps(this.data.title, this.data.width, this.data.height);
    }

    public long getNativeWindow() {
        return this.window;
    }
}