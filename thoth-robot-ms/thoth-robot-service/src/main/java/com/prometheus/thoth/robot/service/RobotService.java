/*
 * Copyright(c) 2017 prometheus.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.prometheus.thoth.robot.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.prometheus.thoth.common.service.BaseService;
import com.prometheus.thoth.robot.dto.RobotDto;
import com.prometheus.thoth.robot.entity.Robot;
import com.prometheus.thoth.robot.vo.RobotVo;

/**
 * @version 1.0
 * @author 
 */
public interface RobotService {
    Boolean save(RobotDto robotDto);

    Boolean update(RobotDto robotDto);

    boolean exists(String name,Long tenantId);

    Boolean delete(Long id);

    Robot getById(Long id);

    RobotVo getRobotVoById(Long id);

    List<Robot> listAll();

    List<Robot> listAll(Robot robot);

    /**
     * 设置机器人状态
     * @param id
     * @param avaliable
     */
    boolean changeState(Long id, boolean avaliable);

    boolean notifyAIForRobotUpdate(Robot robot);
}
