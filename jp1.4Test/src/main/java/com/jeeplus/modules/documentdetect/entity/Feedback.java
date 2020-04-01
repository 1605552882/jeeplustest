/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentdetect.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 单据反馈信息Entity
 * @author liang
 * @version 2019-08-05
 */
public class Feedback extends DataEntity<Feedback> {
	
	private static final long serialVersionUID = 1L;
	private Documentarydetails documentarydetails;		// 单据编号 父类
	private String iserialno;		// 反馈序号
	private String sdeptname;		// 反馈部门
	private String sstaffname;		// 反馈人员
	private String sstaffphone;		// 反馈人电话
	private Date dcreatetime;		// 反馈时间
	private String ssource;		// 反馈来源
	private String sprocdesc;		// 反馈内容
	
	public Feedback() {
		super();
	}

	public Feedback(String id){
		super(id);
	}

	public Feedback(Documentarydetails documentarydetails){
		this.documentarydetails = documentarydetails;
	}

	@ExcelField(value="documentarydetails.sbillno", title="故障单号", align=2, sort=1)
	public Documentarydetails getDocumentarydetails() {
		return documentarydetails;
	}

	public void setDocumentarydetails(Documentarydetails documentarydetails) {
		this.documentarydetails = documentarydetails;
	}
	
	@ExcelField(title="反馈序号", align=2, sort=2)
	public String getIserialno() {
		return iserialno;
	}

	public void setIserialno(String iserialno) {
		this.iserialno = iserialno;
	}
	
	@ExcelField(title="反馈部门", align=2, sort=3)
	public String getSdeptname() {
		return sdeptname;
	}

	public void setSdeptname(String sdeptname) {
		this.sdeptname = sdeptname;
	}
	
	@ExcelField(title="反馈人员", align=2, sort=4)
	public String getSstaffname() {
		return sstaffname;
	}

	public void setSstaffname(String sstaffname) {
		this.sstaffname = sstaffname;
	}
	
	@ExcelField(title="反馈人电话", align=2, sort=5)
	public String getSstaffphone() {
		return sstaffphone;
	}

	public void setSstaffphone(String sstaffphone) {
		this.sstaffphone = sstaffphone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="反馈时间", align=2, sort=6)
	public Date getDcreatetime() {
		return dcreatetime;
	}

	public void setDcreatetime(Date dcreatetime) {
		this.dcreatetime = dcreatetime;
	}
	
	@ExcelField(title="反馈来源", align=2, sort=7)
	public String getSsource() {
		return ssource;
	}

	public void setSsource(String ssource) {
		this.ssource = ssource;
	}
	
	@ExcelField(title="反馈内容", align=2, sort=8)
	public String getSprocdesc() {
		return sprocdesc;
	}

	public void setSprocdesc(String sprocdesc) {
		this.sprocdesc = sprocdesc;
	}
	
}