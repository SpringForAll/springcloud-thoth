/*
 * Copyright(c) 2017 prometheus.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.prometheus.thoth.robot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 欢迎语
 * @version 1.0
 * @author sunliangliang
 */
@ApiModel
public class CommonMsg  {

	/**
	 * id
	 */
	private Long id;
	/**
	 * robotId
	 */
	@ApiModelProperty(value = "机器人id，必填项",required = true)
	@NotNull
	private Long robotId;
	/**
	 *  消息
	 */
	@Size(min = 2, max = 10, message = "输入2-10位中文或英文")
	@Pattern(regexp = "[a-zA-Z\u4e00-\u9fa5]+", message = "输入2-10位中文或英文")
	@ApiModelProperty("内容:（0 欢迎语 1 未知问题答案）")
	private String message;
	/**
	 *
	 */
	@ApiModelProperty("0 欢迎语 1 未知问题答案")
	private Integer type;
	/**
	 *  创建时间，按照时间排序
	 */
	private Date gmtCreate;

	private Date gmtModified;

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
	

	public void setMessage(String value) {
		this.message = value;
	}

	public String getMessage() {
		return this.message;
	}
	

	public void setType(Integer value) {
		this.type = value;
	}

	public Integer getType() {
		return this.type;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
