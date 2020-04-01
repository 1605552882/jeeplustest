/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mscid_config.entity.mscid_config;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * MSCID配置Entity
 * @author 姜森焱
 * @version 2020-02-09
 */
public class MscidConfig extends DataEntity<MscidConfig> {
	
	private static final long serialVersionUID = 1L;
	private String locSheng;		// 省份
	private String locCity;		// 城市
	private String spctype;		// 机型
	private String mscid;		// mscid
	private String mscip;		// mscip
	private String username;		// 用户名
	private String password;		// 密码
	private String path;		// 路径
	private String locflag;		// 本省标识
	private String mscflag;		// 新老网元标识
	
	public MscidConfig() {
		super();
	}

	@ExcelField(title="省份", align=2, sort=0)
	public String getLocSheng() {
		return locSheng;
	}

	public void setLocSheng(String locSheng) {
		this.locSheng = locSheng;
	}
	
	@ExcelField(title="城市", align=2, sort=1)
	public String getLocCity() {
		return locCity;
	}

	public void setLocCity(String locCity) {
		this.locCity = locCity;
	}
	
	@ExcelField(title="机型", align=2, sort=2)
	public String getSpctype() {
		return spctype;
	}

	public void setSpctype(String spctype) {
		this.spctype = spctype;
	}
	
	@ExcelField(title="mscid", align=2, sort=3)
	public String getMscid() {
		return mscid;
	}

	public void setMscid(String mscid) {
		this.mscid = mscid;
	}
	
	@ExcelField(title="mscip", align=2, sort=4)
	public String getMscip() {
		return mscip;
	}

	public void setMscip(String mscip) {
		this.mscip = mscip;
	}
	
	@ExcelField(title="用户名", align=2, sort=5)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@ExcelField(title="密码", align=2, sort=6)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@ExcelField(title="路径", align=2, sort=7)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@ExcelField(title="本省标识", align=2, sort=8)
	public String getLocflag() {
		return locflag;
	}

	public void setLocflag(String locflag) {
		this.locflag = locflag;
	}
	
	@ExcelField(title="新老网元标识", align=2, sort=9)
	public String getMscflag() {
		return mscflag;
	}

	public void setMscflag(String mscflag) {
		this.mscflag = mscflag;
	}
	
}