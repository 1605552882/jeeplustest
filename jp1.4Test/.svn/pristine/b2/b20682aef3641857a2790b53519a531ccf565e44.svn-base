/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialdiscuss.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 长者特别情况Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdSpecialDiscussLog extends DataEntity<EdSpecialDiscussLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者
	private String title;		// 标题
	private String content;		// 正文
	
	public EdSpecialDiscussLog() {
		super();
	}

	public EdSpecialDiscussLog(String id){
		super(id);
	}

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
	
	@ExcelField(title="正文", align=2, sort=8)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}