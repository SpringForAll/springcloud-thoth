package com.prometheus.thoth.data.es.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liangliang on 2017/04/11.
 *
 * @author liangliang
 * @since 2017/04/11
 */
@ConfigurationProperties(prefix = ElasticsearchProperties.ELASTICSEARCH_PREFIX)
public class ElasticsearchProperties {

    public static final String ELASTICSEARCH_PREFIX = "es";

    public static final String COLON = ":";

	public static final String COMMA = ",";

    private String clusterName = "elasticsearch";

    private String clusterNodes = "127.0.0.1:9300";

    private Boolean clientTransportSniff = true;

    /** 是否忽略集群名字验证, 打开后集群名字不对也能连接上 */
    private Boolean clientIgnoreClusterName = Boolean.TRUE;

    /** 报错, ping等待时间 */
    private String clientPingTimeout = "5s";

    private String clientNodesSamplerInterval = "5s";

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public Boolean getClientTransportSniff() {
        return clientTransportSniff;
    }

    public void setClientTransportSniff(Boolean clientTransportSniff) {
        this.clientTransportSniff = clientTransportSniff;
    }

    public Boolean getClientIgnoreClusterName() {
        return clientIgnoreClusterName;
    }

    public void setClientIgnoreClusterName(Boolean clientIgnoreClusterName) {
        this.clientIgnoreClusterName = clientIgnoreClusterName;
    }

    public String getClientPingTimeout() {
        return clientPingTimeout;
    }

    public void setClientPingTimeout(String clientPingTimeout) {
        this.clientPingTimeout = clientPingTimeout;
    }

    public String getClientNodesSamplerInterval() {
        return clientNodesSamplerInterval;
    }

    public void setClientNodesSamplerInterval(String clientNodesSamplerInterval) {
        this.clientNodesSamplerInterval = clientNodesSamplerInterval;
    }
}
