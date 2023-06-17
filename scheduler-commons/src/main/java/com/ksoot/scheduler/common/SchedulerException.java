package com.ksoot.scheduler.common;

import org.springframework.core.NestedRuntimeException;
import org.springframework.lang.Nullable;

public class SchedulerException extends NestedRuntimeException {

    /**
     * Constructor that takes a message.
     * @param msg the detail message
     */
    public SchedulerException(String msg) {
        super(msg);
    }

    /**
     * Constructor that takes a message and a root cause.
     * @param msg the detail message
     * @param cause the cause of the exception. This argument is generally
     * expected to be a proper subclass of {@link org.quartz.SchedulerException},
     * but can also be a JNDI NamingException or the like.
     */
    public SchedulerException(String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructor that takes a plain root cause, intended for
     * subclasses mirroring corresponding {@code jakarta.jms} exceptions.
     * @param cause the cause of the exception. This argument is generally
     * expected to be a proper subclass of {@link org.quartz.SchedulerException}.
     */
    public SchedulerException(@Nullable Throwable cause) {
        super(cause != null ? cause.getMessage() : null, cause);
    }

    /**
     * Return the detail message, including the message from the linked exception
     * if there is one.
     * @see org.quartz.SchedulerException
     */
    @Override
    @Nullable
    public String getMessage() {
        String message = super.getMessage();
        Throwable cause = getCause();
        if (cause instanceof org.quartz.SchedulerException schedulerException) {
            Throwable nestedCause = schedulerException.getCause();
            if (nestedCause != null) {
                String nestedCauseMessage = nestedCause.getMessage();
                String causeMessage = cause.getMessage();
                if (nestedCauseMessage != null && (causeMessage == null || !causeMessage.contains(nestedCauseMessage))) {
                    message = message + "; nested exception is " + nestedCause;
                }
            }
        }
        return message;
    }
}
