/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.morsefall.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 跌倒风险评估Entity
 * @author lukbob
 * @version 2018-11-13
 */
public class EdMorsefallLog extends DataEntity<EdMorsefallLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private String cengyoushuaidaijilu;		// 曾有摔倒记录
	private String duoyuyixiangyiliaozhengduan;		// 多于一项医疗诊断
	private String buxingfuzhuqi;		// 步行辅助器
	private String jingmaizhushe;		// 静脉注射治疗
	private String butai;		// 步态
	private String jingshenzhuangtai;		// 精神状态
	private Integer score;		// 总分(指标分数为55)
	
	public EdMorsefallLog() {
		super();
	}

	public EdMorsefallLog(String id){
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
	
	@ExcelField(title="曾有摔倒记录", dictType="shuaidaojilu", align=2, sort=9)
	public String getCengyoushuaidaijilu() {
		return cengyoushuaidaijilu;
	}

	public void setCengyoushuaidaijilu(String cengyoushuaidaijilu) {
		this.cengyoushuaidaijilu = cengyoushuaidaijilu;
	}
	
	@ExcelField(title="多于一项医疗诊断", dictType="yiliaozhenduan", align=2, sort=10)
	public String getDuoyuyixiangyiliaozhengduan() {
		return duoyuyixiangyiliaozhengduan;
	}

	public void setDuoyuyixiangyiliaozhengduan(String duoyuyixiangyiliaozhengduan) {
		this.duoyuyixiangyiliaozhengduan = duoyuyixiangyiliaozhengduan;
	}
	
	@ExcelField(title="步行辅助器", dictType="buxingfuzhuqi", align=2, sort=11)
	public String getBuxingfuzhuqi() {
		return buxingfuzhuqi;
	}

	public void setBuxingfuzhuqi(String buxingfuzhuqi) {
		this.buxingfuzhuqi = buxingfuzhuqi;
	}
	
	@ExcelField(title="静脉注射治疗", dictType="jingpaizhushe", align=2, sort=12)
	public String getJingmaizhushe() {
		return jingmaizhushe;
	}

	public void setJingmaizhushe(String jingmaizhushe) {
		this.jingmaizhushe = jingmaizhushe;
	}
	
	@ExcelField(title="步态", dictType="butai", align=2, sort=13)
	public String getButai() {
		return butai;
	}

	public void setButai(String butai) {
		this.butai = butai;
	}
	
	@ExcelField(title="精神状态", dictType="jingshengzhuangtai", align=2, sort=14)
	public String getJingshenzhuangtai() {
		return jingshenzhuangtai;
	}

	public void setJingshenzhuangtai(String jingshenzhuangtai) {
		this.jingshenzhuangtai = jingshenzhuangtai;
	}
	
	@ExcelField(title="总分(指标分数为55)", align=2, sort=15)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
}