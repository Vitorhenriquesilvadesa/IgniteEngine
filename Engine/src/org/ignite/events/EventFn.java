package org.ignite.events;

@FunctionalInterface
public interface EventFn<T extends Event> {
    boolean apply(T event);
}
