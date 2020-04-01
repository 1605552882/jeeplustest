/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.statisticdocument.entity;

import java.util.Date;
import com.jeeplus.core.persistence.DataEntity;


/**
 * 表单统计Entity
 * @author lxy
 * @version 2019-09-05
 */
public class Statisticdocument extends DataEntity<Statisticdocument> {
	
	private static final long serialVersionUID = 1L;
	private Integer daynum;		// 原始工单量
	private Integer detectnum;		// 检测工单量
	private Integer otimenum;		// 超时工单量
	private Integer osignnum;		// 签单超时工单量
	private Integer repetitionnum;		// 重复退单工单量
	private Integer rulenum;		// 规则匹配工单工单量
	private Integer ofeedbacknum;		// 反馈超时工单量
	private Integer checknum;		// 检查工单量
	private Integer errornum;		// 存在问题工单量
	private Integer conclusionwrongnum;		// 回单结论不正确工单量
	private Integer controlnum;		// 管控不力工单量
	private Integer irrelevantanswernum;		// 答非所问工单量
	private Integer sendwrongnum;		// 派错处理部门工单量
	private Integer thinkwrongnum;		// 处理思路不正确工单量
	private Integer feedbackfalsenum;		// 回单反馈信息虚假工单量
	private Integer onegroupnum;		// 1组处理工单量
	private Integer onegrouperrornum;		// 1组问题工单量
	private Integer twogroupnum;		// 2组处理工单量
	private Integer twogrouperrornum;		// 2组问题工单量
	private Integer thgroupnum;		// 3组处理工单量
	private Integer thgrouperrornum;		// 3组问题工单量
	private Integer fogroupnum;		// 4组处理工单量
	private Integer fogrouperrornum;		// 4组问题工单量
	private Integer fivegroupnum;		// 5组处理工单量
	private Integer fivegrouperrornum;		// 5组问题工单量
	private Date creatTime;		// 创建时间
	private Date start;		// 创建时间
	private Date end;		// 创建时间
	
	
	public Statisticdocument() {
		super();
	}

	public Statisticdocument(String id){
		super(id);
	}

	public Integer getDaynum() {
		return daynum;
	}

	public void setDaynum(Integer daynum) {
		this.daynum = daynum;
	}

	public Integer getDetectnum() {
		return detectnum;
	}

	public void setDetectnum(Integer detectnum) {
		this.detectnum = detectnum;
	}

	public Integer getOtimenum() {
		return otimenum;
	}

	public void setOtimenum(Integer otimenum) {
		this.otimenum = otimenum;
	}

	public Integer getOsignnum() {
		return osignnum;
	}

	public void setOsignnum(Integer osignnum) {
		this.osignnum = osignnum;
	}

	public Integer getRepetitionnum() {
		return repetitionnum;
	}

	public void setRepetitionnum(Integer repetitionnum) {
		this.repetitionnum = repetitionnum;
	}

	public Integer getRulenum() {
		return rulenum;
	}

	public void setRulenum(Integer rulenum) {
		this.rulenum = rulenum;
	}

	public Integer getOfeedbacknum() {
		return ofeedbacknum;
	}

	public void setOfeedbacknum(Integer ofeedbacknum) {
		this.ofeedbacknum = ofeedbacknum;
	}

	public Integer getChecknum() {
		return checknum;
	}

	public void setChecknum(Integer checknum) {
		this.checknum = checknum;
	}

	public Integer getErrornum() {
		return errornum;
	}

	public void setErrornum(Integer errornum) {
		this.errornum = errornum;
	}

	public Integer getConclusionwrongnum() {
		return conclusionwrongnum;
	}

	public void setConclusionwrongnum(Integer conclusionwrongnum) {
		this.conclusionwrongnum = conclusionwrongnum;
	}

	public Integer getControlnum() {
		return controlnum;
	}

	public void setControlnum(Integer controlnum) {
		this.controlnum = controlnum;
	}

	public Integer getIrrelevantanswernum() {
		return irrelevantanswernum;
	}

	public void setIrrelevantanswernum(Integer irrelevantanswernum) {
		this.irrelevantanswernum = irrelevantanswernum;
	}

	public Integer getSendwrongnum() {
		return sendwrongnum;
	}

	public void setSendwrongnum(Integer sendwrongnum) {
		this.sendwrongnum = sendwrongnum;
	}

	public Integer getThinkwrongnum() {
		return thinkwrongnum;
	}

	public void setThinkwrongnum(Integer thinkwrongnum) {
		this.thinkwrongnum = thinkwrongnum;
	}

	public Integer getFeedbackfalsenum() {
		return feedbackfalsenum;
	}

	public void setFeedbackfalsenum(Integer feedbackfalsenum) {
		this.feedbackfalsenum = feedbackfalsenum;
	}

	public Integer getOnegroupnum() {
		return onegroupnum;
	}

	public void setOnegroupnum(Integer onegroupnum) {
		this.onegroupnum = onegroupnum;
	}

	public Integer getOnegrouperrornum() {
		return onegrouperrornum;
	}

	public void setOnegrouperrornum(Integer onegrouperrornum) {
		this.onegrouperrornum = onegrouperrornum;
	}

	public Integer getTwogroupnum() {
		return twogroupnum;
	}

	public void setTwogroupnum(Integer twogroupnum) {
		this.twogroupnum = twogroupnum;
	}

	public Integer getTwogrouperrornum() {
		return twogrouperrornum;
	}

	public void setTwogrouperrornum(Integer twogrouperrornum) {
		this.twogrouperrornum = twogrouperrornum;
	}

	public Integer getThgroupnum() {
		return thgroupnum;
	}

	public void setThgroupnum(Integer thgroupnum) {
		this.thgroupnum = thgroupnum;
	}

	public Integer getThgrouperrornum() {
		return thgrouperrornum;
	}

	public void setThgrouperrornum(Integer thgrouperrornum) {
		this.thgrouperrornum = thgrouperrornum;
	}

	public Integer getFogroupnum() {
		return fogroupnum;
	}

	public void setFogroupnum(Integer fogroupnum) {
		this.fogroupnum = fogroupnum;
	}

	public Integer getFogrouperrornum() {
		return fogrouperrornum;
	}

	public void setFogrouperrornum(Integer fogrouperrornum) {
		this.fogrouperrornum = fogrouperrornum;
	}

	public Integer getFivegroupnum() {
		return fivegroupnum;
	}

	public void setFivegroupnum(Integer fivegroupnum) {
		this.fivegroupnum = fivegroupnum;
	}

	public Integer getFivegrouperrornum() {
		return fivegrouperrornum;
	}

	public void setFivegrouperrornum(Integer fivegrouperrornum) {
		this.fivegrouperrornum = fivegrouperrornum;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	
	
}