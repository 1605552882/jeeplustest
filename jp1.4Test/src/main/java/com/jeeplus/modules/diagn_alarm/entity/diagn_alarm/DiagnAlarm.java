/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_alarm.entity.diagn_alarm;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 一键诊断告警信息Entity
 * @author 姜森焱
 * @version 2020-03-18
 */
public class DiagnAlarm extends DataEntity<DiagnAlarm> {
	
	private static final long serialVersionUID = 1L;
	private String alarmTitle;		// 告警标题
	private String faultDescription;		// 故障描述
	private String alarmSource;		// 告警来源
	private Date alarmTime;		// 告警时间
	private String alarmLevel;		// 告警级别
	private String majorCategory;		// 专业类别
	private String proposalRepair;		// 建议修复
	private String alarmInfo;		// 详细信息
	private String influenceCustomer;		// 影响客户
	private String influenceRange;		// 影响范围
	private String influenceTime;		// 影响时间
	private String alarmByinfo;		// 告警条件信息
	private String confirmUser;		// 确认人
	private String confirmWhether;		// 是否确认
	private String confirmTime;		// 确认时间
	
	public DiagnAlarm() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public DiagnAlarm(String id){
		super(id);
	}

	@ExcelField(title="告警标题", align=2, sort=1)
	public String getAlarmTitle() {
		return alarmTitle;
	}

	public void setAlarmTitle(String alarmTitle) {
		this.alarmTitle = alarmTitle;
	}
	
	@ExcelField(title="故障描述", align=2, sort=2)
	public String getFaultDescription() {
		return faultDescription;
	}

	public void setFaultDescription(String faultDescription) {
		this.faultDescription = faultDescription;
	}
	
	@ExcelField(title="告警来源", align=2, sort=3)
	public String getAlarmSource() {
		return alarmSource;
	}

	public void setAlarmSource(String alarmSource) {
		this.alarmSource = alarmSource;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="告警时间", align=2, sort=4)
	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	
	@ExcelField(title="告警级别", dictType="", align=2, sort=5)
	public String getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	
	@ExcelField(title="专业类别", align=2, sort=6)
	public String getMajorCategory() {
		return majorCategory;
	}

	public void setMajorCategory(String majorCategory) {
		this.majorCategory = majorCategory;
	}
	
	@ExcelField(title="建议修复", align=2, sort=7)
	public String getProposalRepair() {
		return proposalRepair;
	}

	public void setProposalRepair(String proposalRepair) {
		this.proposalRepair = proposalRepair;
	}
	
	@ExcelField(title="详细信息", align=2, sort=8)
	public String getAlarmInfo() {
		return alarmInfo;
	}

	public void setAlarmInfo(String alarmInfo) {
		this.alarmInfo = alarmInfo;
	}
	
	@ExcelField(title="影响客户", align=2, sort=9)
	public String getInfluenceCustomer() {
		return influenceCustomer;
	}

	public void setInfluenceCustomer(String influenceCustomer) {
		this.influenceCustomer = influenceCustomer;
	}
	
	@ExcelField(title="影响范围", align=2, sort=10)
	public String getInfluenceRange() {
		return influenceRange;
	}

	public void setInfluenceRange(String influenceRange) {
		this.influenceRange = influenceRange;
	}
	
	@ExcelField(title="影响时间", align=2, sort=11)
	public String getInfluenceTime() {
		return influenceTime;
	}

	public void setInfluenceTime(String influenceTime) {
		this.influenceTime = influenceTime;
	}
	
	@ExcelField(title="告警条件信息", align=2, sort=12)
	public String getAlarmByinfo() {
		return alarmByinfo;
	}

	public void setAlarmByinfo(String alarmByinfo) {
		this.alarmByinfo = alarmByinfo;
	}
	
	@ExcelField(title="确认人", align=2, sort=13)
	public String getConfirmUser() {
		return confirmUser;
	}

	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}
	
	@ExcelField(title="是否确认", align=2, sort=14)
	public String getConfirmWhether() {
		return confirmWhether;
	}

	public void setConfirmWhether(String confirmWhether) {
		this.confirmWhether = confirmWhether;
	}
	
	@ExcelField(title="确认时间", align=2, sort=15)
	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	
}