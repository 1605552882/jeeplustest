/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.errordocument.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 疑似异常工单Entity
 * @author lxy
 * @version 2019-08-28
 */
public class Errordocument extends DataEntity<Errordocument> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 单据编号
	private String checkresult;		// 检查结论
	private Date reportTime;		// 申告时间
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	private String remarks ;		//标记
	private String srepfaultdetail ;		//故障内容
	private String sprocesssummary ;		//结单信息
	private String iserialno ;		//命中反馈反馈序号
	private String feedbackrule ;		//反馈信息
	private String checkrule;		//检查规则
	
	private String dutyGroup;		// 责任班组
	private String dutyPeople;		// 责任人
	
	private String faultcategory;		// 故障种类
	private String sStatus;		// 单据状态
	
	@ExcelField(title="责任班组", align=2, sort=7)
	public String getDutyGroup() {
		return dutyGroup;
	}

	public void setDutyGroup(String dutyGroup) {
		this.dutyGroup = dutyGroup;
	}

	@ExcelField(title="责任人", align=2, sort=8)
	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	@ExcelField(title="故障内容", align=2, sort=4)
	public String getSrepfaultdetail() {
		return srepfaultdetail;
	}

	public void setSrepfaultdetail(String srepfaultdetail) {
		this.srepfaultdetail = srepfaultdetail;
	}

	@ExcelField(title="结单信息", align=2, sort=5)
	public String getSprocesssummary() {
		return sprocesssummary;
	}

	public void setSprocesssummary(String sprocesssummary) {
		this.sprocesssummary = sprocesssummary;
	}

	@ExcelField(title="检查规则", align=2, sort=6)
	public String getCheckrule() {
		return checkrule;
	}

	public void setCheckrule(String checkrule) {
		this.checkrule = checkrule;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Errordocument() {
		super();
	}

	public Errordocument(String id){
		super(id);
	}

	@ExcelField(title="单据编号", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="检查结论", dictType="", align=2, sort=2)
	public String getCheckresult() {
		return checkresult;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
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

	public String getIserialno() {
		return iserialno;
	}

	public void setIserialno(String iserialno) {
		this.iserialno = iserialno;
	}

	public String getFeedbackrule() {
		return feedbackrule;
	}

	public void setFeedbackrule(String feedbackrule) {
		this.feedbackrule = feedbackrule;
	}

	public String getFaultcategory() {
		return faultcategory;
	}

	public void setFaultcategory(String faultcategory) {
		this.faultcategory = faultcategory;
	}
	
	@ExcelField(title="单据状态", align=2, sort=9)
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}	
}