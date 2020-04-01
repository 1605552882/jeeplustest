/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.nursingclass.web;

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
import com.jeeplus.modules.nursingclass.entity.EdNursingclassLog;
import com.jeeplus.modules.nursingclass.service.EdNursingclassLogService;

/**
 * 护理级别Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/nursingclass/edNursingclassLog")
public class EdNursingclassLogController extends BaseController {

	@Autowired
	private EdNursingclassLogService edNursingclassLogService;
	
	@ModelAttribute
	public EdNursingclassLog get(@RequestParam(required=false) String id) {
		EdNursingclassLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edNursingclassLogService.get(id);
		}
		if (entity == null){
			entity = new EdNursingclassLog();
		}
		return entity;
	}
	
	/**
	 * 护理级别列表页面
	 */
	@RequiresPermissions("nursingclass:edNursingclassLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/nursingclass/edNursingclassLogList";
	}
	
		/**
	 * 护理级别列表数据
	 */
	@ResponseBody
	@RequiresPermissions("nursingclass:edNursingclassLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdNursingclassLog edNursingclassLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdNursingclassLog> page = edNursingclassLogService.findPage(new Page<EdNursingclassLog>(request, response), edNursingclassLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑护理级别表单页面
	 */
	@RequiresPermissions(value={"nursingclass:edNursingclassLog:view","nursingclass:edNursingclassLog:add","nursingclass:edNursingclassLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdNursingclassLog edNursingclassLog, Model model) {
		model.addAttribute("edNursingclassLog", edNursingclassLog);
		return "modules/nursingclass/edNursingclassLogForm";
	}

	/**
	 * 保存护理级别
	 */
	@ResponseBody
	@RequiresPermissions(value={"nursingclass:edNursingclassLog:add","nursingclass:edNursingclassLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdNursingclassLog edNursingclassLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edNursingclassLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edNursingclassLogService.save(edNursingclassLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存护理级别成功");
		return j;
	}
	
	/**
	 * 删除护理级别
	 */
	@ResponseBody
	@RequiresPermissions("nursingclass:edNursingclassLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdNursingclassLog edNursingclassLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edNursingclassLogService.delete(edNursingclassLog);
		j.setMsg("删除护理级别成功");
		return j;
	}
	
	/**
	 * 批量删除护理级别
	 */
	@ResponseBody
	@RequiresPermissions("nursingclass:edNursingclassLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edNursingclassLogService.delete(edNursingclassLogService.get(id));
		}
		j.setMsg("删除护理级别成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("nursingclass:edNursingclassLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdNursingclassLog edNursingclassLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "护理级别"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdNursingclassLog> page = edNursingclassLogService.findPage(new Page<EdNursingclassLog>(request, response, -1), edNursingclassLog);
    		new ExportExcel("护理级别", EdNursingclassLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出护理级别记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("nursingclass:edNursingclassLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdNursingclassLog> list = ei.getDataList(EdNursingclassLog.class);
			for (EdNursingclassLog edNursingclassLog : list){
				try{
					edNursingclassLogService.save(edNursingclassLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条护理级别记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条护理级别记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入护理级别失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/nursingclass/edNursingclassLog/?repage";
    }
	
	/**
	 * 下载导入护理级别数据模板
	 */
	@RequiresPermissions("nursingclass:edNursingclassLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "护理级别数据导入模板.xlsx";
    		List<EdNursingclassLog> list = Lists.newArrayList(); 
    		new ExportExcel("护理级别数据", EdNursingclassLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/nursingclass/edNursingclassLog/?repage";
    }

}