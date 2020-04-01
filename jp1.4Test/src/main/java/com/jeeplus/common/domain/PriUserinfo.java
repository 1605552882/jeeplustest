package com.jeeplus.common.domain;

import java.sql.Timestamp;

public class PriUserinfo {
	
	private String userId;
	
	private String userName;
	
	private String passwd;
	
	private Timestamp pwTime;
	
	private String primaryAccountFK;
	
	private Integer userGrp;
	
	private String dptId;
	
	private String department;
	
	private String mail;
	
	private String mobile;
	
	private String fixedPhone;
	
	private Integer crmFlag;
	
	private String priStr;
	
	private String menuRight;
	
	private String locRight;
	
	private String queryRegion;
	
	private String status;
	
	private String realPasswd;
	
	private SystemUserGroup smurgpFKjoin;
	
	private String pwResetFlag;
	
	private String a4_name;
	
	private String a4_card;
	
	private String a4_org_name;
	
	private String a4_mobile;
	
	public String getA4_mobile() {
		return a4_mobile;
	}

	public void setA4_mobile(String a4Mobile) {
		a4_mobile = a4Mobile;
	}

	public String getA4_name() {
		return a4_name;
	}

	public void setA4_name(String a4Name) {
		a4_name = a4Name;
	}

	public String getA4_card() {
		return a4_card;
	}

	public void setA4_card(String a4Card) {
		a4_card = a4Card;
	}

	public String getA4_org_name() {
		return a4_org_name;
	}

	public void setA4_org_name(String a4OrgName) {
		a4_org_name = a4OrgName;
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
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public Timestamp getPwTime() {
		return pwTime;
	}
	
	public void setPwTime(Timestamp pwTime) {
		this.pwTime = pwTime;
	}
	
	public String getPrimaryAccountFK() {
		return primaryAccountFK;
	}
	
	public void setPrimaryAccountFK(String primaryAccountFK) {
		this.primaryAccountFK = primaryAccountFK;
	}
	
	public Integer getUserGrp() {
		return userGrp;
	}
	
	public void setUserGrp(Integer userGrp) {
		this.userGrp = userGrp;
	}
	
	public String getDptId() {
		return dptId;
	}
	
	public void setDptId(String dptId) {
		this.dptId = dptId;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getFixedPhone() {
		return fixedPhone;
	}
	
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
	
	public Integer getCrmFlag() {
		return crmFlag;
	}
	
	public void setCrmFlag(Integer crmFlag) {
		this.crmFlag = crmFlag;
	}
	
	public String getPriStr() {
		return priStr;
	}
	
	public void setPriStr(String priStr) {
		this.priStr = priStr;
	}
	
	public String getMenuRight() {
		return menuRight;
	}
	
	public void setMenuRight(String menuRight) {
		this.menuRight = menuRight;
	}
	
	public String getLocRight() {
		return locRight;
	}
	
	public void setLocRight(String locRight) {
		this.locRight = locRight;
	}
	
	public String getQueryRegion() {
		return queryRegion;
	}
	
	public void setQueryRegion(String queryRegion) {
		this.queryRegion = queryRegion;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRealPasswd() {
		return realPasswd;
	}

	public void setRealPasswd(String realPasswd) {
		this.realPasswd = realPasswd;
	}

	public SystemUserGroup getSmurgpFKjoin() {
		return smurgpFKjoin;
	}
	
	public void setSmurgpFKjoin(SystemUserGroup smurgpFKjoin) {
		this.smurgpFKjoin = smurgpFKjoin;
	}
	
	public String getPwResetFlag() {
		return pwResetFlag;
	}

	public void setPwResetFlag(String pwResetFlag) {
		this.pwResetFlag = pwResetFlag;
	}

	public PriUserinfo() {}
	
}