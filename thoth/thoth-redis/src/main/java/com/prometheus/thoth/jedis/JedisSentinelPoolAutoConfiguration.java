package com.prometheus.thoth.jedis;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liangliang on 2017/07/18.
 *
 * @author liangliang
 * @since 2017/07/18
 */
@Configuration
@EnableConfigurationProperties(JedisPoolProperties.class)
@ConditionalOnClass(value = Jedis.class)
public class JedisSentinelPoolAutoConfiguration {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JedisPoolProperties properties;

    public JedisSentinelPoolAutoConfiguration(JedisPoolProperties properties) {
        logger.debug("----init->>>>>>JedisPoolProperties :{}", JSON.toJSONString(properties));
        this.properties = properties;
    }

    @ConditionalOnProperty(value = "jedis.pool.sentinel-nodes", matchIfMissing = false)
    @Bean
    public JedisSentinelPool jedisSentinelPool() {
        String nodesString = properties.getSentinelNodes();
        if (StringUtils.isEmpty(nodesString)) {
            return null;
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // 设置最大连接数
        poolConfig.setMaxTotal(properties.getMaxTotal());
        // 设置空间连接
        poolConfig.setMaxIdle(properties.getMaxIdle());
        poolConfig.setMinIdle(properties.getMinIdle());
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        poolConfig.setMaxWaitMillis(properties.getMaxWaitMillis());
        poolConfig.setTestOnBorrow(properties.isTestOnBorrow());
        poolConfig.setTestOnReturn(true);
        //Idle时进行连接扫描
        poolConfig.setTestWhileIdle(true);
        //表示idle object evitor两次扫描之间要sleep的毫秒数
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        //表示idle object evitor每次扫描的最多的对象数
        poolConfig.setNumTestsPerEvictionRun(10);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        poolConfig.setMinEvictableIdleTimeMillis(60000);

        String masterName = properties.getMasterName();
        String[] nodes = nodesString.split(",");
        Set<String> sentinels = new HashSet<String>(nodes.length);
        for (String node : nodes) {
            sentinels.add(node);
        }
        JedisSentinelPool jedisSentinelPool = null;
        if (StringUtils.isNotEmpty(properties.getPassword())) {
            jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig,
                    properties.getPassword());
        } else {
            jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig);
        }
        return jedisSentinelPool;
    }
}
