package com.prometheus.thoth.data.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liangliang on 2017/03/18.
 *
 * @author liangliang
 * @since 2017/03/18
 */
@ConfigurationProperties(prefix = KafkaConsumerProperties.KAFKA_CONSUMER_PREFIX)
public class KafkaConsumerProperties {

    public static final String KAFKA_CONSUMER_PREFIX = "kafka";

    private String brokerAddress;

    private String groupId;

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public void setBrokerAddress(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
