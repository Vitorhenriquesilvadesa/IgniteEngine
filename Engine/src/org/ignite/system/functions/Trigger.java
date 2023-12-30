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
 * The `Trigger` class represents a mechanism to handle events based on a
 * condition.
 * It ensures that the trigger is executed only once while the condition is
 * true, and is reset once the condition becomes false.
 */
public class Trigger {

    private boolean isUsed = false;
    private boolean isReady = true;

    /**
     * Constructs a new Trigger instance.
     * Initializes the trigger's internal state.
     */
    protected Trigger() {

    }

    /**
     * Calls the trigger with the given condition and a caller to handle the trigger
     * event.
     * The trigger is executed if the condition is true and the trigger is ready.
     * Once executed, the trigger becomes used and is no longer ready until the
     * condition becomes false again.
     * If the condition becomes false and the trigger is used, it becomes ready
     * again.
     *
     * @param condition The condition that determines whether the trigger should be
     *                  executed.
     * @param caller    The generic function representing the event handler for the
     *                  trigger.
     */
    public void call(boolean condition, GenericFunction caller) {

        if (!isUsed && condition && isReady) {
            caller.apply();
            isUsed = true;
            isReady = false;
        }

        if (!condition && !isReady) {
            isReady = true;
        }

        if (condition && isReady && isUsed) {
            isUsed = false;
        }
    }
}
