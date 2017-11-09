package com.prometheus.thoth.redis.support;

import java.lang.annotation.*;

/**
 * Redis key 前缀
 * Created by zhuhuaiqi on 2017/4/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RedisKeyPrefix {
    String value() default "";
}
