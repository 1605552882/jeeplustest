/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.doctor.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 医生信息Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdDoctorInf extends DataEntity<SdDoctorInf> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 微信下，登录id自动生成或者直接填写openid，以后开放app等备用
	private String fullname;		// 姓名
	private String mobileno;		// 手机号
	private String hospital;		// 医院
	private String department;		// 科室
	private String doctortitle;		// 职称
	private String introduction;		// 介绍
	private Integer auditstate;		// 审核状态
	private String headicon;		// headicon
	private Date createtime;		// createtime
	private Integer receptionstate;		// 接诊状态
	private String receptionfee;		// 诊金
	private List<SdDoctorOfferLog> sdDoctorOfferLogList = Lists.newArrayList();		// 子表列表
	private List<SdDoctorRatedLog> sdDoctorRatedLogList = Lists.newArrayList();		// 子表列表
	
	public SdDoctorInf() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdDoctorInf(String id){
		super(id);
	}

	@ExcelField(title="微信下，登录id自动生成或者直接填写openid，以后开放app等备用", align=2, sort=1)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@ExcelField(title="姓名", align=2, sort=2)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@ExcelField(title="手机号", align=2, sort=3)
	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	@ExcelField(title="医院", align=2, sort=4)
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	@ExcelField(title="科室", align=2, sort=5)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@ExcelField(title="职称", align=2, sort=6)
	public String getDoctortitle() {
		return doctortitle;
	}

	public void setDoctortitle(String doctortitle) {
		this.doctortitle = doctortitle;
	}
	
	@ExcelField(title="介绍", align=2, sort=7)
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@NotNull(message="审核状态不能为空")
	@ExcelField(title="审核状态", dictType="doctor_audit_state", align=2, sort=8)
	public Integer getAuditstate() {
		return auditstate;
	}

	public void setAuditstate(Integer auditstate) {
		this.auditstate = auditstate;
	}
	
	@ExcelField(title="headicon", align=2, sort=9)
	public String getHeadicon() {
		return headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="createtime不能为空")
	@ExcelField(title="createtime", align=2, sort=10)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@ExcelField(title="接诊状态", dictType="doctor_recept_state", align=2, sort=11)
	public Integer getReceptionstate() {
		return receptionstate;
	}

	public void setReceptionstate(Integer receptionstate) {
		this.receptionstate = receptionstate;
	}
	
	@ExcelField(title="诊金", align=2, sort=12)
	public String getReceptionfee() {
		return receptionfee;
	}

	public void setReceptionfee(String receptionfee) {
		this.receptionfee = receptionfee;
	}
	
	public List<SdDoctorOfferLog> getSdDoctorOfferLogList() {
		return sdDoctorOfferLogList;
	}

	public void setSdDoctorOfferLogList(List<SdDoctorOfferLog> sdDoctorOfferLogList) {
		this.sdDoctorOfferLogList = sdDoctorOfferLogList;
	}
	public List<SdDoctorRatedLog> getSdDoctorRatedLogList() {
		return sdDoctorRatedLogList;
	}

	public void setSdDoctorRatedLogList(List<SdDoctorRatedLog> sdDoctorRatedLogList) {
		this.sdDoctorRatedLogList = sdDoctorRatedLogList;
	}
}