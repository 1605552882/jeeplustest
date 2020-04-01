/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimesign.web;

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
import com.jeeplus.modules.overtimesign.entity.Overtimesigning;
import com.jeeplus.modules.overtimesign.service.OvertimesigningService;

/**
 * 超时签单Controller
 * @author lxy
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/overtimesign/overtimesigning")
public class OvertimesigningController extends BaseController {

	@Autowired
	private OvertimesigningService overtimesigningService;
	
	@ModelAttribute
	public Overtimesigning get(@RequestParam(required=false) String id) {
		Overtimesigning entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = overtimesigningService.get(id);
		}
		if (entity == null){
			entity = new Overtimesigning();
		}
		return entity;
	}
	
	/**
	 * 超时签单列表页面
	 */
	@RequiresPermissions("overtimesign:overtimesigning:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/overtimesign/overtimesigningList";
	}
	
		/**
	 * 超时签单列表数据
	 */
	@ResponseBody
	@RequiresPermissions("overtimesign:overtimesigning:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Overtimesigning overtimesigning, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Overtimesigning> page = overtimesigningService.findPage(overtimesigning, request, response);
		
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑超时签单表单页面
	 */
	@RequiresPermissions(value={"overtimesign:overtimesigning:view","overtimesign:overtimesigning:add","overtimesign:overtimesigning:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Overtimesigning overtimesigning, Model model) {
		model.addAttribute("overtimesigning", overtimesigning);
		if(StringUtils.isBlank(overtimesigning.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/overtimesign/overtimesigningForm";
	}

	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("overtimesign:overtimesigning:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Overtimesigning overtimesigning, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "超时签单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Overtimesigning> page = overtimesigningService.findPage(new Page<Overtimesigning>(request, response, -1), overtimesigning);
    		new ExportExcel("超时签单", Overtimesigning.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出超时签单记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
}