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

package org.ignite.system.functions;

/**
 * The `TriggerEvent` class represents a trigger event that associates a caller,
 * a trigger, and an event descriptor.
 * It provides a way to call the trigger with a given condition and caller to
 * handle the event.
 */
public class TriggerEvent {

    private Trigger trigger;
    private EventDescriptor descriptor;
    private GenericFunction caller;

    /**
     * Constructs a new `TriggerEvent` instance with the given caller and event
     * descriptor.
     *
     * @param caller     The generic function representing the event handler for the
     *                   trigger.
     * @param descriptor The event descriptor that describes the trigger event.
     */
    public TriggerEvent(GenericFunction caller, EventDescriptor descriptor) {
        this.trigger = new Trigger();
        this.caller = caller;
        this.descriptor = descriptor;
    }

    /**
     * Constructs a new `TriggerEvent` instance with the given caller and event
     * name.
     *
     * @param caller    The generic function representing the event handler for the
     *                  trigger.
     * @param eventName The name of the trigger event.
     */
    public TriggerEvent(GenericFunction caller, String eventName) {
        this.trigger = new Trigger();
        this.caller = caller;
        this.descriptor = new EventDescriptor(eventName);
    }

    /**
     * Retrieves the trigger associated with the event.
     *
     * @return The trigger instance.
     */
    public Trigger getTrigger() {
        return trigger;
    }

    /**
     * Retrieves the generic function representing the event handler for the
     * trigger.
     *
     * @return The generic function associated with the trigger event.
     */
    public GenericFunction getMethod() {
        return this.caller;
    }

    /**
     * Retrieves the event descriptor associated with the trigger event.
     *
     * @return The event descriptor instance.
     */
    public EventDescriptor getDescriptor() {
        return descriptor;
    }

    /**
     * Calls the trigger event with the given condition and caller to handle the
     * event.
     *
     * @param condition The condition that determines whether the trigger should be
     *                  executed.
     * @param caller    The generic function representing the event handler for the
     *                  trigger.
     */
    public void call(boolean condition, GenericFunction caller) {
        this.trigger.call(condition, caller);
    }

    public GenericFunction getCaller() {
        return caller;
    }
}
