/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.serv_project_label.entity.serv_project_label;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 智能预处理配置Entity
 * @author 姜森焱
 * @version 2020-02-20
 */
public class ServProjectLabel extends DataEntity<ServProjectLabel> {
	
	private static final long serialVersionUID = 1L;
	private String projectName;		// 项目名
	private String serv;		// 业务名称
	
	private String serv1;		// 原先业务名称
	private String cspErrMessages;		// CSP错误信息
	private String cspRigMessages;		// csp_rig_messages
	private String cm10000ErrMessages;		// CM10000错误信息
	private String cm10000RigMessages;		// CM10000正确信息
	private String advice;		// 处理建议
	private String reponse;		// 答复口径
	private String group;
	
	private String isinfo;//判断第一个表格是否是修改
	
	
	public String getServ1() {
		return serv1;
	}

	public void setServ1(String serv1) {
		this.serv1 = serv1;
	}

	public String getIsinfo() {
		return isinfo;
	}

	public void setIsinfo(String isinfo) {
		this.isinfo = isinfo;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public ServProjectLabel() {
		super();
	}

	public ServProjectLabel(String id){
		super(id);
	}

	@ExcelField(title="项目名", align=2, sort=1)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@ExcelField(title="业务名称", align=2, sort=2)
	public String getServ() {
		return serv;
	}

	public void setServ(String serv) {
		this.serv = serv;
	}
	
	@ExcelField(title="CSP错误信息", align=2, sort=3)
	public String getCspErrMessages() {
		return cspErrMessages;
	}

	public void setCspErrMessages(String cspErrMessages) {
		this.cspErrMessages = cspErrMessages;
	}
	
	@ExcelField(title="csp_rig_messages", align=2, sort=4)
	public String getCspRigMessages() {
		return cspRigMessages;
	}

	public void setCspRigMessages(String cspRigMessages) {
		this.cspRigMessages = cspRigMessages;
	}
	
	@ExcelField(title="CM10000错误信息", align=2, sort=5)
	public String getCm10000ErrMessages() {
		return cm10000ErrMessages;
	}

	public void setCm10000ErrMessages(String cm10000ErrMessages) {
		this.cm10000ErrMessages = cm10000ErrMessages;
	}
	
	@ExcelField(title="CM10000正确信息", align=2, sort=6)
	public String getCm10000RigMessages() {
		return cm10000RigMessages;
	}

	public void setCm10000RigMessages(String cm10000RigMessages) {
		this.cm10000RigMessages = cm10000RigMessages;
	}
	
	@ExcelField(title="处理建议", align=2, sort=7)
	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	@ExcelField(title="答复口径", align=2, sort=8)
	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
}