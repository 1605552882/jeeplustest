/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.diagn_serv.web.diagn_serv;

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
import com.jeeplus.modules.diagn_serv.entity.diagn_serv.DiagnServ;
import com.jeeplus.modules.diagn_serv.service.diagn_serv.DiagnServService;

/**
 * 一件诊断主表Controller
 * @author 姜森焱
 * @version 2020-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/diagn_serv/diagn_serv/diagnServ")
public class DiagnServController extends BaseController {

	@Autowired
	private DiagnServService diagnServService;
	
	@ModelAttribute
	public DiagnServ get(@RequestParam(required=false) String id) {
		DiagnServ entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = diagnServService.get(id);
		}
		if (entity == null){
			entity = new DiagnServ();
		}
		return entity;
	}
	
	/**
	 * 一件诊断主表列表页面
	 */
	@RequiresPermissions("diagn_serv:diagn_serv:diagnServ:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/diagn_serv/diagn_serv/diagnServList";
	}
	
		/**
	 * 一件诊断主表列表数据
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv:diagn_serv:diagnServ:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DiagnServ diagnServ, HttpServletRequest request, HttpServletResponse response, Model model) {
		diagnServService.updateservid();
		Page<DiagnServ> page = diagnServService.findPage(new Page<DiagnServ>(request, response), diagnServ); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑一件诊断主表表单页面
	 */
	@RequiresPermissions(value={"diagn_serv:diagn_serv:diagnServ:add","diagn_serv:diagn_serv:diagnServ:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(DiagnServ diagnServ, Model model) {
		model.addAttribute("diagnServ", diagnServ);
		if(StringUtils.isBlank(diagnServ.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/diagn_serv/diagn_serv/diagnServForm";
	}

	/**
	 * 保存一件诊断主表
	 */
	@RequiresPermissions(value={"diagn_serv:diagn_serv:diagnServ:add","diagn_serv:diagn_serv:diagnServ:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(DiagnServ diagnServ, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, diagnServ)){
			return form(diagnServ, model);
		}
		//新增或编辑表单保存
		diagnServService.save(diagnServ);//保存
		
		addMessage(redirectAttributes, "保存一件诊断主表成功");
		
		return "redirect:"+Global.getAdminPath()+"/diagn_serv/diagn_serv/diagnServ/?repage";
	}
	
	/**
	 * 删除一件诊断主表
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv:diagn_serv:diagnServ:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DiagnServ diagnServ, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		diagnServService.delete(diagnServ);
		j.setMsg("删除一件诊断主表成功");
		return j;
	}
	
	/**
	 * 批量删除一件诊断主表
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv:diagn_serv:diagnServ:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			diagnServService.delete(diagnServService.get(id));
		}
		j.setMsg("删除一件诊断主表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("diagn_serv:diagn_serv:diagnServ:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(DiagnServ diagnServ, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "一件诊断主表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DiagnServ> page = diagnServService.findPage(new Page<DiagnServ>(request, response, -1), diagnServ);
    		new ExportExcel("一件诊断主表", DiagnServ.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出一件诊断主表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("diagn_serv:diagn_serv:diagnServ:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DiagnServ> list = ei.getDataList(DiagnServ.class);
			for (DiagnServ diagnServ : list){
				try{
					diagnServService.save(diagnServ);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条一件诊断主表记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条一件诊断主表记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入一件诊断主表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_serv/diagn_serv/diagnServ/?repage";
    }
	
	/**
	 * 下载导入一件诊断主表数据模板
	 */
	@RequiresPermissions("diagn_serv:diagn_serv:diagnServ:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "一件诊断主表数据导入模板.xlsx";
    		List<DiagnServ> list = Lists.newArrayList(); 
    		new ExportExcel("一件诊断主表数据", DiagnServ.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/diagn_serv/diagn_serv/diagnServ/?repage";
    }

}