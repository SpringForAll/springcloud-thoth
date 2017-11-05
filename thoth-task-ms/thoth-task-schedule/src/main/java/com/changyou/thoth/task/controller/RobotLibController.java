/*
 * Copyright(c) 2017 changyou.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.changyou.thoth.robot.controller;

import com.changyou.thoth.common.model.RestResult;
import com.changyou.thoth.common.model.RestResultBuilder;
import com.changyou.thoth.robot.dto.RobotLibDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.changyou.thoth.common.web.controller.BaseController;
import com.changyou.thoth.robot.entity.RobotLib;
import com.changyou.thoth.robot.service.RobotLibService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @version 1.0
 * @author sunliangliang generated
 */
@RestController
@RequestMapping("/robot/robot-lib")
public class RobotLibController extends BaseController {
    
    @Autowired
    private RobotLibService robotLibService;

	@ApiOperation(value="添加机器人库", notes="添加机器人库")
	@ApiImplicitParam(name = "robotLib", value = "机器人库实体", required = true, dataType = "RobotLib")
	@PostMapping(value = "/create/{robotId}")
	public RestResult create(@PathVariable Long robotId,@RequestBody @Valid RobotLibDto robotLibDto) {
		robotLibDto.setRobotId(robotId);
		Boolean result = robotLibService.save(robotLibDto);
		if (result) {
		return RestResultBuilder.builder().success().build();
		}
		return RestResultBuilder.builder().failure().build();
	}

	@ApiOperation(value="修改机器人库信息", notes="根据url的id来指定更新对象，并根据传过来的信息来更新机器人库详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "机器人库id", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "robot", value = "机器人库robotLib", required = true, dataType = "RobotLib")
	})
	@PutMapping(value = "/update/{id}")
	public RestResult update(@PathVariable Long id,@RequestBody @Valid RobotLib robotLib){
		robotLib.setId(id);
		robotLibService.update(robotLib);
		return RestResultBuilder.builder().success().build();
	}

	@ApiOperation(value="获取机器人库信息", notes="根据url的id来获取机器人库详细信息")
	@GetMapping(value = "/get/{id}")
	public RestResult get(@PathVariable Long id) {
		RobotLib robotLib = robotLibService.getById(id);
		return RestResultBuilder.builder().success(robotLib).build();
	}

	@ApiOperation(value="获取机器人库列表", notes="")
	@GetMapping(value = "/list")
	public RestResult list() {
		List<RobotLib> robotLibs = robotLibService.listAll();
		return RestResultBuilder.builder().success(robotLibs).build();
	}

	@ApiOperation(value="删除机器人库", notes="根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "机器人库id", required = true, dataType = "Long")
	@DeleteMapping("/delete/{id}")
	public RestResult delete(@PathVariable Long id) {
		robotLibService.delete(id);
		return RestResultBuilder.builder().success().build();
	}
}
