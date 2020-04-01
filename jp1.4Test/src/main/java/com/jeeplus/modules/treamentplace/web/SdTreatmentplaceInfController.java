/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.treamentplace.web;

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
import com.jeeplus.modules.treamentplace.entity.SdTreatmentplaceInf;
import com.jeeplus.modules.treamentplace.service.SdTreatmentplaceInfService;

/**
 * 治疗地点Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/treamentplace/sdTreatmentplaceInf")
public class SdTreatmentplaceInfController extends BaseController {

	@Autowired
	private SdTreatmentplaceInfService sdTreatmentplaceInfService;
	
	@ModelAttribute
	public SdTreatmentplaceInf get(@RequestParam(required=false) String id) {
		SdTreatmentplaceInf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sdTreatmentplaceInfService.get(id);
		}
		if (entity == null){
			entity = new SdTreatmentplaceInf();
		}
		return entity;
	}
	
	/**
	 * 地点列表页面
	 */
	@RequiresPermissions("treamentplace:sdTreatmentplaceInf:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/treamentplace/sdTreatmentplaceInfList";
	}
	
		/**
	 * 地点列表数据
	 */
	@ResponseBody
	@RequiresPermissions("treamentplace:sdTreatmentplaceInf:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SdTreatmentplaceInf sdTreatmentplaceInf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SdTreatmentplaceInf> page = sdTreatmentplaceInfService.findPage(new Page<SdTreatmentplaceInf>(request, response), sdTreatmentplaceInf); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑地点表单页面
	 */
	@RequiresPermissions(value={"treamentplace:sdTreatmentplaceInf:view","treamentplace:sdTreatmentplaceInf:add","treamentplace:sdTreatmentplaceInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SdTreatmentplaceInf sdTreatmentplaceInf, Model model) {
		model.addAttribute("sdTreatmentplaceInf", sdTreatmentplaceInf);
		return "modules/treamentplace/sdTreatmentplaceInfForm";
	}

	/**
	 * 保存地点
	 */
	@ResponseBody
	@RequiresPermissions(value={"treamentplace:sdTreatmentplaceInf:add","treamentplace:sdTreatmentplaceInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SdTreatmentplaceInf sdTreatmentplaceInf, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sdTreatmentplaceInf)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		sdTreatmentplaceInfService.save(sdTreatmentplaceInf);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存地点成功");
		return j;
	}
	
	/**
	 * 删除地点
	 */
	@ResponseBody
	@RequiresPermissions("treamentplace:sdTreatmentplaceInf:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SdTreatmentplaceInf sdTreatmentplaceInf, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sdTreatmentplaceInfService.delete(sdTreatmentplaceInf);
		j.setMsg("删除地点成功");
		return j;
	}
	
	/**
	 * 批量删除地点
	 */
	@ResponseBody
	@RequiresPermissions("treamentplace:sdTreatmentplaceInf:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sdTreatmentplaceInfService.delete(sdTreatmentplaceInfService.get(id));
		}
		j.setMsg("删除地点成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("treamentplace:sdTreatmentplaceInf:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SdTreatmentplaceInf sdTreatmentplaceInf, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "地点"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SdTreatmentplaceInf> page = sdTreatmentplaceInfService.findPage(new Page<SdTreatmentplaceInf>(request, response, -1), sdTreatmentplaceInf);
    		new ExportExcel("地点", SdTreatmentplaceInf.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出地点记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("treamentplace:sdTreatmentplaceInf:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SdTreatmentplaceInf> list = ei.getDataList(SdTreatmentplaceInf.class);
			for (SdTreatmentplaceInf sdTreatmentplaceInf : list){
				try{
					sdTreatmentplaceInfService.save(sdTreatmentplaceInf);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条地点记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条地点记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入地点失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/treamentplace/sdTreatmentplaceInf/?repage";
    }
	
	/**
	 * 下载导入地点数据模板
	 */
	@RequiresPermissions("treamentplace:sdTreatmentplaceInf:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "地点数据导入模板.xlsx";
    		List<SdTreatmentplaceInf> list = Lists.newArrayList(); 
    		new ExportExcel("地点数据", SdTreatmentplaceInf.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/treamentplace/sdTreatmentplaceInf/?repage";
    }

}