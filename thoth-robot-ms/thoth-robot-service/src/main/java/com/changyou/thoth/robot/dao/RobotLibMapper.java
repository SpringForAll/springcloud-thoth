/*
 * Copyright(c) 2017 changyou.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.changyou.thoth.robot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.changyou.thoth.robot.entity.RobotLib;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @author  sunliangliang generated
 */
@Mapper
public interface RobotLibMapper {
    
    RobotLib getById(Long id);
    
    int insert(RobotLib robotLib);

    int batchInsert(List<RobotLib> robotLibs);

    int update(RobotLib robotLib);

    int deleteById(Long id);

    List<RobotLib> listByRobotId(Long robotId);

    List<RobotLib> listAll(@Param("robotLib") RobotLib robotLib);
}