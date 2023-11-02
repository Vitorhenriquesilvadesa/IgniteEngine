package org.ignite.system.functions;

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
 * 
 */

public class TickEvent {

    private Tick tick;
    private EventDescriptor descriptor;
    private GenericFunction caller;

    public TickEvent(GenericFunction caller, EventDescriptor descriptor) {
        this.tick = new Tick();
        this.caller = caller;
        this.descriptor = descriptor;
    }

    public TickEvent(GenericFunction caller, String eventName) {
        this.tick = new Tick();
        this.caller = caller;
        this.descriptor = new EventDescriptor(eventName);
    }

    public Tick getTick() {
        return this.tick;
    }

    public GenericFunction getMethod() {
        return this.caller;
    }

    public EventDescriptor getDescriptor() {
        return descriptor;
    }

    public void call(boolean condition, GenericFunction caller) {
        this.tick.call(condition, caller);
    }

    public GenericFunction getCaller() {
        return caller;
    }
}
