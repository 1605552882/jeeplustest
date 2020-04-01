/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.checkresult.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 人工检测结果Entity
 * @author lxy
 * @version 2019-08-19
 */
public class Checkresult extends DataEntity<Checkresult> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 单据编号
	private String hasproblem;		// 存在问题
	private String details;		// 详细情况
	private String dutyGroup;		// 责任班组
	private String dutyPeople;		// 责任人
	private Date dcreatetime;		// 创建时间
	private Date beginDcreatetime;		// 开始 创建时间
	private Date endDcreatetime;		// 结束 创建时间
	private String remarks; 			//导出标记
	private String sStatus;		// 单据状态
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Checkresult() {
		super();
	}

	public Checkresult(String id){
		super(id);
	}

	@ExcelField(title="单据编号", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="存在问题", align=2, sort=2)
	public String getHasproblem() {
		return hasproblem;
	}

	public void setHasproblem(String hasproblem) {
		this.hasproblem = hasproblem;
	}
	
	@ExcelField(title="详细情况", align=2, sort=3)
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	@ExcelField(title="责任班组", align=2, sort=4)
	public String getDutyGroup() {
		return dutyGroup;
	}

	public void setDutyGroup(String dutyGroup) {
		this.dutyGroup = dutyGroup;
	}
	
	@ExcelField(title="责任人", align=2, sort=5)
	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", align=2, sort=6)
	public Date getDcreatetime() {
		return dcreatetime;
	}

	public void setDcreatetime(Date dcreatetime) {
		this.dcreatetime = dcreatetime;
	}
	
	public Date getBeginDcreatetime() {
		return beginDcreatetime;
	}

	public void setBeginDcreatetime(Date beginDcreatetime) {
		this.beginDcreatetime = beginDcreatetime;
	}
	
	public Date getEndDcreatetime() {
		return endDcreatetime;
	}

	public void setEndDcreatetime(Date endDcreatetime) {
		this.endDcreatetime = endDcreatetime;
	}

	@ExcelField(title="单据状态", align=2, sort=7)
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
		
}