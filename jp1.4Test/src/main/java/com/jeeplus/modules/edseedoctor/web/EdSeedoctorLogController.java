/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.edseedoctor.web;

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
import com.jeeplus.modules.edseedoctor.entity.EdSeedoctorLog;
import com.jeeplus.modules.edseedoctor.service.EdSeedoctorLogService;

/**
 * 就医复诊资料Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/edseedoctor/edSeedoctorLog")
public class EdSeedoctorLogController extends BaseController {

	@Autowired
	private EdSeedoctorLogService edSeedoctorLogService;
	
	@ModelAttribute
	public EdSeedoctorLog get(@RequestParam(required=false) String id) {
		EdSeedoctorLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edSeedoctorLogService.get(id);
		}
		if (entity == null){
			entity = new EdSeedoctorLog();
		}
		return entity;
	}
	
	/**
	 * 就医复诊资料列表页面
	 */
	@RequiresPermissions("edseedoctor:edSeedoctorLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/edseedoctor/edSeedoctorLogList";
	}
	
		/**
	 * 就医复诊资料列表数据
	 */
	@ResponseBody
	@RequiresPermissions("edseedoctor:edSeedoctorLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdSeedoctorLog edSeedoctorLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdSeedoctorLog> page = edSeedoctorLogService.findPage(new Page<EdSeedoctorLog>(request, response), edSeedoctorLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑就医复诊资料表单页面
	 */
	@RequiresPermissions(value={"edseedoctor:edSeedoctorLog:view","edseedoctor:edSeedoctorLog:add","edseedoctor:edSeedoctorLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdSeedoctorLog edSeedoctorLog, Model model) {
		model.addAttribute("edSeedoctorLog", edSeedoctorLog);
		return "modules/edseedoctor/edSeedoctorLogForm";
	}

	/**
	 * 保存就医复诊资料
	 */
	@ResponseBody
	@RequiresPermissions(value={"edseedoctor:edSeedoctorLog:add","edseedoctor:edSeedoctorLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdSeedoctorLog edSeedoctorLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edSeedoctorLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edSeedoctorLogService.save(edSeedoctorLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存就医复诊资料成功");
		return j;
	}
	
	/**
	 * 删除就医复诊资料
	 */
	@ResponseBody
	@RequiresPermissions("edseedoctor:edSeedoctorLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdSeedoctorLog edSeedoctorLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edSeedoctorLogService.delete(edSeedoctorLog);
		j.setMsg("删除就医复诊资料成功");
		return j;
	}
	
	/**
	 * 批量删除就医复诊资料
	 */
	@ResponseBody
	@RequiresPermissions("edseedoctor:edSeedoctorLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edSeedoctorLogService.delete(edSeedoctorLogService.get(id));
		}
		j.setMsg("删除就医复诊资料成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("edseedoctor:edSeedoctorLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdSeedoctorLog edSeedoctorLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "就医复诊资料"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdSeedoctorLog> page = edSeedoctorLogService.findPage(new Page<EdSeedoctorLog>(request, response, -1), edSeedoctorLog);
    		new ExportExcel("就医复诊资料", EdSeedoctorLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出就医复诊资料记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("edseedoctor:edSeedoctorLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdSeedoctorLog> list = ei.getDataList(EdSeedoctorLog.class);
			for (EdSeedoctorLog edSeedoctorLog : list){
				try{
					edSeedoctorLogService.save(edSeedoctorLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条就医复诊资料记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条就医复诊资料记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入就医复诊资料失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/edseedoctor/edSeedoctorLog/?repage";
    }
	
	/**
	 * 下载导入就医复诊资料数据模板
	 */
	@RequiresPermissions("edseedoctor:edSeedoctorLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "就医复诊资料数据导入模板.xlsx";
    		List<EdSeedoctorLog> list = Lists.newArrayList(); 
    		new ExportExcel("就医复诊资料数据", EdSeedoctorLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/edseedoctor/edSeedoctorLog/?repage";
    }

}