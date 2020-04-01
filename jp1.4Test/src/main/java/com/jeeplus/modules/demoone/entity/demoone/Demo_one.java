/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.demoone.entity.demoone;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 作为测试Entity
 * @author 姜森焱
 * @version 2019-09-29
 */
public class Demo_one extends DataEntity<Demo_one> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String age;		// 年龄
	
	public Demo_one() {
		super();
	}

	public Demo_one(String id){
		super(id);
	}

	@ExcelField(title="姓名", align=2, sort=7)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="年龄", align=2, sort=8)
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
}