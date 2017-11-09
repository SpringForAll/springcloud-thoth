/*
 * Created by sunliangliang
 */
package com.prometheus.thoth.robot.service.impl;

import com.prometheus.thoth.robot.dao.IndustryMapper;
import com.prometheus.thoth.robot.entity.Industry;
import com.prometheus.thoth.robot.service.IndustryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 * @version 1.0
 * @author
 */
@Service
public class IndustryServiceImpl implements IndustryService {
	private final Logger logger = LoggerFactory.getLogger(IndustryService.class);

    @Autowired
    private IndustryMapper industryMapper;

	@Override
	public List<Industry> listAll() {
		return industryMapper.listAll();
	}


}
