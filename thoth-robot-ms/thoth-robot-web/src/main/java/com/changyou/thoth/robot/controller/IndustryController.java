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
import com.changyou.thoth.robot.entity.Industry;
import com.changyou.thoth.robot.service.IndustryService;
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
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @version 1.0
 * @author sunliangliang generated
 */
@RestController
@RequestMapping("/industry")
public class IndustryController extends BaseController {
    
    @Autowired
    private IndustryService industryService;
	@ApiOperation(value="获取行业列表", notes="")
	@GetMapping(value = "/list")
	public RestResult list() {
		List<Industry> industrys = industryService.listAll();
		return RestResultBuilder.builder().success(industrys).build();
	}
}
