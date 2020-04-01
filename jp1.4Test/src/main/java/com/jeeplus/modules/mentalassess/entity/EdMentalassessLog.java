/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mentalassess.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.entity.Office;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 心理社交评估Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdMentalassessLog extends DataEntity<EdMentalassessLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private String shifoujieshouwenda;		// 是否接受问答
	private String weinenghuidayuanyin;		// 未能/拒绝回答原因
	private String zhunagezhen;		// 你住紧嘅地方系属于顺德边一个镇/街?
	private String juzhudidaan;		// 住居地答案
	private String zhunatiaolu;		// 你住系边一条路/小区?
	private String juzhuludaan;		// 路/小区答案
	private String jintianjihao;		// 今日系几号?
	private String yuefen;		// 系几月份?
	private String nianfen;		// 系乜嘢年份?
	private String yinianjitian;		// 一年有几多日?
	private String jianguonianfen;		// 新中国系边一年建国?
	private String shuxueti;		// 你有20元，用咗3元，还有多少?再多3元，还有多少?
	private String currentpresidentname;		// 中国现任中国国家主席系边个?
	private String lastpresidentname;		// 中国上一中国国家主席叫乜嘢名?
	private Integer renzhinenglidefen;		// 认知能力得分
	private String zhiliqingkuang;		// 智力情况
	private String yishi;		// 意识
	private String ganzhi;		// 感知
	private String ganzhineirong;		// 感知内容
	private String qingxu;		// 情绪
	private String qitaqingxu;		// 其他情绪
	private String xingwei;		// 行为
	private String qitaxingwei;		// 其他行为
	private String taidu;		// 态度
	private String qitataidu;		// 其他态度
	private String siwei;		// 思维
	private String qitasiwei;		// 其他思维
	private String jiarenbaozhengrendaofang;		// 家人/保证人到访
	private String qinqidaofang;		// 亲戚到访
	private String pengyoudaofang;		// 朋友到访
	private String yujiarenguanxi;		// 与家人关系
	private String yuyuanyouguanxi;		// 与院友关系
	private String yuyuangongguanxi;		// 与员工关系
	private String qitabuchongshuju;		// 其他补充数据
	private User assessor;		// 评估人员
	private Office assessordept;		// 评估人员职位
	
	public EdMentalassessLog() {
		super();
	}

	public EdMentalassessLog(String id){
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
	
	@ExcelField(title="是否接受问答", dictType="shifouhuida", align=2, sort=9)
	public String getShifoujieshouwenda() {
		return shifoujieshouwenda;
	}

	public void setShifoujieshouwenda(String shifoujieshouwenda) {
		this.shifoujieshouwenda = shifoujieshouwenda;
	}
	
	@ExcelField(title="未能/拒绝回答原因", align=2, sort=10)
	public String getWeinenghuidayuanyin() {
		return weinenghuidayuanyin;
	}

	public void setWeinenghuidayuanyin(String weinenghuidayuanyin) {
		this.weinenghuidayuanyin = weinenghuidayuanyin;
	}
	
	@ExcelField(title="你住紧嘅地方系属于顺德边一个镇/街?", dictType="right_or_wrong", align=2, sort=11)
	public String getZhunagezhen() {
		return zhunagezhen;
	}

	public void setZhunagezhen(String zhunagezhen) {
		this.zhunagezhen = zhunagezhen;
	}
	
	@ExcelField(title="住居地答案", align=2, sort=12)
	public String getJuzhudidaan() {
		return juzhudidaan;
	}

	public void setJuzhudidaan(String juzhudidaan) {
		this.juzhudidaan = juzhudidaan;
	}
	
	@ExcelField(title="你住系边一条路/小区?", dictType="right_or_wrong", align=2, sort=13)
	public String getZhunatiaolu() {
		return zhunatiaolu;
	}

	public void setZhunatiaolu(String zhunatiaolu) {
		this.zhunatiaolu = zhunatiaolu;
	}
	
	@ExcelField(title="路/小区答案", align=2, sort=14)
	public String getJuzhuludaan() {
		return juzhuludaan;
	}

	public void setJuzhuludaan(String juzhuludaan) {
		this.juzhuludaan = juzhuludaan;
	}
	
	@ExcelField(title="今日系几号?", dictType="right_or_wrong", align=2, sort=15)
	public String getJintianjihao() {
		return jintianjihao;
	}

	public void setJintianjihao(String jintianjihao) {
		this.jintianjihao = jintianjihao;
	}
	
	@ExcelField(title="系几月份?", dictType="right_or_wrong", align=2, sort=16)
	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}
	
	@ExcelField(title="系乜嘢年份?", dictType="right_or_wrong", align=2, sort=17)
	public String getNianfen() {
		return nianfen;
	}

	public void setNianfen(String nianfen) {
		this.nianfen = nianfen;
	}
	
	@ExcelField(title="一年有几多日?", dictType="right_or_wrong", align=2, sort=18)
	public String getYinianjitian() {
		return yinianjitian;
	}

	public void setYinianjitian(String yinianjitian) {
		this.yinianjitian = yinianjitian;
	}
	
	@ExcelField(title="新中国系边一年建国?", dictType="right_or_wrong", align=2, sort=19)
	public String getJianguonianfen() {
		return jianguonianfen;
	}

	public void setJianguonianfen(String jianguonianfen) {
		this.jianguonianfen = jianguonianfen;
	}
	
	@ExcelField(title="你有20元，用咗3元，还有多少?再多3元，还有多少?", dictType="right_or_wrong", align=2, sort=20)
	public String getShuxueti() {
		return shuxueti;
	}

	public void setShuxueti(String shuxueti) {
		this.shuxueti = shuxueti;
	}
	
	@ExcelField(title="中国现任中国国家主席系边个?", dictType="right_or_wrong", align=2, sort=21)
	public String getCurrentpresidentname() {
		return currentpresidentname;
	}

	public void setCurrentpresidentname(String currentpresidentname) {
		this.currentpresidentname = currentpresidentname;
	}
	
	@ExcelField(title="中国上一中国国家主席叫乜嘢名?", dictType="right_or_wrong", align=2, sort=22)
	public String getLastpresidentname() {
		return lastpresidentname;
	}

	public void setLastpresidentname(String lastpresidentname) {
		this.lastpresidentname = lastpresidentname;
	}
	
	@ExcelField(title="认知能力得分", align=2, sort=23)
	public Integer getRenzhinenglidefen() {
		return renzhinenglidefen;
	}

	public void setRenzhinenglidefen(Integer renzhinenglidefen) {
		this.renzhinenglidefen = renzhinenglidefen;
	}
	
	@ExcelField(title="智力情况", dictType="zhiliqingkuang", align=2, sort=24)
	public String getZhiliqingkuang() {
		return zhiliqingkuang;
	}

	public void setZhiliqingkuang(String zhiliqingkuang) {
		this.zhiliqingkuang = zhiliqingkuang;
	}
	
	@ExcelField(title="意识", dictType="yishi", align=2, sort=25)
	public String getYishi() {
		return yishi;
	}

	public void setYishi(String yishi) {
		this.yishi = yishi;
	}
	
	@ExcelField(title="感知", dictType="ganzhi", align=2, sort=26)
	public String getGanzhi() {
		return ganzhi;
	}

	public void setGanzhi(String ganzhi) {
		this.ganzhi = ganzhi;
	}
	
	@ExcelField(title="感知内容", align=2, sort=27)
	public String getGanzhineirong() {
		return ganzhineirong;
	}

	public void setGanzhineirong(String ganzhineirong) {
		this.ganzhineirong = ganzhineirong;
	}
	
	@ExcelField(title="情绪", dictType="qingxu", align=2, sort=28)
	public String getQingxu() {
		return qingxu;
	}

	public void setQingxu(String qingxu) {
		this.qingxu = qingxu;
	}
	
	@ExcelField(title="其他情绪", align=2, sort=29)
	public String getQitaqingxu() {
		return qitaqingxu;
	}

	public void setQitaqingxu(String qitaqingxu) {
		this.qitaqingxu = qitaqingxu;
	}
	
	@ExcelField(title="行为", dictType="xingwei", align=2, sort=30)
	public String getXingwei() {
		return xingwei;
	}

	public void setXingwei(String xingwei) {
		this.xingwei = xingwei;
	}
	
	@ExcelField(title="其他行为", align=2, sort=31)
	public String getQitaxingwei() {
		return qitaxingwei;
	}

	public void setQitaxingwei(String qitaxingwei) {
		this.qitaxingwei = qitaxingwei;
	}
	
	@ExcelField(title="态度", dictType="taidu", align=2, sort=32)
	public String getTaidu() {
		return taidu;
	}

	public void setTaidu(String taidu) {
		this.taidu = taidu;
	}
	
	@ExcelField(title="其他态度", align=2, sort=33)
	public String getQitataidu() {
		return qitataidu;
	}

	public void setQitataidu(String qitataidu) {
		this.qitataidu = qitataidu;
	}
	
	@ExcelField(title="思维", dictType="siwei", align=2, sort=34)
	public String getSiwei() {
		return siwei;
	}

	public void setSiwei(String siwei) {
		this.siwei = siwei;
	}
	
	@ExcelField(title="其他思维", align=2, sort=35)
	public String getQitasiwei() {
		return qitasiwei;
	}

	public void setQitasiwei(String qitasiwei) {
		this.qitasiwei = qitasiwei;
	}
	
	@ExcelField(title="家人/保证人到访", dictType="daofang", align=2, sort=36)
	public String getJiarenbaozhengrendaofang() {
		return jiarenbaozhengrendaofang;
	}

	public void setJiarenbaozhengrendaofang(String jiarenbaozhengrendaofang) {
		this.jiarenbaozhengrendaofang = jiarenbaozhengrendaofang;
	}
	
	@ExcelField(title="亲戚到访", dictType="qinqidaofang", align=2, sort=37)
	public String getQinqidaofang() {
		return qinqidaofang;
	}

	public void setQinqidaofang(String qinqidaofang) {
		this.qinqidaofang = qinqidaofang;
	}
	
	@ExcelField(title="朋友到访", dictType="pengyoudaofang", align=2, sort=38)
	public String getPengyoudaofang() {
		return pengyoudaofang;
	}

	public void setPengyoudaofang(String pengyoudaofang) {
		this.pengyoudaofang = pengyoudaofang;
	}
	
	@ExcelField(title="与家人关系", dictType="jiarenguanxi", align=2, sort=39)
	public String getYujiarenguanxi() {
		return yujiarenguanxi;
	}

	public void setYujiarenguanxi(String yujiarenguanxi) {
		this.yujiarenguanxi = yujiarenguanxi;
	}
	
	@ExcelField(title="与院友关系", dictType="yuanyouguanxi", align=2, sort=40)
	public String getYuyuanyouguanxi() {
		return yuyuanyouguanxi;
	}

	public void setYuyuanyouguanxi(String yuyuanyouguanxi) {
		this.yuyuanyouguanxi = yuyuanyouguanxi;
	}
	
	@ExcelField(title="与员工关系", dictType="yuangongguanxi", align=2, sort=41)
	public String getYuyuangongguanxi() {
		return yuyuangongguanxi;
	}

	public void setYuyuangongguanxi(String yuyuangongguanxi) {
		this.yuyuangongguanxi = yuyuangongguanxi;
	}
	
	@ExcelField(title="其他补充数据", align=2, sort=42)
	public String getQitabuchongshuju() {
		return qitabuchongshuju;
	}

	public void setQitabuchongshuju(String qitabuchongshuju) {
		this.qitabuchongshuju = qitabuchongshuju;
	}
	
	@ExcelField(title="评估人员", fieldType=User.class, value="assessor.name", align=2, sort=43)
	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	
	@ExcelField(title="评估人员职位", fieldType=Office.class, value="assessordept.name", align=2, sort=44)
	public Office getAssessordept() {
		return assessordept;
	}

	public void setAssessordept(Office assessordept) {
		this.assessordept = assessordept;
	}
	
}