/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.medication.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.jeeplus.modules.medication.entity.EdMedicationLog;
import com.jeeplus.modules.medication.service.EdMedicationLogService;

/**
 * 长者用药情况Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/medication/edMedicationLog")
public class EdMedicationLogController extends BaseController {

	@Autowired
	private EdMedicationLogService edMedicationLogService;
	
	@ModelAttribute
	public EdMedicationLog get(@RequestParam(required=false) String id) {
		EdMedicationLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edMedicationLogService.get(id);
		}
		if (entity == null){
			entity = new EdMedicationLog();
		}
		return entity;
	}
	
	/**
	 * 长者用药情况列表页面
	 */
	@RequiresPermissions("medication:edMedicationLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/medication/edMedicationLogList";
	}
	
		/**
	 * 长者用药情况列表数据
	 */
	@ResponseBody
	@RequiresPermissions("medication:edMedicationLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdMedicationLog edMedicationLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdMedicationLog> page = edMedicationLogService.findPage(new Page<EdMedicationLog>(request, response), edMedicationLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑长者用药情况表单页面
	 */
	@RequiresPermissions(value={"medication:edMedicationLog:view","medication:edMedicationLog:add","medication:edMedicationLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdMedicationLog edMedicationLog, Model model) {
		model.addAttribute("edMedicationLog", edMedicationLog);
		if(StringUtils.isBlank(edMedicationLog.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/medication/edMedicationLogForm";
	}

	/**
	 * 保存长者用药情况
	 */
	@RequiresPermissions(value={"medication:edMedicationLog:add","medication:edMedicationLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(EdMedicationLog edMedicationLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, edMedicationLog)){
			return form(edMedicationLog, model);
		}
		//新增或编辑表单保存
		edMedicationLogService.save(edMedicationLog);//保存
		addMessage(redirectAttributes, "保存长者用药情况成功");
		return "redirect:"+Global.getAdminPath()+"/medication/edMedicationLog/?repage";
	}
	
	/**
	 * 删除长者用药情况
	 */
	@ResponseBody
	@RequiresPermissions("medication:edMedicationLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdMedicationLog edMedicationLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edMedicationLogService.delete(edMedicationLog);
		j.setMsg("删除长者用药情况成功");
		return j;
	}
	
	/**
	 * 批量删除长者用药情况
	 */
	@ResponseBody
	@RequiresPermissions("medication:edMedicationLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edMedicationLogService.delete(edMedicationLogService.get(id));
		}
		j.setMsg("删除长者用药情况成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("medication:edMedicationLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdMedicationLog edMedicationLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "长者用药情况"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdMedicationLog> page = edMedicationLogService.findPage(new Page<EdMedicationLog>(request, response, -1), edMedicationLog);
    		new ExportExcel("长者用药情况", EdMedicationLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出长者用药情况记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("medication:edMedicationLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdMedicationLog> list = ei.getDataList(EdMedicationLog.class);
			for (EdMedicationLog edMedicationLog : list){
				try{
					edMedicationLogService.save(edMedicationLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条长者用药情况记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条长者用药情况记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入长者用药情况失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/medication/edMedicationLog/?repage";
    }
	
	/**
	 * 下载导入长者用药情况数据模板
	 */
	@RequiresPermissions("medication:edMedicationLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "长者用药情况数据导入模板.xlsx";
    		List<EdMedicationLog> list = Lists.newArrayList(); 
    		new ExportExcel("长者用药情况数据", EdMedicationLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/medication/edMedicationLog/?repage";
    }

}