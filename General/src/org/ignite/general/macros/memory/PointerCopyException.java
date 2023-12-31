package org.ignite.general.macros.memory;

public class PointerCopyException extends RuntimeException {

    public PointerCopyException(String message) {
        super(message);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot access all fields or methods in object to copy reference to new instance of class.");

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace;
    }

    @Override
    public void printStackTrace() {

        StackTraceElement[] stackTrace = getStackTrace();

        if (stackTrace.length > 2) {
            StackTraceElement element = stackTrace[2];
            String fileName = element.getFileName();
            int lineNumber = element.getLineNumber();
            System.err.println("Exception throwed on file" + fileName + " on line " + lineNumber);
        }
    }
}
