/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.entity;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.core.persistence.DataEntity;

/**
 * 日志Entity
 * 
 * @author jeeplus
 * @version 2017-8-19
 */
public class Log extends DataEntity<Log> {

	private static final long serialVersionUID = 1L;
	private String sysTitle; // 日志标题
	private Date createDate; // 创建时间
	private String remoteAddr; // 操作IP地址
	private String requestUri;// 请求URI
	private String sysMethod; // 操作的方式
	private String sysParams; // 操作提交的数据
	private String exception; // 异常信息
	private String moduleName;// 平台名称
	private String operateName;// 操作页面名称
	private String operateType;// 操作功能名称
	private String groupId;// 用户组id
	private String userId;// 用户名id
	private String logDetail;// 日志明细
	private String operateNum;// 操作号码
	private Integer connId; // 关联ID
	private String successFlag;// 成功标记
	private String sysRemarks;// 备注
	private String oppositeIp;// 要发送的Ip
	private int backstagePort;// 要发送的IP端口
	private String backstageUser;// 要发送的IP用户
	
	private String tablename;//表名
	
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	private Date beginDate; // 开始日期
	private Date endDate; // 结束日期
	private String userName;// 用户名称
	private String staffId;// 工作人员id
	// 日志类型（1：接入日志；2：错误日志）
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";

	public Log() {
		super();
	}

	public Log(String id) {
		super(id);
	}

	public String getSysTitle() {
		return sysTitle;
	}

	public void setSysTitle(String sysTitle) {
		this.sysTitle = sysTitle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getSysMethod() {
		return sysMethod;
	}

	public void setSysMethod(String sysMethod) {
		this.sysMethod = sysMethod;
	}

	public String getSysParams() {
		return sysParams;
	}

	public void setSysParams(String sysParams) {
		this.sysParams = sysParams;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOperateNum() {
		return operateNum;
	}

	public void setOperateNum(String operateNum) {
		this.operateNum = operateNum;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String getSysRemarks() {
		return sysRemarks;
	}

	public void setSysRemarks(String sysRemarks) {
		this.sysRemarks = sysRemarks;
	}

	public String getLogDetail() {
		return logDetail;
	}

	public void setLogDetail(String logDetail) {
		this.logDetail = logDetail;
	}

	public Integer getConnId() {
		return connId;
	}

	public void setConnId(Integer connId) {
		this.connId = connId;
	}

	public String getOppositeIp() {
		return oppositeIp;
	}

	public void setOppositeIp(String oppositeIp) {
		this.oppositeIp = oppositeIp;
	}

	public int getBackstagePort() {
		return backstagePort;
	}

	public void setBackstagePort(int backstagePort) {
		this.backstagePort = backstagePort;
	}

	public String getBackstageUser() {
		return backstageUser;
	}

	public void setBackstageUser(String backstageUser) {
		this.backstageUser = backstageUser;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 设置请求参数
	 * 
	 * @param paramMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setParams(Map paramMap) {
		if (paramMap == null) {
			return;
		}
		StringBuilder params = new StringBuilder();
		for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
			params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue,
					100));
		}
		this.sysParams = params.toString();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}