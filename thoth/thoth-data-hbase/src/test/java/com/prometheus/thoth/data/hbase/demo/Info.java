package com.prometheus.thoth.data.hbase.demo;

/**
 * Created by liangliang on 2017/04/22.
 *
 * @author liangliang
 * @since 2017/04/22
 */
public class Info<T> {
    private T var;     // 定义泛型变量

    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return this.var;
    }

    public String toString() {   // 直接打印
        return this.var.toString();
    }
};