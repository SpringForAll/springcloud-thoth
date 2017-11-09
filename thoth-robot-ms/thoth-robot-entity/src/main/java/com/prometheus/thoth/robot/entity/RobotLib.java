/*
 * Copyright(c) 2017 prometheus.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.prometheus.thoth.robot.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 机器人库
 * @version 1.0
 * @author
 */
public class RobotLib {

	/**
	 * id
	 */
	@ApiModelProperty("id")
	private Long id;
	/**
	 * 机器人id
	 */
	@ApiModelProperty("机器人id")
	private Long robotId;
	/**
	 * 库id
	 */
	@ApiModelProperty("库id")
	private Long libId;

	public RobotLib(Long robotId, Long libId) {
		this.robotId = robotId;
		this.libId = libId;
	}

	public RobotLib() {
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	

	public void setRobotId(Long value) {
		this.robotId = value;
	}

	public Long getRobotId() {
		return this.robotId;
	}
	

	public void setLibId(Long value) {
		this.libId = value;
	}

	public Long getLibId() {
		return this.libId;
	}

}
