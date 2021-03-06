/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentconfig.entity;


import com.jeeplus.core.persistence.DataEntity;

import java.util.Date;

import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 工单检测规则配置Entity
 * @author zqb
 * @version 2019-10-15
 */
public class Documentconfig extends DataEntity<Documentconfig> {
	
	private static final long serialVersionUID = 1L;
	private String busytype;		// 业务类型
	private String srepfaultdetail;		// 故障内容
	private String sprocesssummary;		// 结单信息
	private String rule;		// 检查规则
	private String result;		// 检查结论
	private String flag;		// 是否启用
	private String feedbackrule;		// 反馈信息工单规范
	private String id;		// id
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	
	private String faultclass;		// 故障分类
	private String office;		// 所属科室'
	private String ids ;     //ids   
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Documentconfig() {
		super();
	}

	public Documentconfig(String id){
		super(id);
	}

	@ExcelField(title="业务类型", dictType="", align=2, sort=1)
	public String getBusytype() {
		return busytype;
	}

	public void setBusytype(String busytype) {
		this.busytype = busytype;
	}
	
	@ExcelField(title="故障内容", align=2, sort=2)
	public String getSrepfaultdetail() {
		return srepfaultdetail;
	}

	public void setSrepfaultdetail(String srepfaultdetail) {
		this.srepfaultdetail = srepfaultdetail;
	}
	
	@ExcelField(title="结单信息", align=2, sort=3)
	public String getSprocesssummary() {
		return sprocesssummary;
	}

	public void setSprocesssummary(String sprocesssummary) {
		this.sprocesssummary = sprocesssummary;
	}
	
	@ExcelField(title="检查规则", align=2, sort=4)
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
	
	@ExcelField(title="检查结论", align=2, sort=5)
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@ExcelField(title="是否启用", dictType="", align=2, sort=6)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@ExcelField(title="反馈信息工单规范", align=2, sort=7)
	public String getFeedbackrule() {
		return feedbackrule;
	}

	public void setFeedbackrule(String feedbackrule) {
		this.feedbackrule = feedbackrule;
	}
	@ExcelField(title="结单信息", align=2, sort=8)
	public String getFaultclass() {
		return faultclass;
	}

	public void setFaultclass(String faultclass) {
		this.faultclass = faultclass;
	}
	@ExcelField(title="结单信息", align=2, sort=9)
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}
	
}