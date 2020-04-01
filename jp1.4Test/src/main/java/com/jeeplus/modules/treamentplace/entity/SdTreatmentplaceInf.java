/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treamentplace.entity;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 治疗地点Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class SdTreatmentplaceInf extends DataEntity<SdTreatmentplaceInf> {
	
	private static final long serialVersionUID = 1L;
	private String placename;		// 地点名称
	private String location;		// location
	
	public SdTreatmentplaceInf() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SdTreatmentplaceInf(String id){
		super(id);
	}

	@Length(min=2, max=50, message="地点名称长度必须介于 2 和 50 之间")
	@ExcelField(title="地点名称", align=2, sort=1)
	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}
	
	@ExcelField(title="location", align=2, sort=2)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}