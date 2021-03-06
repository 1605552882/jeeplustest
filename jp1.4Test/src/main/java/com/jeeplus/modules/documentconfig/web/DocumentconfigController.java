/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.documentconfig.web;

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
import com.jeeplus.modules.documentconfig.entity.Documentconfig;
import com.jeeplus.modules.documentconfig.service.DocumentconfigService;

/**
 * 工单检测规则配置Controller
 * @author zqb
 * @version 2019-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/documentconfig/documentconfig")
public class DocumentconfigController extends BaseController {

	@Autowired
	private DocumentconfigService documentconfigService;
	
	@ModelAttribute
	public Documentconfig get(@RequestParam(required=false) String id) {
		Documentconfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = documentconfigService.get(id);
		}
		if (entity == null){
			entity = new Documentconfig();
		}
		return entity;
	}
	
	/**
	 * 测试规则
	 */
	@ResponseBody
	@RequestMapping(value = "ruleTest")
	public AjaxJson ruleTest(Documentconfig documentconfig, Model model ) {
		AjaxJson j = new AjaxJson();
		try {
			documentconfigService.ruleTest(documentconfig);
			j.setSuccess(true);
			j.setMsg("工单检验成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("工单检验失败");
		}
		return j;
	}
	
	
	
	/**
	 * 工单检测规则配置列表页面
	 */
	@RequiresPermissions("documentconfig:documentconfig:list")
	@RequestMapping(value = {"list", ""})
	public String list(@RequestParam(required=false) String isBack,
			Model model) {
		if(StringUtils.isNotBlank(isBack)) {
			model.addAttribute("isBack", "true");
		}else {
			model.addAttribute("isBack", "false");
		}
		return "modules/documentconfig/documentconfigList";
	}
	
		/**
	 * 工单检测规则配置列表数据
	 */
	@ResponseBody
	@RequiresPermissions("documentconfig:documentconfig:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Documentconfig documentconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Documentconfig> page = documentconfigService.findPage(new Page<Documentconfig>(request, response), documentconfig); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑工单检测规则配置表单页面
	 */
	@RequiresPermissions(value={"documentconfig:documentconfig:view","documentconfig:documentconfig:add","documentconfig:documentconfig:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Documentconfig documentconfig, Model model) {
		model.addAttribute("documentconfig", documentconfig);
		if(StringUtils.isBlank(documentconfig.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/documentconfig/documentconfigForm";
	}

	/**
	 * 保存工单检测规则配置
	 */
	@RequiresPermissions(value={"documentconfig:documentconfig:add","documentconfig:documentconfig:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Documentconfig documentconfig, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, documentconfig)){
			return form(documentconfig, model);
		}
		//新增或编辑表单保存
		String sprocesssummary = documentconfig.getSprocesssummary();
		String srepfaultdetail = documentconfig.getSrepfaultdetail();
		if (sprocesssummary.contains(":")) {
			documentconfig.setSprocesssummary(sprocesssummary.substring(0, sprocesssummary.lastIndexOf(":")));
		}
		if (srepfaultdetail.contains(":")) {
			documentconfig.setSrepfaultdetail(srepfaultdetail.substring(0, srepfaultdetail.lastIndexOf(":")));
		}
		documentconfigService.save(documentconfig);//保存
		addMessage(redirectAttributes, "保存工单检测规则配置成功");
		return "redirect:"+Global.getAdminPath()+"/documentconfig/documentconfig/?repage";
	}
	
	/**
	 * 删除工单检测规则配置
	 */
	@ResponseBody
	@RequiresPermissions("documentconfig:documentconfig:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Documentconfig documentconfig, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		documentconfigService.delete(documentconfig);
		j.setMsg("删除工单检测规则配置成功");
		return j;
	}
	
	/**
	 * 批量删除工单检测规则配置
	 */
	@ResponseBody
	@RequiresPermissions("documentconfig:documentconfig:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			documentconfigService.delete(documentconfigService.get(id));
		}
		j.setMsg("删除工单检测规则配置成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("documentconfig:documentconfig:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Documentconfig documentconfig, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工单检测规则配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Documentconfig> page = documentconfigService.findPage(new Page<Documentconfig>(request, response, -1), documentconfig);
    		new ExportExcel("工单检测规则配置", Documentconfig.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出工单检测规则配置记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("documentconfig:documentconfig:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Documentconfig> list = ei.getDataList(Documentconfig.class);
			for (Documentconfig documentconfig : list){
				try{
					documentconfigService.save(documentconfig);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条工单检测规则配置记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条工单检测规则配置记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入工单检测规则配置失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/documentconfig/documentconfig/?repage";
    }
	
	/**
	 * 下载导入工单检测规则配置数据模板
	 */
	@RequiresPermissions("documentconfig:documentconfig:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工单检测规则配置数据导入模板.xlsx";
    		List<Documentconfig> list = Lists.newArrayList(); 
    		new ExportExcel("工单检测规则配置数据", Documentconfig.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/documentconfig/documentconfig/?repage";
    }

}