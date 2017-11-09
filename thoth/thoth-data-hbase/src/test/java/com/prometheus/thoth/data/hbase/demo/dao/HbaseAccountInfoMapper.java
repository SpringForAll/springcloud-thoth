package com.prometheus.thoth.data.hbase.demo.dao;

import com.prometheus.thoth.data.hbase.demo.entity.UserInfo;
import com.prometheus.thoth.data.hbase.core.HbaseTemplate;
import com.prometheus.thoth.data.hbase.core.RowMapper;
import com.prometheus.thoth.data.hbase.handler.HbaseFindBuilder;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangliang on 2017/04/11.
 *
 * @author liangliang
 * @since 2017/04/11
 */
@Repository("hbaseAccountInfoMapper")
public class HbaseAccountInfoMapper {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    public Boolean createUserInfo(String tableName, final String family, UserInfo userInfo) {

        Put put = new Put(Bytes.toBytes(""));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes("id"), Bytes.toBytes(userInfo.getId()));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes("userName"), Bytes.toBytes(userInfo
                .getId()));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes("id"), Bytes.toBytes(userInfo.getId()));

        hbaseTemplate.saveOrUpdate(tableName, put);
        return false;
    }

    public UserInfo findUserInfoByEntity(String tableName, final String family, String rowKey) {

        return hbaseTemplate.get(tableName, rowKey, family, new RowMapper<UserInfo>() {
            @Override
            public UserInfo mapRow(Result result, int rowNum) throws Exception {
                return (UserInfo) new HbaseFindBuilder<UserInfo>(family, result, UserInfo.class)
                        .build("userName", "age", "id").fetch();
            }
        });
    }

    public List<UserInfo> findAll(String tableName, final String family) {

        final byte[] cf_info = family.getBytes();

        final byte[] age_info = Bytes.toBytes("age");
        final byte[] id_info = Bytes.toBytes("id");
        final byte[] username_info = Bytes.toBytes("userName");

        return hbaseTemplate.find(tableName, family, new RowMapper<UserInfo>() {
            @Override
            public UserInfo mapRow(Result result, int rowNum) throws Exception {

                /*UserInfo u = new UserInfo();

                *//*u.setId(Bytes.toString(result.getValue(cf_info, id_info)));
                u.setUserName(Bytes.toString(result.getValue(cf_info, username_info)));
                u.setAge(Bytes.toInt(result.getValue(cf_info, age_info)));*//*

                return u;*/
                return (UserInfo) new HbaseFindBuilder<UserInfo>(family, result, UserInfo.class)
                        .build("userName", "age", "id").fetch();
            }
        });
    }
}
