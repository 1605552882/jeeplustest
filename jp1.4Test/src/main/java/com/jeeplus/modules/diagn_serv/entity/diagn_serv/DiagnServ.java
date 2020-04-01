/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_serv.entity.diagn_serv;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 一件诊断主表Entity
 * @author 姜森焱
 * @version 2020-02-18
 */
public class DiagnServ extends DataEntity<DiagnServ> {
	
	private static final long serialVersionUID = 1L;
	private String servNameId;		// ID
	private String servName;		// 名称
	
	public DiagnServ() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public DiagnServ(String id){
		super(id);
	}

	@ExcelField(title="ID", align=2, sort=0)
	public String getServNameId() {
		return servNameId;
	}

	public void setServNameId(String servNameId) {
		this.servNameId = servNameId;
	}
	
	@ExcelField(title="名称", align=2, sort=1)
	public String getServName() {
		return servName;
	}

	public void setServName(String servName) {
		this.servName = servName;
	}
	
}