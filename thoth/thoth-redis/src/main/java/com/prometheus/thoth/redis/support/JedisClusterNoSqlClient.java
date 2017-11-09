package com.prometheus.thoth.redis.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.prometheus.thoth.common.util.StringUtils;
import com.prometheus.thoth.redis.exception.RedisException;
import com.prometheus.thoth.redis.support.enums.KeyExpireStrategy;
import com.prometheus.thoth.redis.support.enums.KeyExpireTimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * Jedis cluster NoSqlClient
 * Created by huaiqi-zhu on 2017/3/6.
 */
@Service
public class JedisClusterNoSqlClient<T> implements NoSqlClient<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JedisCluster jedisCluster;


    @Override
    public void set(final String key, final T value) {
        jedisCluster.set(key, checkRedisNoSqlSupport(key, value));
    }

    @Override
    public void set(String key, T value, Long expiry) {
        String v = jedisCluster.set(key, checkRedisNoSqlSupport(key, value), KeyExpireStrategy.SET_ON_KEY_NOT_EXIST.getValue(), KeyExpireTimeUnit.SECOND.getValue(), expiry);
    }

    @Override
    public void set(String key, T value, KeyExpireStrategy keyExpireStrategy, KeyExpireTimeUnit keyExpireTimeUnit, Long expiry) {
        jedisCluster.set(key, checkRedisNoSqlSupport(key, value), keyExpireTimeUnit.getValue(), keyExpireTimeUnit.getValue(), expiry);

    }

    @Override
    public <T> boolean exist(Class<T> clazz, String key) {
        checkRedisKey(key);
        return jedisCluster.exists(key);
    }

    @Override
    public T get(final String key, final Class<T> clazz) {
        checkRedisKey(key);
        String value = jedisCluster.get(key);
        return JSON.parseObject(value, clazz);
    }

    @Override
    public <T> void delete(String key) {
        checkRedisKey(key);
        jedisCluster.del(key);

    }

    @Override
    public <T> void hashSet(String key, String hashKey, T hashValue) {
        checkRedisKey(key);
        jedisCluster.hset(key, hashKey, JSON.toJSONString(hashValue));
    }

    @Override
    public Object hashGet(String key, String hashKey, final Class<?> hashValueClazz) {
        checkRedisKey(key);
        String hashValue = jedisCluster.hget(key, hashKey);
        if (StringUtils.isNotBlank(hashValue))
            return JSON.parseObject(hashValue, hashValueClazz);
        return null;
    }

    public long ttl(String key) {
        this.checkRedisKey(key);
        return jedisCluster.ttl(key);
    }

    /**
     * 检查Redis参数
     *
     * @param key
     * @param value
     * @return
     */
    private String checkRedisNoSqlSupport(final String key, final T value) {
        checkRedisKey(key);
        if (value == null)
            throw new RedisException("value is null!");
        String stringValue = null;
        try {
            stringValue = JSON.toJSONString(value,true);
        } catch (JSONException e) {
            throw new RedisException("value toJSONString exception!");
        }
        if (StringUtils.isBlank(stringValue))
            throw new RedisException("value is null!");
        return stringValue;
    }

    /**
     * 检查Redis key,redis key 长度建议不要超过1M,redis支持512M
     *
     * @param key
     */
    private void checkRedisKey(final String key) {
        if (StringUtils.isBlank(key))
            throw new RedisException("key is null!");
        if (key.length() > 1000000L)
            throw new RedisException("length of key greater than 1M!");
    }
}
