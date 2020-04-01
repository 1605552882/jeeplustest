/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimefeedback.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 反馈超时Entity
 * @author lxy
 * @version 2019-08-14
 */
public class Overtimefeedback extends DataEntity<Overtimefeedback> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 单据编号
	private String feedback;		// 反馈超时
	private Date reportTime;		// 申告时间
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	private String remarks;		// 是否检查
	private Date dfaultapttime;		// 受理时间
	private Date dcreatetime;		// 反馈时间
	private String iserialno;		// 命中反馈反馈序号
	private String checkrule;		// 检查规则
	private String dutyGroup;		// 责任班组
	private String dutyPeople;		// 责任人
	private String faultcategory;		// 故障种类
	private String sStatus;		// 单据状态
	
	@ExcelField(title="责任班组", align=2, sort=8)
	public String getDutyGroup() {
		return dutyGroup;
	}

	public void setDutyGroup(String dutyGroup) {
		this.dutyGroup = dutyGroup;
	}

	@ExcelField(title="责任人", align=2, sort=9)
	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="受理时间", align=2, sort=4)
	public Date getDfaultapttime() {
		return dfaultapttime;
	}

	public void setDfaultapttime(Date dfaultapttime) {
		this.dfaultapttime = dfaultapttime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="反馈时间", align=2, sort=5)
	public Date getDcreatetime() {
		return dcreatetime;
	}

	public void setDcreatetime(Date dcreatetime) {
		this.dcreatetime = dcreatetime;
	}

	@ExcelField(title="反馈序号", align=2, sort=6)
	public String getIserialno() {
		return iserialno;
	}

	public void setIserialno(String iserialno) {
		this.iserialno = iserialno;
	}

	@ExcelField(title="检查规则", align=2, sort=7)
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
	
	public Overtimefeedback() {
		super();
	}

	public Overtimefeedback(String id){
		super(id);
	}

	@ExcelField(title="单据编号", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="反馈超时", align=2, sort=2)
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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
	
	@ExcelField(title="单据状态", align=2, sort=10)
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
}