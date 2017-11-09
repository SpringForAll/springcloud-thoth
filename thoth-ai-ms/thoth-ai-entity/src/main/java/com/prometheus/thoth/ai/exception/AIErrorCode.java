package com.prometheus.thoth.ai.exception;

import com.prometheus.thoth.common.exception.ErrorCode;

/**
 * created by sunliangliang
 * @author sunliangliang
 */
public enum AIErrorCode implements ErrorCode{

    DATA_NOT_EXISTS(2001, "实体不存在！"),
    ROBOT_ALREADY_EXISTS(2002,"机器人名称不允许重复！"),

    NOTIFY_AI_ROBOT_UPDATE_FAILED(2100,"通知机器人更新失败"),

    FILE_EMPTY(2100, "UPLOAD FILE IS EMPTY!"),
    INVALID_IMAGE_FORMAT(2101, "IMAGE FORMAT IS INVALID!");

    private final int code;

    private final String message;

    AIErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
