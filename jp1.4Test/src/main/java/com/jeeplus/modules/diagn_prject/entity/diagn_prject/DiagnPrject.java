/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_prject.entity.diagn_prject;


import com.jeeplus.core.persistence.DataEntity;

import java.util.Date;

import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 一件诊断子表Entity
 * @author 姜森焱
 * @version 2020-02-18
 */
public class DiagnPrject extends DataEntity<DiagnPrject> {
	
	private static final long serialVersionUID = 1L;
	private String projectId;		// ID
	private String label;		// 标签
	private String platName;		// 平台
	private String cLass;		// 类
	private String servId;
	private Date inputTime;
	
	
	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getServId() {
		return servId;
	}

	public void setServId(String servId) {
		this.servId = servId;
	}

	public DiagnPrject() {
		super();
	}

	public DiagnPrject(String id){
		super(id);
	}

	@ExcelField(title="ID", align=2, sort=0)
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@ExcelField(title="标签", align=2, sort=1)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@ExcelField(title="平台", align=2, sort=2)
	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}
	
	@ExcelField(title="类", align=2, sort=3)
	public String getCLass() {
		return cLass;
	}

	public void setCLass(String cLass) {
		this.cLass = cLass;
	}
	
}