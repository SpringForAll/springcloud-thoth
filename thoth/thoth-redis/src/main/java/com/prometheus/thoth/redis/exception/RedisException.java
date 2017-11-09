package com.prometheus.thoth.redis.exception;

/**
 * Redis Exception
 * Created by huaiqi-zhu on 2017/3/6.
 */
public class RedisException extends RuntimeException {


    public RedisException() {
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

}
