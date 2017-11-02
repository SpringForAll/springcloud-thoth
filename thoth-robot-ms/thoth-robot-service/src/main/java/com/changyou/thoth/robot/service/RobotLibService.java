/*
 * Copyright(c) 2017 changyou.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.changyou.thoth.robot.service;

import com.changyou.thoth.common.service.BaseService;
import com.changyou.thoth.robot.dto.RobotLibDto;
import com.changyou.thoth.robot.entity.RobotLib;

import java.util.List;

/**
 * @version 1.0
 * @author 
 */
public interface RobotLibService extends BaseService<RobotLib>{
    boolean batchInsert(List<RobotLib> robotLibs);
    boolean save(RobotLibDto robotLibDto);
}
