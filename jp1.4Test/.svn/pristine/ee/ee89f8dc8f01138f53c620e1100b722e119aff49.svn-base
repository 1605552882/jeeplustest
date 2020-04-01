/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.basis_coding_details.entity.basis_coding_details;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 编码明细表Entity
 * @author 姜森焱
 * @version 2020-01-15
 */
public class BasisCodingDetails extends DataEntity<BasisCodingDetails> {
	
	private static final long serialVersionUID = 1L;
	private String coding;		// 编码
	private String typeFk;		// 对应编码表的type_coding
	private String chineseDescription;		// 中文描述
	private String englishDescription;		// 英文描述
	private String characterValue;		// 字符值
	private String floatValue;		// 浮点值
	private String intValue;		// 整型值
	private String isdefault;		// 是否默认值（Y：是；N：否）
	private String notes;		// 备注
	private String status;		// 状态（N:正常;C:禁用）
	
	public BasisCodingDetails() {
		super();
	}

	public BasisCodingDetails(String id){
		super(id);
	}

	@ExcelField(title="编码", align=2, sort=1)
	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}
	
	@ExcelField(title="对应编码表的type_coding", align=2, sort=2)
	public String getTypeFk() {
		return typeFk;
	}

	public void setTypeFk(String typeFk) {
		this.typeFk = typeFk;
	}
	
	@ExcelField(title="中文描述", align=2, sort=3)
	public String getChineseDescription() {
		return chineseDescription;
	}

	public void setChineseDescription(String chineseDescription) {
		this.chineseDescription = chineseDescription;
	}
	
	@ExcelField(title="英文描述", align=2, sort=4)
	public String getEnglishDescription() {
		return englishDescription;
	}

	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}
	
	@ExcelField(title="字符值", align=2, sort=5)
	public String getCharacterValue() {
		return characterValue;
	}

	public void setCharacterValue(String characterValue) {
		this.characterValue = characterValue;
	}
	
	@ExcelField(title="浮点值", align=2, sort=6)
	public String getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(String floatValue) {
		this.floatValue = floatValue;
	}
	
	@ExcelField(title="整型值", align=2, sort=7)
	public String getIntValue() {
		return intValue;
	}

	public void setIntValue(String intValue) {
		this.intValue = intValue;
	}
	
	@ExcelField(title="是否默认值（Y：是；N：否）", align=2, sort=8)
	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	
	@ExcelField(title="备注", align=2, sort=9)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@ExcelField(title="状态（N:正常;C:禁用）", align=2, sort=10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}