/*
 * Created by sunliangliang
 */
package com.prometheus.thoth.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.prometheus.thoth.common.exception.BusinessException;
import com.prometheus.thoth.common.model.RestResult;
import com.prometheus.thoth.common.util.DateUtils;
import com.prometheus.thoth.common.util.HttpClientUtils;
import com.prometheus.thoth.robot.dao.CommonMsgMapper;

import com.prometheus.thoth.robot.dao.RobotMapper;
import com.prometheus.thoth.robot.dto.RobotDto;
import com.prometheus.thoth.robot.entity.CommonMsg;
import com.prometheus.thoth.robot.entity.Robot;
import com.prometheus.thoth.robot.exception.RobotErrorCode;
import com.prometheus.thoth.robot.service.RobotService;
import com.prometheus.thoth.robot.vo.RobotVo;
import com.prometheus.thoth.utils.RobotConstants;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @version 1.0
 * @author
 */
@Service
public class RobotServiceImpl implements RobotService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private RobotMapper robotMapper;
	@Autowired
	private CommonMsgMapper commonMsgMapper;

	@Value("${fastdfs.nginx-web:}")
	String fastdfsUrl;
	@Value("${robot.ai.robot.update:}")
	private String robot_Update_Url;
	@Override
	@Transactional
	public Boolean save(RobotDto robotDto){
		Robot robot = robotDto.getRobot();
		logger.debug("entity:{}", JSON.toJSONString(robot));
		if (this.exists(robot.getName(),robot.getTenantId())){
			throw new BusinessException(RobotErrorCode.ROBOT_ALREADY_EXISTS);
		}
		int count = robotMapper.insert(robot);
		List<CommonMsg> commonMsgs = robotDto.getCommonMsgs(robot.getId());
		commonMsgMapper.batchInsert(commonMsgs);
		logger.debug("count:{}", count);
		boolean result = count > 0 ? true : false;
		/**
		 * 通知机器人AI
		 */
		if (result) {
			this.notifyAIForRobotUpdate(robot);
		}
		return result;
	}

	/**
	 * 同一租户下机器人名称不允许重复
	 * @param name
	 * @param tenantId
	 * @return
	 */
	@Override
	public boolean exists(String name, Long tenantId) {
		Robot robot = new Robot();
		robot.setTenantId(tenantId);
		robot.setName(name);
		List<Robot> robots = robotMapper.listRobots(robot);
		return robots==null|| robots.isEmpty() ? false : true;
	}

	/**
	 * 消息删除之后然后重新生成
	 * @param robotDto
	 * @return
	 */
	@Override
	@Transactional
	public Boolean update(RobotDto robotDto) {
		Robot robot = robotDto.getRobot();
		logger.debug("entity:{}", JSON.toJSONString(robot));
		int count = robotMapper.update(robot);
		List<CommonMsg> commonMsgs = robotDto.getCommonMsgs(robot.getId());
		commonMsgMapper.deleteByRobotId(robot.getId());
		commonMsgMapper.batchInsert(commonMsgs);
		boolean result = count > 0 ? true : false;
		/**
		 * 通知机器人AI
		 */
		if (result) {
			this.notifyAIForRobotUpdate(robot);
		}
		return result;
	}

	@Override
	public Boolean delete(Long id) {
		int count = robotMapper.deleteById(id);
		commonMsgMapper.deleteByRobotId(id);
		return count > 0 ? true : false;
	}

	@Override
	public Robot getById(Long id) {
		Robot robot = robotMapper.getById(id);
		if (robot == null) {
			throw new BusinessException(RobotErrorCode.DATA_NOT_EXISTS);
		}
		String imgUrl = fastdfsUrl + robot.getHeadPortrait();
		robot.setHeadPortrait(imgUrl);
		return robot;
	}

	@Override
	public RobotVo getRobotVoById(Long robotId) {
		Robot robot = this.getById(robotId);
		RobotVo robotVo = new RobotVo(robot);
		List<CommonMsg> commonMsgs = commonMsgMapper.listByRobotId(robotId,RobotConstants.COMMON_MSG_UNKNOW_ANSWER);
		robotVo.setCommonMsgs(commonMsgs);
		return robotVo;
	}

	@Override
	public List<Robot> listAll() {
		return robotMapper.listAll();
	}

	@Override
	public List<Robot> listAll(Robot robot) {
		List<Robot> robots = robotMapper.listRobots(robot);
		Robot robot1 = new Robot();
		robot1.setName("哈哈哈哈");
		if (robots == null){
			robots = new ArrayList<Robot>();
		}
		robots.add(robot1);
		return robots;
	}

	@Override
	public boolean changeState(Long id, boolean avaliable) {
		Robot robot = new Robot();
		robot.setId(id);
		robot.setAvaliable(avaliable);
		robot.setGmtModified(DateUtils.now());
		int count = robotMapper.update(robot);
		return count > 0 ? true : false;
	}

	@Override
	public boolean notifyAIForRobotUpdate(Robot robot){
		try {
			HttpResponse response = HttpClientUtils.postJsonDataMethod(robot_Update_Url, robot);
			RestResult restResult = HttpClientUtils.jsonHttpResponseToRestResult(response);
			if (restResult.getCode()!= RobotConstants.NOTIFY_ROBOT_FAILURE){
				throw new BusinessException(RobotErrorCode.NOTIFY_AI_ROBOT_UPDATE_FAILED);
			}
			logger.info("------------------->调用接口信息:{}",JSON.toJSONString(restResult));
		} catch (IOException e) {
			throw new BusinessException(RobotErrorCode.NOTIFY_AI_ROBOT_UPDATE_FAILED);
		}

		return true;
	}


}
