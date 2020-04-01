/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimedocument.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 工单超时Entity
 * @author lxy
 * @version 2019-08-14
 */
public class Overtimedocument extends DataEntity<Overtimedocument> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 工单编号
	private String overtime;		// 工单超时
	private Date reportTime;		// 申告时间
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	private String remarks;
	private String dntiremain; //网运剩余时间(分)
	private String checkrule; //检查规则
	
	private String dutyGroup;		// 责任班组
	private String dutyPeople;		// 责任人
	private String faultcategory;		// 故障种类
	
	private String sStatus;		// 单据状态
	

	@ExcelField(title="网运剩余时间(分)", align=2, sort=4)
	public String getDntiremain() {
		return dntiremain;
	}

	public void setDntiremain(String dntiremain) {
		this.dntiremain = dntiremain;
	}

	@ExcelField(title="检查规则", align=2, sort=5)
	public String getCheckrule() {
		return checkrule;
	}

	public void setCheckrule(String checkrule) {
		this.checkrule = checkrule;
	}

	public Overtimedocument() {
		super();
	}

	public Overtimedocument(String id){
		super(id);
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ExcelField(title="工单编号", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="工单超时", align=2, sort=2)
	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
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

	@ExcelField(title="责任班组", align=2, sort=6)
	public String getDutyGroup() {
		return dutyGroup;
	}

	public void setDutyGroup(String dutyGroup) {
		this.dutyGroup = dutyGroup;
	}

	@ExcelField(title="责任人", align=2, sort=7)
	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public String getFaultcategory() {
		return faultcategory;
	}

	public void setFaultcategory(String faultcategory) {
		this.faultcategory = faultcategory;
	}
	
	@ExcelField(title="单据状态", align=2, sort=8)
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
}