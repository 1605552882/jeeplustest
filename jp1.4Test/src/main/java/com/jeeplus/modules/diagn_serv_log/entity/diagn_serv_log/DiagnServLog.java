/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_serv_log.entity.diagn_serv_log;

import com.jeeplus.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 一键诊断详细信息Entity
 * @author 姜森焱
 * @version 2020-02-28
 */
public class DiagnServLog extends DataEntity<DiagnServLog> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户名称
	private String checkPlat;		// 检查平台
	private String mdn;		// 电话号码
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String servNameId;		// 业务名称
	private String city;		// 地市
	
	public DiagnServLog() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public DiagnServLog(String id){
		super(id);
	}

	@ExcelField(title="用户名称", fieldType=User.class, value="user.name", align=2, sort=1)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ExcelField(title="检查平台", dictType="", align=2, sort=2)
	public String getCheckPlat() {
		return checkPlat;
	}

	public void setCheckPlat(String checkPlat) {
		this.checkPlat = checkPlat;
	}
	
	@ExcelField(title="电话号码", align=2, sort=3)
	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始时间", align=2, sort=4)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结束时间", align=2, sort=5)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@ExcelField(title="业务名称", dictType="", align=2, sort=6)
	public String getServNameId() {
		return servNameId;
	}

	public void setServNameId(String servNameId) {
		this.servNameId = servNameId;
	}
	
	@ExcelField(title="地市", dictType="", align=2, sort=7)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}