/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.groupdetails.entity;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 班组详情Entity
 * @author lxy
 * @version 2019-08-28
 */
public class Groupdetails extends DataEntity<Groupdetails> {
	
	private static final long serialVersionUID = 1L;
	private String gName;		// 姓名
	private String num;		// 工号
	private String gGroup;		// 班组
	
	public Groupdetails() {
		super();
	}

	public Groupdetails(String id){
		super(id);
	}

	@ExcelField(title="姓名", align=2, sort=1)
	public String getGName() {
		return gName;
	}

	public void setGName(String gName) {
		this.gName = gName;
	}
	
	@ExcelField(title="工号", align=2, sort=2)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@ExcelField(title="班组", align=2, sort=3)
	public String getGGroup() {
		return gGroup;
	}

	public void setGGroup(String gGroup) {
		this.gGroup = gGroup;
	}
	
}