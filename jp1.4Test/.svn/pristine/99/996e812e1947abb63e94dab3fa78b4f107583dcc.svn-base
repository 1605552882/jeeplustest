/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treatmentorder.web;

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
import com.jeeplus.modules.treatmentorder.entity.SdTreatmentOrderLog;
import com.jeeplus.modules.treatmentorder.service.SdTreatmentOrderLogService;

/**
 * 求医订单Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/treatmentorder/sdTreatmentOrderLog")
public class SdTreatmentOrderLogController extends BaseController {

	@Autowired
	private SdTreatmentOrderLogService sdTreatmentOrderLogService;
	
	@ModelAttribute
	public SdTreatmentOrderLog get(@RequestParam(required=false) String id) {
		SdTreatmentOrderLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sdTreatmentOrderLogService.get(id);
		}
		if (entity == null){
			entity = new SdTreatmentOrderLog();
		}
		return entity;
	}
	
	/**
	 * 求医订单列表页面
	 */
	@RequiresPermissions("treatmentorder:sdTreatmentOrderLog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/treatmentorder/sdTreatmentOrderLogList";
	}
	
		/**
	 * 求医订单列表数据
	 */
	@ResponseBody
	@RequiresPermissions("treatmentorder:sdTreatmentOrderLog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SdTreatmentOrderLog sdTreatmentOrderLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SdTreatmentOrderLog> page = sdTreatmentOrderLogService.findPage(new Page<SdTreatmentOrderLog>(request, response), sdTreatmentOrderLog); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑求医订单表单页面
	 */
	@RequiresPermissions(value={"treatmentorder:sdTreatmentOrderLog:view","treatmentorder:sdTreatmentOrderLog:add","treatmentorder:sdTreatmentOrderLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SdTreatmentOrderLog sdTreatmentOrderLog, Model model) {
		model.addAttribute("sdTreatmentOrderLog", sdTreatmentOrderLog);
		return "modules/treatmentorder/sdTreatmentOrderLogForm";
	}

	/**
	 * 保存求医订单
	 */
	@ResponseBody
	@RequiresPermissions(value={"treatmentorder:sdTreatmentOrderLog:add","treatmentorder:sdTreatmentOrderLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SdTreatmentOrderLog sdTreatmentOrderLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sdTreatmentOrderLog)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		sdTreatmentOrderLogService.save(sdTreatmentOrderLog);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存求医订单成功");
		return j;
	}
	
	/**
	 * 删除求医订单
	 */
	@ResponseBody
	@RequiresPermissions("treatmentorder:sdTreatmentOrderLog:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SdTreatmentOrderLog sdTreatmentOrderLog, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sdTreatmentOrderLogService.delete(sdTreatmentOrderLog);
		j.setMsg("删除求医订单成功");
		return j;
	}
	
	/**
	 * 批量删除求医订单
	 */
	@ResponseBody
	@RequiresPermissions("treatmentorder:sdTreatmentOrderLog:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sdTreatmentOrderLogService.delete(sdTreatmentOrderLogService.get(id));
		}
		j.setMsg("删除求医订单成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("treatmentorder:sdTreatmentOrderLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SdTreatmentOrderLog sdTreatmentOrderLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "求医订单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SdTreatmentOrderLog> page = sdTreatmentOrderLogService.findPage(new Page<SdTreatmentOrderLog>(request, response, -1), sdTreatmentOrderLog);
    		new ExportExcel("求医订单", SdTreatmentOrderLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出求医订单记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("treatmentorder:sdTreatmentOrderLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SdTreatmentOrderLog> list = ei.getDataList(SdTreatmentOrderLog.class);
			for (SdTreatmentOrderLog sdTreatmentOrderLog : list){
				try{
					sdTreatmentOrderLogService.save(sdTreatmentOrderLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条求医订单记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条求医订单记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入求医订单失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/treatmentorder/sdTreatmentOrderLog/?repage";
    }
	
	/**
	 * 下载导入求医订单数据模板
	 */
	@RequiresPermissions("treatmentorder:sdTreatmentOrderLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "求医订单数据导入模板.xlsx";
    		List<SdTreatmentOrderLog> list = Lists.newArrayList(); 
    		new ExportExcel("求医订单数据", SdTreatmentOrderLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/treatmentorder/sdTreatmentOrderLog/?repage";
    }

}