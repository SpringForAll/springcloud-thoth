package com.prometheus.thoth.redis.support;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * support distributed system based redis
 * Created by zhuhuaiqi on 2017/3/22.
 */

public interface DistributedSupport {
    /**
     * generate id for global
     *
     * @returns
     */
    Long generateId(String key);

    /**
     * 计数
     *
     * @param key
     * @return
     */
    Long count(String key);

    /**
     * distributed lock
     *
     * @param key
     * @return
     */
    boolean isLocked(String key);

    boolean tryLocke(String key);

    boolean tryLocke(String key, long timeout, TimeUnit unit);

    void lock(String key);

    boolean tryLock(List<String> keyList);

    boolean tryLock(List<String> keyList, long timeout, TimeUnit unit);

    void unLock(String key);

    void unLock(List<String> keyList);
}
