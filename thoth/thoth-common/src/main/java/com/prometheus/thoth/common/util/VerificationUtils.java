package com.prometheus.thoth.common.util;

import java.security.Security;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunliangliang on 2017/4/26.
 * 校验规则
 */
public class VerificationUtils {
    /**
     * 校验是否为ip
     *
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        return match(regex, ip);
    }

    public static boolean fuzzyIpMatch(String ip) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String symbolnum = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)";
        String regex = "^" + num + "\\." + num + "\\." + symbolnum + "\\." + symbolnum + "$";
        return match(regex, ip);
    }

    /**
     * 校验两个ip大小
     * 转换为long数字比较
     *
     * @param startIp
     * @param endIp
     * @return
     */
    public static boolean ipRangeMatch(String startIp, String endIp) {
        if (isIp(startIp) && isIp(endIp)) {
            Long long_startIp = getIpNumber(startIp);
            Long long_endIp = getIpNumber(endIp);
            if (long_endIp > long_startIp) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验，所提交ip是否在范围
     *
     * @param startIp
     * @param endIp
     * @return
     */

    public static boolean inIpRange(String startIp, String endIp, String ip) {
        if (ip.contains("*")) {
            return false;
        }
        Long long_startIp = getIpNumber(startIp);
        Long long_endIp = getIpNumber(endIp);
        Long long_tmp = getIpNumber(ip);
        if (long_tmp >= long_startIp && long_tmp <= long_endIp) {
            return true;
        }
        return false;
    }


    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 移位运算，避免192.168.1.123 和 192.168.11.23 以及 192.168.112.3类似ip值相等
     *
     * @param ip
     * @return
     */
    public static Long getIpNumber(String ip) {
        String[] ips = ip.split("\\.");
        long n = 0;
        for (int i = 0; i < ips.length; i++) {
            n = n << 8;
            n += Integer.parseInt(ips[i]);
        }
        return n;
    }

    public static boolean isDouble(String value) {
        String regex = "^(0|[1-9]|[1-9]\\d|100)(\\.\\d{1,2}|\\.{0})$";
        return match(regex, value);
    }

    public static boolean isImage(String imageName) {
        String regex = "(?i).+?\\.(jpg|gif|bmp|JPG|png|PNG)";
        return match(regex, imageName);
    }

}
