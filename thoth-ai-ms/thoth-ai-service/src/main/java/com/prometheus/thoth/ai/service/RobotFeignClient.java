package com.prometheus.thoth.ai.service;

/**
 * created by sunliangliang
 */

import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.robot.entity.Robot;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("THOTH-ROBOT-MS")
public interface RobotFeignClient {
    @GetMapping("/rpc/robot/list")
    RestResult<List<Robot>> listRobots();
    @GetMapping("/rpc/robot/{id}")
    RestResult<Robot> getRobot(@PathVariable(value = "id")Long id);
}
