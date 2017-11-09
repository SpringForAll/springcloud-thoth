package com.prometheus.thoth.ai.service.impl;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.ai.dto.QuestionInfo;
import com.prometheus.thoth.ai.entity.AIResult;
import com.prometheus.thoth.ai.service.AIRobotService;
import com.prometheus.thoth.common.exception.BusinessException;
import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.util.HttpClientUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created by sunliangliang
 */
@Service
public class TuLingRobotService implements AIRobotService<QuestionInfo>{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String AI_UPDATE_URL = "http://www.tuling123.com/openapi/api";
    @Override
    public AIResult getAnswer(QuestionInfo questionInfo) {
        try {
            HttpResponse response = HttpClientUtils.postJsonDataMethod(AI_UPDATE_URL, questionInfo);
            RestResult restResult = HttpClientUtils.jsonHttpResponseToRestResult(response);
            System.out.println("---------------->"+JSON.toJSONString(restResult));
            logger.info("------------------->调用接口信息:{}", JSON.toJSONString(restResult));
        } catch (IOException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        TuLingRobotService tuLingRobotService = new TuLingRobotService();
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setInfo("你好吗");
        questionInfo.setKey("160ce2fe5dd14e3ea6c9888051e7027a");
        questionInfo.setUserid("123456");
        tuLingRobotService.getAnswer(questionInfo);


    }
}
