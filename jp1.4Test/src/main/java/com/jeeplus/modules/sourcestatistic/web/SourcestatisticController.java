/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sourcestatistic.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.reportsubareastatistic.entity.Reportsubareastatistic;
import com.jeeplus.modules.reportsubareastatistic.service.ReportsubareastatisticService;
import com.jeeplus.modules.sourcestatistic.entity.Sourcestatistic;
import com.jeeplus.modules.sourcestatistic.service.SourcestatisticService;

/**
 * 工单申告来源统计Controller
 * @author zqb
 * @version 2019-12-06
 */
@Controller
@RequestMapping(value = "${adminPath}/sourcestatistic/sourcestatistic")
public class SourcestatisticController extends BaseController {

	@Autowired
	private SourcestatisticService sourcestatisticService;
	
	@Autowired
	private ReportsubareastatisticService reportsubareastatisticService;
	
	@ModelAttribute
	public Sourcestatistic get(@RequestParam(required=false) String id) {
		Sourcestatistic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sourcestatisticService.get(id);
		}
		if (entity == null){
			entity = new Sourcestatistic();
		}
		return entity;
	}
	
	/**
	 * 工单申告来源统计列表页面
	 */
	@RequiresPermissions("sourcestatistic:sourcestatistic:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sourcestatistic/sourcestatisticList";
	}
	
	/**
	 * 查看，增加，编辑工单申告来源统计表单页面
	 */
	@RequiresPermissions(value={"sourcestatistic:sourcestatistic:view","sourcestatistic:sourcestatistic:add","sourcestatistic:sourcestatistic:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Sourcestatistic sourcestatistic, Model model) {
		model.addAttribute("sourcestatistic", sourcestatistic);
		if(StringUtils.isBlank(sourcestatistic.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/sourcestatistic/sourcestatisticForm";
	}

	/**
	 * 保存工单申告来源统计
	 */
	@RequiresPermissions(value={"sourcestatistic:sourcestatistic:add","sourcestatistic:sourcestatistic:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Sourcestatistic sourcestatistic, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, sourcestatistic)){
			return form(sourcestatistic, model);
		}
		//新增或编辑表单保存
		sourcestatisticService.save(sourcestatistic);//保存
		addMessage(redirectAttributes, "保存工单申告来源统计成功");
		return "redirect:"+Global.getAdminPath()+"/sourcestatistic/sourcestatistic/?repage";
	}
	
	/**
	 * 删除工单申告来源统计
	 */
	@ResponseBody
	@RequiresPermissions("sourcestatistic:sourcestatistic:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Sourcestatistic sourcestatistic, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sourcestatisticService.delete(sourcestatistic);
		j.setMsg("删除工单申告来源统计成功");
		return j;
	}
	
	/**
	 * 批量删除工单申告来源统计
	 */
	@ResponseBody
	@RequiresPermissions("sourcestatistic:sourcestatistic:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sourcestatisticService.delete(sourcestatisticService.get(id));
		}
		j.setMsg("删除工单申告来源统计成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("sourcestatistic:sourcestatistic:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Sourcestatistic sourcestatistic,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

		AjaxJson json = new AjaxJson();
		try {
			//查询
			String timeFlag = request.getParameter("timeFlag");
			String groupFlag = request.getParameter("groupFlag");
			String beginCreateDate = request.getParameter("beginCreateDate");
			String endCreateDate = request.getParameter("endCreateDate");
			String sStatus = request.getParameter("sStatus");
			SimpleDateFormat sdf = null;
			if("1".equals(timeFlag)) {//1=日
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else{//2=月
				sdf = new SimpleDateFormat("yyyy-MM");
			}
			Date bDate = sdf.parse(beginCreateDate);
			Date eDate = sdf.parse(endCreateDate);
			
			if("1".equals(groupFlag)) {//1=来源
				Sourcestatistic condition = new Sourcestatistic();
				condition.setBeginCreateDate(bDate);
				condition.setEndCreateDate(eDate);
				condition.setTimeFlag(timeFlag);
				condition.setGroupFlag(groupFlag);
				condition.setsStatus(sStatus);
				Map<String, Object> list = sourcestatisticService.findStatisticList(condition);
				
				List<String> datefmt = (List<String>) list.get("datefmt");
				List<String> sourceLst = (List<String>) list.get("sourceList");
				List<String> headList = new ArrayList<String>();
				headList.add("");
				headList.addAll(sourceLst);
				int[][] data = (int[][]) list.get("data");
				int[] total = new int[sourceLst.size()];//合计
				
				String fileName = "工单申告来源统计"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
				ExportExcel excel = new ExportExcel("工单申告来源统计",headList);
				int colNum = 0;
				Row row = null;
				for (int i=0; i<datefmt.size(); i++) {
					row = excel.addRow();
					colNum = 0;
					excel.addCell(row, colNum++, datefmt.get(i));//日期
					for(int j=0; j<sourceLst.size(); j++) {
						excel.addCell(row, colNum++, data[j][i]);//次数
						total[j]+=data[j][i];
					}
				}
				row = excel.addRow();
				colNum = 0;
				excel.addCell(row, colNum++, "合计");//合计
				for(int j=0; j<sourceLst.size(); j++) {
					excel.addCell(row, colNum++, total[j]);
				}
				excel.write(response, fileName).dispose();
	       }else {//2=地市
				Reportsubareastatistic condition = new Reportsubareastatistic();
				condition.setBeginCreateDate(bDate);
				condition.setEndCreateDate(eDate);
				condition.setTimeFlag(timeFlag);
				condition.setGroupFlag(groupFlag);
				condition.setsStatus(sStatus);
				Map<String, Object> list = reportsubareastatisticService.findStatisticList(condition);

				List<String> datefmt = (List<String>) list.get("datefmt");
				List<String> reportsubareaList = (List<String>) list.get("reportsubareaList");
				List<String> headList = new ArrayList<String>();
				headList.add("");
				headList.addAll(reportsubareaList);
				int[][] data = (int[][]) list.get("data");
				int[] total = new int[reportsubareaList.size()];//合计
				
				String fileName = "工单申告来源统计"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
				ExportExcel excel = new ExportExcel("工单申告来源统计",headList);
				int colNum = 0;
				Row row = null;
				for (int i=0; i<datefmt.size(); i++) {
					row = excel.addRow();
					colNum = 0;
					excel.addCell(row, colNum++, datefmt.get(i));//日期
					for(int j=0; j<reportsubareaList.size(); j++) {
						excel.addCell(row, colNum++, data[j][i]);//次数
						total[j]+=data[j][i];
					}
				}
				row = excel.addRow();
				colNum = 0;
				excel.addCell(row, colNum++, "合计");//合计
				for(int j=0; j<reportsubareaList.size(); j++) {
					excel.addCell(row, colNum++, total[j]);
				}
				excel.write(response, fileName).dispose();
			}
    		json.setSuccess(true);
    		json.setMsg("导出成功！");
    		return json;
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("导出工单申告来源统计记录失败！失败信息："+e.getMessage());
		}
			return json;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("sourcestatistic:sourcestatistic:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Sourcestatistic> list = ei.getDataList(Sourcestatistic.class);
			for (Sourcestatistic sourcestatistic : list){
				try{
					sourcestatisticService.save(sourcestatistic);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条工单申告来源统计记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条工单申告来源统计记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入工单申告来源统计失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sourcestatistic/sourcestatistic/?repage";
    }
	
	/**
	 * 下载导入工单申告来源统计数据模板
	 */
	@RequiresPermissions("sourcestatistic:sourcestatistic:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工单申告来源统计数据导入模板.xlsx";
    		List<Sourcestatistic> list = Lists.newArrayList(); 
    		new ExportExcel("工单申告来源统计数据", Sourcestatistic.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sourcestatistic/sourcestatistic/?repage";
    }
	
	/**
	 * 工单申告来源统计列表数据
	 */
	@ResponseBody
	@RequiresPermissions("sourcestatistic:sourcestatistic:list")
	@RequestMapping(value = "data")
	public AjaxJson data(HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxJson j = new AjaxJson();
		try {
			String timeFlag = request.getParameter("timeFlag");
			String groupFlag = request.getParameter("groupFlag");
			String beginCreateDate = request.getParameter("beginCreateDate");
			String endCreateDate = request.getParameter("endCreateDate");
			String sStatus = request.getParameter("sStatus");
			SimpleDateFormat sdf = null;
			if("1".equals(timeFlag)) {//1=日
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else{//2=月
				sdf = new SimpleDateFormat("yyyy-MM");
			}
			Date bDate = sdf.parse(beginCreateDate);
			Date eDate = sdf.parse(endCreateDate);
			
			if("1".equals(groupFlag)) {//1=来源
				Sourcestatistic sourcestatistic = new Sourcestatistic();
				sourcestatistic.setBeginCreateDate(bDate);
				sourcestatistic.setEndCreateDate(eDate);
				sourcestatistic.setTimeFlag(timeFlag);
				sourcestatistic.setGroupFlag(groupFlag);
				sourcestatistic.setsStatus(sStatus);
				j.put("data", sourcestatisticService.findStatisticList(sourcestatistic));
			}else {//2=地市
				Reportsubareastatistic reportsubareastatistic = new Reportsubareastatistic();
				reportsubareastatistic.setBeginCreateDate(bDate);
				reportsubareastatistic.setEndCreateDate(eDate);
				reportsubareastatistic.setTimeFlag(timeFlag);
				reportsubareastatistic.setGroupFlag(groupFlag);
				reportsubareastatistic.setsStatus(sStatus);
				j.put("data", reportsubareastatisticService.findStatisticList(reportsubareastatistic));
			}
			j.setSuccess(true);
			j.setMsg("查询成功!");
		}catch(Exception e){
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("查询失败!");
		}
		return j;
	}
	
	/**
	 * 子柱状图页面
	 */
	@RequestMapping(value = "subChart")
	public String subChart() {
		return "modules/sourcestatistic/sourcestatisticSubChart";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}