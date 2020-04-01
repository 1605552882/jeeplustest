/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.hotword.entity;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 热词Entity
 * @author lxy
 * @version 2019-11-14
 */
public class Hotword extends DataEntity<Hotword> {
	
	private static final long serialVersionUID = 1L;
	private String hotword;		// 热词
	private String flag;		// 是否启用
	private String sub;			// 二级菜单
	
	public Hotword() {
		super();
	}

	public Hotword(String id){
		super(id);
	}

	@ExcelField(title="热词", align=2, sort=1)
	public String getHotword() {
		return hotword;
	}

	public void setHotword(String hotword) {
		this.hotword = hotword;
	}
	
	@ExcelField(title="是否启用", align=2, sort=3)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@ExcelField(title="二级菜单", align=2, sort=2)
	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}
}