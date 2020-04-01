/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.web_log.entity.web_log;

import com.jeeplus.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * web请求日志Entity
 * @author 姜森焱
 * @version 2019-12-26
 */
public class WebLog extends DataEntity<WebLog> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// user_id
	private String className;		// class_name
	private String methodName;		// method_name
	private String connId;		// conn_id
	private String requestMsg;		// request_msg
	private String ipAddr;		// ip_addr
	private String type;		// type
	private String reponseMsg;		// reponse_msg
	private String mdn;		// mdn
	private Date startTime;		// start_time
	private Date endTime;		// end_time
	private String takeTime;		// take_time
	
	public WebLog() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public WebLog(String id){
		super(id);
	}

	@ExcelField(title="user_id", align=2, sort=1)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ExcelField(title="class_name", align=2, sort=2)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@ExcelField(title="method_name", align=2, sort=3)
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	@ExcelField(title="conn_id", align=2, sort=4)
	public String getConnId() {
		return connId;
	}

	public void setConnId(String connId) {
		this.connId = connId;
	}
	
	@ExcelField(title="request_msg", align=2, sort=5)
	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}
	
	@ExcelField(title="ip_addr", align=2, sort=6)
	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	@ExcelField(title="type", align=2, sort=7)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@ExcelField(title="reponse_msg", align=2, sort=8)
	public String getReponseMsg() {
		return reponseMsg;
	}

	public void setReponseMsg(String reponseMsg) {
		this.reponseMsg = reponseMsg;
	}
	
	@ExcelField(title="mdn", align=2, sort=9)
	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="start_time", align=2, sort=10)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="end_time", align=2, sort=11)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@ExcelField(title="take_time", align=2, sort=12)
	public String getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	
}