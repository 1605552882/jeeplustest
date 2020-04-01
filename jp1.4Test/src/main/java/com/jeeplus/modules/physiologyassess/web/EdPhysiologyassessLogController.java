/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.physiologyassess.web;

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
import com.jeeplus.modules.physiologyassess.entity.EdPhysiologyassessLog;
import com.jeeplus.modules.physiologyassess.service.EdPhysiologyassessLogService;

/**
 * 生理状况评估Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/physiologyassess/edPhysiologyassessLog")
public class EdPhysiologyassessLogController extends BaseController {

	@Autowired
	private EdPhysiologyassessLogService edPhysiologyassessLogService;
	
	@ModelAttribute
	public EdPhysiologyassessLog get(@RequestParam(required=false) String id) {
		EdPhysiologyassessLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edPhysiologyassessLogService.get(id);
		}
		if (entity == null){
			entity = new EdPhysiologyassessLog();
		}
		return entity;
	}
	
	/**
	 * 生理状况评估列表页面
	 */
	@RequiresPermissions("physiologyassess:edPhysiologyassessLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/physiologyassess/edPhysiologyassessLogList";
	}
	
		/**
	 * 生理状况评估列表数据
	 */
	@ResponseBody
	@RequiresPermissions("physiologyassess:edPhysiologyassessLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdPhysiologyassessLog edPhysiologyassessLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdPhysiologyassessLog> page = edPhysiologyassessLogService.findPage(new Page<EdPhysiologyassessLog>(request, response), edPhysiologyassessLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑生理状况评估表单页面
	 */
	@RequiresPermissions(value={"physiologyassess:edPhysiologyassessLog:view","physiologyassess:edPhysiologyassessLog:add","physiologyassess:edPhysiologyassessLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdPhysiologyassessLog edPhysiologyassessLog, Model model) {
		model.addAttribute("edPhysiologyassessLog", edPhysiologyassessLog);
		return "modules/physiologyassess/edPhysiologyassessLogForm";
	}

	/**
	 * 保存生理状况评估
	 */
	@ResponseBody
	@RequiresPermissions(value={"physiologyassess:edPhysiologyassessLog:add","physiologyassess:edPhysiologyassessLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdPhysiologyassessLog edPhysiologyassessLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edPhysiologyassessLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edPhysiologyassessLogService.save(edPhysiologyassessLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存生理状况评估成功");
		return j;
	}
	
	/**
	 * 删除生理状况评估
	 */
	@ResponseBody
	@RequiresPermissions("physiologyassess:edPhysiologyassessLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdPhysiologyassessLog edPhysiologyassessLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edPhysiologyassessLogService.delete(edPhysiologyassessLog);
		j.setMsg("删除生理状况评估成功");
		return j;
	}
	
	/**
	 * 批量删除生理状况评估
	 */
	@ResponseBody
	@RequiresPermissions("physiologyassess:edPhysiologyassessLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edPhysiologyassessLogService.delete(edPhysiologyassessLogService.get(id));
		}
		j.setMsg("删除生理状况评估成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("physiologyassess:edPhysiologyassessLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdPhysiologyassessLog edPhysiologyassessLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "生理状况评估"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdPhysiologyassessLog> page = edPhysiologyassessLogService.findPage(new Page<EdPhysiologyassessLog>(request, response, -1), edPhysiologyassessLog);
    		new ExportExcel("生理状况评估", EdPhysiologyassessLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出生理状况评估记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("physiologyassess:edPhysiologyassessLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdPhysiologyassessLog> list = ei.getDataList(EdPhysiologyassessLog.class);
			for (EdPhysiologyassessLog edPhysiologyassessLog : list){
				try{
					edPhysiologyassessLogService.save(edPhysiologyassessLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条生理状况评估记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条生理状况评估记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入生理状况评估失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/physiologyassess/edPhysiologyassessLog/?repage";
    }
	
	/**
	 * 下载导入生理状况评估数据模板
	 */
	@RequiresPermissions("physiologyassess:edPhysiologyassessLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "生理状况评估数据导入模板.xlsx";
    		List<EdPhysiologyassessLog> list = Lists.newArrayList(); 
    		new ExportExcel("生理状况评估数据", EdPhysiologyassessLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/physiologyassess/edPhysiologyassessLog/?repage";
    }

}