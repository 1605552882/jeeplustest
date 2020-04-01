/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.medication.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import javax.validation.constraints.NotNull;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 长者用药情况Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdMedicationLog extends DataEntity<EdMedicationLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者
	private String title;		// 标题
	private String content;		// 用药情况说明
	
	public EdMedicationLog() {
		super();
	}

	public EdMedicationLog(String id){
		super(id);
	}

	@NotNull(message="长者不能为空")
	@ExcelField(title="长者", fieldType=EdElderInf.class, value="elderid.funame", align=2, sort=6)
	public EdElderInf getElderid() {
		return elderid;
	}

	public void setElderid(EdElderInf elderid) {
		this.elderid = elderid;
	}
	
	@ExcelField(title="标题", align=2, sort=7)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ExcelField(title="用药情况说明", align=2, sort=8)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}