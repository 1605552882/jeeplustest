/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.braden.web;

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
import com.jeeplus.modules.braden.entity.EdBradenLog;
import com.jeeplus.modules.braden.service.EdBradenLogService;

/**
 * 贝登量表Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/braden/edBradenLog")
public class EdBradenLogController extends BaseController {

	@Autowired
	private EdBradenLogService edBradenLogService;
	
	@ModelAttribute
	public EdBradenLog get(@RequestParam(required=false) String id) {
		EdBradenLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edBradenLogService.get(id);
		}
		if (entity == null){
			entity = new EdBradenLog();
		}
		return entity;
	}
	
	/**
	 * 贝登量表列表页面
	 */
	@RequiresPermissions("braden:edBradenLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/braden/edBradenLogList";
	}
	
		/**
	 * 贝登量表列表数据
	 */
	@ResponseBody
	@RequiresPermissions("braden:edBradenLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdBradenLog edBradenLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdBradenLog> page = edBradenLogService.findPage(new Page<EdBradenLog>(request, response), edBradenLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑贝登量表表单页面
	 */
	@RequiresPermissions(value={"braden:edBradenLog:view","braden:edBradenLog:add","braden:edBradenLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdBradenLog edBradenLog, Model model) {
		model.addAttribute("edBradenLog", edBradenLog);
		return "modules/braden/edBradenLogForm";
	}

	/**
	 * 保存贝登量表
	 */
	@ResponseBody
	@RequiresPermissions(value={"braden:edBradenLog:add","braden:edBradenLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdBradenLog edBradenLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edBradenLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edBradenLogService.save(edBradenLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存贝登量表成功");
		return j;
	}
	
	/**
	 * 删除贝登量表
	 */
	@ResponseBody
	@RequiresPermissions("braden:edBradenLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdBradenLog edBradenLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edBradenLogService.delete(edBradenLog);
		j.setMsg("删除贝登量表成功");
		return j;
	}
	
	/**
	 * 批量删除贝登量表
	 */
	@ResponseBody
	@RequiresPermissions("braden:edBradenLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edBradenLogService.delete(edBradenLogService.get(id));
		}
		j.setMsg("删除贝登量表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("braden:edBradenLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdBradenLog edBradenLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "贝登量表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdBradenLog> page = edBradenLogService.findPage(new Page<EdBradenLog>(request, response, -1), edBradenLog);
    		new ExportExcel("贝登量表", EdBradenLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出贝登量表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("braden:edBradenLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdBradenLog> list = ei.getDataList(EdBradenLog.class);
			for (EdBradenLog edBradenLog : list){
				try{
					edBradenLogService.save(edBradenLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条贝登量表记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条贝登量表记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入贝登量表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/braden/edBradenLog/?repage";
    }
	
	/**
	 * 下载导入贝登量表数据模板
	 */
	@RequiresPermissions("braden:edBradenLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "贝登量表数据导入模板.xlsx";
    		List<EdBradenLog> list = Lists.newArrayList(); 
    		new ExportExcel("贝登量表数据", EdBradenLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/braden/edBradenLog/?repage";
    }

}