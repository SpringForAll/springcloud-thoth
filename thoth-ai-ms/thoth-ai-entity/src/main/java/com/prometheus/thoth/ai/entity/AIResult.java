package com.prometheus.thoth.ai.entity;

/**
 * created by sunliangliang
 */
public class AIResult {
    private Long code;
    private String text;
    private Object data;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
