/**
 * Ignite Engine - A powerful game engine in Java.
 *
 * @license MIT License
 * @author Creator: Lord Vtko
 * @version 1.0.0
 * @since 2023-07-28
 */

package org.ignite.layers.imgui;

import static org.ignite.core.app.Application.ClientLog;

import imgui.*;
import org.ignite.core.app.Application;
import org.ignite.events.*;
import org.ignite.layers.Layer;

import imgui.flag.*;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.ignite.annotations.Define;
import org.ignite.layers.ui.*;
import org.ignite.system.debbuging.DebugConsole;
import org.ignite.system.debbuging.DebugMessage;

import java.util.ArrayList;
import java.util.List;

import static org.ignite.core.macros.debug.Macros.workingDir;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The `ImGuiLayer` class is a custom layer that integrates Dear ImGui into the
 * Ignite Engine.
 * It allows for debugging and user interface interactions.
 */

@Define("IGNITE_API")
public class ImGuiLayer extends Layer {

    private ImGuiImplGl3 implOpenGL = new ImGuiImplGl3();
    private ImGuiImplGlfw implGLFW = new ImGuiImplGlfw();
    public UIFunctionManager functionManager;
    public List<Widget> consoleMessages = new ArrayList<>();
    public static ImGuiTheme theme = ImGuiTheme.getTheme(ImGuiThemes.OneDark);
    FullScreenContainer parentWidget;

    public ImGuiLayer() {
        super("ImGuiLayer");
        ClientLog.debug("ImGuiLayer::Init");
    }

    @Override
    public void onAttach() {
        // Initialize Dear ImGui context and backend.
        ImGui.createContext();
        ImGui.setCurrentContext(ImGui.getCurrentContext());
        ImGuiIO io = ImGui.getIO();

        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);

        UIManager.init();
        this.functionManager = new UIFunctionManager();

        io.addBackendFlags(ImGuiBackendFlags.HasMouseCursors | ImGuiBackendFlags.HasSetMousePos);
        io.setBackendPlatformName("imgui_java_impl_glfw");
        io.setBackendRendererName("imgui_java_impl_opengl3");

        ImGui.styleColorsDark();

        ImGuiStyle style = ImGui.getStyle();

        Application app = Application.getInstance();
        long window = app.getWindow().getNativeWindow();

        implGLFW.init(window, true);
        implOpenGL.init("#version 410");

        this.parentWidget = new FullScreenContainer(

                new StackContainer(0, 32, 300, 1080 - 96,
                        new Container(0, 1080 - 64, 300, 48, new Text("FPS: "+Application.getInstance().lastFPSValue)))
                        .decoration(new BoxDecoration().color(Colors.fromRGBA(30, 30, 30, 255))
                                .borderColor(Colors.fromRGBA(50, 50, 50, 255)).borderWidth(2)),
                new StackContainer(
                        300,
                        1080 - 400,
                        1920 - 600,
                        400).children(new TabContainer(300, 1020 - 400, 1920 - 600, 400, "Project",
                                new Tab(300,
                                        1020 - 400,
                                        1920 - 600,
                                        400, "Debug Console", this.consoleMessages),
                                new Tab(300,
                                        1020 - 400,
                                        1920 - 600,
                                        400, "Project Explorer")
                        ).decoration(new BoxDecoration().color(Colors.fromRGBA(30, 30, 30, 255))
                                .borderColor(Colors.fromRGBA(50, 50, 50, 255)).borderWidth(2).activeColor(Colors.fromRGBA(119, 152, 255, 255)
                                ).hoverColor(Colors.fromRGBA(119, 152, 255, 255))
                        )
                ).decoration(new BoxDecoration().color(Colors.fromRGBA(30, 30, 30, 255))
                        .borderColor(Colors.fromRGBA(50, 50, 50, 255)).borderWidth(2).activeColor(Colors.fromRGBA(203, 122, 119, 255))),

                new VerticalExpanded(1920 - 300, 96, 300
                ).decoration(new BoxDecoration().color(Colors.fromRGBA(30, 30, 30, 255))
                        .borderColor(Colors.fromRGBA(50, 50, 50, 255)).borderWidth(2)),

                new NoDockingStackContainer(0, 0, 1920, 32,
                        new CascadeMenu(28, 0, 80, 32, 80, "New",
                                new CascadeMenuItem("Project").onPressed(functionManager::File_NewProject),
                                new CascadeMenuItem("File").onPressed(functionManager::File_NewFile)
                        ).decoration(new BoxDecoration().borderColor(Colors.fromRGBA(30, 30, 30, 255))
                                .color(Colors.fromRGBA(50, 50, 50, 255))),
                        new CascadeMenu(128, 0, 80, 32, 160, "Open",
                                new CascadeMenuItem("Project").onPressed(functionManager::File_Open),
                                new CascadeMenuItem("Recent Project").onPressed(functionManager::File_OpenRecent),
                                new CascadeMenuItem("File")
                        ).decoration(new BoxDecoration().borderColor(Colors.fromRGBA(30, 30, 30, 255))
                                .color(Colors.fromRGBA(50, 50, 50, 255)))
                ).decoration(new BoxDecoration().borderColor(Colors.fromRGBA(30, 30, 30, 255))
                        .color(Colors.fromRGBA(50, 50, 50, 255))),
                new NoDockingStackContainer(0, 32, 1920, 64,
                        new Image(-8, -4, 24, 24, workingDir + "res/icons/ignite.png")
                ).decoration(new BoxDecoration().color(Colors.fromRGBA(40, 40, 40, 255)))

        ).decoration(new BoxDecoration(
                new Color(0, 0, 0, 0),
                new Color(30, 30, 30, 255),
                new Color(0, 0, 0, 0),
                0, 0, null));

        Screen screen = new Screen(parentWidget);
        Navigator.pushScreen(screen);
    }

    @Override
    public void onDetach() {
        // Cleanup any resources used by ImGui.
        ImGui.destroyContext();
    }

    @Override
    public void onUpdate() {
        syncDisplay();
    }

    public void begin() {

        // Start a new ImGui frame.
        //this.implGLFW.newFrame();
        ImGui.newFrame();
    }

    public void end() {

        ImGuiIO io = ImGui.getIO();
        Application app = Application.getInstance();
        io.setDisplaySize((float) app.getWindow().getWidth(), (float) app.getWindow().getHeight());

        ImGui.render();
        implOpenGL.renderDrawData(ImGui.getDrawData());

        if ((io.getConfigFlags() & ImGuiConfigFlags.ViewportsEnable) != 0) {
            long backup_current_context = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backup_current_context);
        }
    }

    public void onImGuiRender() {

        ImGui.getIO().setConfigFlags(ImGui.getIO().getConfigFlags() | ImGuiConfigFlags.DockingEnable);
        theme.applyTheme();

        if (this.consoleMessages.size() < DebugConsole.getDebugMessages().size()) {
            for (int i = this.consoleMessages.size(); i < DebugConsole.getDebugMessages().size(); i++) {
                DebugMessage message = DebugConsole.getDebugMessages().get(i);
                this.consoleMessages.add(new Text(message.getText()).style(new TextStyle("JetBrainsMono-VariableFont_wght", FontSize.MEDIUM, message.getColor())));
            }
        }

        Navigator.render();
    }


    private void syncDisplay() {
        ImGuiIO io = ImGui.getIO();
        Application app = Application.getInstance();
        io.setDisplaySize(app.getWindow().getWidth(), app.getWindow().getHeight());

    }

    @Override
    public void onEvent(Event e) {
        // Dispatch ImGui events to handle user interactions.
        EventDispatcher dispatcher = new EventDispatcher(e);
        dispatcher.dispatch(EventType.MouseButtonPressed, this::onMouseButtonPressedEvent);
        dispatcher.dispatch(EventType.MouseButtonReleased, this::onMouseButtonReleasedEvent);
        dispatcher.dispatch(EventType.MouseMoved, this::onMouseMovedEvent);
        dispatcher.dispatch(EventType.MouseScrolled, this::onMouseScrolledEvent);
        dispatcher.dispatch(EventType.KeyPressed, this::onKeyPressedEvent);
        dispatcher.dispatch(EventType.KeyReleased, this::onKeyReleasedEvent);
        dispatcher.dispatch(EventType.KeyTyped, this::onKeyTypedEvent);
        dispatcher.dispatch(EventType.WindowResize, this::onWindowResizedEvent);
    }

    // Implement ImGui event handlers for user interactions.

    private boolean onMouseButtonPressedEvent(MouseButtonPressedEvent e) {
        ImGuiIO io = ImGui.getIO();
        io.setMouseDown(e.getMouseButtonCode(), true);
        return false;
    }

    private boolean onMouseButtonReleasedEvent(MouseButtonReleasedEvent e) {
        ImGuiIO io = ImGui.getIO();
        io.setMouseDown(e.getMouseButtonCode(), false);
        return false;
    }

    private boolean onMouseMovedEvent(MouseMovedEvent e) {
        ImGuiIO io = ImGui.getIO();
        io.setMousePos(e.getMouseX(), e.getMouseY());
        return false;
    }

    private boolean onMouseScrolledEvent(MouseScrolledEvent e) {
        ImGuiIO io = ImGui.getIO();
        io.setMouseWheelH(io.getMouseWheelH() + e.getXOffset());
        io.setMouseWheel(io.getMouseWheel() + e.getYOffset());
        return false;
    }

    private boolean onKeyPressedEvent(KeyPressedEvent e) {
        ImGuiIO io = ImGui.getIO();
        io.setKeysDown(e.getKeyCode(), true);

        io.setKeyCtrl(io.getKeysDown(GLFW_KEY_LEFT_CONTROL) || io.getKeysDown(GLFW_KEY_RIGHT_CONTROL));
        io.setKeyShift(io.getKeysDown(GLFW_KEY_LEFT_SHIFT) || io.getKeysDown(GLFW_KEY_RIGHT_SHIFT));
        io.setKeyAlt(io.getKeysDown(GLFW_KEY_LEFT_ALT) || io.getKeysDown(GLFW_KEY_RIGHT_ALT));
        io.setKeySuper(io.getKeysDown(GLFW_KEY_LEFT_SUPER) || io.getKeysDown(GLFW_KEY_RIGHT_SUPER));
        return false;
    }

    private boolean onKeyReleasedEvent(KeyReleasedEvent e) {
        ImGuiIO io = ImGui.getIO();
        io.setKeysDown(e.getKeyCode(), false);
        return false;
    }

    private boolean onKeyTypedEvent(KeyTypedEvent e) {
        ImGuiIO io = ImGui.getIO();
        int keycode = e.getKeyCode();

        if (keycode > 0 && keycode < 0x10000) {
            io.addInputCharacter(keycode);
        }

        return false;
    }

    private boolean onWindowResizedEvent(WindowResizeEvent e) {
        // Update ImGui display size when the window is resized.
        ImGuiIO io = ImGui.getIO();
        io.setDisplaySize(e.getWidth(), e.getHeight());
        io.setDisplayFramebufferScale(1.0f, 1.0f);
        glViewport(0, 0, e.getWidth(), e.getHeight());
        return false;
    }
}
