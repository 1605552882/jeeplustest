/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.system_parameter.entity.system_parameter;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 系统参数表Entity
 * @author 姜森焱
 * @version 2020-02-09
 */
public class SystemParameter extends DataEntity<SystemParameter> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// code
	private String coding;		// 编号
	private String name;		// 名称
	private String value;		// 是/否
	private String notes;		// 备注
	private String status;		// 状态
	
	public SystemParameter() {
		super();
	}

	public SystemParameter(String id){
		super(id);
	}

	@ExcelField(title="code", align=2, sort=0)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@ExcelField(title="编号", align=2, sort=1)
	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}
	
	@ExcelField(title="名称", align=2, sort=2)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="是/否", align=2, sort=3)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@ExcelField(title="备注", align=2, sort=4)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@ExcelField(title="状态", align=2, sort=5)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}