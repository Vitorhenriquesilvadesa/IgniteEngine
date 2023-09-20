package org.ignite.platform.windows;

import org.ignite.system.meta.Define;

@Define("IGNITE_API")
public abstract class GraphicsContext {

    public abstract void init();

    public abstract void swapBuffers();
}
