package org.ignite.system.functions;

@FunctionalInterface
public interface GenericFunction {

    public void apply();

    @Override
    boolean equals(Object obj);

}
