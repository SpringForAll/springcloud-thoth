package com.prometheus.thoth.data.kafka.config;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.common.util.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liangliang on 2017/03/18.
 *
 * @author liangliang
 * @since 2017/03/18
 */
@EnableKafka
@Configuration
@EnableConfigurationProperties(KafkaConsumerProperties.class)
@ConditionalOnClass(value = org.apache.kafka.clients.consumer.KafkaConsumer.class)
public class KafkaConsumerAutoConfiguration {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private KafkaConsumerProperties kafkaConsumerProperties;

    public KafkaConsumerAutoConfiguration(KafkaConsumerProperties kafkaConsumerProperties) {
        logger.debug("KafkaConsumerAutoConfiguration kafkaConsumerProperties:{}",
                JSON.toJSONString(kafkaConsumerProperties));
        this.kafkaConsumerProperties = kafkaConsumerProperties;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(1000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        String brokers = kafkaConsumerProperties.getBrokerAddress();
        if (StringUtils.isEmpty(brokers)) {
            throw new RuntimeException("kafka broker address is emptiy");
        }

        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getBrokerAddress());
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.getGroupId());
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); //自动commit
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100"); //定时commit的周期
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000"); //consumer活性超时时间
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); //从何处开始消费,latest 表示消费最新消息,earliest 表示从头开始消费,none表示抛出异常,默认latest
        return propsMap;
    }

    /*@Bean
    public KafkaConsumer listener() {
        return new KafkaConsumer();
    }*/
}
