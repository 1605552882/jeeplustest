/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.basis_coding.entity.basis_coding;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 编码表Entity
 * @author 姜森焱
 * @version 2020-01-15
 */
public class BasisCoding extends DataEntity<BasisCoding> {
	
	private static final long serialVersionUID = 1L;
	private String typeCoding;		// 类型编码
	private String name;		// 名称
	private String notes;		// 备注
	private String status;		// 状态（N:正常;C:禁用）
	
	public BasisCoding() {
		super();
	}

	public BasisCoding(String id){
		super(id);
	}

	@ExcelField(title="类型编码", align=2, sort=1)
	public String getTypeCoding() {
		return typeCoding;
	}

	public void setTypeCoding(String typeCoding) {
		this.typeCoding = typeCoding;
	}
	
	@ExcelField(title="名称", align=2, sort=2)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="备注", align=2, sort=3)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@ExcelField(title="状态（N:正常;C:禁用）", align=2, sort=4)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}