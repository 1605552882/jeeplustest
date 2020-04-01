/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.repetitivedocument.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.repetitivedocument.entity.Repetitivedocument;
import com.jeeplus.modules.repetitivedocument.service.RepetitivedocumentService;

/**
 * 重复退单Controller
 * @author lxy
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/repetitivedocument/repetitivedocument")
public class RepetitivedocumentController extends BaseController {

	@Autowired
	private RepetitivedocumentService repetitivedocumentService;
	
	@ModelAttribute
	public Repetitivedocument get(@RequestParam(required=false) String id) {
		Repetitivedocument entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = repetitivedocumentService.get(id);
		}
		if (entity == null){
			entity = new Repetitivedocument();
		}
		return entity;
	}
	
	/**
	 * 重复退单列表页面
	 */
	@RequiresPermissions("repetitivedocument:repetitivedocument:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/repetitivedocument/repetitivedocumentList";
	}
	
		/**
	 * 重复退单列表数据
	 */
	@ResponseBody
	@RequiresPermissions("repetitivedocument:repetitivedocument:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Repetitivedocument repetitivedocument, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Repetitivedocument> page = repetitivedocumentService.findPage(repetitivedocument, request, response);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑重复退单表单页面
	 */
	@RequiresPermissions(value={"repetitivedocument:repetitivedocument:view","repetitivedocument:repetitivedocument:add","repetitivedocument:repetitivedocument:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Repetitivedocument repetitivedocument, Model model) {
		model.addAttribute("repetitivedocument", repetitivedocument);
		if(StringUtils.isBlank(repetitivedocument.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/repetitivedocument/repetitivedocumentForm";
	}

	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("repetitivedocument:repetitivedocument:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Repetitivedocument repetitivedocument, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "重复退单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Repetitivedocument> page = repetitivedocumentService.findPage(new Page<Repetitivedocument>(request, response, -1), repetitivedocument); 
    		new ExportExcel("重复退单", Repetitivedocument.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出重复退单记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

}