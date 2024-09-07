package com.example.cryoem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author wangxingang
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(
                Result.error(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
