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

package org.ignite.layers.imgui;

import static org.ignite.core.app.Application.ClientLog;
import org.ignite.core.app.Application;
import org.ignite.events.*;
import org.ignite.layers.Layer;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiBackendFlags;
import imgui.flag.ImGuiKey;
import imgui.gl3.ImGuiImplGl3;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The `ImGuiLayer` class is a custom layer that integrates Dear ImGui into the
 * Ignite Engine.
 * It allows for debugging and user interface interactions.
 */
public class ImGuiLayer extends Layer {

    private ImGuiImplGl3 impl = new ImGuiImplGl3();
    private float time;
    private Application app;

    public ImGuiLayer() {
        super("ImGuiLayer");
        this.app = Application.getInstance();
    }

    @Override
    public void onAttach() {
        // Initialize Dear ImGui context and backend.
        ImGui.createContext();
        ImGui.setCurrentContext(ImGui.getCurrentContext());
        ImGuiIO io = ImGui.getIO();

        // Set backend flags and names.
        io.addBackendFlags(ImGuiBackendFlags.HasMouseCursors | ImGuiBackendFlags.HasSetMousePos);
        io.setBackendPlatformName("imgui_java_impl_glfw");
        io.setBackendRendererName("imgui_java_impl_opengl3");

        // Map ImGui keys to GLFW keys.
        io.setKeyMap(ImGuiKey.Tab, GLFW_KEY_TAB);
        io.setKeyMap(ImGuiKey.LeftArrow, GLFW_KEY_LEFT);
        io.setKeyMap(ImGuiKey.RightArrow, GLFW_KEY_RIGHT);
        io.setKeyMap(ImGuiKey.UpArrow, GLFW_KEY_UP);
        io.setKeyMap(ImGuiKey.DownArrow, GLFW_KEY_DOWN);
        io.setKeyMap(ImGuiKey.Home, GLFW_KEY_HOME);
        io.setKeyMap(ImGuiKey.End, GLFW_KEY_END);
        io.setKeyMap(ImGuiKey.Insert, GLFW_KEY_INSERT);
        io.setKeyMap(ImGuiKey.Delete, GLFW_KEY_DELETE);
        io.setKeyMap(ImGuiKey.Backspace, GLFW_KEY_BACKSPACE);
        io.setKeyMap(ImGuiKey.Space, GLFW_KEY_SPACE);
        io.setKeyMap(ImGuiKey.Enter, GLFW_KEY_ENTER);
        io.setKeyMap(ImGuiKey.Escape, GLFW_KEY_ESCAPE);

        // Initialize the ImGui OpenGL3 renderer.
        impl.init("#version 410");
    }

    @Override
    public void onDetach() {
        // Cleanup any resources used by ImGui.
        ImGui.destroyContext();
    }

    @Override
    public void onUpdate() {
        // Update Dear ImGui for the current frame.
        ClientLog.debug("ImGuiLayer::Update");
        ImGuiIO io = ImGui.getIO();

        // Set the display size and delta time for ImGui.
        io.setDisplaySize(app.getWindow().getWidth(), app.getWindow().getHeight());
        float time = (float) glfwGetTime();
        io.setDeltaTime(this.time > 0.0f ? time - this.time : (1.0f / 60.0f));
        this.time = time;

        // Start a new ImGui frame.
        ImGui.newFrame();

        // Show the ImGui demo window (optional).
        ImGui.showDemoWindow();

        // Render the ImGui draw data.
        ImGui.render();
        impl.renderDrawData(ImGui.getDrawData());
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
