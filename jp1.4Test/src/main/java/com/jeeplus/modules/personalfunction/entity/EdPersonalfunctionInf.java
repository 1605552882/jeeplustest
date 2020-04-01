/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.personalfunction.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 个人功能评估Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdPersonalfunctionInf extends DataEntity<EdPersonalfunctionInf> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private String shili;		// 视力
	private String shilixufuzhuqi;		// 视力需辅助器
	private String tingli;		// 听力
	private String tinglixufuzhuqi;		// 听力需辅助器
	private String yuyanbiaoda;		// 语言表达
	private String lijienengli;		// 理解能力
	private String huodongnengli;		// 活动能力
	private String sanyueneidiedao;		// 三个月内跌倒
	private Integer sanyueneidiedaocishu;		// 三个月内摔倒次数
	
	public EdPersonalfunctionInf() {
		super();
	}

	public EdPersonalfunctionInf(String id){
		super(id);
	}

	@ExcelField(title="长者id", fieldType=EdElderInf.class, value="elderid.funame", align=2, sort=7)
	public EdElderInf getElderid() {
		return elderid;
	}

	public void setElderid(EdElderInf elderid) {
		this.elderid = elderid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="评估时间", align=2, sort=8)
	public Date getAssesstime() {
		return assesstime;
	}

	public void setAssesstime(Date assesstime) {
		this.assesstime = assesstime;
	}
	
	@ExcelField(title="视力", dictType="shili", align=2, sort=9)
	public String getShili() {
		return shili;
	}

	public void setShili(String shili) {
		this.shili = shili;
	}
	
	@ExcelField(title="视力需辅助器", align=2, sort=10)
	public String getShilixufuzhuqi() {
		return shilixufuzhuqi;
	}

	public void setShilixufuzhuqi(String shilixufuzhuqi) {
		this.shilixufuzhuqi = shilixufuzhuqi;
	}
	
	@ExcelField(title="听力", dictType="tingli", align=2, sort=11)
	public String getTingli() {
		return tingli;
	}

	public void setTingli(String tingli) {
		this.tingli = tingli;
	}
	
	@ExcelField(title="听力需辅助器", align=2, sort=12)
	public String getTinglixufuzhuqi() {
		return tinglixufuzhuqi;
	}

	public void setTinglixufuzhuqi(String tinglixufuzhuqi) {
		this.tinglixufuzhuqi = tinglixufuzhuqi;
	}
	
	@ExcelField(title="语言表达", dictType="yuyanbiaoda", align=2, sort=13)
	public String getYuyanbiaoda() {
		return yuyanbiaoda;
	}

	public void setYuyanbiaoda(String yuyanbiaoda) {
		this.yuyanbiaoda = yuyanbiaoda;
	}
	
	@ExcelField(title="理解能力", dictType="lijienengli", align=2, sort=14)
	public String getLijienengli() {
		return lijienengli;
	}

	public void setLijienengli(String lijienengli) {
		this.lijienengli = lijienengli;
	}
	
	@ExcelField(title="活动能力", dictType="huodongnengli", align=2, sort=15)
	public String getHuodongnengli() {
		return huodongnengli;
	}

	public void setHuodongnengli(String huodongnengli) {
		this.huodongnengli = huodongnengli;
	}
	
	@ExcelField(title="三个月内跌倒", dictType="sanyueneishuaidao", align=2, sort=16)
	public String getSanyueneidiedao() {
		return sanyueneidiedao;
	}

	public void setSanyueneidiedao(String sanyueneidiedao) {
		this.sanyueneidiedao = sanyueneidiedao;
	}
	
	@ExcelField(title="三个月内摔倒次数", align=2, sort=17)
	public Integer getSanyueneidiedaocishu() {
		return sanyueneidiedaocishu;
	}

	public void setSanyueneidiedaocishu(Integer sanyueneidiedaocishu) {
		this.sanyueneidiedaocishu = sanyueneidiedaocishu;
	}
	
}