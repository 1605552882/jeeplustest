/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.monthcount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.checkresult.entity.Checkresult;
import com.jeeplus.modules.checkresult.service.CheckresultService;
import com.jeeplus.modules.documentdetect.entity.Documentarydetails;
import com.jeeplus.modules.documentdetect.service.DocumentarydetailsService;
import com.jeeplus.modules.errordocument.entity.Errordocument;
import com.jeeplus.modules.errordocument.service.ErrordocumentService;
import com.jeeplus.modules.monthcount.entity.Monthcount;
import com.jeeplus.modules.monthcount.mapper.MonthcountMapper;
import com.jeeplus.modules.overtimedocument.entity.Overtimedocument;
import com.jeeplus.modules.overtimedocument.service.OvertimedocumentService;
import com.jeeplus.modules.overtimefeedback.entity.Overtimefeedback;
import com.jeeplus.modules.overtimefeedback.service.OvertimefeedbackService;
import com.jeeplus.modules.overtimesign.entity.Overtimesigning;
import com.jeeplus.modules.overtimesign.service.OvertimesigningService;
import com.jeeplus.modules.repetitivedocument.entity.Repetitivedocument;
import com.jeeplus.modules.repetitivedocument.service.RepetitivedocumentService;
import com.jeeplus.modules.sys.utils.DictUtils;

/**
 * 月度汇总Service
 * @author lxy
 * @version 2019-08-22
 */
@Service
@Transactional(readOnly = true)
public class MonthcountService extends CrudService<MonthcountMapper, Monthcount> {
	
	
	@Autowired
	private CheckresultService checkresultService;
	
	@Autowired
	private DocumentarydetailsService documentarydetailsService;
	
	@Autowired
	private OvertimedocumentService overtimedocumentService;
	
	@Autowired
	private OvertimesigningService overtimesigningService;
	
	@Autowired
	private RepetitivedocumentService repetitivedocumentService;
	
	@Autowired
	private ErrordocumentService errordocumentService;
	
	@Autowired
	private OvertimefeedbackService overtimefeedbackService;

	public Monthcount get(String id) {
		return super.get(id);
	}
	
	public List<Monthcount> findList(Monthcount monthcount) {
		return super.findList(monthcount);
	}
	
	public Page<Monthcount> findPage(Page<Monthcount> page, Monthcount monthcount) {
		return super.findPage(page, monthcount);
	}
	
	@Transactional(readOnly = false)
	public void save(Monthcount monthcount) {
		super.save(monthcount);
	}
	
	@Transactional(readOnly = false)
	public void delete(Monthcount monthcount) {
		super.delete(monthcount);
	}
	
	/**
	 * 汇总
	 */
	public Page<Monthcount> handel(Monthcount monthcount, HttpServletRequest request, HttpServletResponse response){
		List<Monthcount> month = new ArrayList<Monthcount>();
		String group = monthcount.getDutyGroup(); //班组
		Date begin = monthcount.getBeginSearchtime();
		Date end = monthcount.getEndSearchtime();
		String sStatus = monthcount.getsStatus();//单据状态
		//合计检查单量
		int cnum = 0;
		//合计问题单量
		int pnum = 0;
		//合计归档单量
		int anum = 0;
		//合计在途单量
		int tnum = 0;
		if (begin != null) {
			Checkresult checkresult = new Checkresult();
			checkresult.setBeginDcreatetime(begin);
			checkresult.setEndDcreatetime(end);
			checkresult.setsStatus(sStatus);
			// 分班组
			if(group != null && group != "" && group.length() != 0) {
				checkresult.setDutyGroup(group);
				List<Checkresult> list = checkresultService.findList(checkresult);
				//统计
				//检查单量
				int checknum = list.size();
				//问题单量 
				int problemnum = 0;
				//在途单量 
				int transitnumber = 0;
				//归档单量 
				int archivingnumber = 0;
				//责任人
				Map<String, Integer> pmap = new HashMap<String, Integer>();
				for (int i = 0; i < list.size(); i++) {
					if("在途".equals(list.get(i).getsStatus())) {
						transitnumber++;
					}else if("归档".equals(list.get(i).getsStatus())) {
						archivingnumber++;
					}
					if("0".equals(list.get(i).getHasproblem())) {//0是正常的单，不计为问题单量
						continue;
					}
					String p = list.get(i).getDutyPeople();//责任人
					if (p != null && p != "" && p.length() != 0) {
						problemnum++;
						String[] ps = p.split(",");
						for (int j = 0; j < ps.length; j++) {
							if (pmap.containsKey(ps[j])) {
								pmap.put(ps[j], pmap.get(ps[j]) + 1);
							} else {
								pmap.put(ps[j], 1);
							}
						}
					}
				}
				//拼接责任人
				String dutyPeopledetail = "";
				 for (Map.Entry<String, Integer> m : pmap.entrySet()) {
					 dutyPeopledetail += m.getKey() + " : " +  m.getValue() + " 、 ";
				    }
				 Monthcount resultcount = new Monthcount();
				 resultcount.setDutyGroup(group);
				 resultcount.setChecknumber(checknum + "");
				 resultcount.setErrornumber(problemnum + "");
				 resultcount.setDutyPeopledetail(dutyPeopledetail);
				 resultcount.setTransitnumber(transitnumber + "");
				 resultcount.setArchivingnumber(archivingnumber + "");
				 month.add(resultcount);
			} else { //所有班组
				List<Checkresult> list = checkresultService.findList(checkresult);
				Map<String, Integer> cmap = new HashMap<String, Integer>();//检查单量
				Map<String, Integer> emap = new HashMap<String, Integer>();//问题单量
				Map<String, Integer> amap = new HashMap<String, Integer>();//归档单量
				Map<String, Integer> tmap = new HashMap<String, Integer>();//在途单量
				Map<String, Integer> pmap = new HashMap<String, Integer>();//责任人明细
				
				for (int i = 0; i < list.size(); i++) {
					String gp = list.get(i).getDutyGroup();//组
					String dp = list.get(i).getDutyPeople(); //责任人
					if (gp != null && gp != "" && gp.length() != 0) {
						//统计每组检查单量
						if (cmap.containsKey(gp)) {
							cmap.put(gp, cmap.get(gp) + 1);
						} else {
							cmap.put(gp, 1);
						}
						//统计每组问题单量
						if (dp != null && dp != "" && dp.length() != 0 && !"0".equals(list.get(i).getHasproblem())) {
							if (emap.containsKey(gp)) {
								emap.put(gp, emap.get(gp) + 1);
							} else {
								emap.put(gp, 1);
							}
							
							String[] ps = dp.split(",");
							for (int j = 0; j < ps.length; j++) {
								//每组的责任人
								if (pmap.containsKey(gp + ","+ ps[j])) {
									pmap.put(gp + ","+ ps[j], pmap.get(gp + ","+ ps[j]) + 1);
								} else {
									pmap.put(gp + ","+ ps[j], 1);
								}
							}
						}
						//统计每组归档单量
						if("归档".equals(list.get(i).getsStatus())) {
							if (amap.containsKey(gp)) {
								amap.put(gp, amap.get(gp) + 1);
							} else {
								amap.put(gp, 1);
							}
						}
						//统计每组在途单量
						if("在途".equals(list.get(i).getsStatus())) {
							if (tmap.containsKey(gp)) {
								tmap.put(gp, tmap.get(gp) + 1);
							} else {
								tmap.put(gp, 1);
							}
						}
					}
				}
				
				 for (Map.Entry<String, Integer> m : cmap.entrySet()) {
					 String g =  m.getKey();//组
					 Monthcount resultcount = new Monthcount();
					 resultcount.setDutyGroup(g);
					 resultcount.setChecknumber(m.getValue() + "");
					 resultcount.setErrornumber(emap.get(g)==null?"0":emap.get(g)+"");
					 resultcount.setArchivingnumber(amap.get(g)==null?"0":amap.get(g)+"");
					 resultcount.setTransitnumber(tmap.get(g)==null?"0":tmap.get(g)+"");
					 
					 //合计
					 cnum += m.getValue();
					 pnum += (emap.get(g)==null?0:emap.get(g));
					 anum += (amap.get(g)==null?0:amap.get(g));
					 tnum += (tmap.get(g)==null?0:tmap.get(g));
					 
					 String dutyPeopledetail = "";
					 for (Map.Entry<String, Integer> p : pmap.entrySet()) {
						 if (p.getKey().split(",")[0].equals(g)) {
							 String[] a = p.getKey().split(",");//拆分组, 责任人
							 try {
								 dutyPeopledetail += a[1] + " : " +  p.getValue() + " 、 ";
							 } catch (Exception e) {
								 
								 dutyPeopledetail = "";
							}
						 }
					 }
					 
					 resultcount.setDutyPeopledetail(dutyPeopledetail);
					 month.add(resultcount);
				 }
				 
				 Monthcount resultcount = new Monthcount();
				 resultcount.setDutyGroup("合计");
				 resultcount.setChecknumber(cnum + "");
				 resultcount.setErrornumber(pnum + "");
				 resultcount.setArchivingnumber(anum + "");
				 resultcount.setTransitnumber(tnum + "");
				 month.add(resultcount);
			}
		}
		
		Page<Monthcount> page = new Page<Monthcount>(request, response); 
		page.setList(month);
		page.setCount(month.size());
		return page;
	}

	public AjaxJson getDetails(Monthcount monthcount, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		Date begin = monthcount.getBeginSearchtime();
		Date end = monthcount.getEndSearchtime();
		String sStatus = monthcount.getsStatus();
		if (begin != null) {
			Checkresult checkresult = new Checkresult();
			checkresult.setBeginDcreatetime(begin);
			checkresult.setEndDcreatetime(end);
			checkresult.setsStatus(sStatus);
			checkresult.setRemarks("true");
			 //所有班组
			List<Checkresult> list = checkresultService.findList(checkresult);
			//合计检查单量
			int cnum = list.size();
			//合计问题单量
			int pnum = 0;
			for (Checkresult checkresult2 : list) {
				//0是正常的
				if(!"0".equals(checkresult2.getHasproblem())) {
					pnum++;
				}
			}
			
			//前三问题工单量document_problem
			List<Map<String, Object>> topThreeProblems = checkresultService.getTopThreeProblems(checkresult);
			String topThreeProblemsMsg = "";
			if(topThreeProblems!=null && topThreeProblems.size()>0) {
				for (Map<String, Object> map : topThreeProblems) {
					String problemName = DictUtils.getDictLabel(String.valueOf(map.get("hasproblem")), "document_problem", "未定义在问题工单字典里");
					String count = String.valueOf(map.get("count"));
					topThreeProblemsMsg += (problemName+":"+count+"个,");
				}
				topThreeProblemsMsg = topThreeProblemsMsg.substring(0, topThreeProblemsMsg.lastIndexOf(','));//不要最后一个逗号
			}

			//受理工单量
			Documentarydetails documentarydetails = new Documentarydetails();
			documentarydetails.setBeginReportTime(begin);
			documentarydetails.setEndReportTime(end);
			List<Documentarydetails> Documentarydetailslist = documentarydetailsService.findList(documentarydetails);
			//受理工单量
			int a = Documentarydetailslist.size();
			//获取超时的工单
			Overtimedocument overtimedocument = new Overtimedocument();
			overtimedocument.setBeginReportTime(begin);
			overtimedocument.setEndReportTime(end);
			List<Overtimedocument> overtimedocumentLlist = overtimedocumentService.findList(overtimedocument);
			
			//获取签单超时
			Overtimesigning Overtimesigning = new Overtimesigning();
			Overtimesigning.setBeginReportTime(begin);
			Overtimesigning.setEndReportTime(end);
			List<Overtimesigning> overtimesigningLlist = overtimesigningService.findList(Overtimesigning);
			
			//获取重复退单
			Repetitivedocument repetitiveDocument = new Repetitivedocument();
			repetitiveDocument.setBeginReportTime(begin);
			repetitiveDocument.setEndReportTime(end);
			List<Repetitivedocument> repetitivedocumentLlist = repetitivedocumentService.findList(repetitiveDocument);
			//异常工单
			Errordocument errordocument = new Errordocument();
			errordocument.setBeginReportTime(begin);
			errordocument.setEndReportTime(end);
			List<Errordocument> errordocumentLlist = errordocumentService.findList(errordocument);
			//反馈超时
			Overtimefeedback overtimefeedback = new Overtimefeedback();
			overtimefeedback.setBeginReportTime(begin);
			overtimefeedback.setEndReportTime(end);
			List<Overtimefeedback> overtimefeedbackLlist = overtimefeedbackService.findList(overtimefeedback);
			
			Integer count = documentarydetailsService.findcount(begin,end);
			//检测工单量
			int b = overtimedocumentLlist.size()+overtimesigningLlist.size()+repetitivedocumentLlist.size()
						+errordocumentLlist.size()+overtimefeedbackLlist.size();
			

			String s = "受理工单量 : " + a + "个  , 反馈信息数量 : "+ count +"个 , 系统检查疑似异常工单量 : " + b + "个 ,  人工复检工单量 : " + cnum
					+ "个,  存在问题工单量  : " +  pnum + "个<br>前三问题工单分别是 : "+topThreeProblemsMsg;
			j.setSuccess(true);
			j.setMsg(s);
		}
		return j;
	}

	
}