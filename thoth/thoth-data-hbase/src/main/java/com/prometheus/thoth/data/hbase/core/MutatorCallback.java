package com.prometheus.thoth.data.hbase.core;

import org.apache.hadoop.hbase.client.BufferedMutator;

/**
 * Created by liangliang on 2017/04/10.
 *
 * @author liangliang
 * @since 2017/04/10
 */
public interface MutatorCallback {

    void doInMutator(BufferedMutator mutator) throws Throwable;
}
