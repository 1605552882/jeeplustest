/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.activityassess.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.entity.Office;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 日常活动能力评估Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdActivityassessLog extends DataEntity<EdActivityassessLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private String jinshi;		// 进食
	private String xizao;		// 洗澡
	private String gerenweisheng;		// 个人卫生
	private String chuangyi;		// 穿衣
	private String dabiankongzhi;		// 大便控制
	private String xiaobiankongzhi;		// 小便控制
	private String ruce;		// 如厕
	private String chuangyizhuanyi;		// 床椅转移
	private String buxing;		// 步行
	private String shangluolouti;		// 上落楼梯
	private Integer score;		// 总得分
	private String teshushiqingjilu;		// 特殊事项记录
	private String richanghuodongnenglifenji;		// 日常活动能力分级
	private User assessor;		// 评估人员
	private Office assessordept;		// 评估人员职位
	
	public EdActivityassessLog() {
		super();
	}

	public EdActivityassessLog(String id){
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
	
	@ExcelField(title="进食", dictType="jinshi", align=2, sort=9)
	public String getJinshi() {
		return jinshi;
	}

	public void setJinshi(String jinshi) {
		this.jinshi = jinshi;
	}
	
	@ExcelField(title="洗澡", dictType="xizao", align=2, sort=10)
	public String getXizao() {
		return xizao;
	}

	public void setXizao(String xizao) {
		this.xizao = xizao;
	}
	
	@ExcelField(title="个人卫生", dictType="gerenweisheng", align=2, sort=11)
	public String getGerenweisheng() {
		return gerenweisheng;
	}

	public void setGerenweisheng(String gerenweisheng) {
		this.gerenweisheng = gerenweisheng;
	}
	
	@ExcelField(title="穿衣", dictType="chuangyi", align=2, sort=12)
	public String getChuangyi() {
		return chuangyi;
	}

	public void setChuangyi(String chuangyi) {
		this.chuangyi = chuangyi;
	}
	
	@ExcelField(title="大便控制", dictType="dabiankongzhi", align=2, sort=13)
	public String getDabiankongzhi() {
		return dabiankongzhi;
	}

	public void setDabiankongzhi(String dabiankongzhi) {
		this.dabiankongzhi = dabiankongzhi;
	}
	
	@ExcelField(title="小便控制", dictType="xiaobiankongzhi", align=2, sort=14)
	public String getXiaobiankongzhi() {
		return xiaobiankongzhi;
	}

	public void setXiaobiankongzhi(String xiaobiankongzhi) {
		this.xiaobiankongzhi = xiaobiankongzhi;
	}
	
	@ExcelField(title="如厕", dictType="ruce", align=2, sort=15)
	public String getRuce() {
		return ruce;
	}

	public void setRuce(String ruce) {
		this.ruce = ruce;
	}
	
	@ExcelField(title="床椅转移", dictType="chuangyizhuangyi", align=2, sort=16)
	public String getChuangyizhuanyi() {
		return chuangyizhuanyi;
	}

	public void setChuangyizhuanyi(String chuangyizhuanyi) {
		this.chuangyizhuanyi = chuangyizhuanyi;
	}
	
	@ExcelField(title="步行", dictType="buxing", align=2, sort=17)
	public String getBuxing() {
		return buxing;
	}

	public void setBuxing(String buxing) {
		this.buxing = buxing;
	}
	
	@ExcelField(title="上落楼梯", dictType="shangluplouti", align=2, sort=18)
	public String getShangluolouti() {
		return shangluolouti;
	}

	public void setShangluolouti(String shangluolouti) {
		this.shangluolouti = shangluolouti;
	}
	
	@ExcelField(title="总得分", align=2, sort=19)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	@ExcelField(title="特殊事项记录", align=2, sort=20)
	public String getTeshushiqingjilu() {
		return teshushiqingjilu;
	}

	public void setTeshushiqingjilu(String teshushiqingjilu) {
		this.teshushiqingjilu = teshushiqingjilu;
	}
	
	@ExcelField(title="日常活动能力分级", dictType="richangshenghuonenglifenji", align=2, sort=21)
	public String getRichanghuodongnenglifenji() {
		return richanghuodongnenglifenji;
	}

	public void setRichanghuodongnenglifenji(String richanghuodongnenglifenji) {
		this.richanghuodongnenglifenji = richanghuodongnenglifenji;
	}
	
	@ExcelField(title="评估人员", fieldType=User.class, value="assessor.name", align=2, sort=22)
	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	
	@ExcelField(title="评估人员职位", fieldType=Office.class, value="assessordept.name", align=2, sort=23)
	public Office getAssessordept() {
		return assessordept;
	}

	public void setAssessordept(Office assessordept) {
		this.assessordept = assessordept;
	}
	
}