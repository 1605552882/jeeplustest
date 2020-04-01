/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.edseedoctor.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 就医复诊资料Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdSeedoctorLog extends DataEntity<EdSeedoctorLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date jiuyifuzhenshijian;		// 就医/复诊时间
	private String jiuyididian;		// 就医地点
	private String jiuyikeshi;		// 就医科室
	private String zhenduan;		// 诊断
	private Date assesstime;		// 评估日期
	private User assessor;		// 评估人员
	
	public EdSeedoctorLog() {
		super();
	}

	public EdSeedoctorLog(String id){
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
	@ExcelField(title="就医/复诊时间", align=2, sort=8)
	public Date getJiuyifuzhenshijian() {
		return jiuyifuzhenshijian;
	}

	public void setJiuyifuzhenshijian(Date jiuyifuzhenshijian) {
		this.jiuyifuzhenshijian = jiuyifuzhenshijian;
	}
	
	@ExcelField(title="就医地点", align=2, sort=9)
	public String getJiuyididian() {
		return jiuyididian;
	}

	public void setJiuyididian(String jiuyididian) {
		this.jiuyididian = jiuyididian;
	}
	
	@ExcelField(title="就医科室", align=2, sort=10)
	public String getJiuyikeshi() {
		return jiuyikeshi;
	}

	public void setJiuyikeshi(String jiuyikeshi) {
		this.jiuyikeshi = jiuyikeshi;
	}
	
	@ExcelField(title="诊断", align=2, sort=11)
	public String getZhenduan() {
		return zhenduan;
	}

	public void setZhenduan(String zhenduan) {
		this.zhenduan = zhenduan;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="评估日期", align=2, sort=12)
	public Date getAssesstime() {
		return assesstime;
	}

	public void setAssesstime(Date assesstime) {
		this.assesstime = assesstime;
	}
	
	@ExcelField(title="评估人员", fieldType=User.class, value="assessor.name", align=2, sort=13)
	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	
}