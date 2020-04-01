/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_holiday.entity.diagn_holiday;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 节假日配置Entity
 * @author 姜森焱
 * @version 2020-02-26
 */
public class DiagnHoliday extends DataEntity<DiagnHoliday> {
	
	private static final long serialVersionUID = 1L;
	private Date startDate;		// 开始时间
	private Date endDate;		// 结束时间
	private String holidayName;		// 节假日名称
	
	public DiagnHoliday() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public DiagnHoliday(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始时间", align=2, sort=1)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结束时间", align=2, sort=2)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@ExcelField(title="节假日名称", align=2, sort=3)
	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	
}