/**
 * Ignite Engine - A powerful game engine in Java.
 *
 * @license MIT License
 *
 * @author Creator: Lord Vtko
 * @version 1.0.0
 * @since 2023-07-28
 */

/**
 * The `Application` class represents the base class for creating and running an
 * Ignite Engine application.
 * It provides functionalities to manage the application's window, layers,
 * events, and triggers.
 */

package org.ignite.core.app;

import org.ignite.events.Event;
import org.ignite.events.EventDispatcher;
import org.ignite.events.EventType;
import org.ignite.events.WindowCloseEvent;
import org.ignite.layers.Layer;
import org.ignite.layers.LayerStack;
import org.ignite.layers.imgui.ImGuiLayer;
import org.ignite.platform.general.Window;
import org.ignite.platform.general.WindowProps;
import org.ignite.platform.windows.WindowsInput;
import org.ignite.platform.windows.WindowsWindow;
import org.ignite.renderer.Shader;
import org.ignite.system.exceptions.DuplicatedTickEventException;
import org.ignite.system.exceptions.DuplicatedTriggerEventException;
import org.ignite.system.exceptions.InexistentTickEventException;
import org.ignite.system.exceptions.InexistentTriggerEventException;
import org.ignite.system.functions.EventDescriptor;
import org.ignite.system.functions.GenericFunction;
import org.ignite.system.functions.Tick;
import org.ignite.system.functions.TickEvent;
import org.ignite.system.functions.Trigger;
import org.ignite.system.functions.TriggerEvent;
import org.ignite.system.log.LogLevel;
import org.ignite.system.log.Logger;
import org.ignite.system.meta.Define;

import static org.ignite.core.macros.Macros.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Define("IGNITE_API")
public abstract class Application {

    /** The logger for client-side logs. */
    public static Logger ClientLog = new Logger("Ignite Engine", LogLevel.TRACE);

    /** Flag to indicate if the application is running. */
    private static boolean RUNNING = true;

    /** The main window of the application. */
    private Window window;

    /** Stack of layers managed by the application. */
    private LayerStack layerStack = new LayerStack();

    /** The instance of the application (singleton). */
    protected static Application app;

    /** Map to store trigger events by their names. */
    private Map<String, TriggerEvent> triggerEvents = new HashMap<String, TriggerEvent>();

    /** Map to store tick events by their names. */
    private Map<String, TickEvent> tickEvents = new HashMap<String, TickEvent>();

    /** List to store the names of trigger events in the order they were added. */
    private List<String> triggerEventNames = new ArrayList<String>();

    /** List to store the names of tick events in the order they were added. */
    private List<String> tickEventNames = new ArrayList<String>();

    private static boolean isInit = false;

    protected ImGuiLayer imGuiLayer;

    /**
     * a parameter that defines whether exceptions for adding, removing or executing
     * events will be thrown or not according to the value.
     */
    private boolean throwEventExceptions = true;

    private int vertexArray, vertexBuffer, indexBuffer;

    private Shader shader;

    /**
     * Constructs a new Application instance.
     * It creates the application's window with default properties and sets the
     * event callback.
     */
    public Application() {
        this.window = WindowsWindow.create(new WindowProps());
        this.window.setEventCallback(this::onEvent);
        this.imGuiLayer = new ImGuiLayer();

        this.vertexArray = glGenVertexArrays();
        glBindVertexArray(this.vertexArray);

        this.vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, this.vertexArray);

        float[] vertices = new float[] {

                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f, 0.5f, 0.0f
        };

        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        this.indexBuffer = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.indexBuffer);

        int[] indices = new int[] { 0, 1, 2 };
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        this.shader = new Shader("renderer/shaders/vertex.shader",
                "renderer/shaders/fragment.shader");
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

        WindowsInput.init();

        if (!System.getProperty("os.name").startsWith("Windows") || !System.getProperty("os.arch").contains("64")) {
            ClientLog.error("Ignite Engine only supports Windows x64");
            System.exit(-1);
        }

        if (DEBUG) {
            System.setProperty("DEBUG", "true");
        }

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
        EventDispatcher dispatcher = new EventDispatcher(e);
        dispatcher.dispatch(EventType.WindowClose, this::onWindowClose);
        ClientLog.debug(e);

        for (int i = this.layerStack.size() - 1; i >= 0; i--) {
            Layer layer = this.layerStack.get(i);
            layer.onEvent(e);
            if (e.isHandled()) {
                break;
            }
        }
    }

    /**
     * Starts the application and enters the main loop.
     * It calls the `start` method, then continuously updates the layers and the
     * application's window until the application is no longer running.
     */
    public void run() {
        this.start();
        pushOverlay(imGuiLayer);

        while (Application.RUNNING) {
            this.update();
            glClearColor(0, 0, 0, 1);
            glClear(GL_COLOR_BUFFER_BIT);

            this.shader.bind();
            glBindVertexArray(this.vertexArray);
            glDrawElements(GL_TRIANGLES, 3, GL_UNSIGNED_INT, 0);
            this.shader.unbind();

            for (Layer layer : this.layerStack) {
                layer.onUpdate();
            }

            this.imGuiLayer.begin();

            for (Layer layer : this.layerStack) {

                layer.onImGuiRender();
            }

            this.imGuiLayer.end();

            this.window.onUpdate();
        }
    }

    /**
     * Abstract method to be implemented by the concrete subclass.
     * It is called at the beginning of the application's execution and can be used
     * to perform initial setup.
     */
    public void start() {

    }

    /**
     * Abstract method to be implemented by the concrete subclass.
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
     * Adds a new layer to the top of the layer stack.
     * It calls the `onAttach` method of the layer after it has been added.
     *
     * @param layer The layer to be added.
     */
    public void pushLayer(Layer layer) {
        this.layerStack.pushLayer(layer);
        layer.onAttach();
    }

    /**
     * Adds a new overlay layer to the top of the layer stack.
     * It calls the `onAttach` method of the overlay layer after it has been added.
     *
     * @param layer The overlay layer to be added.
     */
    public void pushOverlay(Layer layer) {
        this.layerStack.pushOverlay(layer);
        layer.onAttach();
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
     * @param app The application instance to set.
     */
    public static void setInstance(Application app) {
        Application.app = app;
    }

    /**
     * Adds a new trigger event with the given name and associated caller.
     *
     * @param caller    The caller that will handle the trigger event.
     * @param eventName The name of the trigger event.
     * @throws DuplicatedTriggerEventException if a trigger event with the same name
     *                                         already exists.
     */
    public void addTriggerEvent(GenericFunction caller, String eventName) throws DuplicatedTriggerEventException {

        TriggerEvent triggerEvent = new TriggerEvent(caller, eventName);

        if (!this.triggerEvents.containsKey(eventName)) {

            this.triggerEvents.put(eventName, triggerEvent);
            this.triggerEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTriggerEventException(
                    "The trigger with name: \"" + triggerEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Adds a new trigger event with the given name, associated caller, and
     * additional event attributes.
     *
     * @param caller          The caller that will handle the trigger event.
     * @param eventName       The name of the trigger event.
     * @param eventAttributes Additional attributes associated with the trigger
     *                        event.
     * @throws DuplicatedTriggerEventException if a trigger event with the same name
     *                                         already exists.
     */
    public void addTriggerEvent(GenericFunction caller, String eventName, Object... eventAttributes)
            throws DuplicatedTriggerEventException {

        TriggerEvent triggerEvent = new TriggerEvent(caller, new EventDescriptor(eventName, eventAttributes));

        if (!this.triggerEvents.containsKey(eventName)) {

            this.triggerEvents.put(eventName, triggerEvent);
            this.triggerEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTriggerEventException(
                    "The trigger with name: \"" + triggerEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Retrieves a trigger event by its name.
     *
     * @param triggerEventName The name of the trigger event to retrieve.
     * @return The trigger event associated with the given name.
     * @throws InexistentTriggerEventException if the trigger event with the given
     *                                         name
     *                                         does not exist.
     */
    private TriggerEvent getTriggerEvent(String triggerEventName) throws InexistentTriggerEventException {

        if (this.triggerEvents.containsKey(triggerEventName)) {

            return this.triggerEvents.get(triggerEventName);

        } else if (this.throwEventExceptions) {

            throw new InexistentTriggerEventException(
                    "The required trigger with name: \"" + triggerEventName + "\" does not exist.");

        } else {
            return null;
        }
    }

    /**
     * Removes a trigger event by its name.
     *
     * @param triggerEventName The name of the trigger event to remove.
     * @throws InexistentTriggerEventException if the trigger event with the given
     *                                         name
     *                                         does not exist or has already been
     *                                         removed.
     */
    public void removeTriggerEvent(String triggerEventName) throws InexistentTriggerEventException {

        if (this.triggerEventNames.contains(triggerEventName)) {

            this.triggerEvents.remove(triggerEventName);
            this.triggerEventNames.remove(triggerEventName);

        } else {
            throw new InexistentTriggerEventException("The required trigger with name: \"" + triggerEventName
                    + "\" does not exist or has already been removed.");
        }
    }

    /**
     * Calls a trigger event with the given name and condition.
     *
     * @param triggerEventName The name of the trigger event to call.
     * @param condition        The condition that determines whether the trigger
     *                         should be executed.
     */
    public void callTriggerEvent(String triggerEventName, boolean condition) {

        TriggerEvent event;

        try {
            if ((event = this.getTriggerEvent(triggerEventName)) == null) {

                throw new InexistentTriggerEventException("The required trigger with name: \"" + triggerEventName
                        + "\" does not exist or has already been removed.");
            }

        } catch (InexistentTriggerEventException e) {

            if (this.throwEventExceptions) {
                throw e;
            }

            return;
        }

        Trigger trigger = event.getTrigger();
        trigger.call(condition, event.getMethod());
    }

    /**
     * Adds a new tick event with the given name and associated caller.
     *
     * @param caller    The caller that will handle the tick event.
     * @param eventName The name of the tick event.
     * @throws DuplicatedTickEventException if a tick event with the same name
     *                                      already exists.
     */

    public void addTickEvent(GenericFunction caller, String eventName) throws DuplicatedTickEventException {
        TickEvent tickEvent = new TickEvent(caller, eventName);

        if (!this.tickEvents.containsKey(eventName)) {

            this.tickEvents.put(eventName, tickEvent);
            this.tickEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTickEventException(
                    "The tick with name: \"" + tickEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Adds a new tick event with the given name, associated caller, and additional
     * event attributes.
     *
     * @param caller          The caller that will handle the tick event.
     * @param eventName       The name of the tick event.
     * @param eventAttributes Additional attributes associated with the tick event.
     * @throws DuplicatedTickEventException if a tick event with the same name
     *                                      already exists.
     */

    public void addTickEvent(GenericFunction caller, String eventName, Object... eventAttributes)
            throws DuplicatedTickEventException {

        TickEvent tickEvent = new TickEvent(caller, new EventDescriptor(eventName, eventAttributes));

        if (!this.tickEvents.containsKey(eventName)) {

            this.tickEvents.put(eventName, tickEvent);
            this.tickEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTickEventException(
                    "The tick with name: \"" + tickEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Retrieves a tick event by its name.
     *
     * @param tickEventName The name of the tick event to retrieve.
     * @return The tick event associated with the given name.
     * @throws InexistentTickEventException if the tick event with the given name
     *                                      does not exist.
     */

    private TickEvent getTickEvent(String tickEventName) throws InexistentTickEventException {

        if (this.tickEvents.containsKey(tickEventName)) {

            return this.tickEvents.get(tickEventName);

        } else if (this.throwEventExceptions) {

            throw new InexistentTickEventException(
                    "The required tick with name: \"" + tickEventName + "\" does not exist.");

        } else {
            return null;
        }
    }

    /**
     * Removes a tick event by its name.
     *
     * @param tickEventName The name of the tick event to remove.
     * @throws InexistentTickEventException if the tick event with the given name
     *                                      does not exist or has already been
     *                                      removed.
     */

    public void removeTickEvent(String tickEventName) throws InexistentTickEventException {

        if (this.tickEventNames.contains(tickEventName)) {

            this.tickEvents.remove(tickEventName);
            this.tickEventNames.remove(tickEventName);

        } else if (this.throwEventExceptions) {

            throw new InexistentTickEventException("The required tick with name: \"" + tickEventName
                    + "\" does not exist or has already been removed.");
        }
    }

    /**
     * Calls a tick event by its name and condition.
     *
     * @param tickEventName The name of the tick event to call.
     * @param condition     The condition that determines whether the tick event
     *                      should be executed.
     */

    public void callTickEvent(String tickEventName, boolean condition) {

        TickEvent event;

        try {
            if ((event = this.getTickEvent(tickEventName)) == null) {

                throw new InexistentTriggerEventException(
                        "The required tick with name: \"" + tickEventName + "\" does not exist.");
            }

        } catch (InexistentTriggerEventException e) {

            if (this.throwEventExceptions) {

                throw e;
            }

            return;
        }

        Tick tick = event.getTick();
        tick.call(condition, event.getMethod());
    }

    public void disableEventExceptions() {
        throwEventExceptions = false;
    }

    public void enableEventExceptions() {
        throwEventExceptions = true;
    }
}