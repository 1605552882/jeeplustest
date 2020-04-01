/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.nutrition.web;

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
import com.jeeplus.modules.nutrition.entity.EdNutritionLog;
import com.jeeplus.modules.nutrition.service.EdNutritionLogService;

/**
 * 营养筛选问卷Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/nutrition/edNutritionLog")
public class EdNutritionLogController extends BaseController {

	@Autowired
	private EdNutritionLogService edNutritionLogService;
	
	@ModelAttribute
	public EdNutritionLog get(@RequestParam(required=false) String id) {
		EdNutritionLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edNutritionLogService.get(id);
		}
		if (entity == null){
			entity = new EdNutritionLog();
		}
		return entity;
	}
	
	/**
	 * 营养筛选问卷列表页面
	 */
	@RequiresPermissions("nutrition:edNutritionLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/nutrition/edNutritionLogList";
	}
	
		/**
	 * 营养筛选问卷列表数据
	 */
	@ResponseBody
	@RequiresPermissions("nutrition:edNutritionLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdNutritionLog edNutritionLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdNutritionLog> page = edNutritionLogService.findPage(new Page<EdNutritionLog>(request, response), edNutritionLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑营养筛选问卷表单页面
	 */
	@RequiresPermissions(value={"nutrition:edNutritionLog:view","nutrition:edNutritionLog:add","nutrition:edNutritionLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdNutritionLog edNutritionLog, Model model) {
		model.addAttribute("edNutritionLog", edNutritionLog);
		return "modules/nutrition/edNutritionLogForm";
	}

	/**
	 * 保存营养筛选问卷
	 */
	@ResponseBody
	@RequiresPermissions(value={"nutrition:edNutritionLog:add","nutrition:edNutritionLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdNutritionLog edNutritionLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edNutritionLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edNutritionLogService.save(edNutritionLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存营养筛选问卷成功");
		return j;
	}
	
	/**
	 * 删除营养筛选问卷
	 */
	@ResponseBody
	@RequiresPermissions("nutrition:edNutritionLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdNutritionLog edNutritionLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edNutritionLogService.delete(edNutritionLog);
		j.setMsg("删除营养筛选问卷成功");
		return j;
	}
	
	/**
	 * 批量删除营养筛选问卷
	 */
	@ResponseBody
	@RequiresPermissions("nutrition:edNutritionLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edNutritionLogService.delete(edNutritionLogService.get(id));
		}
		j.setMsg("删除营养筛选问卷成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("nutrition:edNutritionLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdNutritionLog edNutritionLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "营养筛选问卷"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdNutritionLog> page = edNutritionLogService.findPage(new Page<EdNutritionLog>(request, response, -1), edNutritionLog);
    		new ExportExcel("营养筛选问卷", EdNutritionLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出营养筛选问卷记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("nutrition:edNutritionLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdNutritionLog> list = ei.getDataList(EdNutritionLog.class);
			for (EdNutritionLog edNutritionLog : list){
				try{
					edNutritionLogService.save(edNutritionLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条营养筛选问卷记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条营养筛选问卷记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入营养筛选问卷失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/nutrition/edNutritionLog/?repage";
    }
	
	/**
	 * 下载导入营养筛选问卷数据模板
	 */
	@RequiresPermissions("nutrition:edNutritionLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "营养筛选问卷数据导入模板.xlsx";
    		List<EdNutritionLog> list = Lists.newArrayList(); 
    		new ExportExcel("营养筛选问卷数据", EdNutritionLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/nutrition/edNutritionLog/?repage";
    }

}