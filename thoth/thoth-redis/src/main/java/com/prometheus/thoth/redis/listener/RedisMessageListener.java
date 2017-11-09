package com.prometheus.thoth.redis.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * RedisMessageListener for key expired,other implement just extends this class
 * Created by zhuhuaiqi on 2017/3/17.
 */
public class RedisMessageListener implements MessageListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        logger.debug("-----------redisMessageListener--------Message received: " + message.toString());
    }
}
