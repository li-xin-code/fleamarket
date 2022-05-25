package com.lixin.common.exception;

/**
 * @author lixin
 */
public class AuthenticateException extends RuntimeException {
    private static final long serialVersionUID = 2499645968728759430L;


    public AuthenticateException(String message) {
        super(message);
    }
}
