/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pri_number_head.entity.pri_number_head;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 号码头归属地表Entity
 * @author 姜森焱
 * @version 2019-12-05
 */
public class PriNumberHead extends DataEntity<PriNumberHead> {
	
	private static final long serialVersionUID = 1L;
	private String locname;		// 地区编号
	private String numHead;		// 号码头
	private String code;		// 数据库有该字段，未在程序中体现作用
	
	public PriNumberHead() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public PriNumberHead(String id){
		super(id);
	}

	@ExcelField(title="地区编号", align=2, sort=1)
	public String getLocname() {
		return locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}
	
	@ExcelField(title="号码头", align=2, sort=2)
	public String getNumHead() {
		return numHead;
	}

	public void setNumHead(String numHead) {
		this.numHead = numHead;
	}
	
	@ExcelField(title="数据库有该字段，未在程序中体现作用", align=2, sort=3)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}