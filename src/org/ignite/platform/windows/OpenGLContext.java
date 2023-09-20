package org.ignite.platform.windows;

import org.ignite.system.meta.Define;

import static org.ignite.core.macros.debug.Macros.*;
import static org.lwjgl.glfw.GLFW.*;

@Define("IGNITE_API")
public class OpenGLContext extends GraphicsContext {

    private long windowHandle;

    public OpenGLContext(long windowHandle) {
        this.windowHandle = windowHandle;
        _assert("Window Handle is NULL!", this.windowHandle != 0);
    }

    @Override
    public void init() {
        glfwMakeContextCurrent(this.windowHandle);
    }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(this.windowHandle);
    }
}
