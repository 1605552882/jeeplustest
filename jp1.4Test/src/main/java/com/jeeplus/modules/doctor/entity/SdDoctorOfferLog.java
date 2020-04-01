/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.doctor.entity;

import javax.validation.constraints.NotNull;
import com.jeeplus.modules.doctor.entity.SdDoctorInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 接单记录Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdDoctorOfferLog extends DataEntity<SdDoctorOfferLog> {
	
	private static final long serialVersionUID = 1L;
	private Long reqid;		// 预约评估信息
	private SdDoctorInf doctorid;		// 评估师 父类
	private Date offertime;		// 接单时间
	private Date receptiontime;		// 接诊时间
	private String comment;		// 备注
	private String receptionfee;		// 诊金
	
	public SdDoctorOfferLog() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdDoctorOfferLog(String id){
		super(id);
	}

	public SdDoctorOfferLog(SdDoctorInf doctorid){
		this.doctorid = doctorid;
	}

	@NotNull(message="预约评估信息不能为空")
	@ExcelField(title="预约评估信息", align=2, sort=1)
	public Long getReqid() {
		return reqid;
	}

	public void setReqid(Long reqid) {
		this.reqid = reqid;
	}
	
	@NotNull(message="评估师不能为空")
	public SdDoctorInf getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(SdDoctorInf doctorid) {
		this.doctorid = doctorid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="接单时间不能为空")
	@ExcelField(title="接单时间", align=2, sort=3)
	public Date getOffertime() {
		return offertime;
	}

	public void setOffertime(Date offertime) {
		this.offertime = offertime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="接诊时间", align=2, sort=4)
	public Date getReceptiontime() {
		return receptiontime;
	}

	public void setReceptiontime(Date receptiontime) {
		this.receptiontime = receptiontime;
	}
	
	@ExcelField(title="备注", align=2, sort=5)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@ExcelField(title="诊金", align=2, sort=6)
	public String getReceptionfee() {
		return receptionfee;
	}

	public void setReceptionfee(String receptionfee) {
		this.receptionfee = receptionfee;
	}
	
}