package com.changyou.thoth.common.exception;

/**
 * Created by wujun on 2017/05/05.
 *
 * @author wujun
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
