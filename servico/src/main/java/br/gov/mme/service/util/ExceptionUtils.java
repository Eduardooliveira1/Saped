package br.gov.mme.service.util;

import static java.lang.Math.max;

public final class ExceptionUtils {
    
    private static final int INNER_STACK_LIMIT = 5;
    private ExceptionUtils() {

    }

    public static void mergeStackTraces(Throwable parentError, Throwable error) {
        int innerStackSize = max(INNER_STACK_LIMIT - error.getStackTrace().length, 0);
        StackTraceElement[] newStack = new StackTraceElement[error.getStackTrace().length - innerStackSize
                + parentError.getStackTrace().length + 1];
        System.arraycopy(parentError.getStackTrace(), 0, newStack, 0, parentError.getStackTrace().length);
        newStack[parentError.getStackTrace().length] = new StackTraceElement("══════════════════════════",
                "", null, 0);
        System.arraycopy(error.getStackTrace(), INNER_STACK_LIMIT, newStack, 
                parentError.getStackTrace().length + 1, (error.getStackTrace().length - innerStackSize));
        error.setStackTrace(newStack);
    }
}
