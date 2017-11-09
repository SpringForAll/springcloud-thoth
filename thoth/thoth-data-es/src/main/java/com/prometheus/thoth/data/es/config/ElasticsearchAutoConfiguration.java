package com.prometheus.thoth.data.es.config;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.net.InetAddress;

import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

/**
 * Created by liangliang on 2017/04/11.
 *
 * @author liangliang
 * @since 2017/04/11
 */
@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
@ConditionalOnClass(TransportClient.class)
public class ElasticsearchAutoConfiguration {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ElasticsearchProperties elasticsearchProperties;

    public ElasticsearchAutoConfiguration(ElasticsearchProperties elasticsearchProperties) {
        logger.debug("ElasticsearchAutoConfiguration elasticsearchProperties:{}", JSON
                .toJSONString(elasticsearchProperties));
        this.elasticsearchProperties = elasticsearchProperties;
    }

    @Bean(destroyMethod = "close")
    public Client client() throws Exception {
        //TransportClient client = TransportClient.builder().settings(settings()).build();
        TransportClient client = new PreBuiltTransportClient(settings());
        //TransportClient client = new PreBuiltXPackTransportClient(settings()); //X-pack

        String clusterNodes = elasticsearchProperties.getClusterNodes();
        Assert.hasText(clusterNodes, "[Assertion failed] clusterNodes settings missing.");

        for (String clusterNode : split(clusterNodes, ElasticsearchProperties.COMMA)) {
            String hostName = substringBeforeLast(clusterNode, ElasticsearchProperties.COLON);
            String port = substringAfterLast(clusterNode, ElasticsearchProperties.COLON);
            Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
            Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");
            logger.info("adding transport node : " + clusterNode);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName
                    (hostName), Integer.valueOf(port)));
        }
        client.connectedNodes();
        return client;
    }

    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }*/

    private Settings settings() {
        return Settings.builder()
                .put("cluster.name", elasticsearchProperties.getClusterName())
                .put("client.transport.sniff", elasticsearchProperties.getClientTransportSniff())
                .put("client.transport.ignore_cluster_name", elasticsearchProperties
                        .getClientIgnoreClusterName())
                .put("client.transport.ping_timeout", elasticsearchProperties
                        .getClientPingTimeout())
                .put("client.transport.nodes_sampler_interval", elasticsearchProperties
                        .getClientNodesSamplerInterval())
                //.put("xpack.security.user", "elastic:1qaz2wsx")
                .build();
    }
}
