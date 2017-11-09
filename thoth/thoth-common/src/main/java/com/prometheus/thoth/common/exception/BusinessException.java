package com.prometheus.thoth.common.exception;

/**
 * 业务异常.
 * <p>
 * Created by liangliang on 2017/02/16.
 */
public class BusinessException extends RuntimeException {

    protected int code = 500;

    protected ErrorCode errorCode;

    @Deprecated
    public BusinessException(int code) {
        this.code = code;
    }

    //    @Deprecated
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    @Deprecated
    public BusinessException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    @Deprecated
    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(formatMsg(errorCode));
        this.code = errorCode.getCode();
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(formatMsg(errorCode), cause);
        this.code = errorCode.getCode();
        this.errorCode = errorCode;
    }

    public int getCode() {
        return this.code;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public final static String formatMsg(ErrorCode errorCode) {
        return String.format("%s-%s", errorCode.getCode(), errorCode.getMessage());
    }

}
