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
 * 机器人
 * @version 1.0
 * @author
 */
@ApiModel
public class Robot {

	/**
	 * 机器人id
	 */
	private Long id;
	/**
	 * 租户id
	 */
	private Long tenantId;
	/**
	 * 机器人名称
	 */
	@ApiModelProperty(value = "机器人名称",required = true)
	@Size(min = 2, max = 10, message = "输入2-10位中文或英文")
	@Pattern(regexp = "[a-zA-Z\u4e00-\u9fa5]+", message = "输入2-10位中文或英文；特殊符号不可输入")
	private String name;
	/**
	 * 性别：0 男 1 女
	 */
	@ApiModelProperty(value ="性别",required = true)
	private Integer sex;
	/**
	 *  头像
	 */
	@ApiModelProperty(value = "头像",required = true)
	private String headPortrait;
	/**
	 * 行业领域id
	 */
	@NotNull
	@ApiModelProperty(value = "行业",required = true)
	private Integer industryId;
	/**
	 * 接入方式 1 Api ,默认方式
	 */
	@ApiModelProperty(value = "接入方式",required = true)
	private Integer accessWay;
	/**
	 * 是否可用，默认可用
	 */
	@ApiModelProperty(value = "是否可用")
	private Boolean avaliable;

	private Date gmtCreate;

	private Date gmtModified;

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}


	public void setTenantId(Long value) {
		this.tenantId = value;
	}

	public Long getTenantId() {
		return this.tenantId;
	}


	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}


	public void setSex(Integer value) {
		this.sex = value;
	}

	public Integer getSex() {
		return this.sex;
	}


	public void setHeadPortrait(String value) {
		this.headPortrait = value;
	}

	public String getHeadPortrait() {
		return this.headPortrait;
	}


	public void setIndustryId(Integer value) {
		this.industryId = value;
	}

	public Integer getIndustryId() {
		return this.industryId;
	}

	public Integer getAccessWay() {
		return accessWay;
	}

	public void setAccessWay(Integer accessWay) {
		this.accessWay = accessWay;
	}

	public Boolean getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Boolean avaliable) {
		this.avaliable = avaliable;
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
