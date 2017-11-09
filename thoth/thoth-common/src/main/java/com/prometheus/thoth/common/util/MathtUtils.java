package com.prometheus.thoth.common.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;

/**
 * 百分比计算.
 *
 * @author liangliang
 * @since 2017/07/03
 */
public class MathtUtils {

    protected static final Logger logger = LoggerFactory.getLogger(MathtUtils.class);

    private static NumberFormat percentFormat;

    static {
        percentFormat = NumberFormat.getInstance();
        percentFormat.setMaximumFractionDigits(2);
    }

    public static long avg(long total, long count) {
        return count > 0 ? total / count : 0;
    }

    public static double avgDouble(long total, long count) {
        double result = 0;
        if (count > 0) {
            result = (double) total * 1000 / (double) count / 1000;
            result = NumberUtils.toDouble(percentFormat.format(result));
        }
        return result;
    }

    public static String percent(long numerator, long denominator) {
        if (denominator < 1) {
            return "0";
        }
        String result = percentFormat.format((float) numerator / (float) denominator * 100);
        /*logger.debug("PercentUtils.percent numerator:{}, denominator:{}, result:{}",
                numerator, denominator, result);*/
        return result;
    }

    public static double percentDouble(long numerator, long denominator) {
        String result = "0";
        if (denominator > 0) {
            result = percentFormat.format((float) numerator / (float) denominator * 100);
        }
        double val = NumberUtils.toDouble(result);
        /*logger.debug("PercentUtils.percentDouble numerator:{}, denominator:{}, result:{}, val:{}",
                numerator, denominator, result, val);*/
        return val;
    }
}
