package com.prometheus.thoth.data.hbase.core;

import org.apache.hadoop.hbase.client.Table;

/**
 * Created by liangliang on 2017/04/10.
 *
 * @author liangliang
 * @since 2017/04/10
 */
public interface TableCallback<T> {

     /**
     * Gets called by {@link HbaseTemplate} execute with an active Hbase table. Does need to care about activating or closing down the table.
     *
     * @param table active Hbase table
     * @return a result object, or null if none
     * @throws Throwable thrown by the Hbase API
     */
    T doInTable(Table table) throws Throwable;
}
