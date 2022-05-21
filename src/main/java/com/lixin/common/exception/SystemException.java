package com.lixin.common.exception;

/**
 * @author lixin
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = -7734526638629826429L;

    public SystemException() {
        super("SystemException");
    }

    public SystemException(String message) {
        super("SystemException: " + message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }
}
