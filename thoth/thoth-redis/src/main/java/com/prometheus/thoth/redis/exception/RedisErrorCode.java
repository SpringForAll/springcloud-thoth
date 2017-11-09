package com.prometheus.thoth.redis.exception;

import com.prometheus.thoth.common.exception.ErrorCode;

public enum RedisErrorCode implements ErrorCode {
    REDIS_KEY_PRFIX_IS_NULL(11000, "Redis Key 前缀为空");

    RedisErrorCode(int code, String message) {
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
