/*
 * Copyright(c) 2017 prometheus.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.prometheus.thoth.robot.controller;

import com.prometheus.thoth.common.enums.AccessWay;
import com.prometheus.thoth.common.enums.YesNo;
import com.prometheus.thoth.common.exception.BusinessException;
import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.model.RestResultBuilder;
import com.prometheus.thoth.common.util.VerificationUtils;
import com.prometheus.thoth.common.web.controller.BaseController;
import com.prometheus.thoth.fastdfs.client.FastDFSClient;
import com.prometheus.thoth.robot.dto.RobotDto;
import com.prometheus.thoth.robot.entity.Robot;
import com.prometheus.thoth.robot.exception.RobotErrorCode;
import com.prometheus.thoth.robot.service.RobotService;
import com.prometheus.thoth.robot.vo.RobotVo;
import com.prometheus.thoth.utils.TenantUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @author sunliangliang generated
 */

@RestController
@RequestMapping("/api/robot")
public class RobotController extends BaseController {

    @Autowired
    private RobotService robotService;

	@ApiOperation(value="添加机器人", notes="添加机器人")
	@ApiImplicitParam(name = "robotDto", value = "机器人实体", required = true, dataType = "RobotDto")
	@PostMapping(value = "/create")
	public RestResult create(@RequestBody @Valid RobotDto robotDto) {
		Long tenantId = TenantUtils.getTenantId();
		robotDto.setTenantId(tenantId);
		robotDto.setAccessWay(AccessWay.API.getType());//默认为API接入
		robotDto.setAvaliable(true);
		Boolean result = robotService.save(robotDto);
		if (result) {
		return RestResultBuilder.builder().success().build();
		}
		return RestResultBuilder.builder().failure().build();
	}

	@ApiOperation(value="修改机器人信息", notes="根据url的id来指定更新对象，并根据传过来的robot信息来更新机器人详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "robotDto", value = "机器人实体robot", required = true, dataType = "RobotDto")
	})
	@PutMapping(value = "/update/{id}")
	public RestResult update(@PathVariable Long id,@RequestBody @Valid RobotDto robotDto){
		robotDto.setId(id);
		robotService.update(robotDto);
		return RestResultBuilder.builder().success().build();
	}

	@ApiOperation(value="修改机器人状态", notes="根据url的id来指定更新对象，并根据传过来的avaliable信息来更新机器人详细信息")
	@PutMapping(value = "/status/update/{id}")
	public RestResult update(@PathVariable Long id,@RequestParam("avaliable") boolean avaliable){
		robotService.changeState(id,avaliable);
		return RestResultBuilder.builder().success().build();
	}

	@ApiOperation(value="获取机器人信息", notes="根据url的id来获取机器人详细信息", response = RobotVo.class)
	@GetMapping(value = "/{id}")
	public RestResult get(@PathVariable Long id) {
		RobotVo robotVo = robotService.getRobotVoById(id);
		return RestResultBuilder.builder().success(robotVo).build();
	}

	@ApiOperation(value="获取机器人列表", notes="获取机器人列表")
	@GetMapping(value = "/list")
	public RestResult list() {
		Long tenantId = TenantUtils.getTenantId();
		Robot robot = new Robot();
		robot.setTenantId(tenantId);
		List<Robot> robots = robotService.listAll(robot);
		return RestResultBuilder.builder().success(robots).build();
	}
	@ApiOperation(value="删除机器人", notes="根据url的id来指定删除对象")
	@DeleteMapping("/{id}")
	public RestResult delete(@PathVariable Long id) {
		robotService.delete(id);
		return RestResultBuilder.builder().success().build();
	}
}
