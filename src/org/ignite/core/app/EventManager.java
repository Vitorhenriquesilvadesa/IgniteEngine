package org.ignite.core.app;

import org.ignite.system.exceptions.DuplicatedTickEventException;
import org.ignite.system.exceptions.DuplicatedTriggerEventException;
import org.ignite.system.exceptions.InexistentTickEventException;
import org.ignite.system.exceptions.InexistentTriggerEventException;
import org.ignite.system.functions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private boolean throwEventExceptions = true;

    /** Map to store trigger events by their names. */
    private Map<String, TriggerEvent> triggerEvents = new HashMap<String, TriggerEvent>();

    /** Map to store tick events by their names. */
    private Map<String, TickEvent> tickEvents = new HashMap<String, TickEvent>();

    private List<String> triggerEventNames = new ArrayList<String>();

    /** List to store the names of tick events in the order they were added. */
    private List<String> tickEventNames = new ArrayList<String>();

    /**
     * Adds a new trigger event with the given name and associated caller.
     *
     * @param caller    The caller that will handle the trigger event.
     * @param eventName The name of the trigger event.
     * @throws DuplicatedTriggerEventException if a trigger event with the same name
     *                                         already exists.
     */
    protected void addTriggerEvent(GenericFunction caller, String eventName) throws DuplicatedTriggerEventException {

        TriggerEvent triggerEvent = new TriggerEvent(caller, eventName);

        if (!this.triggerEvents.containsKey(eventName)) {

            this.triggerEvents.put(eventName, triggerEvent);
            this.triggerEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTriggerEventException(
                    "The trigger with name: \"" + triggerEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Adds a new trigger event with the given name, associated caller, and
     * additional event attributes.
     *
     * @param caller          The caller that will handle the trigger event.
     * @param eventName       The name of the trigger event.
     * @param eventAttributes Additional attributes associated with the trigger
     *                        event.
     * @throws DuplicatedTriggerEventException if a trigger event with the same name
     *                                         already exists.
     */
    protected void addTriggerEvent(GenericFunction caller, String eventName, Object... eventAttributes)
            throws DuplicatedTriggerEventException {

        TriggerEvent triggerEvent = new TriggerEvent(caller, new EventDescriptor(eventName, eventAttributes));

        if (!this.triggerEvents.containsKey(eventName)) {

            this.triggerEvents.put(eventName, triggerEvent);
            this.triggerEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTriggerEventException(
                    "The trigger with name: \"" + triggerEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Retrieves a trigger event by its name.
     *
     * @param triggerEventName The name of the trigger event to retrieve.
     * @return The trigger event associated with the given name.
     * @throws InexistentTriggerEventException if the trigger event with the given
     *                                         name
     *                                         does not exist.
     */
    private TriggerEvent getTriggerEvent(String triggerEventName) throws InexistentTriggerEventException {

        if (this.triggerEvents.containsKey(triggerEventName)) {

            return this.triggerEvents.get(triggerEventName);

        } else if (this.throwEventExceptions) {

            throw new InexistentTriggerEventException(
                    "The required trigger with name: \"" + triggerEventName + "\" does not exist.");

        } else {
            return null;
        }
    }

    /**
     * Removes a trigger event by its name.
     *
     * @param triggerEventName The name of the trigger event to remove.
     * @throws InexistentTriggerEventException if the trigger event with the given
     *                                         name
     *                                         does not exist or has already been
     *                                         removed.
     */
    protected void removeTriggerEvent(String triggerEventName) throws InexistentTriggerEventException {

        if (this.triggerEventNames.contains(triggerEventName)) {

            this.triggerEvents.remove(triggerEventName);
            this.triggerEventNames.remove(triggerEventName);

        } else {
            throw new InexistentTriggerEventException("The required trigger with name: \"" + triggerEventName
                    + "\" does not exist or has already been removed.");
        }
    }

    /**
     * Calls a trigger event with the given name and condition.
     *
     * @param triggerEventName The name of the trigger event to call.
     * @param condition        The condition that determines whether the trigger
     *                         should be executed.
     */
    protected void callTriggerEvent(String triggerEventName, boolean condition) {

        TriggerEvent event;

        try {
            if ((event = this.getTriggerEvent(triggerEventName)) == null) {

                throw new InexistentTriggerEventException("The required trigger with name: \"" + triggerEventName
                        + "\" does not exist or has already been removed.");
            }

        } catch (InexistentTriggerEventException e) {

            if (this.throwEventExceptions) {
                throw e;
            }

            return;
        }

        Trigger trigger = event.getTrigger();
        trigger.call(condition, event.getMethod());
    }

    /**
     * Adds a new tick event with the given name and associated caller.
     *
     * @param caller    The caller that will handle the tick event.
     * @param eventName The name of the tick event.
     * @throws DuplicatedTickEventException if a tick event with the same name
     *                                      already exists.
     */

    protected void addTickEvent(GenericFunction caller, String eventName) throws DuplicatedTickEventException {
        TickEvent tickEvent = new TickEvent(caller, eventName);

        if (!this.tickEvents.containsKey(eventName)) {

            this.tickEvents.put(eventName, tickEvent);
            this.tickEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTickEventException(
                    "The tick with name: \"" + tickEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Adds a new tick event with the given name, associated caller, and additional
     * event attributes.
     *
     * @param caller          The caller that will handle the tick event.
     * @param eventName       The name of the tick event.
     * @param eventAttributes Additional attributes associated with the tick event.
     * @throws DuplicatedTickEventException if a tick event with the same name
     *                                      already exists.
     */

    protected void addTickEvent(GenericFunction caller, String eventName, Object... eventAttributes)
            throws DuplicatedTickEventException {

        TickEvent tickEvent = new TickEvent(caller, new EventDescriptor(eventName, eventAttributes));

        if (!this.tickEvents.containsKey(eventName)) {

            this.tickEvents.put(eventName, tickEvent);
            this.tickEventNames.add(eventName);

        } else if (this.throwEventExceptions) {

            throw new DuplicatedTickEventException(
                    "The tick with name: \"" + tickEvent.getDescriptor().getEventName() + "\" already exists");
        }
    }

    /**
     * Retrieves a tick event by its name.
     *
     * @param tickEventName The name of the tick event to retrieve.
     * @return The tick event associated with the given name.
     * @throws InexistentTickEventException if the tick event with the given name
     *                                      does not exist.
     */

    private TickEvent getTickEvent(String tickEventName) throws InexistentTickEventException {

        if (this.tickEvents.containsKey(tickEventName)) {

            return this.tickEvents.get(tickEventName);

        } else if (this.throwEventExceptions) {

            throw new InexistentTickEventException(
                    "The required tick with name: \"" + tickEventName + "\" does not exist.");

        } else {
            return null;
        }
    }

    /**
     * Removes a tick event by its name.
     *
     * @param tickEventName The name of the tick event to remove.
     * @throws InexistentTickEventException if the tick event with the given name
     *                                      does not exist or has already been
     *                                      removed.
     */

    protected void removeTickEvent(String tickEventName) throws InexistentTickEventException {

        if (this.tickEventNames.contains(tickEventName)) {

            this.tickEvents.remove(tickEventName);
            this.tickEventNames.remove(tickEventName);

        } else if (this.throwEventExceptions) {

            throw new InexistentTickEventException("The required tick with name: \"" + tickEventName
                    + "\" does not exist or has already been removed.");
        }
    }

    /**
     * Calls a tick event by its name and condition.
     *
     * @param tickEventName The name of the tick event to call.
     * @param condition     The condition that determines whether the tick event
     *                      should be executed.
     */

    protected void callTickEvent(String tickEventName, boolean condition) {

        TickEvent event;

        try {
            if ((event = this.getTickEvent(tickEventName)) == null) {

                throw new InexistentTriggerEventException(
                        "The required tick with name: \"" + tickEventName + "\" does not exist.");
            }

        } catch (InexistentTriggerEventException e) {

            if (this.throwEventExceptions) {

                throw e;
            }

            return;
        }

        Tick tick = event.getTick();
        tick.call(condition, event.getMethod());
    }

    protected void disableEventExceptions() {
        throwEventExceptions = false;
    }

    protected void enableEventExceptions() {
        throwEventExceptions = true;
    }
}
