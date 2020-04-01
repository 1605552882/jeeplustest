/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.lnet.web;

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

import com.jeeplus.modules.lnet.service.PcrfService;

/**
 * PcrfController
 * @author zqb
 * @version 2019-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/lnet/pcrf")
public class PcrfController extends BaseController {

	@Autowired
	private PcrfService pcrfService;
	
	/**
	 * PCRF定向流量免流匹配规则查询页面
	 */
	//@RequiresPermissions("lnet:pcrf:direFlowQry")
	@RequestMapping("direFlowQry")
	public String direFlowQry() {
		return "modules/lnet/direFlowQry";
	}
	
	/**
	 * PCRF日志查询页面
	 */
	//@RequiresPermissions("lnet:pcrf:pcrfLogQry")
	@RequestMapping("pcrfLogQry")
	public String pcrfLogQry() {
		return "modules/lnet/pcrfLogQry";
	}
	
	/**
	 * 签约套餐信息页面
	 */
	//@RequiresPermissions("lnet:pcrf:pcrfInfoQry")
	@RequestMapping("pcrfInfoQry")
	public String pcrfInfoQry() {
		return "modules/lnet/pcrfInfoQry";
	}
	
	/**
	 * 签约套餐业务更改页面
	 */
	//@RequiresPermissions("lnet:pcrf:pcrfModifyWindow")
	@RequestMapping("pcrfModifyWindow")
	public String pcrfModifyWindow() {
		return "modules/lnet/pcrfModifyWindow";
	}
	
	/**
	 * 签约套餐批量操作页面
	 */
	//@RequiresPermissions("lnet:pcrf:pcrfBatchOperateWindow")
	@RequestMapping("pcrfBatchOperateWindow")
	public String pcrfBatchOperateWindow() {
		return "modules/lnet/pcrfBatchOperateWindow";
	}
	
	/**
	 * 超量断网日志查询页面
	 */
	//@RequiresPermissions("lnet:pcrf:pcrfUserLogQry")
	@RequestMapping("pcrfUserLogQry")
	public String pcrfUserLogQry() {
		return "modules/lnet/pcrfUserLogQry";
	}
}