/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.personalfunction.web;

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
import com.jeeplus.modules.personalfunction.entity.EdPersonalfunctionInf;
import com.jeeplus.modules.personalfunction.service.EdPersonalfunctionInfService;

/**
 * 个人功能评估Controller
 * @author lukbob
 * @version 2018-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/personalfunction/edPersonalfunctionInf")
public class EdPersonalfunctionInfController extends BaseController {

	@Autowired
	private EdPersonalfunctionInfService edPersonalfunctionInfService;
	
	@ModelAttribute
	public EdPersonalfunctionInf get(@RequestParam(required=false) String id) {
		EdPersonalfunctionInf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = edPersonalfunctionInfService.get(id);
		}
		if (entity == null){
			entity = new EdPersonalfunctionInf();
		}
		return entity;
	}
	
	/**
	 * 个人功能评估列表页面
	 */
	@RequiresPermissions("personalfunction:edPersonalfunctionInf:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/personalfunction/edPersonalfunctionInfList";
	}
	
		/**
	 * 个人功能评估列表数据
	 */
	@ResponseBody
	@RequiresPermissions("personalfunction:edPersonalfunctionInf:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(EdPersonalfunctionInf edPersonalfunctionInf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EdPersonalfunctionInf> page = edPersonalfunctionInfService.findPage(new Page<EdPersonalfunctionInf>(request, response), edPersonalfunctionInf); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑个人功能评估表单页面
	 */
	@RequiresPermissions(value={"personalfunction:edPersonalfunctionInf:view","personalfunction:edPersonalfunctionInf:add","personalfunction:edPersonalfunctionInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(EdPersonalfunctionInf edPersonalfunctionInf, Model model) {
		model.addAttribute("edPersonalfunctionInf", edPersonalfunctionInf);
		return "modules/personalfunction/edPersonalfunctionInfForm";
	}

	/**
	 * 保存个人功能评估
	 */
	@ResponseBody
	@RequiresPermissions(value={"personalfunction:edPersonalfunctionInf:add","personalfunction:edPersonalfunctionInf:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(EdPersonalfunctionInf edPersonalfunctionInf, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, edPersonalfunctionInf)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		edPersonalfunctionInfService.save(edPersonalfunctionInf);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存个人功能评估成功");
		return j;
	}
	
	/**
	 * 删除个人功能评估
	 */
	@ResponseBody
	@RequiresPermissions("personalfunction:edPersonalfunctionInf:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(EdPersonalfunctionInf edPersonalfunctionInf, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		edPersonalfunctionInfService.delete(edPersonalfunctionInf);
		j.setMsg("删除个人功能评估成功");
		return j;
	}
	
	/**
	 * 批量删除个人功能评估
	 */
	@ResponseBody
	@RequiresPermissions("personalfunction:edPersonalfunctionInf:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			edPersonalfunctionInfService.delete(edPersonalfunctionInfService.get(id));
		}
		j.setMsg("删除个人功能评估成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("personalfunction:edPersonalfunctionInf:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(EdPersonalfunctionInf edPersonalfunctionInf, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "个人功能评估"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EdPersonalfunctionInf> page = edPersonalfunctionInfService.findPage(new Page<EdPersonalfunctionInf>(request, response, -1), edPersonalfunctionInf);
    		new ExportExcel("个人功能评估", EdPersonalfunctionInf.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出个人功能评估记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("personalfunction:edPersonalfunctionInf:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdPersonalfunctionInf> list = ei.getDataList(EdPersonalfunctionInf.class);
			for (EdPersonalfunctionInf edPersonalfunctionInf : list){
				try{
					edPersonalfunctionInfService.save(edPersonalfunctionInf);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条个人功能评估记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条个人功能评估记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入个人功能评估失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/personalfunction/edPersonalfunctionInf/?repage";
    }
	
	/**
	 * 下载导入个人功能评估数据模板
	 */
	@RequiresPermissions("personalfunction:edPersonalfunctionInf:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "个人功能评估数据导入模板.xlsx";
    		List<EdPersonalfunctionInf> list = Lists.newArrayList(); 
    		new ExportExcel("个人功能评估数据", EdPersonalfunctionInf.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/personalfunction/edPersonalfunctionInf/?repage";
    }

}