package com.prometheus.thoth.common.util;

import java.lang.reflect.Field;

/**
 * 反射工具类
 * Created by zhuhuaiqi on 2017/3/25.
 */
public class ReflectionUtils {
    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("cannot find or access field '" + fieldName + "' from " + clazz.getName(), e);
        }
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T getValue(Object obj, Field field) {
        try {
            return (T) field.get(obj);
        } catch (IllegalArgumentException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }
}
