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

package org.ignite.platform.general;

import org.ignite.events.Event;
import org.ignite.system.meta.Define;

@Define("IGNITE_API")
@FunctionalInterface
public interface EventCallbackFn<T extends Event> {
    void apply(T target);
}
