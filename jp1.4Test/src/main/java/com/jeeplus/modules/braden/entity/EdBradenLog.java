/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.braden.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 贝登量表Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdBradenLog extends DataEntity<EdBradenLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private User assessor;		// 评估人员
	private String ganguanganjue;		// 感官感觉对不适的压力具有反应的能力
	private String chaoshiqingkuang;		// 潮湿情况皮肤的潮湿程度
	private String huodongqingkuang;		// 活动情况身体活动程度
	private String yidongqingkuang;		// 移动情况变换和控制体位的能力
	private String mocali;		// 摩擦力
	private String yinyangqingkuang;		// 营养情况
	private Integer score;		// 总分
	
	public EdBradenLog() {
		super();
	}

	public EdBradenLog(String id){
		super(id);
	}

	@ExcelField(title="长者id", fieldType=EdElderInf.class, value="elderid.funame", align=2, sort=6)
	public EdElderInf getElderid() {
		return elderid;
	}

	public void setElderid(EdElderInf elderid) {
		this.elderid = elderid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="评估时间", align=2, sort=7)
	public Date getAssesstime() {
		return assesstime;
	}

	public void setAssesstime(Date assesstime) {
		this.assesstime = assesstime;
	}
	
	@ExcelField(title="评估人员", fieldType=User.class, value="assessor.name", align=2, sort=8)
	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	
	@ExcelField(title="感官感觉对不适的压力具有反应的能力", dictType="braden_ganguanganjue", align=2, sort=9)
	public String getGanguanganjue() {
		return ganguanganjue;
	}

	public void setGanguanganjue(String ganguanganjue) {
		this.ganguanganjue = ganguanganjue;
	}
	
	@ExcelField(title="潮湿情况皮肤的潮湿程度", dictType="braden_chaoshiqingkuang", align=2, sort=10)
	public String getChaoshiqingkuang() {
		return chaoshiqingkuang;
	}

	public void setChaoshiqingkuang(String chaoshiqingkuang) {
		this.chaoshiqingkuang = chaoshiqingkuang;
	}
	
	@ExcelField(title="活动情况身体活动程度", dictType="braden_huodongqingkuang", align=2, sort=11)
	public String getHuodongqingkuang() {
		return huodongqingkuang;
	}

	public void setHuodongqingkuang(String huodongqingkuang) {
		this.huodongqingkuang = huodongqingkuang;
	}
	
	@ExcelField(title="移动情况变换和控制体位的能力", dictType="braden_yidongqingkuang", align=2, sort=12)
	public String getYidongqingkuang() {
		return yidongqingkuang;
	}

	public void setYidongqingkuang(String yidongqingkuang) {
		this.yidongqingkuang = yidongqingkuang;
	}
	
	@ExcelField(title="摩擦力", dictType="braden_mocaili", align=2, sort=13)
	public String getMocali() {
		return mocali;
	}

	public void setMocali(String mocali) {
		this.mocali = mocali;
	}
	
	@ExcelField(title="营养情况", dictType="braden_yingyangqingkuang", align=2, sort=14)
	public String getYinyangqingkuang() {
		return yinyangqingkuang;
	}

	public void setYinyangqingkuang(String yinyangqingkuang) {
		this.yinyangqingkuang = yinyangqingkuang;
	}
	
	@ExcelField(title="总分", align=2, sort=15)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
}