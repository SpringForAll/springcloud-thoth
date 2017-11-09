package com.prometheus.thoth.common.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 统计-时间类型.
 *
 * @author liangliang
 * @since 2017/06/20
 */
public enum TimeType {

    /**
     * 自定义范围
     */
    RANGE(1),

    /**
     * 昨天
     */
    YESTERDAY(2),

    /**
     * 过去7天
     */
    LAST_7_DAYS(3),

    /**
     * 过去30天
     */
    LAST_30_DAYS(4),;

    private int type;

    TimeType(int type) {
        this.type = type;
    }

    @JsonValue
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }

    private static Map<Integer, TimeType> map = Maps.newHashMapWithExpectedSize(4);

    static {
        for (TimeType intervalType : TimeType.values()) {
            map.put(intervalType.type, intervalType);
        }
    }

    @JsonCreator
    public static TimeType valueOf(int value) {
        TimeType intervalType = map.get(value);
        Assert.notNull(intervalType, "not defined Enum");
        return intervalType;
    }

}
