package com.prometheus.thoth.redis.support.enums;

/**
 * redis key设置过期时间单位
 */
public enum KeyExpireTimeUnit {
    SECOND("EX"), MILLISECOND("PX");

    KeyExpireTimeUnit(String nx) {
        this.value = nx;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;


}
