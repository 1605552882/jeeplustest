/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.interface_config.entity.interface_config;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 测试编码表Entity
 * @author 姜森焱
 * @version 2020-02-04
 */
public class InterfaceConfig extends DataEntity<InterfaceConfig> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String url;		// url
	private String requst;		// requst
	private String solu;		// solu
	
	public InterfaceConfig() {
		super();
	}

	public InterfaceConfig(String id){
		super(id);
	}

	@ExcelField(title="name", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="url", align=2, sort=2)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@ExcelField(title="requst", align=2, sort=3)
	public String getRequst() {
		return requst;
	}

	public void setRequst(String requst) {
		this.requst = requst;
	}
	
	@ExcelField(title="solu", align=2, sort=4)
	public String getSolu() {
		return solu;
	}

	public void setSolu(String solu) {
		this.solu = solu;
	}
	
}