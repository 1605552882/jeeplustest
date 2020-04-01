/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.morsefall.web;

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
import com.jeeplus.modules.morsefall.entity.EdMorsefallLog;
import com.jeeplus.modules.morsefall.service.EdMorsefallLogService;

/**
 * 跌倒风险评估Controller
 * @author lukbob
 * @version 2018-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/morsefall/edMorsefallLog")
public class EdMorsefallLogController extends BaseController {

	@Autowired
	private EdMorsefallLogService edMorsefallLogService;
	
	@ModelAttribute
	public EdMorsefallLog get(@RequestParam(required=false) String id) {
		EdMorsefallLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edMorsefallLogService.get(id);
		}
		if (entity == null){
			entity = new EdMorsefallLog();
		}
		return entity;
	}
	
	/**
	 * 跌倒风险评估列表页面
	 */
	@RequiresPermissions("morsefall:edMorsefallLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/morsefall/edMorsefallLogList";
	}
	
		/**
	 * 跌倒风险评估列表数据
	 */
	@ResponseBody
	@RequiresPermissions("morsefall:edMorsefallLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdMorsefallLog edMorsefallLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdMorsefallLog> page = edMorsefallLogService.findPage(new Page<EdMorsefallLog>(request, response), edMorsefallLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑跌倒风险评估表单页面
	 */
	@RequiresPermissions(value={"morsefall:edMorsefallLog:view","morsefall:edMorsefallLog:add","morsefall:edMorsefallLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdMorsefallLog edMorsefallLog, Model model) {
		model.addAttribute("edMorsefallLog", edMorsefallLog);
		return "modules/morsefall/edMorsefallLogForm";
	}

	/**
	 * 保存跌倒风险评估
	 */
	@ResponseBody
	@RequiresPermissions(value={"morsefall:edMorsefallLog:add","morsefall:edMorsefallLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdMorsefallLog edMorsefallLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edMorsefallLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edMorsefallLogService.save(edMorsefallLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存跌倒风险评估成功");
		return j;
	}
	
	/**
	 * 删除跌倒风险评估
	 */
	@ResponseBody
	@RequiresPermissions("morsefall:edMorsefallLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdMorsefallLog edMorsefallLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edMorsefallLogService.delete(edMorsefallLog);
		j.setMsg("删除跌倒风险评估成功");
		return j;
	}
	
	/**
	 * 批量删除跌倒风险评估
	 */
	@ResponseBody
	@RequiresPermissions("morsefall:edMorsefallLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edMorsefallLogService.delete(edMorsefallLogService.get(id));
		}
		j.setMsg("删除跌倒风险评估成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("morsefall:edMorsefallLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdMorsefallLog edMorsefallLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "跌倒风险评估"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdMorsefallLog> page = edMorsefallLogService.findPage(new Page<EdMorsefallLog>(request, response, -1), edMorsefallLog);
    		new ExportExcel("跌倒风险评估", EdMorsefallLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出跌倒风险评估记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("morsefall:edMorsefallLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdMorsefallLog> list = ei.getDataList(EdMorsefallLog.class);
			for (EdMorsefallLog edMorsefallLog : list){
				try{
					edMorsefallLogService.save(edMorsefallLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条跌倒风险评估记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条跌倒风险评估记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入跌倒风险评估失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/morsefall/edMorsefallLog/?repage";
    }
	
	/**
	 * 下载导入跌倒风险评估数据模板
	 */
	@RequiresPermissions("morsefall:edMorsefallLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "跌倒风险评估数据导入模板.xlsx";
    		List<EdMorsefallLog> list = Lists.newArrayList(); 
    		new ExportExcel("跌倒风险评估数据", EdMorsefallLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/morsefall/edMorsefallLog/?repage";
    }

}