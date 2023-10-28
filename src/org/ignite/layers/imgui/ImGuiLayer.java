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
import imgui.extension.imnodes.ImNodes;
import org.ignite.core.app.Application;
import org.ignite.core.app.Time;
import org.ignite.events.*;
import org.ignite.layers.Layer;

import imgui.flag.*;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.ignite.annotations.Define;
import org.ignite.system.debbuging.DebugConsole;

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
    public ImGuiElement leftMenuBackground = new ImGuiLeftMenuBackground();
    public ImGuiElement fileDialog = new ImGuiFileDialog();
    public ImGuiElement fpsDisplay;
    public ImGuiElement screenDisplay = new ImGuiScreenDisplay(300, 0);
    public ImGuiTopMenu topMenu = new ImGuiTopMenu();
    public ImGuiFileExplorer fileExplorer;

    public ImGuiRightMenu rightMenu;
    public static ImGuiTheme theme = ImGuiTheme.getTheme(ImGuiThemes.OneDark);

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

        String fontPath = workingDir + "res/fonts/Ubuntu-Regular.ttf";
        float fontSize = 20.0f;
        ImFont font = io.getFonts().addFontFromFileTTF(fontPath, fontSize);
        io.setFontDefault(font);

        io.addBackendFlags(ImGuiBackendFlags.HasMouseCursors | ImGuiBackendFlags.HasSetMousePos);
        io.setBackendPlatformName("imgui_java_impl_glfw");
        io.setBackendRendererName("imgui_java_impl_opengl3");

        ImGui.styleColorsDark();

        ImGuiStyle style = ImGui.getStyle();

        Application app = Application.getInstance();
        long window = app.getWindow().getNativeWindow();

        implGLFW.init(window, true);
        implOpenGL.init("#version 410");

        this.fpsDisplay = new ImGuiFPSDisplay(0, Application.getInstance().getWindow().getHeight() - 40);
        this.fileExplorer = new ImGuiFileExplorer();
        this.rightMenu = new ImGuiRightMenu();
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

        Application app = Application.getInstance();
        ImGuiStyle style = ImGui.getStyle();

        leftMenuBackground.renderElement();
        fileDialog.renderElement();
        fpsDisplay.renderElement();
        screenDisplay.renderElement();
        topMenu.renderElement();
        fileExplorer.renderElement();
        rightMenu.renderElement();

        ImGui.setNextWindowSize(app.getWindow().getWidth() - 600, app.getWindow().getHeight(), ImGuiCond.Once);

        if(ImGui.begin("Debugging Console", ImGuiWindowFlags.NoCollapse)){

            for(String s : DebugConsole.getDebugMessages()){
                ImGui.text(s);
            }

            ImGui.end();
        }
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
