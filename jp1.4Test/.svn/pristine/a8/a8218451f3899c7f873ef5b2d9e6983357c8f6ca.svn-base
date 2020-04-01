/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.doctor.web;

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
import com.jeeplus.modules.doctor.entity.SdDoctorInf;
import com.jeeplus.modules.doctor.service.SdDoctorInfService;

/**
 * 医生信息Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/doctor/sdDoctorInf")
public class SdDoctorInfController extends BaseController {

	@Autowired
	private SdDoctorInfService sdDoctorInfService;
	
	@ModelAttribute
	public SdDoctorInf get(@RequestParam(required=false) String id) {
		SdDoctorInf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sdDoctorInfService.get(id);
		}
		if (entity == null){
			entity = new SdDoctorInf();
		}
		return entity;
	}
	
	/**
	 * 医生列表页面
	 */
	@RequiresPermissions("doctor:sdDoctorInf:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/doctor/sdDoctorInfList";
	}
	
		/**
	 * 医生列表数据
	 */
	@ResponseBody
	@RequiresPermissions("doctor:sdDoctorInf:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SdDoctorInf sdDoctorInf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SdDoctorInf> page = sdDoctorInfService.findPage(new Page<SdDoctorInf>(request, response), sdDoctorInf); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑医生表单页面
	 */
	@RequiresPermissions(value={"doctor:sdDoctorInf:view","doctor:sdDoctorInf:add","doctor:sdDoctorInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SdDoctorInf sdDoctorInf, Model model) {
		model.addAttribute("sdDoctorInf", sdDoctorInf);
		return "modules/doctor/sdDoctorInfForm";
	}

	/**
	 * 保存医生
	 */
	@ResponseBody
	@RequiresPermissions(value={"doctor:sdDoctorInf:add","doctor:sdDoctorInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SdDoctorInf sdDoctorInf, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sdDoctorInf)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		sdDoctorInfService.save(sdDoctorInf);//保存
		j.setSuccess(true);
		j.setMsg("保存医生成功");
		return j;
	}
	
	/**
	 * 删除医生
	 */
	@ResponseBody
	@RequiresPermissions("doctor:sdDoctorInf:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SdDoctorInf sdDoctorInf, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sdDoctorInfService.delete(sdDoctorInf);
		j.setMsg("删除医生成功");
		return j;
	}
	
	/**
	 * 批量删除医生
	 */
	@ResponseBody
	@RequiresPermissions("doctor:sdDoctorInf:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sdDoctorInfService.delete(sdDoctorInfService.get(id));
		}
		j.setMsg("删除医生成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("doctor:sdDoctorInf:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SdDoctorInf sdDoctorInf, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "医生"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SdDoctorInf> page = sdDoctorInfService.findPage(new Page<SdDoctorInf>(request, response, -1), sdDoctorInf);
    		new ExportExcel("医生", SdDoctorInf.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出医生记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public SdDoctorInf detail(String id) {
		return sdDoctorInfService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("doctor:sdDoctorInf:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SdDoctorInf> list = ei.getDataList(SdDoctorInf.class);
			for (SdDoctorInf sdDoctorInf : list){
				try{
					sdDoctorInfService.save(sdDoctorInf);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条医生记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条医生记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入医生失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/doctor/sdDoctorInf/?repage";
    }
	
	/**
	 * 下载导入医生数据模板
	 */
	@RequiresPermissions("doctor:sdDoctorInf:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "医生数据导入模板.xlsx";
    		List<SdDoctorInf> list = Lists.newArrayList(); 
    		new ExportExcel("医生数据", SdDoctorInf.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/doctor/sdDoctorInf/?repage";
    }
	

}