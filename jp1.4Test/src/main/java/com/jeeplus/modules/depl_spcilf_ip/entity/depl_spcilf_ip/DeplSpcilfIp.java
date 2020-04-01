/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.depl_spcilf_ip.entity.depl_spcilf_ip;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 机器部署信息Entity
 * @author 姜森焱
 * @version 2020-03-04
 */
public class DeplSpcilfIp extends DataEntity<DeplSpcilfIp> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 机器名
	private String sys;		// 配置信息
	private String ip;		// 地址
	private String address;		// 物理位置
	private String useful;		// 用途
	
	public DeplSpcilfIp() {
		super();
	}

	public DeplSpcilfIp(String id){
		super(id);
	}

	@ExcelField(title="机器名", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="配置信息", dictType="", align=2, sort=2)
	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}
	
	@ExcelField(title="地址", align=2, sort=3)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@ExcelField(title="物理位置", align=2, sort=4)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@ExcelField(title="用途", align=2, sort=5)
	public String getUseful() {
		return useful;
	}

	public void setUseful(String useful) {
		this.useful = useful;
	}
	
}