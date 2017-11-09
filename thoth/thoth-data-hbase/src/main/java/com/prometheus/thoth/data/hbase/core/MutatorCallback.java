package com.prometheus.thoth.data.hbase.core;

import org.apache.hadoop.hbase.client.BufferedMutator;

/**
 * Created by wujun on 2017/04/10.
 *
 * @author wujun
 * @since 2017/04/10
 */
public interface MutatorCallback {

    void doInMutator(BufferedMutator mutator) throws Throwable;
}
