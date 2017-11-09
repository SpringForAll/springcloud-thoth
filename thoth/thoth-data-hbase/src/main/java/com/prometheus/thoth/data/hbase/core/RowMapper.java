package com.prometheus.thoth.data.hbase.core;

import org.apache.hadoop.hbase.client.Result;

/**
 * Created by liangliang on 2017/04/10.
 *
 * @author liangliang
 * @since 2017/04/10
 */
public interface RowMapper<T> {

    T mapRow(Result result, int rowNum) throws Exception;
}
