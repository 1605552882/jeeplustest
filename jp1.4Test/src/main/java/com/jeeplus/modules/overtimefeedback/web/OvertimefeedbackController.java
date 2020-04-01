/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimefeedback.web;

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
import com.jeeplus.common.config.Global;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.overtimefeedback.entity.Overtimefeedback;
import com.jeeplus.modules.overtimefeedback.service.OvertimefeedbackService;

/**
 * 反馈超时Controller
 * @author lxy
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/overtimefeedback/overtimefeedback")
public class OvertimefeedbackController extends BaseController {

	@Autowired
	private OvertimefeedbackService overtimefeedbackService;
	
	@ModelAttribute
	public Overtimefeedback get(@RequestParam(required=false) String id) {
		Overtimefeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = overtimefeedbackService.get(id);
		}
		if (entity == null){
			entity = new Overtimefeedback();
		}
		return entity;
	}
	
	/**
	 * 反馈超时列表页面
	 */
	@RequiresPermissions("overtimefeedback:overtimefeedback:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/overtimefeedback/overtimefeedbackList";
	}
	
		/**
	 * 反馈超时列表数据
	 */
	@ResponseBody
	@RequiresPermissions("overtimefeedback:overtimefeedback:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Overtimefeedback overtimefeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Overtimefeedback> page = overtimefeedbackService.findPage(overtimefeedback, request, response);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑反馈超时表单页面
	 */
	@RequiresPermissions(value={"overtimefeedback:overtimefeedback:view","overtimefeedback:overtimefeedback:add","overtimefeedback:overtimefeedback:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Overtimefeedback overtimefeedback, Model model) {
		model.addAttribute("overtimefeedback", overtimefeedback);
		if(StringUtils.isBlank(overtimefeedback.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/overtimefeedback/overtimefeedbackForm";
	}

	/**
	 * 保存反馈超时
	 */
	@RequiresPermissions(value={"overtimefeedback:overtimefeedback:add","overtimefeedback:overtimefeedback:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Overtimefeedback overtimefeedback, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, overtimefeedback)){
			return form(overtimefeedback, model);
		}
		//新增或编辑表单保存
		overtimefeedbackService.save(overtimefeedback);//保存
		addMessage(redirectAttributes, "保存反馈超时成功");
		return "redirect:"+Global.getAdminPath()+"/overtimefeedback/overtimefeedback/?repage";
	}
	
	/**
	 * 删除反馈超时
	 */
	@ResponseBody
	@RequiresPermissions("overtimefeedback:overtimefeedback:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Overtimefeedback overtimefeedback, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		overtimefeedbackService.delete(overtimefeedback);
		j.setMsg("删除反馈超时成功");
		return j;
	}
	
	/**
	 * 批量删除反馈超时
	 */
	@ResponseBody
	@RequiresPermissions("overtimefeedback:overtimefeedback:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			overtimefeedbackService.delete(overtimefeedbackService.get(id));
		}
		j.setMsg("删除反馈超时成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("overtimefeedback:overtimefeedback:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Overtimefeedback overtimefeedback, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "反馈超时"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Overtimefeedback> page = overtimefeedbackService.findPage(new Page<Overtimefeedback>(request, response, -1), overtimefeedback);
    		new ExportExcel("反馈超时", Overtimefeedback.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出反馈超时记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("overtimefeedback:overtimefeedback:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Overtimefeedback> list = ei.getDataList(Overtimefeedback.class);
			for (Overtimefeedback overtimefeedback : list){
				try{
					overtimefeedbackService.save(overtimefeedback);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条反馈超时记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条反馈超时记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入反馈超时失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/overtimefeedback/overtimefeedback/?repage";
    }
	
	/**
	 * 下载导入反馈超时数据模板
	 */
	@RequiresPermissions("overtimefeedback:overtimefeedback:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "反馈超时数据导入模板.xlsx";
    		List<Overtimefeedback> list = Lists.newArrayList(); 
    		new ExportExcel("反馈超时数据", Overtimefeedback.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/overtimefeedback/overtimefeedback/?repage";
    }

}