/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.nursingclass.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.entity.Office;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 护理级别Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdNursingclassLog extends DataEntity<EdNursingclassLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private String richanghuodongnengli;		// 日常活动能力
	private String zhili;		// 智力
	private String hulijibie;		// 护理级别
	private User assessor1;		// 评估人员1
	private User assessor2;		// 评估人员2
	private Office assessor1dept;		// 评估人员1职位
	private Office assessor2dept;		// 评估人员2职位
	private String zhangzhehuojiashu;		// 长者/家属
	private Date qianziriqi;		// 签字日期
	
	public EdNursingclassLog() {
		super();
	}

	public EdNursingclassLog(String id){
		super(id);
	}

	@ExcelField(title="长者id", fieldType=EdElderInf.class, value="elderid.funame", align=2, sort=7)
	public EdElderInf getElderid() {
		return elderid;
	}

	public void setElderid(EdElderInf elderid) {
		this.elderid = elderid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="评估时间", align=2, sort=8)
	public Date getAssesstime() {
		return assesstime;
	}

	public void setAssesstime(Date assesstime) {
		this.assesstime = assesstime;
	}
	
	@ExcelField(title="日常活动能力", dictType="richangshenghuonenglifenji", align=2, sort=9)
	public String getRichanghuodongnengli() {
		return richanghuodongnengli;
	}

	public void setRichanghuodongnengli(String richanghuodongnengli) {
		this.richanghuodongnengli = richanghuodongnengli;
	}
	
	@ExcelField(title="智力", dictType="zhiliqingkuang", align=2, sort=10)
	public String getZhili() {
		return zhili;
	}

	public void setZhili(String zhili) {
		this.zhili = zhili;
	}
	
	@ExcelField(title="护理级别", dictType="hulijibie", align=2, sort=11)
	public String getHulijibie() {
		return hulijibie;
	}

	public void setHulijibie(String hulijibie) {
		this.hulijibie = hulijibie;
	}
	
	@ExcelField(title="评估人员1", fieldType=User.class, value="assessor1.name", align=2, sort=12)
	public User getAssessor1() {
		return assessor1;
	}

	public void setAssessor1(User assessor1) {
		this.assessor1 = assessor1;
	}
	
	@ExcelField(title="评估人员2", fieldType=User.class, value="assessor2.name", align=2, sort=13)
	public User getAssessor2() {
		return assessor2;
	}

	public void setAssessor2(User assessor2) {
		this.assessor2 = assessor2;
	}
	
	@ExcelField(title="评估人员1职位", fieldType=Office.class, value="assessor1dept.name", align=2, sort=14)
	public Office getAssessor1dept() {
		return assessor1dept;
	}

	public void setAssessor1dept(Office assessor1dept) {
		this.assessor1dept = assessor1dept;
	}
	
	@ExcelField(title="评估人员2职位", fieldType=Office.class, value="assessor2dept.name", align=2, sort=15)
	public Office getAssessor2dept() {
		return assessor2dept;
	}

	public void setAssessor2dept(Office assessor2dept) {
		this.assessor2dept = assessor2dept;
	}
	
	@ExcelField(title="长者/家属", align=2, sort=16)
	public String getZhangzhehuojiashu() {
		return zhangzhehuojiashu;
	}

	public void setZhangzhehuojiashu(String zhangzhehuojiashu) {
		this.zhangzhehuojiashu = zhangzhehuojiashu;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="签字日期", align=2, sort=17)
	public Date getQianziriqi() {
		return qianziriqi;
	}

	public void setQianziriqi(Date qianziriqi) {
		this.qianziriqi = qianziriqi;
	}
	
}