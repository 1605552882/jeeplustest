/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sourcestatistic.entity;


import com.jeeplus.core.persistence.DataEntity;

import java.util.Date;

import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 工单申告来源统计Entity
 * @author zqb
 * @version 2019-12-06
 */
public class Sourcestatistic extends DataEntity<Sourcestatistic> {
	
	private static final long serialVersionUID = 1L;
	private String source;		// 申告来源
	private String sStatus;		// 单据状态
	
	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}

	private String times;		// 统计次数
	
	private String timeFlag;		// 日期标识1“天”2“月”
	private String groupFlag;		// 类型标识1“申告来源”2申告地市
	private Date beginCreateDate;	//查询开始日期
	private Date endCreateDate;		//查询结束日期
	
	
	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}

	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Sourcestatistic() {
		super();
	}

	public Sourcestatistic(String id){
		super(id);
	}

	@ExcelField(title="申告来源", align=2, sort=1)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@ExcelField(title="统计次数", align=2, sort=2)
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
}