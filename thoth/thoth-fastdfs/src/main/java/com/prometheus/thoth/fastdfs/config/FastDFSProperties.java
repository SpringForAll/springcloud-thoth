package com.prometheus.thoth.fastdfs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liangliang on 2017/03/27.
 *
 * @author liangliang
 * @since 2017/03/27
 */
@ConfigurationProperties(prefix = FastDFSProperties.CONFIG_PREFIX)
public class FastDFSProperties {

    public static final String CONFIG_PREFIX = "fastdfs";

    /** 是否需要返回NGINX全路径，默认不需要 */
    private boolean hasNginxWeb = false;

    private String nginxWeb;

    public boolean isHasNginxWeb() {
        return hasNginxWeb;
    }

    public void setHasNginxWeb(boolean hasNginxWeb) {
        this.hasNginxWeb = hasNginxWeb;
    }

    public String getNginxWeb() {
        return nginxWeb;
    }

    public void setNginxWeb(String nginxWeb) {
        this.nginxWeb = nginxWeb;
    }
}
