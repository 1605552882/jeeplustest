/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.elder.entity;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 家庭状况Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdFamilyInf extends DataEntity<EdFamilyInf> {
	
	private static final long serialVersionUID = 1L;
	private String fullname;		// 姓名
	private Integer nianling;		// 年龄
	private String guanxi;		// 关系
	private String sex;		// 性别
	private EdElderInf elderid;		// 长者id 父类
	
	public EdFamilyInf() {
		super();
	}

	public EdFamilyInf(String id){
		super(id);
	}

	public EdFamilyInf(EdElderInf elderid){
		this.elderid = elderid;
	}

	@ExcelField(title="姓名", align=2, sort=7)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@ExcelField(title="年龄", align=2, sort=8)
	public Integer getNianling() {
		return nianling;
	}

	public void setNianling(Integer nianling) {
		this.nianling = nianling;
	}
	
	@ExcelField(title="关系", align=2, sort=9)
	public String getGuanxi() {
		return guanxi;
	}

	public void setGuanxi(String guanxi) {
		this.guanxi = guanxi;
	}
	
	@ExcelField(title="性别", dictType="male_or_femal", align=2, sort=10)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public EdElderInf getElderid() {
		return elderid;
	}

	public void setElderid(EdElderInf elderid) {
		this.elderid = elderid;
	}
	
}