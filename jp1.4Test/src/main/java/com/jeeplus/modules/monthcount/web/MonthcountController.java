/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.monthcount.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeeplus.modules.checkresult.entity.Checkresult;
import com.jeeplus.modules.checkresult.service.CheckresultService;
import com.jeeplus.modules.documentdetect.entity.Documentarydetails;
import com.jeeplus.modules.documentdetect.service.DocumentarydetailsService;
import com.jeeplus.modules.errordocument.entity.Errordocument;
import com.jeeplus.modules.errordocument.service.ErrordocumentService;
import com.jeeplus.modules.monthcount.entity.Monthcount;
import com.jeeplus.modules.monthcount.service.MonthcountService;
import com.jeeplus.modules.overtimedocument.entity.Overtimedocument;
import com.jeeplus.modules.overtimedocument.service.OvertimedocumentService;
import com.jeeplus.modules.overtimefeedback.entity.Overtimefeedback;
import com.jeeplus.modules.overtimefeedback.service.OvertimefeedbackService;
import com.jeeplus.modules.overtimesign.entity.Overtimesigning;
import com.jeeplus.modules.overtimesign.service.OvertimesigningService;
import com.jeeplus.modules.repetitivedocument.entity.Repetitivedocument;
import com.jeeplus.modules.repetitivedocument.service.RepetitivedocumentService;

/**
 * 月度汇总Controller
 * @author lxy
 * @version 2019-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/monthcount/monthcount")
public class MonthcountController extends BaseController {

	@Autowired
	private MonthcountService monthcountService;

	@ModelAttribute
	public Monthcount get(@RequestParam(required=false) String id) {
		Monthcount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = monthcountService.get(id);
		}
		if (entity == null){
			entity = new Monthcount();
		}
		return entity;
	}
	
	/**
	 * 月度汇总列表页面
	 */
	@RequiresPermissions("monthcount:monthcount:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/monthcount/monthcountList";
	}
	
		/**
	 * 月度汇总列表数据
	 */
	@ResponseBody
	@RequiresPermissions("monthcount:monthcount:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Monthcount monthcount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Monthcount> page = monthcountService.handel(monthcount, request,  response);
		return getBootstrapData(page);
	}
	
	/**
	 *  详情
	 */
	@ResponseBody
	@RequestMapping(value = "getDetails")
	public AjaxJson getDetails(Monthcount monthcount, HttpServletRequest request, HttpServletResponse response){
		AjaxJson j = monthcountService.getDetails(monthcount, request,  response);
		return j;
	}
	
	@RequestMapping(value = "form")
	public String form(Monthcount monthcount, Model model) {
		Checkresult check = new Checkresult();
		check.setDutyGroup(monthcount.getDutyGroup());
		check.setBeginDcreatetime(monthcount.getBeginSearchtime());
		check.setEndDcreatetime(monthcount.getEndSearchtime());
		check.setsStatus(monthcount.getsStatus());
		model.addAttribute("checkresult", check);
		/*
		 * if(StringUtils.isBlank(monthcount.getId())){//如果ID是空为添加
		 * model.addAttribute("isAdd", true); }
		 */
		return "modules/monthcount/checkresultList";
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("monthcount:monthcount:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Monthcount monthcount, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "月度汇总"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Monthcount> page = monthcountService.handel(monthcount, request,  response);
    		new ExportExcel("月度汇总", Monthcount.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出月度汇总记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
	

}