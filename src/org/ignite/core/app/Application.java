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

import org.ignite.core.macros.memory.SharedPointer;
import org.ignite.events.*;
import org.ignite.layers.Layer;
import org.ignite.layers.LayerStack;
import org.ignite.layers.imgui.ImGuiLayer;
import org.ignite.layers.ui.FontFamily;
import org.ignite.layers.ui.UIManager;
import org.ignite.mathf.Vector4;
import org.ignite.platform.general.Window;
import org.ignite.platform.general.WindowProps;
import org.ignite.platform.windows.WindowsInput;
import org.ignite.platform.windows.WindowsWindow;
import org.ignite.renderertools.buffers.general.*;
import org.ignite.renderertools.renderer.RenderCommand;
import org.ignite.renderertools.renderer.Renderer;
import org.ignite.renderertools.renderer.RendererAPI;
import org.ignite.renderertools.shader.Shader;
import org.ignite.system.debbuging.DebugColor;
import org.ignite.system.debbuging.DebugConsole;
import org.ignite.system.debbuging.DebugLevel;
import org.ignite.system.debbuging.DebugMessage;
import org.ignite.system.log.LogLevel;
import org.ignite.system.log.Logger;
import org.ignite.annotations.Define;

import static org.ignite.core.macros.debug.Macros.*;

import java.lang.reflect.InvocationTargetException;

@Define("IGNITE_API")
public abstract class Application extends EventManager {

    /** The logger for client-side logs. */
    public static Logger ClientLog = new Logger("Ignite Engine", LogLevel.TRACE);

    /** Flag to indicate if the application is running. */
    protected static boolean RUNNING = true;

    public static float FPS = 0f;

    /** The main window of the application. */
    private Window window;

    /** Stack of layers managed by the application. */
    private LayerStack layerStack = new LayerStack();

    /** The instance of the application (singleton). */
    protected static Application app;

    private static boolean isInit = false;

    protected ImGuiLayer imGuiLayer;

    /**
     * a parameter that defines whether exceptions for adding, removing or executing
     * events will be thrown or not according to the value.
     */
    private boolean throwEventExceptions = true;

    private SharedPointer<VertexBuffer> vertexBuffer = new SharedPointer<VertexBuffer>();
    private SharedPointer<VertexBuffer> squareVB = new SharedPointer<VertexBuffer>();
    private SharedPointer<IndexBuffer> indexBuffer = new SharedPointer<IndexBuffer>();
    private SharedPointer<IndexBuffer> squareIB = new SharedPointer<IndexBuffer>();
    private SharedPointer<VertexArray> vertexArray = new SharedPointer<VertexArray>();
    private SharedPointer<VertexArray> squareVA = new SharedPointer<VertexArray>();

    public float lastFPSValue = 0f;
    private Shader shader;
    private Shader blueShader;

    /**
     * Constructs a new Application instance.
     * It creates the application's window with default properties and sets the
     * event callback.
     */
    protected Application() {

        this.window = WindowsWindow.create(new WindowProps());
        this.window.setEventCallback(this::onEvent);
        this.imGuiLayer = new ImGuiLayer();

        float[] vertices = new float[]{

                -0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
                0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f,
        };

        float[] square_vertices = new float[]{

                -0.8f, -0.8f, 0.0f,
                0.8f, -0.8f, 0.0f,
                0.8f, 0.8f, 0.0f,
                -0.8f, 0.8f, 0.0f
        };

        int[] indices = new int[]{0, 1, 2};
        int[] squareIndices = new int[]{0, 1, 2, 2, 3, 0};

        this.vertexArray.reset(VertexArray.create());
        this.squareVA.reset(VertexArray.create());

        this.vertexBuffer.reset(VertexBuffer.create(vertices));
        this.squareVB.reset(VertexBuffer.create(square_vertices));

        BufferLayout layout = new BufferLayout(
                new BufferElement(ShaderDataType.Float3, "a_Position"),
                new BufferElement(ShaderDataType.Float4, "a_Color"));

        BufferLayout blueLayout = new BufferLayout(new BufferElement(ShaderDataType.Float3, "a_Position"));

        squareVB.getReference().setLayout(blueLayout);
        squareVA.getReference().addVertexBuffer(squareVB);

        this.vertexBuffer.getReference().setLayout(layout);
        this.vertexArray.getReference().addVertexBuffer(vertexBuffer);

        this.indexBuffer.reset(IndexBuffer.create(indices));
        this.squareIB.reset(IndexBuffer.create(squareIndices));

        vertexArray.getReference().setIndexBuffer(indexBuffer);
        squareVA.getReference().setIndexBuffer(squareIB);

        this.shader = new Shader("renderertools/shaders/vertex.shader",
                "renderertools/shaders/fragment.shader");

        this.blueShader = new Shader("renderertools/shaders/square_vertex.shader",
                "renderertools/shaders/square_fragment.shader");
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
        DebugConsole.sendMessage(new DebugMessage("Window successful initialized!", DebugColor.BLUE, DebugLevel.INFO, "Application"));
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
        EventDispatcher dispatcher = new EventDispatcher(e);
        dispatcher.dispatch(EventType.WindowClose, this::onWindowClose);
        ClientLog.debug(e);

        for (Layer layer : this.layerStack) {

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

        int frameCount = 0;

        while (Application.RUNNING) {
            this.update();
            Time.update();

            RenderCommand.setClearColor(new Vector4(0f, 0f, 0f, 1f));
            RenderCommand.clear();
//            Renderer.beginScene();
//            shader.bind();
//            Renderer.submit(vertexArray);
//            shader.unbind();
//            Renderer.endScene();

            for (Layer layer : this.layerStack) {
                layer.onUpdate();
            }

            imGuiLayer.begin();

            for (Layer layer : this.layerStack) {
                layer.onImGuiRender();
            }

            imGuiLayer.end();

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
     * Adds a new layer to the top of the layer stack.
     * It calls the `onAttach` method of the layer after it has been added.
     *
     * @param layer The layer to be added.
     */
    protected void pushLayer(Layer layer) {
        this.layerStack.pushLayer(layer);
        layer.onAttach();
    }

    /**
     * Adds a new overlay layer to the top of the layer stack.
     * It calls the `onAttach` method of the overlay layer after it has been added.
     *
     * @param layer The overlay layer to be added.
     */
    protected void pushOverlay(Layer layer) {
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