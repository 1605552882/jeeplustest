/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.elder.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 长者基本资料管理Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdElderInf extends DataEntity<EdElderInf> {
	
	private static final long serialVersionUID = 1L;
	private String funame;		// 姓名
	private String sex;		// 性别
	private String nianling;		// 年龄
	private Date chushengriqi;		// 出生日期
	private Date ruzhuriqi;		// 入住日期
	private String hunyinzhuangkuang;		// 婚姻状况
	private String hunyinzhuangkuangqita;		// 婚姻状况其他
	private String richangjiaoliuyuyan;		// 日常交流语言
	private String richangjiaoliuyuyanqita;		// 日常交流语言其他
	private String jiaoyuchengdu;		// 教育程度
	private String jiaoyuchengduqita;		// 教育程度其他
	private String xianjuzhudi;		// 现居住地
	private String tongjurenyuan;		// 同居人员
	private String tuixiuqianzhiye;		// 退休前职业
	private String zongjiao;		// 宗教
	private String zongjiaoqita;		// 宗教其他
	private String lianxiren1;		// 联系人1
	private String lianxiren1guanxi;		// 联系人1关系
	private String lianxirendianhua;		// 联系人1电话
	private String lianxiren1dizhi;		// 联系人1地址
	private String lianxiren2;		// 联系人2
	private String lianxiren2guanxi;		// 联系人2关系
	private String lianxiren2dianhua;		// 联系人2电话
	private String lianxiren2dizhi;		// 联系人2地址
	private String meiyueshourlaiyuan;		// 每月收入来源
	private Double tuixiujin;		// 退休金¥
	private Double qinyouzizhu;		// 亲友资助¥
	private Double wubaohu;		// 五保户¥
	private Double dibaohu;		// 低保户¥
	private Double qitazhengfuzizhu;		// 其他政府资助¥
	private Double qitazizhu;		// 其他资助¥
	private Double zongjine;		// 总金额¥
	private String qitashuju;		// 其他数据
	private String yiliaofeiyongzhifufangshi;		// 医疗费用支付方式
	private String yiliaofeiyongzhifufangshiqita;		// 医疗费用支付方式其他
	private String xqkandianshi;		// 兴趣-看电视
	private String xqtingshouyinji;		// 兴趣-听收音机
	private String xqyuebaoyuedu;		// 兴趣-阅报阅读
	private String xqzuoyundong;		// 兴趣-做运动
	private String shenghuoxiguan;		// 生活习惯
	private String bingli;		// 病历
	private String guanjieyanweizhi;		// 关节炎位置
	private String aizhengweizhi;		// 癌症位置
	private String jingshenbing;		// 精神病
	private String qitaneirong;		// 其他内容
	private String cengshishoushu;		// 曾施手术内容
	private String shiwumingan;		// 食物敏感
	private String yaowumingan;		// 药物敏感
	private String shiwuminganneirong;		// 食物敏感内容
	private String yaowuminganneirong;		// 药物敏感内容
	private String yuzhangzheguanxi;		// 与长者关系
	private String zhangzhehuojiashu;		// 长者/家属
	private Date riqi;		// 日期
	private List<EdFamilyInf> edFamilyInfList = Lists.newArrayList();		// 子表列表
	
	public EdElderInf() {
		super();
	}

	public EdElderInf(String id){
		super(id);
	}

	@ExcelField(title="姓名", align=2, sort=7)
	public String getFuname() {
		return funame;
	}

	public void setFuname(String funame) {
		this.funame = funame;
	}
	
	@ExcelField(title="性别", dictType="male_or_femal", align=2, sort=8)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@ExcelField(title="年龄", align=2, sort=9)
	public String getNianling() {
		return nianling;
	}

	public void setNianling(String nianling) {
		this.nianling = nianling;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="出生日期", align=2, sort=10)
	public Date getChushengriqi() {
		return chushengriqi;
	}

	public void setChushengriqi(Date chushengriqi) {
		this.chushengriqi = chushengriqi;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="入住日期", align=2, sort=11)
	public Date getRuzhuriqi() {
		return ruzhuriqi;
	}

	public void setRuzhuriqi(Date ruzhuriqi) {
		this.ruzhuriqi = ruzhuriqi;
	}
	
	@ExcelField(title="婚姻状况", dictType="marrige_state", align=2, sort=12)
	public String getHunyinzhuangkuang() {
		return hunyinzhuangkuang;
	}

	public void setHunyinzhuangkuang(String hunyinzhuangkuang) {
		this.hunyinzhuangkuang = hunyinzhuangkuang;
	}
	
	@ExcelField(title="婚姻状况其他", align=2, sort=13)
	public String getHunyinzhuangkuangqita() {
		return hunyinzhuangkuangqita;
	}

	public void setHunyinzhuangkuangqita(String hunyinzhuangkuangqita) {
		this.hunyinzhuangkuangqita = hunyinzhuangkuangqita;
	}
	
	@ExcelField(title="日常交流语言", dictType="speak_language", align=2, sort=14)
	public String getRichangjiaoliuyuyan() {
		return richangjiaoliuyuyan;
	}

	public void setRichangjiaoliuyuyan(String richangjiaoliuyuyan) {
		this.richangjiaoliuyuyan = richangjiaoliuyuyan;
	}
	
	@ExcelField(title="日常交流语言其他", align=2, sort=15)
	public String getRichangjiaoliuyuyanqita() {
		return richangjiaoliuyuyanqita;
	}

	public void setRichangjiaoliuyuyanqita(String richangjiaoliuyuyanqita) {
		this.richangjiaoliuyuyanqita = richangjiaoliuyuyanqita;
	}
	
	@ExcelField(title="教育程度", dictType="educational_state", align=2, sort=16)
	public String getJiaoyuchengdu() {
		return jiaoyuchengdu;
	}

	public void setJiaoyuchengdu(String jiaoyuchengdu) {
		this.jiaoyuchengdu = jiaoyuchengdu;
	}
	
	@ExcelField(title="教育程度其他", align=2, sort=17)
	public String getJiaoyuchengduqita() {
		return jiaoyuchengduqita;
	}

	public void setJiaoyuchengduqita(String jiaoyuchengduqita) {
		this.jiaoyuchengduqita = jiaoyuchengduqita;
	}
	
	@ExcelField(title="现居住地", align=2, sort=18)
	public String getXianjuzhudi() {
		return xianjuzhudi;
	}

	public void setXianjuzhudi(String xianjuzhudi) {
		this.xianjuzhudi = xianjuzhudi;
	}
	
	@ExcelField(title="同居人员", align=2, sort=19)
	public String getTongjurenyuan() {
		return tongjurenyuan;
	}

	public void setTongjurenyuan(String tongjurenyuan) {
		this.tongjurenyuan = tongjurenyuan;
	}
	
	@ExcelField(title="退休前职业", align=2, sort=20)
	public String getTuixiuqianzhiye() {
		return tuixiuqianzhiye;
	}

	public void setTuixiuqianzhiye(String tuixiuqianzhiye) {
		this.tuixiuqianzhiye = tuixiuqianzhiye;
	}
	
	@ExcelField(title="宗教", dictType="region_list", align=2, sort=21)
	public String getZongjiao() {
		return zongjiao;
	}

	public void setZongjiao(String zongjiao) {
		this.zongjiao = zongjiao;
	}
	
	@ExcelField(title="宗教其他", align=2, sort=22)
	public String getZongjiaoqita() {
		return zongjiaoqita;
	}

	public void setZongjiaoqita(String zongjiaoqita) {
		this.zongjiaoqita = zongjiaoqita;
	}
	
	@ExcelField(title="联系人1", align=2, sort=23)
	public String getLianxiren1() {
		return lianxiren1;
	}

	public void setLianxiren1(String lianxiren1) {
		this.lianxiren1 = lianxiren1;
	}
	
	@ExcelField(title="联系人1关系", align=2, sort=24)
	public String getLianxiren1guanxi() {
		return lianxiren1guanxi;
	}

	public void setLianxiren1guanxi(String lianxiren1guanxi) {
		this.lianxiren1guanxi = lianxiren1guanxi;
	}
	
	@ExcelField(title="联系人1电话", align=2, sort=25)
	public String getLianxirendianhua() {
		return lianxirendianhua;
	}

	public void setLianxirendianhua(String lianxirendianhua) {
		this.lianxirendianhua = lianxirendianhua;
	}
	
	@ExcelField(title="联系人1地址", align=2, sort=26)
	public String getLianxiren1dizhi() {
		return lianxiren1dizhi;
	}

	public void setLianxiren1dizhi(String lianxiren1dizhi) {
		this.lianxiren1dizhi = lianxiren1dizhi;
	}
	
	@ExcelField(title="联系人2", align=2, sort=27)
	public String getLianxiren2() {
		return lianxiren2;
	}

	public void setLianxiren2(String lianxiren2) {
		this.lianxiren2 = lianxiren2;
	}
	
	@ExcelField(title="联系人2关系", align=2, sort=28)
	public String getLianxiren2guanxi() {
		return lianxiren2guanxi;
	}

	public void setLianxiren2guanxi(String lianxiren2guanxi) {
		this.lianxiren2guanxi = lianxiren2guanxi;
	}
	
	@ExcelField(title="联系人2电话", align=2, sort=29)
	public String getLianxiren2dianhua() {
		return lianxiren2dianhua;
	}

	public void setLianxiren2dianhua(String lianxiren2dianhua) {
		this.lianxiren2dianhua = lianxiren2dianhua;
	}
	
	@ExcelField(title="联系人2地址", align=2, sort=30)
	public String getLianxiren2dizhi() {
		return lianxiren2dizhi;
	}

	public void setLianxiren2dizhi(String lianxiren2dizhi) {
		this.lianxiren2dizhi = lianxiren2dizhi;
	}
	
	@ExcelField(title="每月收入来源", dictType="income_list", align=2, sort=31)
	public String getMeiyueshourlaiyuan() {
		return meiyueshourlaiyuan;
	}

	public void setMeiyueshourlaiyuan(String meiyueshourlaiyuan) {
		this.meiyueshourlaiyuan = meiyueshourlaiyuan;
	}
	
	@ExcelField(title="退休金¥", align=2, sort=32)
	public Double getTuixiujin() {
		return tuixiujin;
	}

	public void setTuixiujin(Double tuixiujin) {
		this.tuixiujin = tuixiujin;
	}
	
	@ExcelField(title="亲友资助¥", align=2, sort=33)
	public Double getQinyouzizhu() {
		return qinyouzizhu;
	}

	public void setQinyouzizhu(Double qinyouzizhu) {
		this.qinyouzizhu = qinyouzizhu;
	}
	
	@ExcelField(title="五保户¥", align=2, sort=34)
	public Double getWubaohu() {
		return wubaohu;
	}

	public void setWubaohu(Double wubaohu) {
		this.wubaohu = wubaohu;
	}
	
	@ExcelField(title="低保户¥", align=2, sort=35)
	public Double getDibaohu() {
		return dibaohu;
	}

	public void setDibaohu(Double dibaohu) {
		this.dibaohu = dibaohu;
	}
	
	@ExcelField(title="其他政府资助¥", align=2, sort=36)
	public Double getQitazhengfuzizhu() {
		return qitazhengfuzizhu;
	}

	public void setQitazhengfuzizhu(Double qitazhengfuzizhu) {
		this.qitazhengfuzizhu = qitazhengfuzizhu;
	}
	
	@ExcelField(title="其他资助¥", align=2, sort=37)
	public Double getQitazizhu() {
		return qitazizhu;
	}

	public void setQitazizhu(Double qitazizhu) {
		this.qitazizhu = qitazizhu;
	}
	
	@ExcelField(title="总金额¥", align=2, sort=38)
	public Double getZongjine() {
		return zongjine;
	}

	public void setZongjine(Double zongjine) {
		this.zongjine = zongjine;
	}
	
	@ExcelField(title="其他数据", align=2, sort=39)
	public String getQitashuju() {
		return qitashuju;
	}

	public void setQitashuju(String qitashuju) {
		this.qitashuju = qitashuju;
	}
	
	@ExcelField(title="医疗费用支付方式", dictType="medical_paytype", align=2, sort=40)
	public String getYiliaofeiyongzhifufangshi() {
		return yiliaofeiyongzhifufangshi;
	}

	public void setYiliaofeiyongzhifufangshi(String yiliaofeiyongzhifufangshi) {
		this.yiliaofeiyongzhifufangshi = yiliaofeiyongzhifufangshi;
	}
	
	@ExcelField(title="医疗费用支付方式其他", align=2, sort=41)
	public String getYiliaofeiyongzhifufangshiqita() {
		return yiliaofeiyongzhifufangshiqita;
	}

	public void setYiliaofeiyongzhifufangshiqita(String yiliaofeiyongzhifufangshiqita) {
		this.yiliaofeiyongzhifufangshiqita = yiliaofeiyongzhifufangshiqita;
	}
	
	@ExcelField(title="兴趣-看电视", dictType="interest_freq", align=2, sort=42)
	public String getXqkandianshi() {
		return xqkandianshi;
	}

	public void setXqkandianshi(String xqkandianshi) {
		this.xqkandianshi = xqkandianshi;
	}
	
	@ExcelField(title="兴趣-听收音机", dictType="interest_freq", align=2, sort=43)
	public String getXqtingshouyinji() {
		return xqtingshouyinji;
	}

	public void setXqtingshouyinji(String xqtingshouyinji) {
		this.xqtingshouyinji = xqtingshouyinji;
	}
	
	@ExcelField(title="兴趣-阅报阅读", dictType="interest_freq", align=2, sort=44)
	public String getXqyuebaoyuedu() {
		return xqyuebaoyuedu;
	}

	public void setXqyuebaoyuedu(String xqyuebaoyuedu) {
		this.xqyuebaoyuedu = xqyuebaoyuedu;
	}
	
	@ExcelField(title="兴趣-做运动", dictType="interest_freq", align=2, sort=45)
	public String getXqzuoyundong() {
		return xqzuoyundong;
	}

	public void setXqzuoyundong(String xqzuoyundong) {
		this.xqzuoyundong = xqzuoyundong;
	}
	
	@ExcelField(title="生活习惯", align=2, sort=46)
	public String getShenghuoxiguan() {
		return shenghuoxiguan;
	}

	public void setShenghuoxiguan(String shenghuoxiguan) {
		this.shenghuoxiguan = shenghuoxiguan;
	}
	
	@ExcelField(title="病历", dictType="medical_record", align=2, sort=47)
	public String getBingli() {
		return bingli;
	}

	public void setBingli(String bingli) {
		this.bingli = bingli;
	}
	
	@ExcelField(title="关节炎位置", align=2, sort=48)
	public String getGuanjieyanweizhi() {
		return guanjieyanweizhi;
	}

	public void setGuanjieyanweizhi(String guanjieyanweizhi) {
		this.guanjieyanweizhi = guanjieyanweizhi;
	}
	
	@ExcelField(title="癌症位置", align=2, sort=49)
	public String getAizhengweizhi() {
		return aizhengweizhi;
	}

	public void setAizhengweizhi(String aizhengweizhi) {
		this.aizhengweizhi = aizhengweizhi;
	}
	
	@ExcelField(title="精神病", align=2, sort=50)
	public String getJingshenbing() {
		return jingshenbing;
	}

	public void setJingshenbing(String jingshenbing) {
		this.jingshenbing = jingshenbing;
	}
	
	@ExcelField(title="其他内容", align=2, sort=51)
	public String getQitaneirong() {
		return qitaneirong;
	}

	public void setQitaneirong(String qitaneirong) {
		this.qitaneirong = qitaneirong;
	}
	
	@ExcelField(title="曾施手术内容", align=2, sort=52)
	public String getCengshishoushu() {
		return cengshishoushu;
	}

	public void setCengshishoushu(String cengshishoushu) {
		this.cengshishoushu = cengshishoushu;
	}
	
	@ExcelField(title="食物敏感", dictType="yes_or_not", align=2, sort=53)
	public String getShiwumingan() {
		return shiwumingan;
	}

	public void setShiwumingan(String shiwumingan) {
		this.shiwumingan = shiwumingan;
	}
	
	@ExcelField(title="药物敏感", dictType="yes_or_not", align=2, sort=54)
	public String getYaowumingan() {
		return yaowumingan;
	}

	public void setYaowumingan(String yaowumingan) {
		this.yaowumingan = yaowumingan;
	}
	
	@ExcelField(title="食物敏感内容", align=2, sort=55)
	public String getShiwuminganneirong() {
		return shiwuminganneirong;
	}

	public void setShiwuminganneirong(String shiwuminganneirong) {
		this.shiwuminganneirong = shiwuminganneirong;
	}
	
	@ExcelField(title="药物敏感内容", align=2, sort=56)
	public String getYaowuminganneirong() {
		return yaowuminganneirong;
	}

	public void setYaowuminganneirong(String yaowuminganneirong) {
		this.yaowuminganneirong = yaowuminganneirong;
	}
	
	@ExcelField(title="与长者关系", align=2, sort=57)
	public String getYuzhangzheguanxi() {
		return yuzhangzheguanxi;
	}

	public void setYuzhangzheguanxi(String yuzhangzheguanxi) {
		this.yuzhangzheguanxi = yuzhangzheguanxi;
	}
	
	@ExcelField(title="长者/家属", align=2, sort=58)
	public String getZhangzhehuojiashu() {
		return zhangzhehuojiashu;
	}

	public void setZhangzhehuojiashu(String zhangzhehuojiashu) {
		this.zhangzhehuojiashu = zhangzhehuojiashu;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="日期", align=2, sort=59)
	public Date getRiqi() {
		return riqi;
	}

	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}
	
	public List<EdFamilyInf> getEdFamilyInfList() {
		return edFamilyInfList;
	}

	public void setEdFamilyInfList(List<EdFamilyInf> edFamilyInfList) {
		this.edFamilyInfList = edFamilyInfList;
	}
}