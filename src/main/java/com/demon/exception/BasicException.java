package com.demon.exception;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2020/9/17 13:30
 */
/**
 * 自定义异常
 */
public class BasicException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code = 0;

    public BasicException() {}

    public BasicException(int code, String message) {
        super(message);
        this.code = code;
    }
    public BasicException(String message) {
        super(message);
    }
    public BasicException(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
