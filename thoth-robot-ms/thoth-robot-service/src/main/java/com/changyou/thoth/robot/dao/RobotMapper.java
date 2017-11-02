/*
 * Copyright(c) 2017 changyou.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.changyou.thoth.robot.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.changyou.thoth.robot.entity.Robot;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @author  sunliangliang generated
 */
@Mapper
public interface RobotMapper {
    
    Robot getById(Long id);
    
    int insert(Robot robot);

    int insertSelective(@Param("robot") Robot robot);

    int update(@Param("robot") Robot robot);

    int deleteById(Long id);

    /**
     * @param robot
     * @return
     */
    List<Robot> listRobots(@Param("robot") Robot robot);

    List<Robot> listAll();
}