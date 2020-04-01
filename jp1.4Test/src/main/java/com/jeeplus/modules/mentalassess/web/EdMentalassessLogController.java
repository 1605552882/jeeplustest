/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mentalassess.web;

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
import com.jeeplus.modules.mentalassess.entity.EdMentalassessLog;
import com.jeeplus.modules.mentalassess.service.EdMentalassessLogService;

/**
 * 心理社交评估Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mentalassess/edMentalassessLog")
public class EdMentalassessLogController extends BaseController {

	@Autowired
	private EdMentalassessLogService edMentalassessLogService;
	
	@ModelAttribute
	public EdMentalassessLog get(@RequestParam(required=false) String id) {
		EdMentalassessLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edMentalassessLogService.get(id);
		}
		if (entity == null){
			entity = new EdMentalassessLog();
		}
		return entity;
	}
	
	/**
	 * 心理社交评估列表页面
	 */
	@RequiresPermissions("mentalassess:edMentalassessLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mentalassess/edMentalassessLogList";
	}
	
		/**
	 * 心理社交评估列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mentalassess:edMentalassessLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdMentalassessLog edMentalassessLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdMentalassessLog> page = edMentalassessLogService.findPage(new Page<EdMentalassessLog>(request, response), edMentalassessLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑心理社交评估表单页面
	 */
	@RequiresPermissions(value={"mentalassess:edMentalassessLog:view","mentalassess:edMentalassessLog:add","mentalassess:edMentalassessLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdMentalassessLog edMentalassessLog, Model model) {
		model.addAttribute("edMentalassessLog", edMentalassessLog);
		return "modules/mentalassess/edMentalassessLogForm";
	}

	/**
	 * 保存心理社交评估
	 */
	@ResponseBody
	@RequiresPermissions(value={"mentalassess:edMentalassessLog:add","mentalassess:edMentalassessLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdMentalassessLog edMentalassessLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edMentalassessLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edMentalassessLogService.save(edMentalassessLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存心理社交评估成功");
		return j;
	}
	
	/**
	 * 删除心理社交评估
	 */
	@ResponseBody
	@RequiresPermissions("mentalassess:edMentalassessLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdMentalassessLog edMentalassessLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edMentalassessLogService.delete(edMentalassessLog);
		j.setMsg("删除心理社交评估成功");
		return j;
	}
	
	/**
	 * 批量删除心理社交评估
	 */
	@ResponseBody
	@RequiresPermissions("mentalassess:edMentalassessLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edMentalassessLogService.delete(edMentalassessLogService.get(id));
		}
		j.setMsg("删除心理社交评估成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mentalassess:edMentalassessLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdMentalassessLog edMentalassessLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "心理社交评估"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdMentalassessLog> page = edMentalassessLogService.findPage(new Page<EdMentalassessLog>(request, response, -1), edMentalassessLog);
    		new ExportExcel("心理社交评估", EdMentalassessLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出心理社交评估记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mentalassess:edMentalassessLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdMentalassessLog> list = ei.getDataList(EdMentalassessLog.class);
			for (EdMentalassessLog edMentalassessLog : list){
				try{
					edMentalassessLogService.save(edMentalassessLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条心理社交评估记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条心理社交评估记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入心理社交评估失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mentalassess/edMentalassessLog/?repage";
    }
	
	/**
	 * 下载导入心理社交评估数据模板
	 */
	@RequiresPermissions("mentalassess:edMentalassessLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "心理社交评估数据导入模板.xlsx";
    		List<EdMentalassessLog> list = Lists.newArrayList(); 
    		new ExportExcel("心理社交评估数据", EdMentalassessLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mentalassess/edMentalassessLog/?repage";
    }

}