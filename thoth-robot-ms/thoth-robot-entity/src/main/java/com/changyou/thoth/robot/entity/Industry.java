/*
 * Copyright(c) 2017 changyou.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sunliangliang.com/
 * Changyou Rampage -Integrated customer service solution
 */
package com.changyou.thoth.robot.entity;


/**
 * 行业领域
 * @version 1.0
 * @author
 */
public class Industry {

	// id
	private Long id;
	// 名称
	private String name;

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
}
