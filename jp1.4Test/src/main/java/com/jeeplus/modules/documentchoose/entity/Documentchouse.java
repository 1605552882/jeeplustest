/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentchoose.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 工单抽取Entity
 * @author lxy
 * @version 2019-08-14
 */
public class Documentchouse extends DataEntity<Documentchouse> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 单据编号
	private String result;		// 结论
	private Date reportTime;		// 申告时间
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	private String remarks;		// 是否检查
	
	private String key; //关键词
	private String busytype; //业务类型
	private String group; //责任班组
	private String people; //责任人
	
	private String srepfaultdetail ;		//故障内容
	private String sprocesssummary ;		//结单信息
	private String iserialno ;		//命中反馈反馈序号
	private String feedbackrule ;		//反馈信息
	private String checkrule;		//检查规则
	
	private String sStatus;		//单据状态
	
	public String getSrepfaultdetail() {
		return srepfaultdetail;
	}

	public void setSrepfaultdetail(String srepfaultdetail) {
		this.srepfaultdetail = srepfaultdetail;
	}

	public String getSprocesssummary() {
		return sprocesssummary;
	}

	public void setSprocesssummary(String sprocesssummary) {
		this.sprocesssummary = sprocesssummary;
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

	public String getCheckrule() {
		return checkrule;
	}

	public void setCheckrule(String checkrule) {
		this.checkrule = checkrule;
	}

	@ExcelField(title="责任班组", align=2, sort=4)
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
	
	public Documentchouse() {
		super();
	}

	public Documentchouse(String id){
		super(id);
	}

	@ExcelField(title="单据编号", dictType="", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="结论", align=2, sort=2)
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBusytype() {
		return busytype;
	}

	public void setBusytype(String busytype) {
		this.busytype = busytype;
	}

	@ExcelField(title="责任人", align=2, sort=5)
	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	@ExcelField(title="单据状态", align=2, sort=6)
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
		
}