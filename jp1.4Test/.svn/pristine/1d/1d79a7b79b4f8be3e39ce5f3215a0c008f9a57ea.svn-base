/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.patient.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.google.common.collect.Lists;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 病人基础信息Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdPatientInf extends DataEntity<SdPatientInf> {
	
	private static final long serialVersionUID = 1L;
	private String fullname;		// 姓名
	private String openid;		// openid
	private String mobileno;		// 手机号
	private Date createtime;		// 创建时间
	private String headicon;		// 图片的网络相对地址
	private List<SdPatientRatedLog> sdPatientRatedLogList = Lists.newArrayList();		// 子表列表
	
	public SdPatientInf() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdPatientInf(String id){
		super(id);
	}

	@ExcelField(title="姓名", align=2, sort=1)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@ExcelField(title="openid", align=2, sort=2)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@ExcelField(title="手机号", align=2, sort=3)
	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	@ExcelField(title="创建时间", align=2, sort=4)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@ExcelField(title="图片的网络相对地址", align=2, sort=5)
	public String getHeadicon() {
		return headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}
	
	public List<SdPatientRatedLog> getSdPatientRatedLogList() {
		return sdPatientRatedLogList;
	}

	public void setSdPatientRatedLogList(List<SdPatientRatedLog> sdPatientRatedLogList) {
		this.sdPatientRatedLogList = sdPatientRatedLogList;
	}
}