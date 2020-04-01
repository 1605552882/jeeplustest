/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.faultcategorystatistic.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.faultcategorystatistic.entity.Faultcategorystatistic;
import com.jeeplus.modules.faultcategorystatistic.service.FaultcategorystatisticService;

/**
 * 故障种类统计表Controller
 * @author lxy
 * @version 2019-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/faultcategorystatistic/faultcategorystatistic")
public class FaultcategorystatisticController extends BaseController {

	@Autowired
	private FaultcategorystatisticService faultcategorystatisticService;
	
	@ModelAttribute
	public Faultcategorystatistic get(@RequestParam(required=false) String id) {
		Faultcategorystatistic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = faultcategorystatisticService.get(id);
		}
		if (entity == null){
			entity = new Faultcategorystatistic();
		}
		return entity;
	}
	
	/**
	 * 表单统计列表数据
		 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "btData")
	public AjaxJson btData(Faultcategorystatistic faultcategorystatistic, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
		AjaxJson j = new AjaxJson();
		List<Faultcategorystatistic> document = faultcategorystatisticService.findMonth(faultcategorystatistic);
		j.setSuccess(true);
		j.setMsg("保存成功!");
		j.put("document", document);
		return j;
	}
	
	
	/**
	 * 故障种类统计列表页面
	 */
	@RequiresPermissions("faultcategorystatistic:faultcategorystatistic:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/faultcategorystatistic/faultcategorystatisticList";
	}
	
	/**
 * 故障种类统计列表数据
 */
	@ResponseBody
	@RequiresPermissions("faultcategorystatistic:faultcategorystatistic:list")
	@RequestMapping(value = "data")
	public AjaxJson data(Faultcategorystatistic faultcategorystatistic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			j.setSuccess(true);
			j.setMsg("查询成功!");
			j.put("data", faultcategorystatisticService.findStatisticList(faultcategorystatistic));
		}catch(Exception e){
			j.setSuccess(false);
			j.setMsg("查询失败!");
		}
		return j;
	}

	/**
	 * 查看，增加，编辑故障种类统计表单页面
	 */
	@RequiresPermissions(value={"faultcategorystatistic:faultcategorystatistic:view","faultcategorystatistic:faultcategorystatistic:add","faultcategorystatistic:faultcategorystatistic:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Faultcategorystatistic faultcategorystatistic, Model model) {
		model.addAttribute("faultcategorystatistic", faultcategorystatistic);
		return "modules/faultcategorystatistic/faultcategorystatisticForm";
	}
	
	/**
	 * 子柱状图页面
	 */
	@RequestMapping(value = "subChart")
	public String subChart() {
		return "modules/faultcategorystatistic/faultcategorystatisticSubChart";
	}
	
	/**
	 * 子饼图页面
	 */
	@RequestMapping(value = "subPie")
	public String subPie() {
		return "modules/faultcategorystatistic/faultcategorystatisticSubPie";
	}
	
	/**
	 * 子饼图页面按城市分类
	 */
	@ResponseBody
	@RequestMapping(value = "cityData")
	public List<Faultcategorystatistic> cityData(Faultcategorystatistic faultcategorystatistic, HttpServletRequest request) {
		return faultcategorystatisticService.findCityData(faultcategorystatistic);
	}
	
	/**
	 * 子饼图页面按申告种类分类
	 */
	@ResponseBody
	@RequestMapping(value = "faultCategoryData")
	public List<Faultcategorystatistic> faultCategoryData(Faultcategorystatistic faultcategorystatistic, HttpServletRequest request) {
		return faultcategorystatisticService.findFaultCategoryData(faultcategorystatistic);
	}
	
	/**
	 * 子表格页面
	 */
	@RequestMapping(value = "subTable")
	public String subTable() {
		return "modules/faultcategorystatistic/faultcategorystatisticSubTable";
	}
}