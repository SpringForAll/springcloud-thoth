/*
 * Copyright(c) 2017 prometheus.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.prometheus.thoth.robot.service;

import com.prometheus.thoth.common.service.BaseService;
import com.prometheus.thoth.robot.dto.RobotLibDto;
import com.prometheus.thoth.robot.entity.RobotLib;

import java.util.List;

/**
 * @version 1.0
 * @author 
 */
public interface RobotLibService extends BaseService<RobotLib>{
    boolean batchInsert(List<RobotLib> robotLibs);
    boolean save(RobotLibDto robotLibDto);
}
