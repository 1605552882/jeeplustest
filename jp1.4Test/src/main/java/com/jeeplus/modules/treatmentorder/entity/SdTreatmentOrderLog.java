/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treatmentorder.entity;

import javax.validation.constraints.NotNull;
import com.jeeplus.modules.patient.entity.SdPatientInf;
import com.jeeplus.modules.doctor.entity.SdDoctorInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 求医订单Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdTreatmentOrderLog extends DataEntity<SdTreatmentOrderLog> {
	
	private static final long serialVersionUID = 1L;
	private String orderno;		// 订单号
	private Long reqid;		// 求医信息
	private Long offerid;		// 接单信息
	private SdPatientInf patientid;		// 预约人
	private SdDoctorInf doctorid;		// 评估师
	private String orderstate;		// 订单状态
	private Date createtime;		// createtime
	private String receptionfee;		// 诊金
	private Date receptiontime;		// 就诊时间
	private String doconfirmstate;		// 评估师确认状态
	private Date doconfirmtime;		// 评估师确认时间
	private String paconfirmstate;		// 预约人确认状态
	private Date paconfirmtime;		// 预约人确认时间
	
	public SdTreatmentOrderLog() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdTreatmentOrderLog(String id){
		super(id);
	}

	@ExcelField(title="订单号", align=2, sort=1)
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	@NotNull(message="求医信息不能为空")
	@ExcelField(title="求医信息", align=2, sort=2)
	public Long getReqid() {
		return reqid;
	}

	public void setReqid(Long reqid) {
		this.reqid = reqid;
	}
	
	@NotNull(message="接单信息不能为空")
	@ExcelField(title="接单信息", align=2, sort=3)
	public Long getOfferid() {
		return offerid;
	}

	public void setOfferid(Long offerid) {
		this.offerid = offerid;
	}
	
	@NotNull(message="预约人不能为空")
	@ExcelField(title="预约人", fieldType=SdPatientInf.class, value="patientid.fullname", align=2, sort=4)
	public SdPatientInf getPatientid() {
		return patientid;
	}

	public void setPatientid(SdPatientInf patientid) {
		this.patientid = patientid;
	}
	
	@NotNull(message="评估师不能为空")
	@ExcelField(title="评估师", fieldType=SdDoctorInf.class, value="doctorid.fullname", align=2, sort=5)
	public SdDoctorInf getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(SdDoctorInf doctorid) {
		this.doctorid = doctorid;
	}
	
	@ExcelField(title="订单状态", dictType="sd_order_state", align=2, sort=6)
	public String getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="createtime不能为空")
	@ExcelField(title="createtime", align=2, sort=7)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@ExcelField(title="诊金", align=2, sort=8)
	public String getReceptionfee() {
		return receptionfee;
	}

	public void setReceptionfee(String receptionfee) {
		this.receptionfee = receptionfee;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="就诊时间不能为空")
	@ExcelField(title="就诊时间", align=2, sort=9)
	public Date getReceptiontime() {
		return receptiontime;
	}

	public void setReceptiontime(Date receptiontime) {
		this.receptiontime = receptiontime;
	}
	
	@ExcelField(title="评估师确认状态", dictType="sd_confirm_state", align=2, sort=10)
	public String getDoconfirmstate() {
		return doconfirmstate;
	}

	public void setDoconfirmstate(String doconfirmstate) {
		this.doconfirmstate = doconfirmstate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="评估师确认时间", align=2, sort=11)
	public Date getDoconfirmtime() {
		return doconfirmtime;
	}

	public void setDoconfirmtime(Date doconfirmtime) {
		this.doconfirmtime = doconfirmtime;
	}
	
	@ExcelField(title="预约人确认状态", dictType="sd_confirm_state", align=2, sort=12)
	public String getPaconfirmstate() {
		return paconfirmstate;
	}

	public void setPaconfirmstate(String paconfirmstate) {
		this.paconfirmstate = paconfirmstate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="预约人确认时间", align=2, sort=13)
	public Date getPaconfirmtime() {
		return paconfirmtime;
	}

	public void setPaconfirmtime(Date paconfirmtime) {
		this.paconfirmtime = paconfirmtime;
	}
	
}