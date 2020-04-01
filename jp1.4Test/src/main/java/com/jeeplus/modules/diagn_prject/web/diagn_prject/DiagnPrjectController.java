/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_prject.web.diagn_prject;

import java.util.Date;
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
import com.jeeplus.modules.diagn_prject.entity.diagn_prject.DiagnPrject;
import com.jeeplus.modules.diagn_prject.service.diagn_prject.DiagnPrjectService;

/**
 * 一件诊断子表Controller
 * @author 姜森焱
 * @version 2020-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/diagn_prject/diagn_prject/diagnPrject")
public class DiagnPrjectController extends BaseController {

	@Autowired
	private DiagnPrjectService diagnPrjectService;
	
	@ModelAttribute
	public DiagnPrject get(@RequestParam(required=false) String id) {
		DiagnPrject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = diagnPrjectService.get(id);
		}
		if (entity == null){
			entity = new DiagnPrject();
		}
		return entity;
	}
	
	/**
	 * 一件诊断子表列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/diagn_prject/diagn_prject/diagnPrjectList";
	}
	
		/**
	 * 一件诊断子表列表数据
	 */
	@ResponseBody
	/*@RequiresPermissions("diagn_prject:diagn_prject:diagnPrject:list")*/
	@RequestMapping(value = "data")
	public Map<String, Object> data(DiagnPrject diagnPrject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DiagnPrject> page = diagnPrjectService.findPage(new Page<DiagnPrject>(request, response), diagnPrject); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑一件诊断子表表单页面
	 */
	@RequiresPermissions(value={"diagn_prject:diagn_prject:diagnPrject:view","diagn_prject:diagn_prject:diagnPrject:add","diagn_prject:diagn_prject:diagnPrject:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(DiagnPrject diagnPrject, Model model) {
		model.addAttribute("diagnPrject", diagnPrject);
		if(StringUtils.isBlank(diagnPrject.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}else {
			diagnPrject.setServId("");
		}
		return "modules/diagn_prject/diagn_prject/diagnPrjectForm";
	}

	/**
	 * 保存一件诊断子表
	 */
	@RequiresPermissions(value={"diagn_prject:diagn_prject:diagnPrject:add","diagn_prject:diagn_prject:diagnPrject:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(DiagnPrject diagnPrject, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, diagnPrject)){
			return form(diagnPrject, model);
		}
		//新增或编辑表单保存
		diagnPrject.setInputTime(new Date());
		diagnPrjectService.save(diagnPrject);//保存
		Integer id=diagnPrjectService.updateprojectId();
		diagnPrjectService.insertproject(id.toString(), diagnPrject.getServId());
		addMessage(redirectAttributes, "保存一件诊断子表成功");
		return "redirect:"+Global.getAdminPath()+"/diagn_serv/diagn_serv/diagnServ/?repage";
	}
	
	/**
	 * 删除一件诊断子表
	 */
	@ResponseBody
	@RequiresPermissions("diagn_prject:diagn_prject:diagnPrject:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DiagnPrject diagnPrject, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		diagnPrjectService.delete(diagnPrject);
		j.setMsg("删除一件诊断子表成功");
		return j;
	}
	
	/**
	 * 批量删除一件诊断子表
	 */
	@ResponseBody
	@RequiresPermissions("diagn_prject:diagn_prject:diagnPrject:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			diagnPrjectService.delete(diagnPrjectService.get(id));
		}
		j.setMsg("删除一件诊断子表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("diagn_prject:diagn_prject:diagnPrject:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(DiagnPrject diagnPrject, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "一件诊断子表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DiagnPrject> page = diagnPrjectService.findPage(new Page<DiagnPrject>(request, response, -1), diagnPrject);
    		new ExportExcel("一件诊断子表", DiagnPrject.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出一件诊断子表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("diagn_prject:diagn_prject:diagnPrject:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DiagnPrject> list = ei.getDataList(DiagnPrject.class);
			for (DiagnPrject diagnPrject : list){
				try{
					diagnPrjectService.save(diagnPrject);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条一件诊断子表记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条一件诊断子表记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入一件诊断子表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_prject/diagn_prject/diagnPrject/?repage";
    }
	
	/**
	 * 下载导入一件诊断子表数据模板
	 */
	@RequiresPermissions("diagn_prject:diagn_prject:diagnPrject:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "一件诊断子表数据导入模板.xlsx";
    		List<DiagnPrject> list = Lists.newArrayList(); 
    		new ExportExcel("一件诊断子表数据", DiagnPrject.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_prject/diagn_prject/diagnPrject/?repage";
    }

}