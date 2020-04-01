/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.nutrition.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 营养筛选问卷Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdNutritionLog extends DataEntity<EdNutritionLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private String guoqusanyueshiyu;		// 过去三个月内有没有因为食欲不振、消化问题、咀嚼或吞咽困难而
	private String guoqusanyuetizhongxiajiang;		// 过去三个月内体重下降的情况？
	private String guoqusanyuechuangshang;		// 过去三个月内有没有受到心理创伤或患上急性疾病？
	private String huodongnengli;		// 活动能力？
	private String jingshenxinliwenti;		// 精神心理问题？
	private String shentizhiliangzhishu;		// 身体质量指数（BMI）（公斤/米²，kg/m²)？
	private String xiaotuiwei;		// 小腿围（CC）（公分，cm）？
	private User assessor;		// 评估人员
	
	public EdNutritionLog() {
		super();
	}

	public EdNutritionLog(String id){
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
	
	@ExcelField(title="过去三个月内有没有因为食欲不振、消化问题、咀嚼或吞咽困难而", dictType="jianshaoshiliang", align=2, sort=9)
	public String getGuoqusanyueshiyu() {
		return guoqusanyueshiyu;
	}

	public void setGuoqusanyueshiyu(String guoqusanyueshiyu) {
		this.guoqusanyueshiyu = guoqusanyueshiyu;
	}
	
	@ExcelField(title="过去三个月内体重下降的情况？", dictType="tizhongxiajiang", align=2, sort=10)
	public String getGuoqusanyuetizhongxiajiang() {
		return guoqusanyuetizhongxiajiang;
	}

	public void setGuoqusanyuetizhongxiajiang(String guoqusanyuetizhongxiajiang) {
		this.guoqusanyuetizhongxiajiang = guoqusanyuetizhongxiajiang;
	}
	
	@ExcelField(title="过去三个月内有没有受到心理创伤或患上急性疾病？", dictType="xinlichuangshang", align=2, sort=11)
	public String getGuoqusanyuechuangshang() {
		return guoqusanyuechuangshang;
	}

	public void setGuoqusanyuechuangshang(String guoqusanyuechuangshang) {
		this.guoqusanyuechuangshang = guoqusanyuechuangshang;
	}
	
	@ExcelField(title="活动能力？", dictType="huodongnengli", align=2, sort=12)
	public String getHuodongnengli() {
		return huodongnengli;
	}

	public void setHuodongnengli(String huodongnengli) {
		this.huodongnengli = huodongnengli;
	}
	
	@ExcelField(title="精神心理问题？", dictType="jingshenxinliwenti", align=2, sort=13)
	public String getJingshenxinliwenti() {
		return jingshenxinliwenti;
	}

	public void setJingshenxinliwenti(String jingshenxinliwenti) {
		this.jingshenxinliwenti = jingshenxinliwenti;
	}
	
	@ExcelField(title="身体质量指数（BMI）（公斤/米²，kg/m²)？", dictType="shentizhiliangzhishu", align=2, sort=14)
	public String getShentizhiliangzhishu() {
		return shentizhiliangzhishu;
	}

	public void setShentizhiliangzhishu(String shentizhiliangzhishu) {
		this.shentizhiliangzhishu = shentizhiliangzhishu;
	}
	
	@ExcelField(title="小腿围（CC）（公分，cm）？", dictType="xiaotuiwei", align=2, sort=15)
	public String getXiaotuiwei() {
		return xiaotuiwei;
	}

	public void setXiaotuiwei(String xiaotuiwei) {
		this.xiaotuiwei = xiaotuiwei;
	}
	
	@ExcelField(title="评估人员", fieldType=User.class, value="assessor.name", align=2, sort=16)
	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	
}