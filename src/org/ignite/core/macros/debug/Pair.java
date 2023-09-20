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

package org.ignite.core.macros.debug;

import org.ignite.system.meta.Define;

/**
 * The `Pair` class represents a generic pair of values.
 * It holds two values of different types, T and F.
 *
 * @param <T> The type of the first value.
 * @param <F> The type of the second value.
 */

@Define("IGNITE_API")
public class Pair<T, F> {

    private T Tvalue;
    private F Fvalue;

    /**
     * Constructs a new `Pair` with the given values.
     *
     * @param tvalue The value of the first element (T).
     * @param fvalue The value of the second element (F).
     */
    public Pair(T tvalue, F fvalue) {
        this.Tvalue = tvalue;
        this.Fvalue = fvalue;
    }

    /**
     * Gets the value of the first element (T).
     *
     * @return The value of the first element.
     */
    public T getTvalue() {
        return Tvalue;
    }

    /**
     * Sets the value of the first element (T).
     *
     * @param tvalue The new value for the first element.
     */
    public void setTvalue(T tvalue) {
        Tvalue = tvalue;
    }

    /**
     * Gets the value of the second element (F).
     *
     * @return The value of the second element.
     */
    public F getFvalue() {
        return Fvalue;
    }

    /**
     * Sets the value of the second element (F).
     *
     * @param fvalue The new value for the second element.
     */
    public void setFvalue(F fvalue) {
        Fvalue = fvalue;
    }
}
