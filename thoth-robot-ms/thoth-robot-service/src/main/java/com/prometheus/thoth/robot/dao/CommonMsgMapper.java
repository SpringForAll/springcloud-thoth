/*
 * Copyright(c) 2017 prometheus.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.prometheus.thoth.robot.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.prometheus.thoth.robot.entity.CommonMsg;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @author  sunliangliang generated
 */
@Mapper
public interface CommonMsgMapper {

    CommonMsg getById(Long id);

    int insert(CommonMsg commonMsg);

    boolean batchInsert(List<CommonMsg> commonMsgs);

    int update(CommonMsg commonMsg);

    int deleteById(Long id);

    int deleteByRobotId(Long robotId);

    List<CommonMsg> listAll(@Param("commonMsg") CommonMsg commonMsg);

    List<CommonMsg> listCommonMsgsByRobotId(Long id);

}