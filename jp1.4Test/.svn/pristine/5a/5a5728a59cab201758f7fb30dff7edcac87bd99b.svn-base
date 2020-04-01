/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.complaindocument.web;

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
import com.jeeplus.modules.checkresult.entity.Checkresult;
import com.jeeplus.modules.checkresult.service.CheckresultService;
import com.jeeplus.modules.complaindocument.entity.Complaindocument;
import com.jeeplus.modules.complaindocument.service.ComplaindocumentService;


/**
 * 重复投诉工单Controller
 * @author zqb
 * @version 2019-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/complaindocument/complaindocument")
public class ComplaindocumentController extends BaseController {

	@Autowired
	private ComplaindocumentService complaindocumentService;
	
	@ModelAttribute
	public Complaindocument get(@RequestParam(required=false) String id) {
		Complaindocument entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaindocumentService.get(id);
		}
		if (entity == null){
			entity = new Complaindocument();
		}
		return entity;
	}
	
	/**
	 * 重复投诉工单列表页面
	 */
	@RequiresPermissions("complaindocument:complaindocument:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/complaindocument/complaindocumentList";
	}
	
		/**
	 * 重复投诉工单列表数据
	 */
	@ResponseBody
	@RequiresPermissions("complaindocument:complaindocument:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Complaindocument complaindocument, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Complaindocument> page = complaindocumentService.findPage(new Page<Complaindocument>(request, response), complaindocument); 
		Page<Complaindocument> resultPage = complaindocumentService.changeList(page);
		return getBootstrapData(resultPage);
	}

	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("complaindocument:complaindocument:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Complaindocument complaindocument, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "重复投诉工单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Complaindocument> page = complaindocumentService.findPage(new Page<Complaindocument>(request, response, -1), complaindocument);
    		new ExportExcel("重复投诉工单", Complaindocument.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出重复投诉工单记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

}