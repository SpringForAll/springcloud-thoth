package com.prometheus.thoth.common.exception;

/**
 * Created by liangliang on 2017/02/16.
 */
public enum GlobalErrorCode implements ErrorCode {

    SUCCESS(1, "OK"),

    FAILURE(0, "Failure"),

    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    MONGO_COLLECTION_NOT_EXIT(3301, "MONGO COLLECTION NOT EXIST"),
    INVALID_PARAM(100, "参数错误"),
    TENANTID_ID_NOT_EMPTY(1001, "租户ID不能为空"),
    UNSUPPORT_IMAGE_TYPE(110, "不支持的图片格式"),
    UNSUPPORT_STORE_PATH(111, "找不到文件"),;

    GlobalErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
