/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treatmentreq.entity;

import com.jeeplus.modules.treatmentreq.entity.SdTreatmentReqLog;
import javax.validation.constraints.NotNull;
import com.jeeplus.modules.patient.entity.SdPatientInf;
import com.jeeplus.modules.doctor.entity.SdDoctorInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 预约评估邀请Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdTreatmentInviteLog extends DataEntity<SdTreatmentInviteLog> {
	
	private static final long serialVersionUID = 1L;
	private SdTreatmentReqLog reqid;		// 评估信息 父类
	private SdPatientInf patientid;		// 预约人
	private SdDoctorInf doctorid;		// 评估师
	private Date invitetime;		// 邀请时间
	
	public SdTreatmentInviteLog() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdTreatmentInviteLog(String id){
		super(id);
	}

	public SdTreatmentInviteLog(SdTreatmentReqLog reqid){
		this.reqid = reqid;
	}

	@NotNull(message="评估信息不能为空")
	public SdTreatmentReqLog getReqid() {
		return reqid;
	}

	public void setReqid(SdTreatmentReqLog reqid) {
		this.reqid = reqid;
	}
	
	@NotNull(message="预约人不能为空")
	@ExcelField(title="预约人", fieldType=SdPatientInf.class, value="patientid.fullname", align=2, sort=2)
	public SdPatientInf getPatientid() {
		return patientid;
	}

	public void setPatientid(SdPatientInf patientid) {
		this.patientid = patientid;
	}
	
	@NotNull(message="评估师不能为空")
	@ExcelField(title="评估师", fieldType=SdDoctorInf.class, value="doctorid.fullname", align=2, sort=3)
	public SdDoctorInf getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(SdDoctorInf doctorid) {
		this.doctorid = doctorid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="邀请时间不能为空")
	@ExcelField(title="邀请时间", align=2, sort=4)
	public Date getInvitetime() {
		return invitetime;
	}

	public void setInvitetime(Date invitetime) {
		this.invitetime = invitetime;
	}
	
}