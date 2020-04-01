/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.patient.entity;

import com.jeeplus.modules.patient.entity.SdPatientInf;
import javax.validation.constraints.NotNull;
import com.jeeplus.modules.doctor.entity.SdDoctorInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 预约人评价Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdPatientRatedLog extends DataEntity<SdPatientRatedLog> {
	
	private static final long serialVersionUID = 1L;
	private SdPatientInf patientid;		// 预约人 父类
	private SdDoctorInf doctorid;		// 评估师
	private Long orderid;		// 订单号
	private String rateddetail;		// 评价内容
	private Date ratedtime;		// 评价时间
	
	public SdPatientRatedLog() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdPatientRatedLog(String id){
		super(id);
	}

	public SdPatientRatedLog(SdPatientInf patientid){
		this.patientid = patientid;
	}

	@NotNull(message="预约人不能为空")
	public SdPatientInf getPatientid() {
		return patientid;
	}

	public void setPatientid(SdPatientInf patientid) {
		this.patientid = patientid;
	}
	
	@NotNull(message="评估师不能为空")
	@ExcelField(title="评估师", fieldType=SdDoctorInf.class, value="doctorid.fullname", align=2, sort=2)
	public SdDoctorInf getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(SdDoctorInf doctorid) {
		this.doctorid = doctorid;
	}
	
	@NotNull(message="订单号不能为空")
	@ExcelField(title="订单号", align=2, sort=3)
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	@ExcelField(title="评价内容", align=2, sort=4)
	public String getRateddetail() {
		return rateddetail;
	}

	public void setRateddetail(String rateddetail) {
		this.rateddetail = rateddetail;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="评价时间不能为空")
	@ExcelField(title="评价时间", align=2, sort=5)
	public Date getRatedtime() {
		return ratedtime;
	}

	public void setRatedtime(Date ratedtime) {
		this.ratedtime = ratedtime;
	}
	
}