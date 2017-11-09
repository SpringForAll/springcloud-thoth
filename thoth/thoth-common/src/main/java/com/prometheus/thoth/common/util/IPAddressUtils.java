package com.prometheus.thoth.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liangliang on 2017/3/31.
 */
public class IPAddressUtils {


    public static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (isUnknown(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isUnknown(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isUnknown(ip)) {
            ip = request.getRemoteAddr();
        }

        return getFirstAddress(ip);
    }

    private static boolean isUnknown(String str) {
        return StringUtils.isBlank(str) || "unknown".equalsIgnoreCase(StringUtils.trimToEmpty(str));
    }

    private static String getFirstAddress(String str) {
        Matcher matcher = IP_ADDRESS_PATTERN.matcher(str);
        return (matcher.find() ? matcher.group() : null);
    }

    private static final Pattern IP_ADDRESS_PATTERN = Pattern
            .compile("[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}");
}
