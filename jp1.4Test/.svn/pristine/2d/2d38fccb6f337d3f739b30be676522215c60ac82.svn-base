/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.physiologyassess.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.entity.Office;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 生理状况评估Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdPhysiologyassessLog extends DataEntity<EdPhysiologyassessLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private Integer xueya;		// 血压(mmHg)
	private Integer maibo;		// 脉搏(次/min)
	private Integer huxi;		// 呼吸(次/min)
	private Double tiwen;		// 体温(℃)
	private Double tizzhong;		// 体重(千克)
	private Double shengao;		// 身高(米)
	private Integer tizhongzhibiao;		// 体重指标(BMI)
	private String shuimianzhuangkuang;		// 睡眠状况
	private String pifuzhuangkuang;		// 皮肤状况
	private String yinyangzhuangkuang;		// 营养状况
	private String tunyanqingkuang;		// 吞咽情况
	private String yinshiqingkuang;		// 饮食情况
	private String jujueqingkuang;		// 咀嚼情况
	private String yachizhuangkuang;		// 牙齿状况
	private String xiaobian;		// 小便
	private String dabian;		// 大便
	private String xiyan;		// 吸烟
	private Integer xiyanshuliang;		// 吸烟数量
	private String yijiediaonianfen;		// 已戒掉年份
	private String yinjiu;		// 饮酒
	private String yinjiufenliang;		// 饮酒份量
	private String yijiejiunianfen;		// 已戒酒年份
	private String tongzheng;		// 痛症
	private String tongzhengweizhi;		// 痛症位置
	private User assessor;		// 评估人员
	private Office assessordept;		// 评估人员职位
	
	public EdPhysiologyassessLog() {
		super();
	}

	public EdPhysiologyassessLog(String id){
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
	
	@ExcelField(title="血压(mmHg)", align=2, sort=9)
	public Integer getXueya() {
		return xueya;
	}

	public void setXueya(Integer xueya) {
		this.xueya = xueya;
	}
	
	@ExcelField(title="脉搏(次/min)", align=2, sort=10)
	public Integer getMaibo() {
		return maibo;
	}

	public void setMaibo(Integer maibo) {
		this.maibo = maibo;
	}
	
	@ExcelField(title="呼吸(次/min)", align=2, sort=11)
	public Integer getHuxi() {
		return huxi;
	}

	public void setHuxi(Integer huxi) {
		this.huxi = huxi;
	}
	
	@ExcelField(title="体温(℃)", align=2, sort=12)
	public Double getTiwen() {
		return tiwen;
	}

	public void setTiwen(Double tiwen) {
		this.tiwen = tiwen;
	}
	
	@ExcelField(title="体重(千克)", align=2, sort=13)
	public Double getTizzhong() {
		return tizzhong;
	}

	public void setTizzhong(Double tizzhong) {
		this.tizzhong = tizzhong;
	}
	
	@ExcelField(title="身高(米)", align=2, sort=14)
	public Double getShengao() {
		return shengao;
	}

	public void setShengao(Double shengao) {
		this.shengao = shengao;
	}
	
	@ExcelField(title="体重指标(BMI)", align=2, sort=15)
	public Integer getTizhongzhibiao() {
		return tizhongzhibiao;
	}

	public void setTizhongzhibiao(Integer tizhongzhibiao) {
		this.tizhongzhibiao = tizhongzhibiao;
	}
	
	@ExcelField(title="睡眠状况", dictType="shuimianzhuangkuang", align=2, sort=16)
	public String getShuimianzhuangkuang() {
		return shuimianzhuangkuang;
	}

	public void setShuimianzhuangkuang(String shuimianzhuangkuang) {
		this.shuimianzhuangkuang = shuimianzhuangkuang;
	}
	
	@ExcelField(title="皮肤状况", dictType="pifuzhuangkuang", align=2, sort=17)
	public String getPifuzhuangkuang() {
		return pifuzhuangkuang;
	}

	public void setPifuzhuangkuang(String pifuzhuangkuang) {
		this.pifuzhuangkuang = pifuzhuangkuang;
	}
	
	@ExcelField(title="营养状况", dictType="yingyangzhuangkuang", align=2, sort=18)
	public String getYinyangzhuangkuang() {
		return yinyangzhuangkuang;
	}

	public void setYinyangzhuangkuang(String yinyangzhuangkuang) {
		this.yinyangzhuangkuang = yinyangzhuangkuang;
	}
	
	@ExcelField(title="吞咽情况", dictType="tunyanqingkuang", align=2, sort=19)
	public String getTunyanqingkuang() {
		return tunyanqingkuang;
	}

	public void setTunyanqingkuang(String tunyanqingkuang) {
		this.tunyanqingkuang = tunyanqingkuang;
	}
	
	@ExcelField(title="饮食情况", dictType="yinshi", align=2, sort=20)
	public String getYinshiqingkuang() {
		return yinshiqingkuang;
	}

	public void setYinshiqingkuang(String yinshiqingkuang) {
		this.yinshiqingkuang = yinshiqingkuang;
	}
	
	@ExcelField(title="咀嚼情况", dictType="jujueqingkuang", align=2, sort=21)
	public String getJujueqingkuang() {
		return jujueqingkuang;
	}

	public void setJujueqingkuang(String jujueqingkuang) {
		this.jujueqingkuang = jujueqingkuang;
	}
	
	@ExcelField(title="牙齿状况", dictType="yachizhuangkuang", align=2, sort=22)
	public String getYachizhuangkuang() {
		return yachizhuangkuang;
	}

	public void setYachizhuangkuang(String yachizhuangkuang) {
		this.yachizhuangkuang = yachizhuangkuang;
	}
	
	@ExcelField(title="小便", dictType="xiaobian", align=2, sort=23)
	public String getXiaobian() {
		return xiaobian;
	}

	public void setXiaobian(String xiaobian) {
		this.xiaobian = xiaobian;
	}
	
	@ExcelField(title="大便", dictType="dabian", align=2, sort=24)
	public String getDabian() {
		return dabian;
	}

	public void setDabian(String dabian) {
		this.dabian = dabian;
	}
	
	@ExcelField(title="吸烟", dictType="xiyan", align=2, sort=25)
	public String getXiyan() {
		return xiyan;
	}

	public void setXiyan(String xiyan) {
		this.xiyan = xiyan;
	}
	
	@ExcelField(title="吸烟数量", align=2, sort=26)
	public Integer getXiyanshuliang() {
		return xiyanshuliang;
	}

	public void setXiyanshuliang(Integer xiyanshuliang) {
		this.xiyanshuliang = xiyanshuliang;
	}
	
	@ExcelField(title="已戒掉年份", align=2, sort=27)
	public String getYijiediaonianfen() {
		return yijiediaonianfen;
	}

	public void setYijiediaonianfen(String yijiediaonianfen) {
		this.yijiediaonianfen = yijiediaonianfen;
	}
	
	@ExcelField(title="饮酒", dictType="yinjiu", align=2, sort=28)
	public String getYinjiu() {
		return yinjiu;
	}

	public void setYinjiu(String yinjiu) {
		this.yinjiu = yinjiu;
	}
	
	@ExcelField(title="饮酒份量", align=2, sort=29)
	public String getYinjiufenliang() {
		return yinjiufenliang;
	}

	public void setYinjiufenliang(String yinjiufenliang) {
		this.yinjiufenliang = yinjiufenliang;
	}
	
	@ExcelField(title="已戒酒年份", align=2, sort=30)
	public String getYijiejiunianfen() {
		return yijiejiunianfen;
	}

	public void setYijiejiunianfen(String yijiejiunianfen) {
		this.yijiejiunianfen = yijiejiunianfen;
	}
	
	@ExcelField(title="痛症", dictType="tongzheng", align=2, sort=31)
	public String getTongzheng() {
		return tongzheng;
	}

	public void setTongzheng(String tongzheng) {
		this.tongzheng = tongzheng;
	}
	
	@ExcelField(title="痛症位置", align=2, sort=32)
	public String getTongzhengweizhi() {
		return tongzhengweizhi;
	}

	public void setTongzhengweizhi(String tongzhengweizhi) {
		this.tongzhengweizhi = tongzhengweizhi;
	}
	
	@ExcelField(title="评估人员", fieldType=User.class, value="assessor.name", align=2, sort=33)
	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	
	@ExcelField(title="评估人员职位", fieldType=Office.class, value="assessordept.name", align=2, sort=34)
	public Office getAssessordept() {
		return assessordept;
	}

	public void setAssessordept(Office assessordept) {
		this.assessordept = assessordept;
	}
	
}