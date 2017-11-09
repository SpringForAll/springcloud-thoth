package com.prometheus.thoth.jedis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration

@ConfigurationProperties(prefix = "jedis.cluster")
public class JedisClusterConfig {
    private String nodesString;
    private Boolean testWhileIdle;
    private Integer connectionTimeout;
    private Integer soTimeout;
    private Integer maxAttempts;

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }


    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public String getNodesString() {
        return nodesString;
    }

    public void setNodesString(String nodesString) {
        this.nodesString = nodesString;
    }

    @Bean
    public JedisCluster jedisCluster() {

        String[] nodes = nodesString.split(",");
        Set<HostAndPort> nodeSet = new HashSet<HostAndPort>(nodes.length);

        for (String node : nodes) {
            String[] nodeAttrs = node.trim().split(":");
            HostAndPort hap = new HostAndPort(nodeAttrs[0], Integer.valueOf(nodeAttrs[1]));
            nodeSet.add(hap);
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // TODO:password and poolconfig
        JedisCluster jc = new JedisCluster(nodeSet, connectionTimeout, soTimeout, maxAttempts, poolConfig);
//                new JedisCluster(nodeSet, poolConfig);
        return jc;
    }

}
