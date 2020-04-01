/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.overtimedocument.web;

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
import com.jeeplus.modules.checkresult.entity.Checkresult;
import com.jeeplus.modules.checkresult.service.CheckresultService;
import com.jeeplus.modules.documentdetect.entity.Documentarydetails;
import com.jeeplus.modules.documentdetect.service.DocumentarydetailsService;
import com.jeeplus.modules.overtimedocument.entity.Overtimedocument;
import com.jeeplus.modules.overtimedocument.service.OvertimedocumentService;

/**
 * 工单超时Controller
 * @author lxy
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/overtimedocument/overtimedocument")
public class OvertimedocumentController extends BaseController {

	@Autowired
	private OvertimedocumentService overtimedocumentService;
	
	@Autowired
	private DocumentarydetailsService documentarydetailsService;
	
	
	
	@ModelAttribute
	public Overtimedocument get(@RequestParam(required=false) String id) {
		Overtimedocument entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = overtimedocumentService.get(id);
		}
		if (entity == null){
			entity = new Overtimedocument();
		}
		return entity;
	}
	/**
	 * 获取工单详情
	 * @param overtimedocument
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "newtable")
	public AjaxJson newtable(Overtimedocument overtimedocument, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String sbillno = overtimedocument.getSbillno();
		Documentarydetails documentarydetails = documentarydetailsService.getIdBySbillno(sbillno);
		j.put("documentarydetails",documentarydetails);
		j.setSuccess(true);
		return j;
	}
	/**
	 * 工单超时列表页面
	 */
	@RequiresPermissions("overtimedocument:overtimedocument:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/overtimedocument/overtimedocumentList";
	}
	
		/**
	 * 工单超时列表数据
	 */
	@ResponseBody
	@RequiresPermissions("overtimedocument:overtimedocument:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Overtimedocument overtimedocument, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Overtimedocument> page = overtimedocumentService.findPage(overtimedocument, request, response);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑工单超时表单页面
	 */
	@RequiresPermissions(value={"overtimedocument:overtimedocument:view","overtimedocument:overtimedocument:add","overtimedocument:overtimedocument:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Overtimedocument overtimedocument, Model model) {
		model.addAttribute("overtimedocument", overtimedocument);
		if(StringUtils.isBlank(overtimedocument.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/overtimedocument/overtimedocumentForm";
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("overtimedocument:overtimedocument:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Overtimedocument overtimedocument, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工单超时"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Overtimedocument> page = overtimedocumentService.findPage(new Page<Overtimedocument>(request, response, -1), overtimedocument);
    		new ExportExcel("工单超时", Overtimedocument.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出工单超时记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
}