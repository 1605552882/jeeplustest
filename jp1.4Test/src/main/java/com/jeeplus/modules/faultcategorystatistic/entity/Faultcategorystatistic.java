/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.faultcategorystatistic.entity;

import java.util.Date;
import java.util.List;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 故障种类统计表Entity
 * @author lxy
 * @version 2019-10-15
 */
public class Faultcategorystatistic extends DataEntity<Faultcategorystatistic> {
	
	private static final long serialVersionUID = 1L;
	private String faultcategory;		// 故障种类
	private String city;		// 地市
	private List<String> useCity; //需要的工单来源
	private String times;		// 统计次数
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	private List<String> useFaultcategory;// 需要的故障种类
	private List<String> uselessFaultcategory;// 不需要的故障种类
	private String timeFlag;//时间 1按日 2按月
	private String contrastFlag;//对比 1环比
	
	private Date beginContrastDate;		// 开始 环比同比时间
	private Date endContrastDate;		// 结束 环比同比时间
	
	private String sStatus;//单据状态
	
	public List<String> getUseCity() {
		return useCity;
	}

	public void setUseCity(List<String> useCity) {
		this.useCity = useCity;
	}

	private String groupFlag;		//分组标识 1申告种类 2工单来源
	


	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public Faultcategorystatistic() {
		super();
	}

	public Faultcategorystatistic(String id){
		super(id);
	}

	@ExcelField(title="故障种类", align=2, sort=1)
	public String getFaultcategory() {
		return faultcategory;
	}

	public void setFaultcategory(String faultcategory) {
		this.faultcategory = faultcategory;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@ExcelField(title="统计次数", align=2, sort=2)
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
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
	
	public List<String> getUselessFaultcategory() {
		return uselessFaultcategory;
	}

	public void setUselessFaultcategory(List<String> uselessFaultcategory) {
		this.uselessFaultcategory = uselessFaultcategory;
	}
	
	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}
	
	public String getContrastFlag() {
		return contrastFlag;
	}

	public void setContrastFlag(String contrastFlag) {
		this.contrastFlag = contrastFlag;
	}

	public List<String> getUseFaultcategory() {
		return useFaultcategory;
	}

	public void setUseFaultcategory(List<String> useFaultcategory) {
		this.useFaultcategory = useFaultcategory;
	}

	public Date getBeginContrastDate() {
		return beginContrastDate;
	}

	public void setBeginContrastDate(Date beginContrastDate) {
		this.beginContrastDate = beginContrastDate;
	}

	public Date getEndContrastDate() {
		return endContrastDate;
	}

	public void setEndContrastDate(Date endContrastDate) {
		this.endContrastDate = endContrastDate;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
}