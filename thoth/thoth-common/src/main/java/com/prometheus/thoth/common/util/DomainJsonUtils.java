package com.prometheus.thoth.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Domain JSON Utils
 * Created by zhuhuaiqi on 2017/3/29.
 */
public class DomainJsonUtils {
    protected final static Logger logger = LoggerFactory.getLogger(DomainJsonUtils.class);
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    /**
     * 对象转JSON
     *
     * @param t
     * @return
     */
    public static String buildToJson(Object t) {
        if (t == null) {
            return null;
        }
        String json = null;
        try {
            json = mapper.writeValueAsString(t);
            return json;
        } catch (Exception e) {
            logger.error(":>>> buildToJson error,[errorMag] is:\n{}", ExceptionLog.getErrorStack(e));

        }

        return json;
    }

    //TODO:
    public static Object buildFromJson(String data, Object clazz) {
        try {
            return mapper.readValue(data, clazz.getClass());
        } catch (IOException e) {
            logger.error(":>>> buildFromJson error,[errorMag] is:\n{}", ExceptionLog.getErrorStack(e));
        }
        return null;

    }

    public static Object buildFromJson(String data, Object clazz, Object... args) {
        try {
            for (Object o : args) {
                mapper.getTypeFactory().constructParametricType(List.class, o.getClass());
            }
            return mapper.readValue(data, clazz.getClass());
        } catch (IOException e) {
            logger.error(":>>> buildFromJson error,[errorMag] is:\n{}", ExceptionLog.getErrorStack(e));
        }
        return null;

    }
}
