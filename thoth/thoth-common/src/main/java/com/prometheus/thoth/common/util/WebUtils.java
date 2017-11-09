package com.prometheus.thoth.common.util;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.common.exception.ErrorCode;
import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.model.RestResultBuilder;
import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * Created by liangliang on 2017/05/17.
 *
 * @author liangliang
 * @since 2017/05/17
 */
public class WebUtils {

    protected static final Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * 直接使用 response 输出JSON
     *
     * @param response
     * @param errorCode
     */
    public static void outJSON(ServletResponse response, ErrorCode errorCode, Object data) {
        RestResult restResult = null;
        if (data != null) {
            restResult = RestResultBuilder.builder().errorCode(errorCode).data(data).build();
        } else {
            restResult = RestResultBuilder.builder().errorCode(errorCode).build();
        }
        outJSON(response, restResult);
    }

    /**
     * 直使用 response 输出JSON
     *
     * @param response
     * @param errorCode
     */
    public static void outJSON(ServletResponse response, ErrorCode errorCode) {
        outJSON(response, RestResultBuilder.builder().errorCode(errorCode).build());
    }

    /**
     * 直使用 response 输出JSON
     *
     * @param response
     * @param content
     */
    public static void outJSON(ServletResponse response, Object content) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding(CharEncoding.UTF_8);//设置编码
            response.setContentType("application/json; charset=utf-8");//设置返回类型
            out = response.getWriter();
            out.println(JSON.toJSONString(content));//输出
        } catch (Exception e) {
            logger.error("json out error:{}", e.getMessage());
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
