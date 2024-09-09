package com.example.cryoem.exception;

/**
 * @author wangxingang
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        this(message, new RuntimeException());
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
