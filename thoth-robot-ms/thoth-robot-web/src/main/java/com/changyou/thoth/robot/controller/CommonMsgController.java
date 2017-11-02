/*
 * Copyright(c) 2017 changyou.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.changyou.thoth.robot.controller;

import com.changyou.thoth.common.model.RestResult;
import com.changyou.thoth.common.model.RestResultBuilder;
import com.changyou.thoth.common.web.controller.BaseController;
import com.changyou.thoth.robot.entity.CommonMsg;
import com.changyou.thoth.robot.service.CommonMsgService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

/**
 * @version 1.0
 * @author sunliangliang generated
 */
@RestController
@RequestMapping("/robot/commonMsg")
public class CommonMsgController extends BaseController {
    
    @Autowired
    private CommonMsgService commonMsgService;

	@ApiOperation(value="添加欢迎语", notes="添加欢迎语")
	@ApiImplicitParam(name = "commonMsg", value = "欢迎语实体", required = true, dataType = "CommonMsg")
	@PostMapping(value = "/create")
	public RestResult create(@RequestBody @Valid CommonMsg commonMsg) {
		Boolean result = commonMsgService.save(commonMsg);
		if (result) {
			return RestResultBuilder.builder().success().build();
		}
		return RestResultBuilder.builder().failure().build();
	}

	@ApiOperation(value="获取欢迎语信息", notes="根据url的id来获取欢迎语详细信息",response = CommonMsg.class)
	@GetMapping(value = "/{id}")
	public RestResult get(@PathVariable Long id) {
		CommonMsg commonMsg = commonMsgService.getById(id);
		return RestResultBuilder.builder().success(commonMsg).build();
	}

	@ApiOperation(value="获取欢迎语列表", notes="获取当前机器人的欢迎语列表",response = CommonMsg.class)
	@GetMapping(value = "/list/{robotId}")
	public RestResult list(@PathVariable Long robotId) {
		CommonMsg commonMsg = new CommonMsg();
		commonMsg.setRobotId(robotId);
		List<CommonMsg> commonMsgs = commonMsgService.listAll(commonMsg);
		return RestResultBuilder.builder().success(commonMsgs).build();
	}

	@ApiOperation(value="删除欢迎语", notes="根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "欢迎语id", paramType = "path", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public RestResult delete(@PathVariable Long id) {
		commonMsgService.delete(id);
		return RestResultBuilder.builder().success().build();
	}
}
