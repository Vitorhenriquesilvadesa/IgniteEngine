/**
 * Ignite Engine - A powerful game engine in Java.
 *
 * @license MIT License
 * @author Creator: Lord Vtko
 * @version 1.0.0
 * @since 2023-07-28
 */

package org.ignite.editor.layers.imgui;

import org.ignite.editor.layers.Layer;
import org.ignite.editor.ui.Navigator;
import org.ignite.editor.ui.UIManager;
import imgui.*;
import org.ignite.core.app.Application;
import org.ignite.events.*;

import imgui.flag.*;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.ignite.annotations.Define;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45.*;

/**
 * The `ImGuiLayer` class is a custom layer that integrates Dear ImGui into the
 * Ignite Engine.
 * It allows for debugging and user interface interactions.
 */

@Define("IGNITE_API")
public abstract class ImGuiLayer extends Layer {

    private static ImGuiImplGl3 implOpenGL = new ImGuiImplGl3();
    private static ImGuiImplGlfw implGLFW = new ImGuiImplGlfw();
    public UIFunctionManager functionManager;
    public static ImGuiTheme theme = ImGuiTheme.getTheme(ImGuiThemes.OneDark);

    public ImGuiLayer() {
        super("ImGuiLayer");
        Application.ClientLog.debug("ImGuiLayer::Init");
    }

    public abstract void onCreate();
    public abstract void beforeUpdate();

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

        onCreate();
    }

    @Override
    public void onDetach() {
        // Cleanup any resources used by ImGui.
        ImGui.destroyContext();
    }

    @Override
    public void onUpdate() {
        beforeUpdate();
        syncDisplay();
    }

    public static void begin() {

        // Start a new ImGui frame.
        //this.implGLFW.newFrame();
        ImGui.newFrame();
    }

    public static void end() {

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
