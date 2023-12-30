/**
 * Ignite Engine - A powerful game engine in Java.
 *
 * @license MIT License
 * @author Creator: Lord Vtko
 * @version 1.0.0
 * @since 2023-07-28
 * <p>
 * The `Application` class represents the base class for creating and running an
 * Ignite Engine application.
 * It provides functionalities to manage the application's window, layers,
 * events, and triggers.
 * <p>
 * The `Application` class represents the base class for creating and running an
 * Ignite Engine application.
 * It provides functionalities to manage the application's window, layers,
 * events, and triggers.
 */

/**
 * The `Application` class represents the base class for creating and running an
 * Ignite Engine application.
 * It provides functionalities to manage the application's window, layers,
 * events, and triggers.
 */

package org.ignite.core.app;

import org.ignite.engine.Engine;
import org.ignite.events.*;
import org.ignite.mathf.Vector4;
import org.ignite.platform.general.Window;
import org.ignite.platform.general.WindowProps;
import org.ignite.platform.windows.WindowsInput;
import org.ignite.platform.windows.WindowsWindow;
import org.ignite.renderertools.renderer.RenderCommand;
import org.ignite.renderertools.renderer.RendererAPI;
import org.ignite.renderertools.shader.Shader;
import org.ignite.system.Time;
import org.ignite.system.debbuging.DebugColor;
import org.ignite.system.debbuging.DebugConsole;
import org.ignite.system.debbuging.DebugLevel;
import org.ignite.system.debbuging.DebugMessage;
import org.ignite.general.log.*;
import org.ignite.annotations.Define;

import static org.ignite.general.macros.debug.Macros.*;

import java.lang.reflect.InvocationTargetException;

@Define("IGNITE_API")
public abstract class Application extends EventManager {

    /** The logger for client-side logs. */
    public static Logger ClientLog = new Logger("Engine", LogLevel.TRACE);

    /** Flag to indicate if the application is running. */
    protected static boolean RUNNING = true;

    public static float FPS = 0f;

    /** The main window of the application. */
    private Window window;

    /** The instance of the application (singleton). */
    protected static Application app;

    private static boolean isInit = false;

    /**
     * a parameter that defines whether exceptions for adding, removing or executing
     * events will be thrown or not according to the value.
     */
    private boolean throwEventExceptions = true;

    public float lastFPSValue = 0f;

    /**
     * Constructs a new Application instance.
     * It creates the application's window with default properties and sets the
     * event callback.
     */
    protected Application() {

        this.window = WindowsWindow.create(new WindowProps());
        this.window.setEventCallback(this::onEvent);
    }

    /**
     * Initializes the Ignite Engine application.
     * It initializes the Windows Input system and checks if the application is
     * running on Windows x64.
     * Additionally, it sets the DEBUG system property and enables line separators
     * in the logger.
     */
    public static void init() {

        if (!isInit) {
            isInit = true;

        } else {
            throw new IllegalCallerException("The application must be call init only one time.");
        }

        ClientLog.trace("Application initialized.");

        if (System.getProperty("os.name").startsWith("Windows")) {
            WindowsInput.init();
        }

        if (!System.getProperty("os.name").startsWith("Windows") || !System.getProperty("os.arch").contains("64")) {
            ClientLog.error("Ignite Engine only supports Windows x64");
            System.exit(-1);
        }

        if (DEBUG) {
            System.setProperty("DEBUG", "true");
        }

        DebugConsole.sendMessage(new DebugMessage("Application successful initialized!", DebugColor.BLUE, DebugLevel.INFO, "Application"));
        DebugConsole.sendMessage(new DebugMessage("Welcome to Ignite Engine 1.0.1", DebugColor.BLUE, DebugLevel.INFO, "Application"));
        DebugConsole.sendMessage(new DebugMessage("Rendering API: " + RendererAPI.getAPI(), DebugColor.BLUE, DebugLevel.INFO, "Application"));

        // Logger.setLineSeparator(true);
    }

    /**
     * Callback function for handling events dispatched by the application's window.
     * It dispatches the WindowCloseEvent to the `onWindowClose` method and passes
     * the event to the layer stack for handling.
     *
     * @param e The event to be handled.
     */
    public void onEvent(Event e) {

    }

    /**
     * Starts the application and enters the main loop.
     * It calls the `start` method, then continuously updates the layers and the
     * application's window until the application is no longer running.
     */

    public void run() {

        this.start();
        Engine.init();

        int frameCount = 0;

        while (Application.RUNNING) {
            Time.update();

            RenderCommand.setClearColor(new Vector4(0f, 0f, 0f, 1f));
            RenderCommand.clear();

            this.update();

            this.window.onUpdate();

            float frameTime = Time.deltaTime();
            FPS += frameTime;

            if (FPS >= 1.0f) {
                lastFPSValue = 1 / Time.deltaTime();
                FPS = 0.0f;
            }
        }
    }


    /**
     * Method to be implemented by the concrete subclass.
     * It is called at the beginning of the application's execution and can be used
     * to perform initial setup.
     */
    public void start() {

    }

    /**
     * Method to be implemented by the concrete subclass.
     * It is called repeatedly in the main loop and can be used to update the
     * application's state.
     */
    public void update() {
    }

    /**
     * Callback method for handling the WindowCloseEvent.
     * It sets the `RUNNING` flag to false, indicating that the application should
     * stop running.
     *
     * @param e The WindowCloseEvent.
     * @return Always returns true to indicate that the event has been handled.
     */
    public boolean onWindowClose(WindowCloseEvent e) {
        RUNNING = false;
        return true;
    }

    /**
     * Retrieves the application's main window.
     *
     * @return The application's window.
     */
    public Window getWindow() {
        return this.window;
    }

    /**
     * Retrieves the instance of the application (singleton).
     *
     * @return The application instance.
     */
    public static Application getInstance() {
        return app;
    }

    /**
     * Sets the instance of the application (singleton).
     *
     * @param instance The application instance to set.
     */
    public static <T extends Application> void setInstance(Class<T> instance) {
        try {
            Application.app = instance.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            ClientLog.error("The class \"" + instance.getSimpleName() + "\" not extends of Application");
        }
    }
}