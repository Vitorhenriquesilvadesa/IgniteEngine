package org.ignite.platform.general;

import org.ignite.annotations.Define;

@Define("IGNITE_API")
public abstract class GraphicsContext {

    public abstract void init();

    public abstract void swapBuffers();
}
