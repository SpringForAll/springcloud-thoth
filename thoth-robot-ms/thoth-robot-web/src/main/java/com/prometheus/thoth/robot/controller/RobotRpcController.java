package com.prometheus.thoth.robot.controller;

import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.model.RestResultBuilder;
import com.prometheus.thoth.common.web.controller.BaseController;
import com.prometheus.thoth.robot.entity.Robot;
import com.prometheus.thoth.robot.service.RobotService;
import com.prometheus.thoth.robot.vo.RobotVo;
import com.prometheus.thoth.utils.TenantUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by sunliangliang
 */
@RestController
@RequestMapping("/rpc/robot")
public class RobotRpcController extends BaseController{
    @Autowired
    private RobotService robotService;
    @ApiOperation(value="获取机器人列表", notes="获取机器人列表")
    @GetMapping(value = "/list")
    public RestResult<Robot> list() {
        Long tenantId = TenantUtils.getTenantId();
        Robot robot = new Robot();
        robot.setTenantId(tenantId);
        List<Robot> robots = robotService.listAll(robot);
        return RestResultBuilder.builder().success(robots).build();
    }
    @ApiOperation(value="获取机器人信息", notes="根据url的id来获取机器人详细信息", response = RobotVo.class)
    @GetMapping(value = "/{id}")
    public RestResult get(@PathVariable Long id) {
        RobotVo robotVo = robotService.getRobotVoById(id);
        return RestResultBuilder.builder().success(robotVo).build();
    }

}
