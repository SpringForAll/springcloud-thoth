package com.prometheus.thoth.data.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by liangliang on 2017/03/18.
 *
 * @author liangliang
 * @since 2017/03/18
 */
@Component
public class KafkaConsumer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String KAFKA_GROUP = "thoth-data-consumer-test";

    @PostConstruct
    public void init() {
        logger.debug("=====>>>>>>init>>>>>>>>>>>>>>>>>>");
    }

    /**
     * 监听kafka消息,如果有消息则消费
     *
     * @param record 消息实体bean
     */
    @KafkaListener(topics = "test", group = KAFKA_GROUP)
    public void executeSession(ConsumerRecord<String, String> record) {
        logger.info("listen record:{}", record.value());

        logger.debug("execute topic:{}, value:{}, key:{}, offset:{}, partition:{}, checksum:{}," +
                "timestampType:{},", record.topic(), record.value(), record.key(), record.offset
                (), record.partition(), record.checksum(), record.timestampType(), record
                .timestamp());
    }

}
