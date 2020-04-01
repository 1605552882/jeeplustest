/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.complaindocument.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 重复投诉工单Entity
 * @author zqb
 * @version 2019-10-11
 */
public class Complaindocument extends DataEntity<Complaindocument> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 单据编号
	private String times;		// 投诉次数
	private Date reportTime;		// 申告时间
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	
	private String sStatus;		// 单据状态
	
	public Complaindocument() {
		super();
	}

	public Complaindocument(String id){
		super(id);
	}

	@ExcelField(title="单据编号", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="投诉次数", align=2, sort=2)
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="申告时间", align=2, sort=3)
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	
	public Date getBeginReportTime() {
		return beginReportTime;
	}

	public void setBeginReportTime(Date beginReportTime) {
		this.beginReportTime = beginReportTime;
	}
	
	public Date getEndReportTime() {
		return endReportTime;
	}

	public void setEndReportTime(Date endReportTime) {
		this.endReportTime = endReportTime;
	}
	
	@ExcelField(title="单据状态", align=2, sort=4)
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
}