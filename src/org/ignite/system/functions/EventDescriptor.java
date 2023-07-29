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
 * The `EventDescriptor` class represents a description of an event with its
 * name and optional attributes.
 * It is used to define events for trigger events.
 */
public class EventDescriptor {

    private Object[] attributes;
    private String eventName;

    /**
     * Constructs a new `EventDescriptor` instance with the given event name and
     * attributes.
     *
     * @param eventName  The name of the event.
     * @param attributes Optional attributes associated with the event.
     */
    public EventDescriptor(String eventName, Object... attributes) {
        this.attributes = attributes;
        this.eventName = eventName;
    }

    /**
     * Constructs a new `EventDescriptor` instance with the given event name and no
     * attributes.
     *
     * @param eventName The name of the event.
     */
    public EventDescriptor(String eventName) {
        this.attributes = null;
        this.eventName = eventName;
    }

    /**
     * Retrieves the attributes associated with the event.
     *
     * @return An array of attributes, or null if no attributes are associated with
     *         the event.
     */
    public Object[] getAttributes() {
        return attributes;
    }

    /**
     * Retrieves the name of the event.
     *
     * @return The name of the event.
     */
    public String getEventName() {
        return eventName;
    }

}
