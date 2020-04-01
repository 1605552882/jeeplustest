/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialdiscuss.web;

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
import com.jeeplus.modules.specialdiscuss.entity.EdSpecialDiscussLog;
import com.jeeplus.modules.specialdiscuss.service.EdSpecialDiscussLogService;

/**
 * 长者特别情况Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/specialdiscuss/edSpecialDiscussLog")
public class EdSpecialDiscussLogController extends BaseController {

	@Autowired
	private EdSpecialDiscussLogService edSpecialDiscussLogService;
	
	@ModelAttribute
	public EdSpecialDiscussLog get(@RequestParam(required=false) String id) {
		EdSpecialDiscussLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edSpecialDiscussLogService.get(id);
		}
		if (entity == null){
			entity = new EdSpecialDiscussLog();
		}
		return entity;
	}
	
	/**
	 * 长者特别情况列表页面
	 */
	@RequiresPermissions("specialdiscuss:edSpecialDiscussLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/specialdiscuss/edSpecialDiscussLogList";
	}
	
		/**
	 * 长者特别情况列表数据
	 */
	@ResponseBody
	@RequiresPermissions("specialdiscuss:edSpecialDiscussLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdSpecialDiscussLog edSpecialDiscussLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdSpecialDiscussLog> page = edSpecialDiscussLogService.findPage(new Page<EdSpecialDiscussLog>(request, response), edSpecialDiscussLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑长者特别情况表单页面
	 */
	@RequiresPermissions(value={"specialdiscuss:edSpecialDiscussLog:view","specialdiscuss:edSpecialDiscussLog:add","specialdiscuss:edSpecialDiscussLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdSpecialDiscussLog edSpecialDiscussLog, Model model) {
		model.addAttribute("edSpecialDiscussLog", edSpecialDiscussLog);
		if(StringUtils.isBlank(edSpecialDiscussLog.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/specialdiscuss/edSpecialDiscussLogForm";
	}

	/**
	 * 保存长者特别情况
	 */
	@RequiresPermissions(value={"specialdiscuss:edSpecialDiscussLog:add","specialdiscuss:edSpecialDiscussLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(EdSpecialDiscussLog edSpecialDiscussLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, edSpecialDiscussLog)){
			return form(edSpecialDiscussLog, model);
		}
		//新增或编辑表单保存
		edSpecialDiscussLogService.save(edSpecialDiscussLog);//保存
		addMessage(redirectAttributes, "保存长者特别情况成功");
		return "redirect:"+Global.getAdminPath()+"/specialdiscuss/edSpecialDiscussLog/?repage";
	}
	
	/**
	 * 删除长者特别情况
	 */
	@ResponseBody
	@RequiresPermissions("specialdiscuss:edSpecialDiscussLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdSpecialDiscussLog edSpecialDiscussLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edSpecialDiscussLogService.delete(edSpecialDiscussLog);
		j.setMsg("删除长者特别情况成功");
		return j;
	}
	
	/**
	 * 批量删除长者特别情况
	 */
	@ResponseBody
	@RequiresPermissions("specialdiscuss:edSpecialDiscussLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edSpecialDiscussLogService.delete(edSpecialDiscussLogService.get(id));
		}
		j.setMsg("删除长者特别情况成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("specialdiscuss:edSpecialDiscussLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdSpecialDiscussLog edSpecialDiscussLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "长者特别情况"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdSpecialDiscussLog> page = edSpecialDiscussLogService.findPage(new Page<EdSpecialDiscussLog>(request, response, -1), edSpecialDiscussLog);
    		new ExportExcel("长者特别情况", EdSpecialDiscussLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出长者特别情况记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("specialdiscuss:edSpecialDiscussLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdSpecialDiscussLog> list = ei.getDataList(EdSpecialDiscussLog.class);
			for (EdSpecialDiscussLog edSpecialDiscussLog : list){
				try{
					edSpecialDiscussLogService.save(edSpecialDiscussLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条长者特别情况记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条长者特别情况记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入长者特别情况失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/specialdiscuss/edSpecialDiscussLog/?repage";
    }
	
	/**
	 * 下载导入长者特别情况数据模板
	 */
	@RequiresPermissions("specialdiscuss:edSpecialDiscussLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "长者特别情况数据导入模板.xlsx";
    		List<EdSpecialDiscussLog> list = Lists.newArrayList(); 
    		new ExportExcel("长者特别情况数据", EdSpecialDiscussLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/specialdiscuss/edSpecialDiscussLog/?repage";
    }

}