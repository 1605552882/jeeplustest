/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.reportsubareastatistic.entity;


import com.jeeplus.core.persistence.DataEntity;

import java.util.Date;

import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 申告地市统计Entity
 * @author zqb
 * @version 2019-12-06
 */
public class Reportsubareastatistic extends DataEntity<Reportsubareastatistic> {
	
	private static final long serialVersionUID = 1L;
	private String reportsubarea;		// 地市
	private String sStatus;				// 单据状态
	private String times;		// 统计次数
	
	private Date beginCreateDate;
	private Date endCreateDate;
	private String timeFlag;
	private String groupFlag;
	
	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}

	public Reportsubareastatistic() {
		super();
	}

	public Reportsubareastatistic(String id){
		super(id);
	}

	@ExcelField(title="地市", align=2, sort=1)
	public String getReportsubarea() {
		return reportsubarea;
	}

	public void setReportsubarea(String reportsubarea) {
		this.reportsubarea = reportsubarea;
	}
	
	@ExcelField(title="统计次数", align=2, sort=2)
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	
}