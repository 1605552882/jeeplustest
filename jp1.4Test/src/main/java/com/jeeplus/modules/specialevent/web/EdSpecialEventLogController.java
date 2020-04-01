/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.specialevent.web;

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
import com.jeeplus.modules.specialevent.entity.EdSpecialEventLog;
import com.jeeplus.modules.specialevent.service.EdSpecialEventLogService;

/**
 * 长者特殊事件Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/specialevent/edSpecialEventLog")
public class EdSpecialEventLogController extends BaseController {

	@Autowired
	private EdSpecialEventLogService edSpecialEventLogService;
	
	@ModelAttribute
	public EdSpecialEventLog get(@RequestParam(required=false) String id) {
		EdSpecialEventLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edSpecialEventLogService.get(id);
		}
		if (entity == null){
			entity = new EdSpecialEventLog();
		}
		return entity;
	}
	
	/**
	 * 长者特殊事件列表页面
	 */
	@RequiresPermissions("specialevent:edSpecialEventLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/specialevent/edSpecialEventLogList";
	}
	
		/**
	 * 长者特殊事件列表数据
	 */
	@ResponseBody
	@RequiresPermissions("specialevent:edSpecialEventLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdSpecialEventLog edSpecialEventLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdSpecialEventLog> page = edSpecialEventLogService.findPage(new Page<EdSpecialEventLog>(request, response), edSpecialEventLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑长者特殊事件表单页面
	 */
	@RequiresPermissions(value={"specialevent:edSpecialEventLog:view","specialevent:edSpecialEventLog:add","specialevent:edSpecialEventLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdSpecialEventLog edSpecialEventLog, Model model) {
		model.addAttribute("edSpecialEventLog", edSpecialEventLog);
		if(StringUtils.isBlank(edSpecialEventLog.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/specialevent/edSpecialEventLogForm";
	}

	/**
	 * 保存长者特殊事件
	 */
	@RequiresPermissions(value={"specialevent:edSpecialEventLog:add","specialevent:edSpecialEventLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(EdSpecialEventLog edSpecialEventLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, edSpecialEventLog)){
			return form(edSpecialEventLog, model);
		}
		//新增或编辑表单保存
		edSpecialEventLogService.save(edSpecialEventLog);//保存
		addMessage(redirectAttributes, "保存长者特殊事件成功");
		return "redirect:"+Global.getAdminPath()+"/specialevent/edSpecialEventLog/?repage";
	}
	
	/**
	 * 删除长者特殊事件
	 */
	@ResponseBody
	@RequiresPermissions("specialevent:edSpecialEventLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdSpecialEventLog edSpecialEventLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edSpecialEventLogService.delete(edSpecialEventLog);
		j.setMsg("删除长者特殊事件成功");
		return j;
	}
	
	/**
	 * 批量删除长者特殊事件
	 */
	@ResponseBody
	@RequiresPermissions("specialevent:edSpecialEventLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edSpecialEventLogService.delete(edSpecialEventLogService.get(id));
		}
		j.setMsg("删除长者特殊事件成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("specialevent:edSpecialEventLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdSpecialEventLog edSpecialEventLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "长者特殊事件"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdSpecialEventLog> page = edSpecialEventLogService.findPage(new Page<EdSpecialEventLog>(request, response, -1), edSpecialEventLog);
    		new ExportExcel("长者特殊事件", EdSpecialEventLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出长者特殊事件记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("specialevent:edSpecialEventLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdSpecialEventLog> list = ei.getDataList(EdSpecialEventLog.class);
			for (EdSpecialEventLog edSpecialEventLog : list){
				try{
					edSpecialEventLogService.save(edSpecialEventLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条长者特殊事件记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条长者特殊事件记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入长者特殊事件失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/specialevent/edSpecialEventLog/?repage";
    }
	
	/**
	 * 下载导入长者特殊事件数据模板
	 */
	@RequiresPermissions("specialevent:edSpecialEventLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "长者特殊事件数据导入模板.xlsx";
    		List<EdSpecialEventLog> list = Lists.newArrayList(); 
    		new ExportExcel("长者特殊事件数据", EdSpecialEventLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/specialevent/edSpecialEventLog/?repage";
    }

}