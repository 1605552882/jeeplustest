/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.activityassess.web;

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
import com.jeeplus.modules.activityassess.entity.EdActivityassessLog;
import com.jeeplus.modules.activityassess.service.EdActivityassessLogService;

/**
 * 日常活动能力评估Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/activityassess/edActivityassessLog")
public class EdActivityassessLogController extends BaseController {

	@Autowired
	private EdActivityassessLogService edActivityassessLogService;
	
	@ModelAttribute
	public EdActivityassessLog get(@RequestParam(required=false) String id) {
		EdActivityassessLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edActivityassessLogService.get(id);
		}
		if (entity == null){
			entity = new EdActivityassessLog();
		}
		return entity;
	}
	
	/**
	 * 日常活动能力评估列表页面
	 */
	@RequiresPermissions("activityassess:edActivityassessLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/activityassess/edActivityassessLogList";
	}
	
		/**
	 * 日常活动能力评估列表数据
	 */
	@ResponseBody
	@RequiresPermissions("activityassess:edActivityassessLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdActivityassessLog edActivityassessLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdActivityassessLog> page = edActivityassessLogService.findPage(new Page<EdActivityassessLog>(request, response), edActivityassessLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑日常活动能力评估表单页面
	 */
	@RequiresPermissions(value={"activityassess:edActivityassessLog:view","activityassess:edActivityassessLog:add","activityassess:edActivityassessLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdActivityassessLog edActivityassessLog, Model model) {
		model.addAttribute("edActivityassessLog", edActivityassessLog);
		return "modules/activityassess/edActivityassessLogForm";
	}

	/**
	 * 保存日常活动能力评估
	 */
	@ResponseBody
	@RequiresPermissions(value={"activityassess:edActivityassessLog:add","activityassess:edActivityassessLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdActivityassessLog edActivityassessLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edActivityassessLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edActivityassessLogService.save(edActivityassessLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存日常活动能力评估成功");
		return j;
	}
	
	/**
	 * 删除日常活动能力评估
	 */
	@ResponseBody
	@RequiresPermissions("activityassess:edActivityassessLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdActivityassessLog edActivityassessLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edActivityassessLogService.delete(edActivityassessLog);
		j.setMsg("删除日常活动能力评估成功");
		return j;
	}
	
	/**
	 * 批量删除日常活动能力评估
	 */
	@ResponseBody
	@RequiresPermissions("activityassess:edActivityassessLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edActivityassessLogService.delete(edActivityassessLogService.get(id));
		}
		j.setMsg("删除日常活动能力评估成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("activityassess:edActivityassessLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdActivityassessLog edActivityassessLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "日常活动能力评估"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdActivityassessLog> page = edActivityassessLogService.findPage(new Page<EdActivityassessLog>(request, response, -1), edActivityassessLog);
    		new ExportExcel("日常活动能力评估", EdActivityassessLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出日常活动能力评估记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("activityassess:edActivityassessLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdActivityassessLog> list = ei.getDataList(EdActivityassessLog.class);
			for (EdActivityassessLog edActivityassessLog : list){
				try{
					edActivityassessLogService.save(edActivityassessLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条日常活动能力评估记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条日常活动能力评估记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入日常活动能力评估失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/activityassess/edActivityassessLog/?repage";
    }
	
	/**
	 * 下载导入日常活动能力评估数据模板
	 */
	@RequiresPermissions("activityassess:edActivityassessLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "日常活动能力评估数据导入模板.xlsx";
    		List<EdActivityassessLog> list = Lists.newArrayList(); 
    		new ExportExcel("日常活动能力评估数据", EdActivityassessLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/activityassess/edActivityassessLog/?repage";
    }

}