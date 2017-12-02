package com.prometheus.thoth.ai.controller;

import com.prometheus.thoth.ai.entity.Interlocution;
import com.prometheus.thoth.ai.service.RobotFeignClient;
import com.prometheus.thoth.ai.service.RobotInterlocutionService;
import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.model.RestResultBuilder;
import com.prometheus.thoth.common.web.controller.BaseController;
import com.prometheus.thoth.robot.entity.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by sunliangliang
 * 问答接口
 */
@RestController
@RequestMapping("/api")
public class IntegerlocutionController extends BaseController{
    @Autowired
    private RobotInterlocutionService interlocutionService;
    @GetMapping("/integerlocution")
    public RestResult getAnswer(@RequestParam("question")String question){
        logger.info("-------------【api.integerlocution】------------${question}:{}",question);
        Interlocution result = interlocutionService.getInterlocation(question);
        return RestResultBuilder.builder().success(result).build();
    }
    /**
     * 测试内部通过feign调用服务
     */
    @GetMapping("/robots")
    public RestResult listRobots(){
        List<Robot> robots = interlocutionService.listRobots();
        return RestResultBuilder.builder().success(robots).build();
    }
}
