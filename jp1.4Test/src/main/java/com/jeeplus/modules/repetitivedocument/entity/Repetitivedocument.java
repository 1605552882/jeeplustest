/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.repetitivedocument.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 重复退单Entity
 * @author lxy
 * @version 2019-08-14
 */
public class Repetitivedocument extends DataEntity<Repetitivedocument> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 单据编号
	private String times;		// 重复退单次数
	private Date reportTime;		// 申告时间
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	private String remarks;		// 是否检查
	
	private String dutyGroup;		// 责任班组
	private String dutyPeople;		// 责任人
	private String faultcategory;		// 故障种类
	
	private String sStatus;		// 单据状态
	
	@ExcelField(title="责任班组", align=2, sort=4)
	public String getDutyGroup() {
		return dutyGroup;
	}

	public void setDutyGroup(String dutyGroup) {
		this.dutyGroup = dutyGroup;
	}

	@ExcelField(title="责任人", align=2, sort=5)
	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Repetitivedocument() {
		super();
	}

	public Repetitivedocument(String id){
		super(id);
	}

	@ExcelField(title="单据编号", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="重复退单次数", align=2, sort=2)
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

	public String getFaultcategory() {
		return faultcategory;
	}

	public void setFaultcategory(String faultcategory) {
		this.faultcategory = faultcategory;
	}
	
	@ExcelField(title="单据状态", align=2, sort=6)
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
}