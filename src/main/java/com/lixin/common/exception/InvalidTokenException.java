package com.lixin.common.exception;

/**
 * @author lixin
 */
public class InvalidTokenException extends RuntimeException {
    private static final long serialVersionUID = 6297286553760451122L;

    public InvalidTokenException() {
        super("无效token");
    }
}
