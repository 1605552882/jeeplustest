/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treatmentreq.web;

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
import com.jeeplus.modules.treatmentreq.entity.SdTreatmentReqLog;
import com.jeeplus.modules.treatmentreq.service.SdTreatmentReqLogService;

/**
 * 求医记录Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/treatmentreq/sdTreatmentReqLog")
public class SdTreatmentReqLogController extends BaseController {

	@Autowired
	private SdTreatmentReqLogService sdTreatmentReqLogService;
	
	@ModelAttribute
	public SdTreatmentReqLog get(@RequestParam(required=false) String id) {
		SdTreatmentReqLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sdTreatmentReqLogService.get(id);
		}
		if (entity == null){
			entity = new SdTreatmentReqLog();
		}
		return entity;
	}
	
	/**
	 * 求医记录列表页面
	 */
	@RequiresPermissions("treatmentreq:sdTreatmentReqLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/treatmentreq/sdTreatmentReqLogList";
	}
	
		/**
	 * 求医记录列表数据
	 */
	@ResponseBody
	@RequiresPermissions("treatmentreq:sdTreatmentReqLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SdTreatmentReqLog sdTreatmentReqLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SdTreatmentReqLog> page = sdTreatmentReqLogService.findPage(new Page<SdTreatmentReqLog>(request, response), sdTreatmentReqLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑求医记录表单页面
	 */
	@RequiresPermissions(value={"treatmentreq:sdTreatmentReqLog:view","treatmentreq:sdTreatmentReqLog:add","treatmentreq:sdTreatmentReqLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SdTreatmentReqLog sdTreatmentReqLog, Model model) {
		model.addAttribute("sdTreatmentReqLog", sdTreatmentReqLog);
		return "modules/treatmentreq/sdTreatmentReqLogForm";
	}

	/**
	 * 保存求医记录
	 */
	@ResponseBody
	@RequiresPermissions(value={"treatmentreq:sdTreatmentReqLog:add","treatmentreq:sdTreatmentReqLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SdTreatmentReqLog sdTreatmentReqLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sdTreatmentReqLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		sdTreatmentReqLogService.save(sdTreatmentReqLog);//保存
		j.setSuccess(true);
		j.setMsg("保存求医记录成功");
		return j;
	}
	
	/**
	 * 删除求医记录
	 */
	@ResponseBody
	@RequiresPermissions("treatmentreq:sdTreatmentReqLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SdTreatmentReqLog sdTreatmentReqLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sdTreatmentReqLogService.delete(sdTreatmentReqLog);
		j.setMsg("删除求医记录成功");
		return j;
	}
	
	/**
	 * 批量删除求医记录
	 */
	@ResponseBody
	@RequiresPermissions("treatmentreq:sdTreatmentReqLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sdTreatmentReqLogService.delete(sdTreatmentReqLogService.get(id));
		}
		j.setMsg("删除求医记录成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("treatmentreq:sdTreatmentReqLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SdTreatmentReqLog sdTreatmentReqLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "求医记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SdTreatmentReqLog> page = sdTreatmentReqLogService.findPage(new Page<SdTreatmentReqLog>(request, response, -1), sdTreatmentReqLog);
    		new ExportExcel("求医记录", SdTreatmentReqLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出求医记录记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public SdTreatmentReqLog detail(String id) {
		return sdTreatmentReqLogService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("treatmentreq:sdTreatmentReqLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SdTreatmentReqLog> list = ei.getDataList(SdTreatmentReqLog.class);
			for (SdTreatmentReqLog sdTreatmentReqLog : list){
				try{
					sdTreatmentReqLogService.save(sdTreatmentReqLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条求医记录记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条求医记录记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入求医记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/treatmentreq/sdTreatmentReqLog/?repage";
    }
	
	/**
	 * 下载导入求医记录数据模板
	 */
	@RequiresPermissions("treatmentreq:sdTreatmentReqLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "求医记录数据导入模板.xlsx";
    		List<SdTreatmentReqLog> list = Lists.newArrayList(); 
    		new ExportExcel("求医记录数据", SdTreatmentReqLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/treatmentreq/sdTreatmentReqLog/?repage";
    }
	

}