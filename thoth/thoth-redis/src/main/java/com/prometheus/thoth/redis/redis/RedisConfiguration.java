package com.prometheus.thoth.redis.redis;


import com.prometheus.thoth.redis.listener.RedisMessageListener;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * redis配置
 * Created by huaiqi-zhu on 2017/3/6.
 */
//@Configuration
public class RedisConfiguration<K, V> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.redis.cluster.nodes}")

    private String clusterNodes;

    @Value("${spring.redis.cluster.timeout}")

    private Long timeout;

    @Value("${spring.redis.cluster.max-redirects}")

    private int redirects;

    // 配置文件加载Redis cluster配置
//    @Bean
    public RedisClusterConfiguration getClusterConfiguration() {
        logger.info("------------start RedisClusterConfiguration----");
        Map<String, Object> source = new HashMap<String, Object>();

        source.put("spring.redis.cluster.nodes", clusterNodes);

        source.put("spring.redis.cluster.timeout", timeout);

        source.put("spring.redis.cluster.max-redirects", redirects);
        logger.info("------------end RedisClusterConfiguration----");
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));

    }

    //    @Bean
    public JedisConnectionFactory getConnectionFactory() {

        return new JedisConnectionFactory(getClusterConfiguration());

    }

    //    @Bean
    public JedisClusterConnection getJedisClusterConnection() {

        return (JedisClusterConnection) getConnectionFactory().getConnection();

    }

    //    @Bean
    GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        return jackson2JsonRedisSerializer;
    }

    //    @Bean(name = "defaultRedisTemplate")
    RedisTemplate<String, Object> objRedisTemplate(JedisConnectionFactory connectionFactory,
                                                   GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setEnableTransactionSupport(true);
        logger.info("---------------load bean redisTemplate-------------");
        return redisTemplate;
    }


    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageListener());
    }

    /**
     * register listener for key expired
     *
     * @param connectionFactory
     * @return
     */

    RedisMessageListenerContainer keyExpirationListenerContainer(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);

        listenerContainer.addMessageListener(messageListener(), new PatternTopic("thoth.*__:expired"));

        return listenerContainer;
    }
}
