/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treatmentreq.entity;

import com.jeeplus.modules.treamentplace.entity.SdTreatmentplaceInf;
import javax.validation.constraints.NotNull;
import com.jeeplus.modules.patient.entity.SdPatientInf;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 求医记录Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdTreatmentReqLog extends DataEntity<SdTreatmentReqLog> {
	
	private static final long serialVersionUID = 1L;
	private SdTreatmentplaceInf sdTreatmentplaceInf;		// 地点
	private SdPatientInf sdPatientInf;		// 预约人
	private String patientname;		// 地点名称
	private String reqstate;		// 记录状态
	private String reqtitle;		// 标题
	private String reqdetail;		// 内容
	private Date createtime;		// createtime
	private List<SdTreatmentInviteLog> sdTreatmentInviteLogList = Lists.newArrayList();		// 子表列表
	
	public SdTreatmentReqLog() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdTreatmentReqLog(String id){
		super(id);
	}

	@NotNull(message="地点不能为空")
	@ExcelField(title="地点", fieldType=SdTreatmentplaceInf.class, value="sdTreatmentplaceInf.placename", align=2, sort=1)
	public SdTreatmentplaceInf getSdTreatmentplaceInf() {
		return sdTreatmentplaceInf;
	}

	public void setSdTreatmentplaceInf(SdTreatmentplaceInf sdTreatmentplaceInf) {
		this.sdTreatmentplaceInf = sdTreatmentplaceInf;
	}
	
	@NotNull(message="预约人不能为空")
	@ExcelField(title="预约人", fieldType=SdPatientInf.class, value="sdPatientInf.fullname", align=2, sort=2)
	public SdPatientInf getSdPatientInf() {
		return sdPatientInf;
	}

	public void setSdPatientInf(SdPatientInf sdPatientInf) {
		this.sdPatientInf = sdPatientInf;
	}
	
	@ExcelField(title="地点名称", align=2, sort=3)
	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	
	@ExcelField(title="记录状态", dictType="treatmentreq_state", align=2, sort=4)
	public String getReqstate() {
		return reqstate;
	}

	public void setReqstate(String reqstate) {
		this.reqstate = reqstate;
	}
	
	@Length(min=10, max=100, message="标题长度必须介于 10 和 100 之间")
	@ExcelField(title="标题", align=2, sort=5)
	public String getReqtitle() {
		return reqtitle;
	}

	public void setReqtitle(String reqtitle) {
		this.reqtitle = reqtitle;
	}
	
	@Length(min=10, max=1000, message="内容长度必须介于 10 和 1000 之间")
	@ExcelField(title="内容", align=2, sort=6)
	public String getReqdetail() {
		return reqdetail;
	}

	public void setReqdetail(String reqdetail) {
		this.reqdetail = reqdetail;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="createtime", align=2, sort=7)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public List<SdTreatmentInviteLog> getSdTreatmentInviteLogList() {
		return sdTreatmentInviteLogList;
	}

	public void setSdTreatmentInviteLogList(List<SdTreatmentInviteLog> sdTreatmentInviteLogList) {
		this.sdTreatmentInviteLogList = sdTreatmentInviteLogList;
	}
}