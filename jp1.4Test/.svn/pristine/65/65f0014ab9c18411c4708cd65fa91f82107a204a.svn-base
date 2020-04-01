/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentdetect.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.modules.checkresult.entity.Checkresult;

/**
 * 工单详情Entity
 * @author liang
 * @version 2019-08-05
 */
public class Documentarydetails extends DataEntity<Documentarydetails> {
	
	private static final long serialVersionUID = 1L;
	private String sbillno;		// 故障单号
	private String sfaulttitle;		// 故障标题
	private String phone;			// 手机号码
	private String sfaultcategory;		// 故障种类
	private String sfaultrepresent;		// 故障现象
	private String srepfaultdetail;		// 故障内容
	private String sprocesssummary;		// 结单信息
	private String sfaultcauseid;		// 故障原因
	private String sansquaeval;		// 回单质量评价
	private String dremaintimetimit;		// 剩余时间
	private String dfaulttimelimit;		// 故障时限(分)
	private String ddurtime;		// 故障历时(分)
	private String dntfaultime;		// 网运时限(分)
	private String dntiremain;		// 网运剩余时间(分)
	private Date dfaultapttime;		// 受理时间
	private Date dfirstreptime;		// 首次回应时间
	private Date reportTime;		// 申告时间
	private Date darchivetime;		// 确认时间
	private Date beginReportTime;		// 开始 申告时间
	private Date endReportTime;		// 结束 申告时间
	private List<Feedback> feedbackList = Lists.newArrayList();		// 子表列表
	private List<Documentarydetails> repetitiveDocument = Lists.newArrayList();		// 重复退单
	
	private String sReportsubarea;	// 申告地市
	private String iSource;			// 申告来源
	
	private String check; // 
	private String text; // 
	
	private String problem;		// 存在问题
	private String details;		// 详细情况
	private String group;		// 责任班组
	private String people;		// 责任人
	private String hotword;		// 责任人
	private String city;		// 地市
	private List<String> uselessFaultcategory;//不需要的分类
	List<Checkresult> checkresult;//检查结论
	
	private String relation; //查询关系
	private String sStatus; //单据状态
	
	private String chooseId; //抽取id
	public Date getBeginReportTime() {
		return beginReportTime;
	}

	public void setBeginReportTime(Date beginReportTime) {
		this.beginReportTime = beginReportTime;
	}

	public Date getEndReportTime() {
		return endReportTime;
	}

	public void setEndReportTime(Date endReportTime) {
		this.endReportTime = endReportTime;
	}
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Documentarydetails() {
		super();
	}

	public Documentarydetails(String id){
		super(id);
	}

	@ExcelField(title="故障单号", align=2, sort=1)
	public String getSbillno() {
		return sbillno;
	}

	public void setSbillno(String sbillno) {
		this.sbillno = sbillno;
	}
	
	@ExcelField(title="故障标题", align=2, sort=2)
	public String getSfaulttitle() {
		return sfaulttitle;
	}

	public void setSfaulttitle(String sfaulttitle) {
		this.sfaulttitle = sfaulttitle;
	}
	
	@ExcelField(title="故障种类", align=2, sort=3)
	public String getSfaultcategory() {
		return sfaultcategory;
	}

	public void setSfaultcategory(String sfaultcategory) {
		this.sfaultcategory = sfaultcategory;
	}
	
	@ExcelField(title="故障现象", align=2, sort=4)
	public String getSfaultrepresent() {
		return sfaultrepresent;
	}

	public void setSfaultrepresent(String sfaultrepresent) {
		this.sfaultrepresent = sfaultrepresent;
	}
	
	@ExcelField(title="故障内容", align=2, sort=5)
	public String getSrepfaultdetail() {
		return srepfaultdetail;
	}

	public void setSrepfaultdetail(String srepfaultdetail) {
		this.srepfaultdetail = srepfaultdetail;
	}
	
	@ExcelField(title="结单信息", align=2, sort=6)
	public String getSprocesssummary() {
		return sprocesssummary;
	}

	public void setSprocesssummary(String sprocesssummary) {
		this.sprocesssummary = sprocesssummary;
	}
	
	@ExcelField(title="故障原因", align=2, sort=7)
	public String getSfaultcauseid() {
		return sfaultcauseid;
	}

	public void setSfaultcauseid(String sfaultcauseid) {
		this.sfaultcauseid = sfaultcauseid;
	}
	
	@ExcelField(title="回单质量评价", align=2, sort=8)
	public String getSansquaeval() {
		return sansquaeval;
	}

	public void setSansquaeval(String sansquaeval) {
		this.sansquaeval = sansquaeval;
	}
	
	@ExcelField(title="剩余时间", align=2, sort=9)
	public String getDremaintimetimit() {
		return dremaintimetimit;
	}

	public void setDremaintimetimit(String dremaintimetimit) {
		this.dremaintimetimit = dremaintimetimit;
	}
	
	@ExcelField(title="故障时限(分)", align=2, sort=10)
	public String getDfaulttimelimit() {
		return dfaulttimelimit;
	}

	public void setDfaulttimelimit(String dfaulttimelimit) {
		this.dfaulttimelimit = dfaulttimelimit;
	}
	
	@ExcelField(title="故障历时(分)", align=2, sort=11)
	public String getDdurtime() {
		return ddurtime;
	}

	public void setDdurtime(String ddurtime) {
		this.ddurtime = ddurtime;
	}
	
	@ExcelField(title="网运时限(分)", align=2, sort=12)
	public String getDntfaultime() {
		return dntfaultime;
	}

	public void setDntfaultime(String dntfaultime) {
		this.dntfaultime = dntfaultime;
	}
	
	@ExcelField(title="网运剩余时间(分)", align=2, sort=13)
	public String getDntiremain() {
		return dntiremain;
	}

	public void setDntiremain(String dntiremain) {
		this.dntiremain = dntiremain;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="受理时间", align=2, sort=14)
	public Date getDfaultapttime() {
		return dfaultapttime;
	}

	public void setDfaultapttime(Date dfaultapttime) {
		this.dfaultapttime = dfaultapttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="首次回应时间", align=2, sort=15)
	public Date getDfirstreptime() {
		return dfirstreptime;
	}

	public void setDfirstreptime(Date dfirstreptime) {
		this.dfirstreptime = dfirstreptime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="确认时间", align=2, sort=16)
	public Date getDarchivetime() {
		return darchivetime;
	}

	public void setDarchivetime(Date darchivetime) {
		this.darchivetime = darchivetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="申告时间", align=2, sort=17)
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getChooseId() {
		return chooseId;
	}

	public void setChooseId(String chooseId) {
		this.chooseId = chooseId;
	}

	public List<Documentarydetails> getRepetitiveDocument() {
		return repetitiveDocument;
	}

	public void setRepetitiveDocument(List<Documentarydetails> repetitiveDocument) {
		this.repetitiveDocument = repetitiveDocument;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getUselessFaultcategory() {
		return uselessFaultcategory;
	}

	public void setUselessFaultcategory(List<String> uselessFaultcategory) {
		this.uselessFaultcategory = uselessFaultcategory;
	}

	public List<Checkresult> getCheckresult() {
		return checkresult;
	}

	public void setCheckresult(List<Checkresult> checkresult) {
		this.checkresult = checkresult;
	}

	public String getHotword() {
		return hotword;
	}

	public void setHotword(String hotword) {
		this.hotword = hotword;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}

	@ExcelField(title="手机号码", align=2, sort=18)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ExcelField(title="申告地市", align=2, sort=19)
	public String getsReportsubarea() {
		return sReportsubarea;
	}
	
	public void setsReportsubarea(String sReportsubarea) {
		this.sReportsubarea = sReportsubarea;
	}
	
	@ExcelField(title="申告来源", align=2, sort=20)
	public String getiSource() {
		return iSource;
	}

	public void setiSource(String iSource) {
		this.iSource = iSource;
	}
}