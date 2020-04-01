/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialnursing.entity;

import com.jeeplus.modules.elder.entity.EdElderInf;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.entity.Office;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 特殊护理记录Entity
 * @author lukbob
 * @version 2018-11-12
 */
public class EdSpecialnursingLog extends DataEntity<EdSpecialnursingLog> {
	
	private static final long serialVersionUID = 1L;
	private EdElderInf elderid;		// 长者id
	private Date assesstime;		// 评估时间
	private String youwuteshuhuli;		// 有无特殊护理
	private String teshuhulineirong;		// 特殊护理内容
	private User assessor;		// 评估人员
	private Office assessordept;		// 评估人员职位
	
	public EdSpecialnursingLog() {
		super();
	}

	public EdSpecialnursingLog(String id){
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
	
	@ExcelField(title="有无特殊护理", dictType="youwuteshuhuli", align=2, sort=9)
	public String getYouwuteshuhuli() {
		return youwuteshuhuli;
	}

	public void setYouwuteshuhuli(String youwuteshuhuli) {
		this.youwuteshuhuli = youwuteshuhuli;
	}
	
	@ExcelField(title="特殊护理内容", dictType="teshuhulineirong", align=2, sort=10)
	public String getTeshuhulineirong() {
		return teshuhulineirong;
	}

	public void setTeshuhulineirong(String teshuhulineirong) {
		this.teshuhulineirong = teshuhulineirong;
	}
	
	@ExcelField(title="评估人员", fieldType=User.class, value="assessor.name", align=2, sort=11)
	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}
	
	@ExcelField(title="评估人员职位", fieldType=Office.class, value="assessordept.name", align=2, sort=12)
	public Office getAssessordept() {
		return assessordept;
	}

	public void setAssessordept(Office assessordept) {
		this.assessordept = assessordept;
	}
	
}