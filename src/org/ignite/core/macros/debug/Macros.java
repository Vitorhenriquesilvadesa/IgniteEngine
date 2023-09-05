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

import static org.ignite.core.app.Application.ClientLog;

import org.ignite.core.macros.memory.Pointer;

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

    public static String workingDir = System.getProperty("user.dir") + "/src/org/ignite/";

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
                ClientLog.exception(message);
            }
        }
    }

    public static void _assert(Exception exception, boolean... args) {
        for (boolean b : args) {
            if (!b) {
                ClientLog.exception(exception);
            }
        }
    }

    /**
     * A debugging utility that triggers a breakpoint and halts execution if the
     * DEBUG mode is enabled.
     * In DEBUG mode, this method prints a trace message indicating the file and
     * line number where the breakpoint is hit.
     * In non-DEBUG mode, this method does nothing.
     *
     * @return A `BreakPoint` object representing the file and line where the
     *         breakpoint is hit (only available in DEBUG mode).
     */
    public static BreakPoint _debugbreak() {
        if (DEBUG) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length > 2) {
                StackTraceElement element = stackTrace[2];
                String fileName = element.getFileName();
                int lineNumber = element.getLineNumber();
                ClientLog.trace("__debugbreak executed on file " + fileName + " on line " + lineNumber);
                System.exit(-1);
                return new BreakPoint(fileName, lineNumber);
            }
        }

        return null;
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
