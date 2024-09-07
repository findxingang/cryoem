package com.example.cryoem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author wangxingang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    /**
     * {
     *     "code": 200,
     *     "message": "OK",
     *     "data": null
     * }
     */
    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return ok("OK", data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(HttpStatus.OK.value(), message, data);
    }

    /**
     * {
     *     "code": 500,
     *     "message": "服务器内部错误",
     *     "data": null
     * }
     */
    public static <T> Result<T> error() {
        return error("服务器内部错误");
    }

    public static <T> Result<T> error(String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

}
