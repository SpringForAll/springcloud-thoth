package com.prometheus.thoth.redis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * created by sunliangliang
 */
public class RedisLock {
    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);
    private final StringRedisTemplate stringRedisTemplate;
    private final byte[] lockKey;
    public RedisLock(StringRedisTemplate stringRedisTemplate, String lockKey) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockKey = lockKey.getBytes();
    }
    private boolean tryLock(RedisConnection conn, int lockSeconds) throws Exception {
        long nowTime = System.currentTimeMillis();
        long expireTime = nowTime + lockSeconds * 1000 + 1000; // 容忍不同服务器时间有1秒内的误差
        if (conn.setNX(lockKey, longToBytes(expireTime))) {
            conn.expire(lockKey, lockSeconds);
            return true;
        } else {
            byte[] oldValue = conn.get(lockKey);
            if (oldValue != null && bytesToLong(oldValue) < nowTime) {
                // 这个锁已经过期了，可以获得它
                // PS: 如果setNX和expire之间客户端发生崩溃，可能会出现这样的情况
                byte[] oldValue2 = conn.getSet(lockKey, longToBytes(expireTime));
                if (Arrays.equals(oldValue, oldValue2)) {
                    // 获得了锁
                    conn.expire(lockKey, lockSeconds);
                    return true;
                } else {
                    // 被别人抢占了锁(此时已经修改了lockKey中的值，不过误差很小可以忽略)
                    return false;
                }
            }
        }
        return false;
    }
    /**
     * 尝试获得锁，成功返回true，如果失败或异常立即返回false
     *
     * @param lockSeconds 加锁的时间(秒)，超过这个时间后锁会自动释放
     */
    public boolean tryLock(final int lockSeconds) {
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
                try {
                    return tryLock(conn, lockSeconds);
                } catch (Exception e) {
                    logger.error("tryLock Error", e);
                    return false;
                }
            }
        });
    }
    /**
     * 轮询的方式去获得锁，成功返回true，超过轮询次数或异常返回false
     *
     * @param lockSeconds       加锁的时间(秒)，超过这个时间后锁会自动释放
     * @param tryIntervalMillis 轮询的时间间隔(毫秒)
     * @param maxTryCount       最大的轮询次数
     */
    public boolean tryLock(final int lockSeconds, final long tryIntervalMillis, final int maxTryCount) {
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
                int tryCount = 0;
                while (true) {
                    if (++tryCount >= maxTryCount) {
                        // 获取锁超时
                        return false;
                    }
                    try {
                        if (tryLock(conn, lockSeconds)) {
                            return true;
                        }
                    } catch (Exception e) {
                        logger.error("tryLock Error", e);
                        return false;
                    }
                    try {
                        Thread.sleep(tryIntervalMillis);
                    } catch (InterruptedException e) {
                        logger.error("tryLock interrupted", e);
                        return false;
                    }
                }
            }
        });
    }
    /**
     * 如果加锁后的操作比较耗时，调用方其实可以在unlock前根据时间判断下锁是否已经过期
     * 如果已经过期可以不用调用，减少一次请求
     */
    public void unlock() {
        stringRedisTemplate.delete(new String(lockKey));
    }
    public byte[] longToBytes(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / Byte.SIZE);
        buffer.putLong(value);
        return buffer.array();
    }
    public long bytesToLong(byte[] bytes) {
        if (bytes.length != Long.SIZE / Byte.SIZE) {
            throw new IllegalArgumentException("wrong length of bytes!");
        }
        return ByteBuffer.wrap(bytes).getLong();
    }
}

