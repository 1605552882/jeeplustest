/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialevent.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 长者特殊事件Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdSpecialEventLog extends DataEntity<EdSpecialEventLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者
	private String title;		// 标题
	private String content;		// 简单情况
	private Date eventdate;		// 时间
	private String eventpos;		// 地点
	private String witness;		// 目击人
	
	public EdSpecialEventLog() {
		super();
	}

	public EdSpecialEventLog(String id){
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
	
	@ExcelField(title="简单情况", align=2, sort=8)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="时间", align=2, sort=9)
	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}
	
	@ExcelField(title="地点", align=2, sort=10)
	public String getEventpos() {
		return eventpos;
	}

	public void setEventpos(String eventpos) {
		this.eventpos = eventpos;
	}
	
	@ExcelField(title="目击人", align=2, sort=11)
	public String getWitness() {
		return witness;
	}

	public void setWitness(String witness) {
		this.witness = witness;
	}
	
}