/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.system_parameter.web.system_parameter;

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
import com.jeeplus.modules.system_parameter.entity.system_parameter.SystemParameter;
import com.jeeplus.modules.system_parameter.service.system_parameter.SystemParameterService;

/**
 * 系统参数表Controller
 * @author 姜森焱
 * @version 2020-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/system_parameter/system_parameter/systemParameter")
public class SystemParameterController extends BaseController {

	@Autowired
	private SystemParameterService systemParameterService;
	
	@ModelAttribute
	public SystemParameter get(@RequestParam(required=false) String id) {
		SystemParameter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = systemParameterService.get(id);
		}
		if (entity == null){
			entity = new SystemParameter();
		}
		return entity;
	}
	
	/**
	 * 系统参数列表页面
	 */
	@RequiresPermissions("system_parameter:system_parameter:systemParameter:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/system_parameter/system_parameter/systemParameterList";
	}
	
		/**
	 * 系统参数列表数据
	 */
	@ResponseBody
	@RequiresPermissions("system_parameter:system_parameter:systemParameter:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SystemParameter systemParameter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SystemParameter> page = systemParameterService.findPage(new Page<SystemParameter>(request, response), systemParameter); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑系统参数表单页面
	 */
	@RequiresPermissions(value={"system_parameter:system_parameter:systemParameter:view","system_parameter:system_parameter:systemParameter:add","system_parameter:system_parameter:systemParameter:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SystemParameter systemParameter, Model model) {
		model.addAttribute("systemParameter", systemParameter);
		if(StringUtils.isBlank(systemParameter.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/system_parameter/system_parameter/systemParameterForm";
	}

	/**
	 * 保存系统参数
	 */
	@RequiresPermissions(value={"system_parameter:system_parameter:systemParameter:add","system_parameter:system_parameter:systemParameter:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(SystemParameter systemParameter, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, systemParameter)){
			return form(systemParameter, model);
		}
		//新增或编辑表单保存
		systemParameterService.save(systemParameter);//保存
		addMessage(redirectAttributes, "保存系统参数成功");
		return "redirect:"+Global.getAdminPath()+"/system_parameter/system_parameter/systemParameter/?repage";
	}
	
	/**
	 * 删除系统参数
	 */
	@ResponseBody
	@RequiresPermissions("system_parameter:system_parameter:systemParameter:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SystemParameter systemParameter, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		systemParameterService.delete(systemParameter);
		j.setMsg("删除系统参数成功");
		return j;
	}
	
	/**
	 * 批量删除系统参数
	 */
	@ResponseBody
	@RequiresPermissions("system_parameter:system_parameter:systemParameter:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			systemParameterService.delete(systemParameterService.get(id));
		}
		j.setMsg("删除系统参数成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("system_parameter:system_parameter:systemParameter:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SystemParameter systemParameter, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "系统参数"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SystemParameter> page = systemParameterService.findPage(new Page<SystemParameter>(request, response, -1), systemParameter);
    		new ExportExcel("系统参数", SystemParameter.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出系统参数记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("system_parameter:system_parameter:systemParameter:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SystemParameter> list = ei.getDataList(SystemParameter.class);
			for (SystemParameter systemParameter : list){
				try{
					systemParameterService.save(systemParameter);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条系统参数记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条系统参数记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入系统参数失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/system_parameter/system_parameter/systemParameter/?repage";
    }
	
	/**
	 * 下载导入系统参数数据模板
	 */
	@RequiresPermissions("system_parameter:system_parameter:systemParameter:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "系统参数数据导入模板.xlsx";
    		List<SystemParameter> list = Lists.newArrayList(); 
    		new ExportExcel("系统参数数据", SystemParameter.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/system_parameter/system_parameter/systemParameter/?repage";
    }

}