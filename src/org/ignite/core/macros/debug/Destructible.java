package org.ignite.core.macros.debug;


import org.ignite.annotations.Define;

@Define("IGNITE_API")
public interface Destructible {

    public void destroy();
}