/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentconfig.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.documentconfig.entity.Documentconfig;
import com.jeeplus.modules.documentconfig.mapper.DocumentconfigMapper;
import com.jeeplus.modules.documentdetect.entity.Documentarydetails;
import com.jeeplus.modules.documentdetect.service.DocumentarydetailsService;
import com.jeeplus.modules.errordocument.entity.Errordocument;
import com.jeeplus.modules.errordocument.service.ErrordocumentService;
import com.jeeplus.modules.groupdetails.entity.Groupdetails;
import com.jeeplus.modules.groupdetails.service.GroupdetailsService;

/**
 * 工单检测规则配置Service
 * @author zqb
 * @version 2019-10-15
 */
@Service
@Transactional(readOnly = true)
public class DocumentconfigService extends CrudService<DocumentconfigMapper, Documentconfig> {
	
	@Autowired
	private GroupdetailsService groupdetailsService;
	
	@Autowired
	private ErrordocumentService errordocumentService;
	
	@Autowired
	private DocumentarydetailsService documentarydetailsService;
	
	@Autowired
	private DocumentconfigMapper documentconfigMapper;
	
	public List<String> findResult() {
		return documentconfigMapper.findResult();
	}


	public Documentconfig get(String id) {
		return super.get(id);
	}
	
	public List<Documentconfig> findList(Documentconfig documentconfig) {
		return super.findList(documentconfig);
	}
	
	public Page<Documentconfig> findPage(Page<Documentconfig> page, Documentconfig documentconfig) {
		return super.findPage(page, documentconfig);
	}
	
	@Transactional(readOnly = false)
	public void save(Documentconfig documentconfig) {
		super.save(documentconfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(Documentconfig documentconfig) {
		super.delete(documentconfig);
	}

	@Transactional(readOnly = false)
	public void ruleTest(Documentconfig documentconfig) {
		//获取所有工单
		Documentarydetails documentarydetails = new Documentarydetails();
		documentarydetails.setBeginReportTime(documentconfig.getBeginReportTime());
		documentarydetails.setEndReportTime(documentconfig.getEndReportTime());
		List<Documentarydetails> list = documentarydetailsService.findList(documentarydetails);
		List<Groupdetails> glist = groupdetailsService.findList(new Groupdetails());
//		String saverepfaultdetail = entity.getSrepfaultdetail();// 故障内容
//		String sprocesssummary = entity.getSprocesssummary();// 结单信息
		//检查规则
		
		String[] idStrings = documentconfig.getIds().split(",");
		for (int w = 0; w < idStrings.length; w++) {
			Documentconfig entity = this.get(idStrings[w]);
			String rule = entity.getRule();						// 检查规则
			String busyType = entity.getBusytype();				// 业务类型
			String srepfaultdetail = entity.getSrepfaultdetail();// 故障内容
			String sprocesssummary = entity.getSprocesssummary();// 结单信息
			String result = entity.getResult();					//检查结论
			
			for (int a = 0; a < list.size(); a++) {
				String checkResult = "";
				String type = list.get(a).getSfaultcategory();// 故障种类
				String[] path = type.split("/");// 故障种类路径切割
				String lastType = path[path.length-1];//故障种类路径的最后一项("/用户业务故障(全局)/个人客户投诉/移动/无线宽带(2016全新)/4G无线宽带/"--->"4G无线宽带")
				String resultSrepfaultdetail = list.get(a).getSrepfaultdetail();// 故障内容
				String resultSprocesssummary =  list.get(a).getSprocesssummary();// 结单信息
				
				
				
				if (type.contains(busyType)) {
					if ("3".equals(rule)) {// 不包含
						// 故障内容匹配
						if (srepfaultdetail != null && srepfaultdetail != "" && srepfaultdetail.length() > 0) {
							String[] srepfaultdetails = srepfaultdetail.split(":");
							for (int i = 0; i < srepfaultdetails.length; i++) {
								if (resultSrepfaultdetail.contains(srepfaultdetails[i])) {
									if (sprocesssummary != null && sprocesssummary != "" && sprocesssummary.length() > 0) {
										// 结单信息匹配 必须不包含匹配信息 如果包含是错误
										String[] sprocesssummarys = sprocesssummary.split(":");
										for (int d = 0; d < sprocesssummarys.length; d++) {
											String[] sprocesssummaryss = sprocesssummarys[d].split(",");
											for (int b = 0; b < sprocesssummaryss.length; b++) {
												// 一旦匹配到规则内容 说明此单为异常 立即退出
												if (resultSprocesssummary.contains(sprocesssummaryss[b])) {
													checkResult = result;
													break;
												}
											}
											if (!"".equals(checkResult)) {
												break;
											}
										}
										break;
									}
								}
							}
							
						} else { // 故障内容为空 直接判断结单信息
							// 结单信息匹配 必须包含匹配信息 如果不包含是错误
							if (sprocesssummary != null && sprocesssummary != "" && sprocesssummary.length() > 0) {
								// 结单信息匹配 必须不包含匹配信息 如果包含是错误
								String[] sprocesssummarys = sprocesssummary.split(":");
								for (int d = 0; d < sprocesssummarys.length; d++) {
									String[] sprocesssummaryss = sprocesssummarys[d].split(",");
									for (int b = 0; b < sprocesssummaryss.length; b++) {
										// 一旦匹配到规则内容 说明此单为异常 立即退出
										if (resultSprocesssummary.contains(sprocesssummaryss[b])) {
											checkResult = result;
											break;
										}
									}
									if (!"".equals(checkResult)) {
										break;
									}
								}
							}
						}
					} else if ("1".equals(rule)) {//全包含
						//匹配任意一个关键字
						// 故障内容匹配
						if (srepfaultdetail != null && srepfaultdetail != "" && srepfaultdetail.length() > 0) {
							String[] srepfaultdetails = srepfaultdetail.split(":");
							boolean flag = false;
							for (int i = 0; i < srepfaultdetails.length; i++) {
								if (resultSrepfaultdetail.contains(srepfaultdetails[i])) {
									// 结单信息匹配 必须包含匹配信息 如果不包含是错误
									if (sprocesssummary != null && sprocesssummary != "" && sprocesssummary.length() > 0) {
										String[] sprocesssummarys = sprocesssummary.split(":");
										int errorNum = 0;
										for (int j = 0; j < sprocesssummarys.length; j++) {
											String[] sprocesssummaryss = sprocesssummarys[j].split(",");
											int num = 0;
											for (int b = 0; b < sprocesssummaryss.length; b++) {
												if (resultSprocesssummary.contains(sprocesssummaryss[b])) {
													num++;
												}
											}
											
											if(num != sprocesssummaryss.length) {
												errorNum++;
											} else {
												flag = true; // 匹配到一条路 说明此单没问题
												break;
											}
										}
										
										if(flag){
											break;
										}
										if(errorNum == sprocesssummarys.length) {
											checkResult = result;
										}
									}
									//匹配到信息 退出循环
									break;
								}
							}
							
						} else { // 故障内容为空 直接判断结单信息
							// 结单信息匹配 必须包含匹配信息 如果不包含是错误
							if (sprocesssummary != null && sprocesssummary != "" && sprocesssummary.length() > 0) {
								String[] sprocesssummarys = sprocesssummary.split(":");
								int errorNum = 0;
								for (int j = 0; j < sprocesssummarys.length; j++) {
									String[] sprocesssummaryss = sprocesssummarys[j].split(",");
									int num = 0;
									for (int b = 0; b < sprocesssummaryss.length; b++) {
										if (resultSprocesssummary.contains(sprocesssummaryss[b])) {
											num++;
										}
									}
									
									if(num != sprocesssummaryss.length) {//此路不通 下一条
										errorNum++;
									} else { //此路同
										break;
									}
								}
								if(errorNum == sprocesssummarys.length) {
									checkResult = result;
								}
							}
						}
						
						
					}
					
				}
				
				if (!"".equals(checkResult)) {
					//获取名字
					String  idString = "";
					String fgroup = "";
					String name ="";
					if (resultSprocesssummary.trim().length() > 6 && !"".equals(resultSprocesssummary.trim())) {
						String num = resultSprocesssummary.substring(resultSprocesssummary.length()-5);
						if (num.contains("#")) {
							idString = num.substring(num.indexOf("#"));
							if (!"".equals(idString.trim())) {
								for (int i = 0; i < glist.size(); i++) {
									if (glist.get(i).getNum().equals(idString)) {
										name = glist.get(i).getGName();
										fgroup = glist.get(i).getGGroup();
										break;
									}
								}
							}
							
						}
					}
					//存入
					Errordocument errordocument = new Errordocument();
					errordocument.setDutyGroup(fgroup);
					errordocument.setDutyPeople(name);
					errordocument.setSbillno(list.get(a).getSbillno());
					errordocument.setCheckresult(checkResult);
					errordocument.setReportTime(list.get(a).getReportTime());
					errordocument.setSrepfaultdetail(srepfaultdetail);
					errordocument.setSprocesssummary(sprocesssummary);
					errordocument.setCheckrule(rule);
					errordocument.setFaultcategory(lastType);
					errordocument.setCreateDate(new Date());
					errordocumentService.save(errordocument);
					
				}
			}
		}
	}
}