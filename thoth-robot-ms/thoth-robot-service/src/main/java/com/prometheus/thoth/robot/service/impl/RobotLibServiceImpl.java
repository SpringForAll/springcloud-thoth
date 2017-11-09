/*
 * Created by sunliangliang
 */
package com.prometheus.thoth.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.common.exception.BusinessException;
import com.prometheus.thoth.robot.dao.RobotLibMapper;
import com.prometheus.thoth.robot.dto.RobotLibDto;
import com.prometheus.thoth.robot.entity.RobotLib;
import com.prometheus.thoth.robot.exception.RobotErrorCode;
import com.prometheus.thoth.robot.service.RobotLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @version 1.0
 * @author
 */
@Service
public class RobotLibServiceImpl implements RobotLibService {
	private final Logger logger = LoggerFactory.getLogger(RobotLibService.class);

	@Autowired
    RobotLibMapper robotLibMapper;

	@Override
	public Boolean save(RobotLib robotLib) {
		logger.debug("entity:{}", JSON.toJSONString(robotLib));
		int count = robotLibMapper.insert(robotLib);
		logger.debug("count:{}", count);
		return count > 0 ? true : false;
	}

	@Override
	public boolean save(RobotLibDto robotLibDto) {
		logger.debug("entity:{}", JSON.toJSONString(robotLibDto));
		List<Long> libIds = robotLibDto.getLibIds();
		for (Long libId : libIds){
			RobotLib robotLib = new RobotLib();
		}

		return false;
	}

	@Override
	public Boolean update(RobotLib robotLib) {
		int count = robotLibMapper.update(robotLib);
		return count > 0 ? true : false;
	}


	@Override
	public Boolean delete(Long id) {
		int count = robotLibMapper.deleteById(id);
		return count > 0 ? true : false;
	}

	@Override
	public RobotLib getById(Long id) {
		RobotLib robotLib = robotLibMapper.getById(id);
		if (robotLib == null) {
			throw new BusinessException(RobotErrorCode.DATA_NOT_EXISTS);
		}
		return robotLib;
	}

	@Override
	@Deprecated
	public List<RobotLib> listAll() {
		return null;
	}


	@Override
	public List<RobotLib> listAll(RobotLib robotLib) {
		return robotLibMapper.listAll(robotLib);
	}

	@Override
	@Transactional
	public boolean batchInsert(List<RobotLib> robotLibs) {
		if (robotLibs==null||robotLibs.isEmpty()){
			return false;
		}
		int size = robotLibs.size();
		int count = robotLibMapper.batchInsert(robotLibs);
		return size==count;
	}

}
