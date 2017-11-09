package com.prometheus.thoth.data.hbase.config;

import com.prometheus.thoth.data.hbase.core.HbaseTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Created by liangliang on 2017/04/10.
 *
 * @author liangliang
 * @since 2017/04/10
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(HbaseProperties.class)
@ConditionalOnClass(HBaseConfiguration.class)
public class HbaseAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";

    private HbaseProperties hbaseProperties;

    public HbaseAutoConfiguration(HbaseProperties hbaseProperties) {
        this.hbaseProperties = hbaseProperties;
    }

    @Bean
    @ConditionalOnMissingBean(com.prometheus.thoth.data.hbase.core.HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate() {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(HBASE_QUORUM, this.hbaseProperties.getQuorum());
        logger.info("hbase.zookeeper.quorum:{}", this.hbaseProperties.getQuorum());
        return new HbaseTemplate(configuration);
    }
}
