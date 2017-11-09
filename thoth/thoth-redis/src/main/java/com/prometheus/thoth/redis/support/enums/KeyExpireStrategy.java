package com.prometheus.thoth.redis.support.enums;

/**
 * redis key设置过期时间策略
 */
public enum KeyExpireStrategy {
    SET_ON_KEY_EXIST("XX"), SET_ON_KEY_NOT_EXIST("NX");

    private String value;

    KeyExpireStrategy(String nx) {
        this.value = nx;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
