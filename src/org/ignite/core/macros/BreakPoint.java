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

package org.ignite.core.macros;

/**
 * The `BreakPoint` class represents a breakpoint in source code.
 * It holds information about the file and line number where the breakpoint is
 * set.
 */
public class BreakPoint {

    private String file;
    private int line;

    /**
     * Constructs a new `BreakPoint` with the given file and line number.
     *
     * @param file The name of the file where the breakpoint is set.
     * @param line The line number in the file where the breakpoint is set.
     */
    public BreakPoint(String file, int line) {
        this.file = file;
        this.line = line;
    }

    /**
     * Gets the name of the file where the breakpoint is set.
     *
     * @return The name of the file.
     */
    public String getFile() {
        return file;
    }

    /**
     * Gets the line number in the file where the breakpoint is set.
     *
     * @return The line number.
     */
    public int getLine() {
        return line;
    }
}
