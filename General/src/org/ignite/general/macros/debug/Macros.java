package org.ignite.general.macros.debug; /**
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

import org.ignite.general.macros.memory.Pointer;
import org.ignite.general.log.Logger;

/**
 * The `Macros` class contains various utility methods and macros commonly used
 * in the Ignite Engine.
 * These methods provide functionality for debugging, assertion, and other
 * convenience operations.
 */

public final class Macros {

    /**
     * A boolean constant that indicates whether the DEBUG mode is enabled.
     * When DEBUG is true, certain debugging features may be active.
     * Set this value to true during development and testing and false for
     * production.
     */
    public static boolean DEBUG = false;

    private static final Logger logger = new Logger("Ignite Engine", 0);

    public static String workingDir = System.getProperty("user.dir") + "/Engine/";

    /**
     * Generates an integer with a single bit set to 1 at the specified position.
     *
     * @param x The position of the bit to set (0-indexed).
     * @return An integer with a single bit set to 1 at the specified position.
     */
    public static int _bit(int x) {
        return (int) 1 << x;
    }

    /**
     * Checks the specified boolean values for falsity and throws an exception with
     * the given message if any are false.
     * This method is useful for runtime assertion checks during development and
     * debugging.
     *
     * @param message The message to include in the exception if any of the booleans
     *                are false.
     * @param args    The boolean values to check for falsity.
     * @throws RuntimeException if any of the booleans are false.
     */
    public static void _assert(String message, boolean... args) {
        for (boolean b : args) {
            if (!b) {
                logger.exception(message);
            }
        }
    }

    public static void _assert(Exception exception, boolean... args) {
        for (boolean b : args) {
            if (!b) {
                logger.exception(exception);
            }
        }
    }


    /**
     * Clears the reference held by the specified pointer by setting it to null.
     *
     * @param ref The pointer whose reference needs to be cleared.
     * @param <T> The type of the object pointed to by the pointer.
     */
    public static <T extends Object> void _delete(Pointer<? extends Object> ref) {
        ref.setReference(null);
    }
}
