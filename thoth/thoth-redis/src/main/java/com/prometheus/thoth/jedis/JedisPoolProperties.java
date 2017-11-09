package com.prometheus.thoth.jedis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import redis.clients.jedis.Protocol;

/**
 * Created by liangliang on 2017/07/18.
 *
 * @author liangliang
 * @since 2017/07/18
 */
@ConfigurationProperties(prefix = JedisPoolProperties.JEDIS_POOL_PREFIX)
public class JedisPoolProperties {

    public static final String JEDIS_POOL_PREFIX = "jedis.pool";

    private String masterName = "mymaster";

    private String sentinelNodes;

    private String password = null;

    private int database = Protocol.DEFAULT_DATABASE;

    /** 对象池最大数 */
    private int maxTotal = 200;

    /** idle对列最大数 */
    private int maxIdle = 100;

    /** idle队列最小数 */
    private int minIdle = 10;

    /** 获取连接超时时间 */
    private int maxWaitMillis = 10000;

    private boolean testOnBorrow = true;

    private boolean testOnReturn = true;

    /** Idle时进行连接扫描 */
    private boolean testWhileIdle = true;

    /** 表示idle object evitor两次扫描之间要sleep的毫秒数 */
    private long timeBetweenEvictionRunsMillis = 30000;

    /** 表示idle object evitor每次扫描的最多的对象数 */
    private int numTestsPerEvictionRun = 10;

    /** 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
     * 这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义 */
    private int minEvictableIdleTimeMillis = 60000;

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getSentinelNodes() {
        return sentinelNodes;
    }

    public void setSentinelNodes(String sentinelNodes) {
        this.sentinelNodes = sentinelNodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }
}
