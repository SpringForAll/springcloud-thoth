package com.prometheus.thoth.common.util;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.common.model.JsonResult;
import com.prometheus.thoth.common.model.RestResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liangliang on 2017/3/14.
 */
public class HttpClientUtils {
    static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);


    public static HttpResponse postJsonDataMethod(String url, Object object) throws IOException {
        return postJsonDataMethod(url, object, null);
    }

    public static HttpResponse postJsonDataMethod(String url, Object object, Cookie... cookies) throws IOException {
        logger.debug("POST请求URL: {}", url);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            String str = DomainJsonUtils.buildToJson(object);
            logger.info("POST发送出消息体: {}", str);
            HttpPost request = new HttpPost(url);
            setCookie(request, cookies);
            request.setHeader(new BasicHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()));
            HttpEntity entity = new ByteArrayEntity(str.getBytes("UTF-8"));
            request.setEntity(entity);
            HttpResponse response = client.execute(request);
            return response;
        } finally {

//            client.close();
        }
    }

    public static HttpResponse getRequestMethod(String url) throws IOException {
        return getRequestMethod(url, null, null);
    }

    public static HttpResponse getRequestMethod(String url, Map<String, ? extends Object> params, Cookie... cookies) throws IOException {
        logger.debug("GET请求URL: {}", url);
        logger.debug("GET请求参数： {}", DomainJsonUtils.buildToJson(params));
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet(url + appendParams(params));
            setCookie(request, cookies);
            HttpResponse response = client.execute(request);
            return response;
        } finally {
        }

    }

    private static String appendParams(Map<String, ? extends Object> params) {
        StringBuilder sb = new StringBuilder("?");
        if (params == null || params.size() == 0)
            return "";

        List<String> strList = new ArrayList<>();
        for (String key : params.keySet()) {
            strList.add(key + "=" + params.get(key).toString());
        }
        String ap = StringUtils.join(strList, "&");
        String result = sb.append(ap).toString();
        logger.debug("appendParams result :{}", result);
        return result;
    }

    private static void setCookie(HttpRequest request, Cookie[] cookies) {
        if (cookies != null && cookies.length > 0) {
            List<String> cookieStr = new LinkedList<>();
            for (Cookie cookie : cookies) {
                cookieStr.add(cookie.getName() + "=" + cookie.getValue());
            }
            request.setHeader("Cookie", StringUtils.join(cookieStr, "; "));
        }
    }

    public static RestResult jsonHttpResponseToRestResult(HttpResponse response) throws IOException {
         HttpEntity responseEntity = response.getEntity();
        StringBuilder sb = new StringBuilder();
        InputStream in = responseEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            logger.debug("response inputStream: {}", sb.toString());
            RestResult restResult = JSON.parseObject(sb.toString(), RestResult.class);
            return restResult;
        } finally {
            in.close();
        }
    }

    public static JsonResult jsonHttpResponseToJson(HttpResponse response) throws IOException {
        HttpEntity responseEntity = response.getEntity();
        StringBuilder sb = new StringBuilder();
        InputStream in = responseEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            logger.debug("response inputStream: {}", sb.toString());
            JsonResult jsonResult = JSON.parseObject(sb.toString(), JsonResult.class);
            return jsonResult;
        } finally {
            in.close();
        }
    }








    public static String getContent(HttpResponse response) throws IOException {
        HttpEntity responseEntity = response.getEntity();
        StringBuilder sb = new StringBuilder();
        InputStream in = responseEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            logger.debug("response inputStream: {}", sb.toString());
            return sb.toString();
        } finally {
            in.close();
        }
    }

    /**
     * @param response:HttpResponse
     * @param restResult:传入需要反序列化的类描述信息
     * @throws IOException
     * @throws UnsupportedOperationException
     * @return:RestResult.Class
     */
    public static RestResult jsonHttpResponseToRestResult(HttpResponse response, RestResult restResult) throws IOException, UnsupportedOperationException {
        logger.debug("------start jsonHttpResponseToRestResult-------");
        String content = parseResponseEntity(response);
        if (StringUtils.isBlank(content)) {
            logger.warn("content is null");
            return null;
        }
        logger.debug("{$content} is:{}", content);
        if (restResult == null) {
            logger.warn("restResult is null");
            return null;
        }
        try {

            RestResult r = JSON.parseObject(content, restResult.getClass());
            logger.debug("------end jsonHttpResponseToRestResult-------");
            return r;
        } catch (Exception e) {
            logger.error("jsonHttpResponseToRestResult parseObject error,[$errorMsg] is:{}", ExceptionLog.getErrorStack(e));
            return null;
        }

    }

    /**
     * @param response:HttpResponse
     * @return
     * @throws IOException
     * @throws UnsupportedOperationException
     */
    private static String parseResponseEntity(HttpResponse response) throws IOException, UnsupportedOperationException {
        logger.debug("------start parseResponseEntity-------");
        if (response == null || response.getEntity() == null) {
            logger.warn("response is null,or entity is null");
            return null;
        }
        InputStream in = null;
        try {
            in = response.getEntity().getContent();
            StringBuilder sb = new StringBuilder();
            byte[] bs = new byte[2048];
            if (in == null) {
                logger.error("in is null");
                return null;
            }
            while (in.read(bs) != -1) {
                sb.append(new String(bs).trim());
            }
            logger.debug("------end parseResponseEntity-------");
            return sb.toString();

        } catch (IOException iOe) {
            logger.error("IOException,[$errorMsg] is:{}", ExceptionLog.getErrorStack(iOe));
            throw iOe;
        } finally {
            in.close();
            logger.debug("close InputStream finished");
        }
    }
}
