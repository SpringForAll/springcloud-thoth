package com.prometheus.thoth.data.hbase.demo.entity;

import com.prometheus.thoth.data.hbase.annotation.Column;
import com.prometheus.thoth.data.hbase.annotation.HbaseTeable;
import com.prometheus.thoth.data.hbase.annotation.RowKey;

/**
 * Created by liangliang on 2017/04/11.
 *
 * @author liangliang
 * @since 2017/04/11
 */
@HbaseTeable(tableName = "user", family = "info")
public class UserInfo {

    @RowKey
    private String id;

    @Column(qualifier = "user_a")
    private String userName;

    @Column
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
