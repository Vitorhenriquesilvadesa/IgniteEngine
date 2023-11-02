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

package org.ignite.general.log;

import org.ignite.general.macros.debug.Macros;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The `Logger` class provides logging functionalities with different log levels
 * and colors for better visualization.
 */
public class Logger {

    private int logLevel;
    private String color;
    private String resetColor = "\u001B[0m";
    private String name;

    private DateFormat dateFormat;

    private static boolean lineSeparator = false;
    private Exception exception;

    /**
     * Constructs a new `Logger` instance with the given name and log level.
     *
     * @param name     The name of the logger, typically used to identify the source
     *                 of the log.
     * @param logLevel The initial log level for the logger.
     */
    public Logger(String name, int logLevel) {
        this.logLevel = logLevel;
        this.name = name;
    }

    /**
     * Retrieves the current log level of the logger.
     *
     * @return The log level of the logger.
     */
    public int getLogLevel() {
        return this.logLevel;
    }

    /**
     * Sets the log level for the logger.
     *
     * @param level The log level to be set for the logger.
     */
    public void setLogLevel(int level) {
        this.logLevel = level;
    }

    /**
     * Sets whether line separators should be printed before each log message.
     *
     * @param value If true, line separators will be printed before each log
     *              message; otherwise, they won't be printed.
     */
    public static void setLineSeparator(boolean value) {
        Logger.lineSeparator = value;
    }

    /**
     * Logs a message at the TRACE log level with the specified target.
     *
     * @param target The target object to be logged.
     */
    public void trace(Object target) {
        setColor(LogColor.WHITE);
        print("TRACE", target);
    }

    /**
     * Logs a message at the DEBUG log level with the specified target.
     * This log level is only active when the `DEBUG` flag is true.
     *
     * @param target The target object to be logged.
     */
    public void debug(Object target) {
        if (Macros.DEBUG) {
            setColor(LogColor.GREEN);
            print("DEBUG", target);
        }
    }

    /**
     * Logs a message at the INFO log level with the specified target.
     *
     * @param target The target object to be logged.
     */
    public void info(Object target) {
        setColor(LogColor.BLUE);
        print("INFO", target);
    }

    /**
     * Logs a message at the WARN log level with the specified target.
     *
     * @param target The target object to be logged.
     */
    public void warn(Object target) {
        setColor(LogColor.YELLOW);
        print("WARN", target);
    }

    /**
     * Logs a message at the ERROR log level with the specified target.
     *
     * @param target The target object to be logged.
     */
    public void error(Object target) {
        setColor(LogColor.MAGENTA);
        print("ERROR", target);
    }

    /**
     * Logs a message at the CRITICAL log level with the specified target.
     *
     * @param target The target object to be logged.
     */
    public void critical(Object target) {
        setColor(LogColor.RED);
        print("CRITICAL", target);
    }

    /**
     * Logs an exception at the ERROR log level.
     * Prints the exception message and its stack trace, then exits the program with
     * a status code of -1.
     *
     * @param exception The exception to be logged.
     */
    public void exception(Exception exception) {
        setColor(LogColor.RED);
        print("EXCEPTION", exception.getClass().getSimpleName() + " -> " + exception.getMessage());

        this.exception = exception;

        if (Macros.DEBUG) {
            System.exit(-1);
        }
    }

    public void exceptionTrace() {

        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stack = this.exception.getStackTrace();

        for (StackTraceElement element : stack) {
            sb.append("File: " + "\"" + element.getFileName() + "\"" + ", ");
            sb.append("Line " + element.getLineNumber() + ", ");
            sb.append("Method " + "\"" + element.getMethodName() + "\"" + ", ");
            sb.append("Class -> " + "\"" + element.getClassLoaderName() + "\"" + ".");
        }

        print("DESCRIPTOR", sb.toString());
    }

    /**
     * Logs an object as an exception at the ERROR log level.
     * Prints the object's string representation, then exits the program with a
     * status code of -1.
     *
     * @param exception The object to be logged as an exception.
     */
    public void exception(Object exception) {
        setColor(LogColor.RED);
        print("EXCEPTION", exception.getClass().getSimpleName() + ": " + exception.toString());
        System.exit(-1);
    }

    private void setColor(String color) {
        this.color = color;
    }

    private void print(String handle, Object target) {

        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String formatedDate = this.dateFormat.format(date);

        String logtarget;

        logtarget = this.color + formatedDate + " " + this.name + " [" + handle + "]: " + target.toString()
                + this.resetColor;

        if (Logger.lineSeparator) {
            StringBuilder line = new StringBuilder();

            for (int i = 0; i < logtarget.length(); i++) {
                line.append("_");
            }

            System.out.println(line + "\n");
        }
        System.out.println(logtarget);
    }

}
