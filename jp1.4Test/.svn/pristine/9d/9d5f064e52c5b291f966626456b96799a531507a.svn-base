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

import com.jeeplus.modules.lnet.service.HssService;

/**
 * HssController
 * @author zqb
 * @version 2019-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/lnet/hss")
public class HssController extends BaseController {

	@Autowired
	private HssService hssService;
	
	/**
	 * HSS日志查询页面
	 */
	@RequiresPermissions("lnet:hss:hssLogQry")
	@RequestMapping("hssLogQry")
	public String hssLogQry() {
		return "modules/lnet/hssLogQry";
	}
	
	/**
	 * HSS用户基本信息查询页面
	 */
	@RequiresPermissions("lnet:hss:hssUserInfoQry")
	@RequestMapping("hssUserInfoQry")
	public String hssUserInfoQry() {
		return "modules/lnet/hssUserInfoQry";
	}
	
	/**
	 * HSS用户签约信息页面
	 */
	@RequiresPermissions("lnet:hss:hssUserInfo")
	@RequestMapping("hssUserInfo")
	public String hssUserInfo() {
		return "modules/lnet/hssUserInfo";
	}
	
	/**
	 * HSS业务更改页面
	 */
	@RequestMapping("hssModifyWindow")
	public String hssModifyWindow() {
		return "modules/lnet/hssModifyWindow";
	}
	
	/**
	 * HSS业务更改功能
	 */
	@RequestMapping("hssModify")
	public String hssModify() {
		return "modules/lnet/hssModify";
	}
	
	/**
	 * HSS批量操作页面
	 */
	@RequestMapping("hssBatchOperateWindow")
	public String hssBatchOperateWindow() {
		return "modules/lnet/hssBatchOperateWindow";
	}
}