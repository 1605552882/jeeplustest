/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.groupdetails.web;

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
import com.jeeplus.modules.groupdetails.entity.Groupdetails;
import com.jeeplus.modules.groupdetails.service.GroupdetailsService;

/**
 * 班组详情Controller
 * @author lxy
 * @version 2019-08-28
 */
@Controller
@RequestMapping(value = "${adminPath}/groupdetails/groupdetails")
public class GroupdetailsController extends BaseController {

	@Autowired
	private GroupdetailsService groupdetailsService;
	
	@ModelAttribute
	public Groupdetails get(@RequestParam(required=false) String id) {
		Groupdetails entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = groupdetailsService.get(id);
		}
		if (entity == null){
			entity = new Groupdetails();
		}
		return entity;
	}
	
	/**
	 * 班组详情列表页面
	 */
	@RequiresPermissions("groupdetails:groupdetails:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/groupdetails/groupdetailsList";
	}
	
		/**
	 * 班组详情列表数据
	 */
	@ResponseBody
	@RequiresPermissions("groupdetails:groupdetails:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Groupdetails groupdetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Groupdetails> page = groupdetailsService.findPage(new Page<Groupdetails>(request, response), groupdetails); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑班组详情表单页面
	 */
	@RequiresPermissions(value={"groupdetails:groupdetails:view","groupdetails:groupdetails:add","groupdetails:groupdetails:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Groupdetails groupdetails, Model model) {
		model.addAttribute("groupdetails", groupdetails);
		if(StringUtils.isBlank(groupdetails.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/groupdetails/groupdetailsForm";
	}

	/**
	 * 保存班组详情
	 */
	@RequiresPermissions(value={"groupdetails:groupdetails:add","groupdetails:groupdetails:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Groupdetails groupdetails, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, groupdetails)){
			return form(groupdetails, model);
		}
		//新增或编辑表单保存
		groupdetailsService.save(groupdetails);//保存
		addMessage(redirectAttributes, "保存班组详情成功");
		return "redirect:"+Global.getAdminPath()+"/groupdetails/groupdetails/?repage";
	}
	
	/**
	 * 删除班组详情
	 */
	@ResponseBody
	@RequiresPermissions("groupdetails:groupdetails:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Groupdetails groupdetails, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		groupdetailsService.delete(groupdetails);
		j.setMsg("删除班组详情成功");
		return j;
	}
	
	/**
	 * 批量删除班组详情
	 */
	@ResponseBody
	@RequiresPermissions("groupdetails:groupdetails:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			groupdetailsService.delete(groupdetailsService.get(id));
		}
		j.setMsg("删除班组详情成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("groupdetails:groupdetails:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Groupdetails groupdetails, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "班组详情"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Groupdetails> page = groupdetailsService.findPage(new Page<Groupdetails>(request, response, -1), groupdetails);
    		new ExportExcel("班组详情", Groupdetails.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出班组详情记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("groupdetails:groupdetails:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Groupdetails> list = ei.getDataList(Groupdetails.class);
			for (Groupdetails groupdetails : list){
				try{
					groupdetailsService.save(groupdetails);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条班组详情记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条班组详情记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入班组详情失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/groupdetails/groupdetails/?repage";
    }
	
	/**
	 * 下载导入班组详情数据模板
	 */
	@RequiresPermissions("groupdetails:groupdetails:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "班组详情数据导入模板.xlsx";
    		List<Groupdetails> list = Lists.newArrayList(); 
    		new ExportExcel("班组详情数据", Groupdetails.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/groupdetails/groupdetails/?repage";
    }

}