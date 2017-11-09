package com.prometheus.thoth.redis.support;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Created by huaiqi-zhu on 2017/3/8.
 */
public abstract class RedisId<T> implements Serializable {

    private static final long serialVersionUID = 1947961636324648604L;

    @Id
    private T id;
    // 修改时间
    private Long updateStamp;
    // 创建时间
    public Long createdTime;

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }


    public RedisId() {
        resetUpdateStamp();
    }

    public void resetUpdateStamp() {
        Date date = new Date();

        updateStamp = date.getTime();
    }

    public Long getUpdateStamp() {
        if (updateStamp == null) {
            return 0L;
        }

        return updateStamp;
    }
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public void setUpdateStamp(Long updateStamp) {
        this.updateStamp = updateStamp;
    }


}
