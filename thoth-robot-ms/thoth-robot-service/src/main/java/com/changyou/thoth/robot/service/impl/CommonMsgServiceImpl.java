/*
 * Created by sunliangliang
 */
package com.changyou.thoth.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.changyou.thoth.common.exception.BusinessException;
import com.changyou.thoth.robot.dao.CommonMsgMapper;
import com.changyou.thoth.robot.entity.CommonMsg;
import com.changyou.thoth.robot.exception.RobotErrorCode;
import com.changyou.thoth.robot.service.CommonMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
/**
 * @version 1.0
 * @author
 */
@Service
public class CommonMsgServiceImpl implements CommonMsgService {
	private final Logger logger = LoggerFactory.getLogger(CommonMsgService.class);

	@Autowired
    CommonMsgMapper commonMsgMapper;

	@Override
	public Boolean save(CommonMsg commonMsg) {
		logger.debug("entity:{}", JSON.toJSONString(commonMsg));
		int count = commonMsgMapper.insert(commonMsg);
		logger.debug("count:{}", count);
		return count > 0 ? true : false;
	}


	@Override
	public Boolean update(CommonMsg commonMsg) {
		int count = commonMsgMapper.update(commonMsg);
		return count > 0 ? true : false;
	}


	@Override
	public Boolean delete(Long id) {
		int count = commonMsgMapper.deleteById(id);
		return count > 0 ? true : false;
	}

	@Override
	public CommonMsg getById(Long id) {
		CommonMsg commonMsg = commonMsgMapper.getById(id);
		if (commonMsg == null) {
			throw new BusinessException(RobotErrorCode.DATA_NOT_EXISTS);
		}
		return commonMsg;
	}

	@Override
	@Deprecated
	public List<CommonMsg> listAll() {
		return null;
	}

	@Override
	public List<CommonMsg> listAll(CommonMsg commonMsg) {

		return commonMsgMapper.listAll(commonMsg);
	}

}
