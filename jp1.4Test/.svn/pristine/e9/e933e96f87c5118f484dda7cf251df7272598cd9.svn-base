/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.patient.web;

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
import com.jeeplus.modules.patient.entity.SdPatientInf;
import com.jeeplus.modules.patient.service.SdPatientInfService;

/**
 * 病人基础信息Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/patient/sdPatientInf")
public class SdPatientInfController extends BaseController {

	@Autowired
	private SdPatientInfService sdPatientInfService;
	
	@ModelAttribute
	public SdPatientInf get(@RequestParam(required=false) String id) {
		SdPatientInf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sdPatientInfService.get(id);
		}
		if (entity == null){
			entity = new SdPatientInf();
		}
		return entity;
	}
	
	/**
	 * 病人列表页面
	 */
	@RequiresPermissions("patient:sdPatientInf:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/patient/sdPatientInfList";
	}
	
		/**
	 * 病人列表数据
	 */
	@ResponseBody
	@RequiresPermissions("patient:sdPatientInf:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SdPatientInf sdPatientInf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SdPatientInf> page = sdPatientInfService.findPage(new Page<SdPatientInf>(request, response), sdPatientInf); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑病人表单页面
	 */
	@RequiresPermissions(value={"patient:sdPatientInf:view","patient:sdPatientInf:add","patient:sdPatientInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SdPatientInf sdPatientInf, Model model) {
		model.addAttribute("sdPatientInf", sdPatientInf);
		return "modules/patient/sdPatientInfForm";
	}

	/**
	 * 保存病人
	 */
	@ResponseBody
	@RequiresPermissions(value={"patient:sdPatientInf:add","patient:sdPatientInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SdPatientInf sdPatientInf, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sdPatientInf)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		sdPatientInfService.save(sdPatientInf);//保存
		j.setSuccess(true);
		j.setMsg("保存病人成功");
		return j;
	}
	
	/**
	 * 删除病人
	 */
	@ResponseBody
	@RequiresPermissions("patient:sdPatientInf:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SdPatientInf sdPatientInf, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sdPatientInfService.delete(sdPatientInf);
		j.setMsg("删除病人成功");
		return j;
	}
	
	/**
	 * 批量删除病人
	 */
	@ResponseBody
	@RequiresPermissions("patient:sdPatientInf:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sdPatientInfService.delete(sdPatientInfService.get(id));
		}
		j.setMsg("删除病人成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("patient:sdPatientInf:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SdPatientInf sdPatientInf, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "病人"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SdPatientInf> page = sdPatientInfService.findPage(new Page<SdPatientInf>(request, response, -1), sdPatientInf);
    		new ExportExcel("病人", SdPatientInf.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出病人记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public SdPatientInf detail(String id) {
		return sdPatientInfService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("patient:sdPatientInf:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SdPatientInf> list = ei.getDataList(SdPatientInf.class);
			for (SdPatientInf sdPatientInf : list){
				try{
					sdPatientInfService.save(sdPatientInf);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条病人记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条病人记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入病人失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/patient/sdPatientInf/?repage";
    }
	
	/**
	 * 下载导入病人数据模板
	 */
	@RequiresPermissions("patient:sdPatientInf:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "病人数据导入模板.xlsx";
    		List<SdPatientInf> list = Lists.newArrayList(); 
    		new ExportExcel("病人数据", SdPatientInf.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/patient/sdPatientInf/?repage";
    }
	

}