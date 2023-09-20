package org.ignite.core.macros.debug;


import org.ignite.system.meta.Define;

@Define("IGNITE_API")
public interface Destructible {

    public void destroy();
}