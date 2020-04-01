/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.hlr.entity;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * hlr信息管理Entity
 * @author 钟晖
 * @version 2019-10-25
 */
public class HlrInfoFields extends DataEntity<HlrInfoFields> {
	
	
	/** 手机状态 */
	private String userStatus;		
	/** 无条件 */
	private String withoutCondition; 
	/** 遇忙 */
	private String whenBusy;
	/** 无应答 */
	private String noAnswer;
	/** 默认 */
	private String defaultCall;	
	/** 1X功能 */
	private String base1xFunction;
	/** 三方通话 */
	private String multiCall;
	/** 主叫限制：（0:其他 1:国际长途 2:国内长途 3:本地长途):不允许修改成其他） */
	private String callingPrivileges;
	/** 漫游 */
	private String roamingPrivileges;
	/** 短信功能授权 */
	private String smsFunction;
	/** 短信功能始呼 */
	private String smsOriginalCall;
	/** 短消息终呼 */
	private String smsAnswerCall;
	/** 来电显示1 */
	private String callDisplay1;
	/** 来电显示2 */
	private String callDisplay2;
	/** 彩铃功能 */
	private String brtFunction;
	/** 鉴权数据 */
	private String appreciation;
	/** 智能网数据 */
	private String netWork;
	/** 呼叫等待 */
	private String callWaiting;
	/** 呼叫保持 */
	private String callKeeping;
	/** 免打扰功能 */
	private String noDisturb;
	/** 呼叫限制 */
	private String callLimit;
	/** 欠费锁 */
	private String betChain;
	/** 申请锁 */
	private String applyChain;
	/** 被盗锁 */
	private String rabedChain;
	/** 复制锁 */
	private String copyChain;
	/** CFMN */
	private String cfmn;
	/** 无条件呼转 */
	private String withoutConditionNo;
	/** 遇忙呼转 */
	private String whenBusyNo;
	/** 无应答呼转 */
	private String noAnswerNo;
	/** 默认呼转 */
	private String defaultCallNo;
	/** IMSI或者手机号码 */
	private String searchResult;
	/** 地区 */
	private String area;
	/**
	 * 省
	 */
	private String province;
	/** 机型 */
	private String phoneType;
	
	/** ROAMTYPE */
	private String rruaType;
	
	/** RRUASTATUS */
	private String rruaStatus;
	
	/** UZID1 */
	private String uzid1;
	
	/** UZID2 */
	private String uzid2;
	
	/** UZID3 */
	private String uzid3;
	
	/** UZID4 */
	private String uzid4;
	
	/** UZID5 */
	private String uzid5;
	
	/** UZID6 */
	private String uzid6;
	
	private String volte;
	
	public String getMultiCall() {
		return multiCall;
	}

	public void setMultiCall(String multiCall) {
		this.multiCall = multiCall;
	}

	public String getCallingPrivileges() {
		return callingPrivileges;
	}

	public void setCallingPrivileges(String callingPrivileges) {
		this.callingPrivileges = callingPrivileges;
	}

	public String getRoamingPrivileges() {
		return roamingPrivileges;
	}

	public void setRoamingPrivileges(String roamingPrivileges) {
		this.roamingPrivileges = roamingPrivileges;
	}

	public String getSmsFunction() {
		return smsFunction;
	}

	public void setSmsFunction(String smsFunction) {
		this.smsFunction = smsFunction;
	}

	public String getSmsOriginalCall() {
		return smsOriginalCall;
	}

	public void setSmsOriginalCall(String smsOriginalCall) {
		this.smsOriginalCall = smsOriginalCall;
	}

	public String getSmsAnswerCall() {
		return smsAnswerCall;
	}

	public void setSmsAnswerCall(String smsAnswerCall) {
		this.smsAnswerCall = smsAnswerCall;
	}

	public String getCallDisplay1() {
		return callDisplay1;
	}

	public void setCallDisplay1(String callDisplay1) {
		this.callDisplay1 = callDisplay1;
	}

	public String getCallDisplay2() {
		return callDisplay2;
	}

	public void setCallDisplay2(String callDisplay2) {
		this.callDisplay2 = callDisplay2;
	}

	public String getBrtFunction() {
		return brtFunction;
	}

	public void setBrtFunction(String brtFunction) {
		this.brtFunction = brtFunction;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public String getNetWork() {
		return netWork;
	}

	public void setNetWork(String netWork) {
		this.netWork = netWork;
	}

	public String getCallWaiting() {
		return callWaiting;
	}

	public void setCallWaiting(String callWaiting) {
		this.callWaiting = callWaiting;
	}

	public String getCallKeeping() {
		return callKeeping;
	}

	public void setCallKeeping(String callKeeping) {
		this.callKeeping = callKeeping;
	}

	public String getNoDisturb() {
		return noDisturb;
	}

	public void setNoDisturb(String noDisturb) {
		this.noDisturb = noDisturb;
	}

	public String getCallLimit() {
		return callLimit;
	}

	public void setCallLimit(String callLimit) {
		this.callLimit = callLimit;
	}

	public String getBetChain() {
		return betChain;
	}

	public void setBetChain(String betChain) {
		this.betChain = betChain;
	}

	public String getApplyChain() {
		return applyChain;
	}

	public void setApplyChain(String applyChain) {
		this.applyChain = applyChain;
	}

	public String getRabedChain() {
		return rabedChain;
	}

	public void setRabedChain(String rabedChain) {
		this.rabedChain = rabedChain;
	}

	public String getCopyChain() {
		return copyChain;
	}

	public void setCopyChain(String copyChain) {
		this.copyChain = copyChain;
	}

	public String getCfmn() {
		return cfmn;
	}

	public void setCfmn(String cfmn) {
		this.cfmn = cfmn;
	}

	public String getWithoutConditionNo() {
		return withoutConditionNo;
	}

	public void setWithoutConditionNo(String withoutConditionNo) {
		this.withoutConditionNo = withoutConditionNo;
	}

	public String getWhenBusyNo() {
		return whenBusyNo;
	}

	public void setWhenBusyNo(String whenBusyNo) {
		this.whenBusyNo = whenBusyNo;
	}

	public String getNoAnswerNo() {
		return noAnswerNo;
	}

	public void setNoAnswerNo(String noAnswerNo) {
		this.noAnswerNo = noAnswerNo;
	}

	public String getDefaultCallNo() {
		return defaultCallNo;
	}

	public void setDefaultCallNo(String defaultCallNo) {
		this.defaultCallNo = defaultCallNo;
	}

	public String getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getRruaType() {
		return rruaType;
	}

	public void setRruaType(String rruaType) {
		this.rruaType = rruaType;
	}

	public String getRruaStatus() {
		return rruaStatus;
	}

	public void setRruaStatus(String rruaStatus) {
		this.rruaStatus = rruaStatus;
	}

	public String getUzid1() {
		return uzid1;
	}

	public void setUzid1(String uzid1) {
		this.uzid1 = uzid1;
	}

	public String getUzid2() {
		return uzid2;
	}

	public void setUzid2(String uzid2) {
		this.uzid2 = uzid2;
	}

	public String getUzid3() {
		return uzid3;
	}

	public void setUzid3(String uzid3) {
		this.uzid3 = uzid3;
	}

	public String getUzid4() {
		return uzid4;
	}

	public void setUzid4(String uzid4) {
		this.uzid4 = uzid4;
	}

	public String getUzid5() {
		return uzid5;
	}

	public void setUzid5(String uzid5) {
		this.uzid5 = uzid5;
	}

	public String getUzid6() {
		return uzid6;
	}

	public void setUzid6(String uzid6) {
		this.uzid6 = uzid6;
	}

	public String getVolte() {
		return volte;
	}

	public void setVolte(String volte) {
		this.volte = volte;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	/** 程控位(该字段必须在最后一位，否则解码时出错) */
	private String functionCode;
	
	public HlrInfoFields() {
		super();
	}

	public HlrInfoFields(String id){
		super(id);
	}

	@ExcelField(title="手机状态", align=2, sort=7)
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	@ExcelField(title="无条件", align=2, sort=8)
	public String getWithoutCondition() {
		return withoutCondition;
	}

	public void setWithoutCondition(String withoutCondition) {
		this.withoutCondition = withoutCondition;
	}
	
	@ExcelField(title="遇忙", align=2, sort=9)
	public String getWhenBusy() {
		return whenBusy;
	}

	public void setWhenBusy(String whenBusy) {
		this.whenBusy = whenBusy;
	}
	
	@ExcelField(title="无应答", align=2, sort=10)
	public String getNoAnswer() {
		return noAnswer;
	}

	public void setNoAnswer(String noAnswer) {
		this.noAnswer = noAnswer;
	}
	
	@ExcelField(title="默认", align=2, sort=11)
	public String getDefaultCall() {
		return defaultCall;
	}

	public void setDefaultCall(String defaultCall) {
		this.defaultCall = defaultCall;
	}
	
	@ExcelField(title="1X功能", align=2, sort=12)
	public String getBase1xFunction() {
		return base1xFunction;
	}

	public void setBase1xFunction(String base1xFunction) {
		this.base1xFunction = base1xFunction;
	}
	
}