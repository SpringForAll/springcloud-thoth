package com.prometheus.thoth.common.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 统一返回结果RestResult.
 * <p>
 * Created by liangliang on 2017/02/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult<T extends Object> implements Serializable{

    protected static final Logger LOGGER = LoggerFactory.getLogger(RestResult.class);

    /**
     * 返回编码
     */
    @JSONField(ordinal = 1)
    private int code;

    /**
     * 返回消息
     */
    @JSONField(ordinal = 2)
    private String message;

    /**
     * 返回数据
     */
    @JSONField(ordinal = 3)
    private T data;

    public RestResult() {

    }

    public RestResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return StringUtils.trimToEmpty(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult(code=" + this.code + ", message=" + this
                .message + ", data=" + JSON.toJSONString(this.data) + ")";
    }
}
