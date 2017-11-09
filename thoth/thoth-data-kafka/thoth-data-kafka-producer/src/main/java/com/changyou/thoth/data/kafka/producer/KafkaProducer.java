package com.prometheus.thoth.data.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;

/**
 * Created by liangliang on 2017/03/18.
 *
 * @author liangliang
 * @since 2017/03/18
 */
public class KafkaProducer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final String topic, Serializable data) {
        final String jsonData = JSON.toJSONString(data, SerializerFeature.UseSingleQuotes);
        logger.info("kafka producer topic:{}, data:{}", topic, jsonData);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic,
                jsonData);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("sent topic={} data={} with offset={} partition={}", topic, jsonData,
                        result.getRecordMetadata().offset(), result.getRecordMetadata().partition
                                ());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("unable to send topic={}, data={}", topic, jsonData, ex);
            }
        });

    }

    /**
     * 把相同的comId和bizId放在同一个分区.
     *
     * @param topic
     * @param key
     * @param data
     */
    public <T> void send(final String topic, T key, Serializable data) {
        final String jsonData = JSON.toJSONString(data, SerializerFeature.UseSingleQuotes);
        String strKey = String.valueOf(key);
        logger.info("kafka producer topic:{}, key:{}, data:{}", topic, strKey, jsonData);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, strKey,
                jsonData);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("sent topic={} data={} with offset={} partition={}", topic, jsonData,
                        result.getRecordMetadata().offset(), result.getRecordMetadata().partition
                                ());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("unable to send topic={}, data={}", topic, jsonData, ex);
            }
        });

    }

    public KafkaTemplate<String, String> getKafkaTemplate() {
        return kafkaTemplate;
    }

    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
