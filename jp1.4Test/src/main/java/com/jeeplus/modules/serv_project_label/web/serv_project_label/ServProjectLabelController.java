/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.serv_project_label.web.serv_project_label;

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
import com.jeeplus.modules.serv_project_label.entity.serv_project_label.ServProjectLabel;
import com.jeeplus.modules.serv_project_label.service.serv_project_label.ServProjectLabelService;

/**
 * 智能预处理配置Controller
 * 
 * @author 姜森焱
 * @version 2020-02-20
 */
@Controller
@RequestMapping(value = "${adminPath}/serv_project_label/serv_project_label/servProjectLabel")
public class ServProjectLabelController extends BaseController {

	@Autowired
	private ServProjectLabelService servProjectLabelService;

	@ModelAttribute
	public ServProjectLabel get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String serv) {
		ServProjectLabel entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = servProjectLabelService.get(id);
		}
		if (entity == null) {
			entity = new ServProjectLabel();
			entity.setServ(serv);
		}
		return entity;
	}

	/**
	 * 智能预处理配置列表页面
	 */
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:list")
	@RequestMapping(value = { "list", "" })
	public String list() {
		return "modules/serv_project_label/serv_project_label/servProjectLabelList";
	}

	/**
	 * 智能预处理配置列表数据
	 */
	@ResponseBody
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(ServProjectLabel servProjectLabel, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<ServProjectLabel> page = servProjectLabelService.findPage(new Page<ServProjectLabel>(request, response),
				servProjectLabel);
		return getBootstrapData(page);
	}

	/**
	 * 智能预处理配置列表数据
	 */
	@ResponseBody
	@RequestMapping(value = "data1")
	public Map<String, Object> data1(ServProjectLabel servProjectLabel, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Page<ServProjectLabel> page = servProjectLabelService.findPage(new Page<ServProjectLabel>(request, response),
				servProjectLabel);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑智能预处理配置表单页面
	 */
	@RequiresPermissions(value = { "serv_project_label:serv_project_label:servProjectLabel:add",
			"serv_project_label:serv_project_label:servProjectLabel:edit" })
	@RequestMapping(value = "saddform")
	public String form(ServProjectLabel servProjectLabel, Model model) {
		model.addAttribute("servProjectLabel", servProjectLabel);
		if (StringUtils.isBlank(servProjectLabel.getServ())) {// 如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/serv_project_label/serv_project_label/servProjectLabelsForm";

	}

	/**
	 * 第二个表格 查看，增加，编辑智能预处理配置表单页面
	 */
	@RequiresPermissions(value = { "serv_project_label:serv_project_label:servProjectLabel:add1",
			"serv_project_label:serv_project_label:servProjectLabel:edit1" })
	@RequestMapping(value = "form")
	public String form1(ServProjectLabel servProjectLabel, Model model) {
		model.addAttribute("servProjectLabel", servProjectLabel);
		if (StringUtils.isBlank(servProjectLabel.getId())) {// 如果ID是空为添加
			model.addAttribute("isAdd", true);
		}

		return "modules/serv_project_label/serv_project_label/servProjectLabelForm";

	}

	/**
	 * 保存智能预处理配置
	 */
	@RequestMapping(value = "save")
	public String save(ServProjectLabel servProjectLabel, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		if (!beanValidator(model, servProjectLabel)) {
			return form(servProjectLabel, model);
		}
		// 新增或编辑表单保存
		servProjectLabelService.save(servProjectLabel);// 保存
		addMessage(redirectAttributes, "保存智能预处理配置成功");
		return "redirect:" + Global.getAdminPath() + "/serv_project_label/serv_project_label/servProjectLabel/?repage";
	}

	/**
	 * 保存智能预处理配置
	 */
	@RequestMapping(value = "save1")
	public String save1(ServProjectLabel servProjectLabel, String isinfo, Model model,
			RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, servProjectLabel)) {
			return form(servProjectLabel, model);
		}
		if (isinfo.equals("123")) {
			servProjectLabelService.updateserv(servProjectLabel.getServ(),servProjectLabel.getServ1());
		} else {
			// 新增或编辑表单保存
			servProjectLabelService.save(servProjectLabel);// 保存
		}
		addMessage(redirectAttributes, "保存智能预处理配置成功");
		return "redirect:" + Global.getAdminPath() + "/serv_project_label/serv_project_label/servProjectLabel/?repage";
	}

	/**
	 * 删除智能预处理配置
	 */
	@ResponseBody
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(ServProjectLabel servProjectLabel, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		servProjectLabelService.delete(servProjectLabel);
		j.setMsg("删除智能预处理配置成功");
		return j;
	}

	/**
	 * 批量删除智能预处理配置
	 */
	@ResponseBody
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] = ids.split(",");
		for (String id : idArray) {
			servProjectLabelService.delete(servProjectLabelService.get(id));
		}
		j.setMsg("删除智能预处理配置成功");
		return j;
	}

	/**
	 * 批量删除智能预处理配置
	 */
	@ResponseBody
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:del")
	@RequestMapping(value = "deleteAll1")
	public AjaxJson deleteAll1(String serv, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] = serv.split(",");
		for (String id : idArray) {
			servProjectLabelService.deleteserv(id);
		}
		j.setMsg("删除智能预处理配置成功");
		return j;
	}

	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public AjaxJson exportFile(ServProjectLabel servProjectLabel, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
			String fileName = "智能预处理配置" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<ServProjectLabel> page = servProjectLabelService
					.findPage(new Page<ServProjectLabel>(request, response, -1), servProjectLabel);
			new ExportExcel("智能预处理配置", ServProjectLabel.class).setDataList(page.getList()).write(response, fileName)
					.dispose();
			j.setSuccess(true);
			j.setMsg("导出成功！");
			return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出智能预处理配置记录失败！失败信息：" + e.getMessage());
		}
		return j;
	}

	/**
	 * 导入Excel数据
	 * 
	 */
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ServProjectLabel> list = ei.getDataList(ServProjectLabel.class);
			for (ServProjectLabel servProjectLabel : list) {
				try {
					servProjectLabelService.save(servProjectLabel);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条智能预处理配置记录。");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条智能预处理配置记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入智能预处理配置失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/serv_project_label/serv_project_label/servProjectLabel/?repage";
	}

	/**
	 * 下载导入智能预处理配置数据模板
	 */
	@RequiresPermissions("serv_project_label:serv_project_label:servProjectLabel:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "智能预处理配置数据导入模板.xlsx";
			List<ServProjectLabel> list = Lists.newArrayList();
			new ExportExcel("智能预处理配置数据", ServProjectLabel.class, 1).setDataList(list).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/serv_project_label/serv_project_label/servProjectLabel/?repage";
	}

}