/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.hlr_config.entity.hlr_config;

import com.jeeplus.modules.sys.entity.Area;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * HLR配置表Entity
 * @author 姜森焱
 * @version 2019-12-05
 */
public class HlrConfig extends DataEntity<HlrConfig> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// code
	private String ip;		// IP地址
	private String port;		// 端口
	private String spctype;		// 机型
	private String locname;		// 地区标识
	private String hlrname;		// hlr名称
	private String userid;		// 用户名（页面输入）
	private String cipher;		// 密码
	private Area area;		// 程序中硬编码为0
	private String numHead;		// 手机号码头
	private String imsiHead;		// IMSI号码头
	private String flag;		// 在用标识（1：在用；0：未用）
	
	public HlrConfig() {
		super();
	}

	public HlrConfig(String id){
		super(id);
	}

	@ExcelField(title="code", align=2, sort=1)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@ExcelField(title="IP地址", align=2, sort=2)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@ExcelField(title="端口", align=2, sort=3)
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	@ExcelField(title="机型", align=2, sort=4)
	public String getSpctype() {
		return spctype;
	}

	public void setSpctype(String spctype) {
		this.spctype = spctype;
	}
	
	@ExcelField(title="地区标识", align=2, sort=5)
	public String getLocname() {
		return locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}
	
	@ExcelField(title="hlr名称", align=2, sort=6)
	public String getHlrname() {
		return hlrname;
	}

	public void setHlrname(String hlrname) {
		this.hlrname = hlrname;
	}
	
	@ExcelField(title="用户名（页面输入）", align=2, sort=7)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@ExcelField(title="密码", align=2, sort=8)
	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}
	
	@ExcelField(title="程序中硬编码为0", fieldType=Area.class, value="area.name", align=2, sort=9)
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@ExcelField(title="手机号码头", align=2, sort=10)
	public String getNumHead() {
		return numHead;
	}

	public void setNumHead(String numHead) {
		this.numHead = numHead;
	}
	
	@ExcelField(title="IMSI号码头", align=2, sort=11)
	public String getImsiHead() {
		return imsiHead;
	}

	public void setImsiHead(String imsiHead) {
		this.imsiHead = imsiHead;
	}
	
	@ExcelField(title="在用标识（1：在用；0：未用）", align=2, sort=12)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}