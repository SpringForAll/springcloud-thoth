package com.prometheus.thoth.common.exception;

/**
 * Created by liangliang on 2017/05/05.
 *
 * @author liangliang
 * @since 2017/05/05
 */
public class ArgumentException extends java.lang.IllegalArgumentException {

    protected ErrorCode errorCode;

    public ArgumentException(ErrorCode errorCode) {
        super(formatMsg(errorCode));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public final static String formatMsg(ErrorCode errorCode) {
        return String.format("%s:%s", errorCode.getCode(), errorCode.getMessage());
    }
}
