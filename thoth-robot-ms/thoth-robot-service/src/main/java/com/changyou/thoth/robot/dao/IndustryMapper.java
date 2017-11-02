/*
 * Copyright(c) 2017 changyou.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.changyou.thoth.robot.dao;


import com.changyou.thoth.robot.entity.Industry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 * @author  sunliangliang generated
 */
@Mapper
public interface IndustryMapper {
    
    Industry getById(Integer id);
    
    int insert(Industry industry);

    int insertSelective(Industry industry);

    int updateByPrimaryKey(Industry industry);

    int updateByPrimaryKeySelective(Industry industry);

    int deleteById(Integer id);

    List<Industry> listAll();

}