/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.anaaa.web;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.AppUtil;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.modules.anaaa.service.AnaaaService;

/**
 * AnaaaController
 * @author zqb
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/anaaa/anaaa")
public class AnaaaController extends BaseController {

	@Autowired
	private AnaaaService anaaaService;
	
	/**
	 * Anaaa列表页面
	 */
	@RequiresPermissions("anaaa:anaaa:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/anaaa/anaaaList";
	}
	
	/**
	 * Anaaa业务更改页面
	 */
	@RequiresPermissions("anaaa:anaaa:modify")
	@RequestMapping("anaaaModifyWindow")
	public String anaaaModifyWindow() {
		return "modules/anaaa/anaaaModifyWindow";
	}
	
	/**
	 * Anaaa业务更改功能
	 */
	@RequiresPermissions("anaaa:anaaa:modify")
	@ResponseBody
	@RequestMapping("modify")
	public AjaxJson modify(HttpServletRequest request) {
		String operation = request.getParameter("operation");
		String userNoValue = request.getParameter("userNoValue");
		String opeatorType = request.getParameter("opeatorType");
		String phone = request.getParameter("phone");

		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		json.setMsg("业务更改成功");
		return json;
	}
	
	/**
	 * Anaaa开启IOS测试页面
	 */
	@RequiresPermissions("anaaa:anaaa:bind")
	@RequestMapping("anaaaBindPhoneCardWindow")
	public String anaaaBindPhoneCardWindow() {
		return "modules/anaaa/anaaaBindPhoneCardWindow";
	}
	
	/**
	 * Anaaa开启IOS测试功能
	 */
	@RequiresPermissions("anaaa:anaaa:bind")
	@ResponseBody
	@RequestMapping("bindPhoneCard")
	public AjaxJson bindPhoneCard(HttpServletRequest request) {
		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		json.setMsg("开启IOS测试成功");
		return json;
	}
	
	/**
	 * Anaaa关闭IOS测试页面
	 */
	@RequiresPermissions("anaaa:anaaa:bind")
	@RequestMapping("anaaaUnbindPhoneCardWindow")
	public String anaaaUnbindPhoneCardWindow() {
		return "modules/anaaa/anaaaUnbindPhoneCardWindow";
	}
	
	/**
	 * Anaaa关闭IOS测试功能
	 */
	@RequiresPermissions("anaaa:anaaa:bind")
	@ResponseBody
	@RequestMapping("unbindPhoneCard")
	public AjaxJson unbindPhoneCard(HttpServletRequest request) {
		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		json.setMsg("关闭IOS测试成功");
		return json;
	}
	
	/**
	 * Anaaa批量开启/关闭IOS测试页面
	 */
	@RequestMapping("anaaaBindPhoneBatchWindow")
	public String anaaaBindPhoneBatchWindow() {
		return "modules/anaaa/anaaaBindPhoneBatchWindow";
	}

	/**
	 * Anaaa批量操作
	 */
	@RequestMapping("anaaaBatchOperateWindow")
	public String anaaaBatchOperateWindow() {
		return "modules/anaaa/anaaaBatchOperateWindow";
	}
	
	/**
	 * Anaaa认证日志查询
	 */
	@RequiresPermissions("anaaa:anaaa:authLog")
	@RequestMapping("anaaaAuthLogWindow")
	public String anaaaAuthLogWindow() {
		return "modules/anaaa/anaaaAuthLogWindow";
	}
	
	/**
	 * Anaaa认证日志查询
	 */
	@RequiresPermissions("anaaa:anaaa:authLog")
	@RequestMapping("anaaaAuthLog")
	public String anaaaAuthLog() {
		return null;
	}
	
	/**
	 * Anaaa开销户接口日志查询
	 */
	@RequiresPermissions("anaaa:anaaa:accountLog")
	@RequestMapping("anaaaAccountLogWindow")
	public String anaaaAccountLogWindow() {
		return "modules/anaaa/anaaaAccountLogWindow";
	}
	
	/**
	 * Anaaa开销户接口日志查询
	 */
	@RequiresPermissions("anaaa:anaaa:accountLog")
	@RequestMapping("anaaaAccountLog")
	public String anaaaAccountLog() {
		return null;
	}
	
	/**
	 * 	Anaaa查询
	 * @return
	 */
	@RequiresPermissions("anaaa:anaaa:list")
	@RequestMapping("query")
	@ResponseBody
	public AjaxJson query() {
		
		// System.out.println("结果:"+systemReturn);
		AjaxJson json = new AjaxJson();
		
		String systemReturn = "WSQAN3A|1|460036221281615|0$0|Success|460036221281615|1|0|8602088332110||-1|-1|0|1||0";
		if (systemReturn.equalsIgnoreCase("f")) {
			json.setSuccess(false);
			json.setMsg("没有查询到相关信息");
		} else if (systemReturn.equalsIgnoreCase("NRM")) {
			json.setSuccess(false);
			json.setMsg("查询失败");
		} else {
			if (systemReturn.split("\\|").length <= 4) {
				json.setSuccess(false);
				json.setMsg("后台无数据返回");
			} else {
				// System.out.println("sockeReturn" + systemReturn);
				String[] strs = systemReturn.split("\\$", -1);
				if (strs.length > 1) {
					String[] columnArray = strs[1].split("\\|", -1);
					// 用户状态
					String status = "";
					if (!StringUtils.isEmpty(columnArray[3])) {
						if (columnArray[3].equals("1")) {
							status = "上线并鉴权成功";
						} else if (columnArray[3].equals("7")) {
							status = "未激活";
						} else if (columnArray[3].equals("4")) {
							status = "暂停";
						} else {
							status = columnArray[3];
						}
					}
					// 用户类型
					String userType = "";
					if (!StringUtils.isEmpty(columnArray[7])) {
						if (columnArray[7].equals("0")) {
							userType = "普通";
						} else {
							userType = columnArray[6];
						}
					}
					// 用户UIM卡类型
					String uimType = "";
					if (!StringUtils.isEmpty(columnArray[8])) {
						if (columnArray[8].equals("0")) {
							uimType = "MD5";
						} else if (columnArray[8].equals("1")) {
							uimType = "CAVE";
						} else if (columnArray[8].equals("2")) {
							uimType = "AKA-CAVE";
						} else if (columnArray[8].equals("3")) {
							uimType = "AKA/MD5";
						} else {
							uimType = columnArray[8];
						}
					}
					// 免监权标识
					String noAuthFlag = "";
					if (!StringUtils.isEmpty(columnArray[9])) {
						if (columnArray[9].equals("0")) {
							noAuthFlag = "鉴权";
						} else if (columnArray[9].equals("1")) {
							noAuthFlag = "免鉴权";
						} else {
							noAuthFlag = columnArray[9];
						}
					}
					// 漫游标识
					String roamId = "";
					if (!StringUtils.isEmpty(columnArray[10])) {
						if (columnArray[10].equals("0")) {
							roamId = "鉴权";
						} else if (columnArray[10].equals("1")) {
							roamId = "免鉴权";
						} else {
							roamId = columnArray[10];
						}
					}
					// HARDWAREIDAUTH
					String hardwareIdAuth = "";
					if (!StringUtils.isEmpty(columnArray[12])) {
						if (columnArray[12].equals("2")) {
							hardwareIdAuth = "绑定";
						} else {
							hardwareIdAuth = "没有进行MEID绑定";
						}
					}
					LinkedHashMap<String, Object> body = new LinkedHashMap<String,Object>();
					// 只有手机号非空的时候才进行权限控制
					if (StringUtils.isNotEmpty(columnArray[5]) && false) {//&& !anaaaService.hasRight(userinfo, columnArray[5])) {
						json.setSuccess(false);
						json.setMsg("没有该号码的操作权限");
					} else {
						body.put("resultCode", columnArray[0]);
						body.put("resultDescr", columnArray[1]);
						body.put("imsi", columnArray[2]);
						body.put("status", status);
						body.put("failTimes", columnArray[4]);
						body.put("mdn", columnArray[5]);
						body.put("evdoAlgorithm", columnArray[6]);
						body.put("userType", userType);
						body.put("uimType", uimType);
						body.put("noAuthFlag", noAuthFlag);
						body.put("roamId", roamId);
						if (AppUtil.isEmpty(columnArray[11]) == false && columnArray[11].startsWith("0x")) {
							body.put("meid", columnArray[11].substring(2));
						} else {
							body.put("meid", columnArray[11]);
						}
						body.put("hardwareIdAuth", hardwareIdAuth);
						json.setSuccess(true);
						json.setBody(body);
					}
				}
			}
		}
		return json;
	}
}