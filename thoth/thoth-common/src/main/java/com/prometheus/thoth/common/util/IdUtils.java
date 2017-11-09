package com.prometheus.thoth.common.util;


import com.prometheus.thoth.common.exception.GlobalErrorCode;

/**
 * Created by liangliang on 2017/04/27.
 *
 * @author liangliang
 * @since 2017/04/27
 */
public class IdUtils {

    public static String getId(Long tenantId, String bizId) {
        AssertUtils.notNull(tenantId, GlobalErrorCode.TENANTID_ID_NOT_EMPTY);
        return String.format("%s:%s", tenantId, bizId);
    }
}
