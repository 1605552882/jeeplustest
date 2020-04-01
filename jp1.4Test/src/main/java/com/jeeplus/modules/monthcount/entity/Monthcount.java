/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.monthcount.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 月度汇总Entity
 * @author lxy
 * @version 2019-08-22
 */
public class Monthcount extends DataEntity<Monthcount> {
	
	private static final long serialVersionUID = 1L;
	private String dutyGroup;		// 班组
	private String checknumber;		// 检查单量
	private String errornumber;		// 问题单量
	private String dutyPeopledetail;		// 责任人明细
	//private Date searchtime;		// 查询时间
	private Date beginSearchtime;		// 开始 查询时间
	private Date endSearchtime;		// 结束 查询时间
	
	private String sStatus;			// 单据状态
	private String transitnumber;		// 单据状态:在途	单量
	private String archivingnumber;		// 单据状态:归档	单量

	public Monthcount() {
		super();
	}

	public Monthcount(String id){
		super(id);
	}

	@ExcelField(title="班组", align=2, sort=1)
	public String getDutyGroup() {
		return dutyGroup;
	}

	public void setDutyGroup(String dutyGroup) {
		this.dutyGroup = dutyGroup;
	}
	
	@ExcelField(title="检查单量", align=2, sort=2)
	public String getChecknumber() {
		return checknumber;
	}

	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}
	
	@ExcelField(title="问题单量", align=2, sort=3)
	public String getErrornumber() {
		return errornumber;
	}

	public void setErrornumber(String errornumber) {
		this.errornumber = errornumber;
	}
	
	@ExcelField(title="责任人明细", align=2, sort=4)
	public String getDutyPeopledetail() {
		return dutyPeopledetail;
	}

	public void setDutyPeopledetail(String dutyPeopledetail) {
		this.dutyPeopledetail = dutyPeopledetail;
	}
	
	/*
	 * @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 * 
	 * @ExcelField(title="查询时间", align=2, sort=5) public Date getSearchtime() {
	 * return searchtime; }
	 */

	/*
	 * public void setSearchtime(Date searchtime) { this.searchtime = searchtime; }
	 */
	public Date getBeginSearchtime() {
		return beginSearchtime;
	}

	public void setBeginSearchtime(Date beginSearchtime) {
		this.beginSearchtime = beginSearchtime;
	}
	
	public Date getEndSearchtime() {
		return endSearchtime;
	}

	public void setEndSearchtime(Date endSearchtime) {
		this.endSearchtime = endSearchtime;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	@ExcelField(title="在途单量", align=2, sort=5)
	public String getTransitnumber() {
		return transitnumber;
	}

	public void setTransitnumber(String transitnumber) {
		this.transitnumber = transitnumber;
	}
	@ExcelField(title="归档单量", align=2, sort=6)
	public String getArchivingnumber() {
		return archivingnumber;
	}

	public void setArchivingnumber(String archivingnumber) {
		this.archivingnumber = archivingnumber;
	}
		
}