/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.aaa.web;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.AppUtil;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.domain.PriUserinfo;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.memory.MemoryDataManager;
import com.jeeplus.common.model.SessionModel;
import com.jeeplus.common.socket.SocketTool;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.ToolFunctions;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.aaa.entity.PhoneStatusLog;
import com.jeeplus.modules.aaa.service.AaaService;

/**
 * AaaController
 * @author zqb
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/aaa/aaa")
public class AaaController extends BaseController {
	
	private static String PROTOCOLHEADS = "QUAAA|";
	
	private static String[] userTypeCh = { "后付费", "预付费" };
	
	private static String[] WhiteNum={"ANO","CRMInfo","UserProperty","Validtime"};
	@Autowired
	private AaaService aaaService;
	
	/*
	@ModelAttribute
	public AAA get(@RequestParam(required=false) String id) {
		AAA entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = aaaService.get(id);
		}
		if (entity == null){
			entity = new AAA();
		}
		return entity;
	}*/
	
	/**
	 * AAA列表页面
	 */
	@RequiresPermissions("aaa:aaa:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/aaa/aaaList";
	}
	
	/**
	 * AAA集成开户页面
	 */
	@RequiresPermissions("aaa:aaa:jckh")
	@RequestMapping(value = "jckhForm")
	public String jckhForm() {
		return "modules/aaa/aaaJckhForm";
	}

	/**
	 * AAA销卡页面
	 */
	@RequiresPermissions("aaa:aaa:cancelAccount")
	@RequestMapping(value = "cancelAccountForm")
	public String cancelAccountForm() {
		return "modules/aaa/aaaCancelAccountForm";
	}
	
	/**
	 * AAA换号页面
	 */
	@RequiresPermissions("aaa:aaa:changeNumber")
	@RequestMapping(value = "changeNumberForm")
	public String changeNumberForm() {
		return "modules/aaa/aaaChangeNumberForm";
	}
	
	/**
	 * AAA加入黑名单页面
	 */
	@RequiresPermissions("aaa:aaa:addBlackList")
	@RequestMapping(value = "addBlackListForm")
	public String addBlackListForm() {
		return "modules/aaa/aaaAddBlackListForm";
	}
	
	/**
	 * AAA解除黑名单页面
	 */
	@RequiresPermissions("aaa:aaa:removeBlackList")
	@RequestMapping(value = "removeBlackListForm")
	public String removeBlackListForm() {
		return "modules/aaa/aaaRemoveBlackListForm";
	}
	
	/**
	 * AAA业务更改页面
	 */
	@RequestMapping(value = "businessModifyForm")
	public String businessModifyForm() {
		return "modules/aaa/aaaBusinessModifyForm";
	}
	
	/**
	 * AAA普通用户换卡页面
	 */
	@RequiresPermissions("aaa:aaa:changeIMSI")
	@RequestMapping(value = "changeIMSIForm")
	public String changeIMSIForm() {
		return "modules/aaa/aaaChangeIMSIForm";
	}
	
	/**
	 * AAACG国际漫游卡换卡页面
	 */
	@RequiresPermissions("aaa:aaa:changeCGIMSI")
	@RequestMapping(value = "changeCGIMSIForm")
	public String changeCGIMSIForm() {
		return "modules/aaa/aaaChangeCGIMSIForm";
	}
	
	/**
	 * AAA认证日志页面
	 */
	@RequiresPermissions("aaa:aaa:authenticationLog")
	@RequestMapping(value = "authenticationLogForm")
	public String authenticationLogForm() {
		return "modules/aaa/aaaAuthenticationLogForm";
	}
	
	/**
	 * AAA开销户日志页面
	 */
	@RequiresPermissions("aaa:aaa:accountLog")
	@RequestMapping(value = "accountLogQueryWindow")
	public String accountLogForm() {
		return "modules/aaa/aaaAccountLogQueryWindow";
	}
	
	/**
	 * AAA软拨测页面
	 */
	@RequiresPermissions("aaa:aaa:softwareCallTest")
	@RequestMapping(value = "softwareCallTestWindow")
	public String softwareCallTestWindow() {
		return "modules/aaa/aaaSoftwareCallTestWindow";
	}
	
	/**
	 * AAA批量操作页面
	 */
	@RequestMapping(value = "batchOperateWindow")
	public String batchOperateWindow() {
		return "modules/aaa/aaaBatchOperateWindow";
	}
	
	/**
	 * AAA查询
	 */
	@ResponseBody
	@RequestMapping("infoQuery")
	public AjaxJson infoQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();
		AjaxJson json = new AjaxJson();
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		String userNoValue = String.valueOf(paramsMap.get("userNoValue")); // 接收来的号码参数：“IMSI号”或者“手机号”
		List<?> infoList = aaaService.aaaInfoQuery(request, paramsMap);
		body.put("aaaInfo", infoList);
		if (infoList != null) {
			json.setSuccess(true);
			json.setBody(body);
		} else {
			json.setSuccess(false);
			json.setMsg("查无此号码！");
		}
		return json;
	}
	
	/**
	 * <p>
	 * <strong>integratedOpenAccount</strong> - 集成开户
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return AjaxJson throws Exception
	 */
	@RequiresPermissions("aaa:aaa:jckh")
	@ResponseBody
	@RequestMapping(value = "integratedOpenAccount")
	public AjaxJson integratedOpenAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		String cMDN = String.valueOf(paramsMap.get("cMDN"));
		Date operateStartTime = new Date();
		String result = aaaService.integratedOpenAccount(request, paramsMap);
		
		AjaxJson json = new AjaxJson();
		json.setMsg(result);
		json.setSuccess(true);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>cancelAccount</strong> - 销户
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return AjaxJson
	 */
	@RequiresPermissions("aaa:aaa:cancelAccount")
	@ResponseBody
	@RequestMapping(value = "cancelAccount")
	public AjaxJson cancelAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		String numberType = String.valueOf(paramsMap.get("numberType")).trim();
		String operateNumber = String.valueOf(paramsMap.get("operateNumber")).trim();
//		String result = aaaService.cancelAccount(request, numberType, operateNumber);
		String result = "成功";
		
		AjaxJson json = new AjaxJson();
		if (result.indexOf("成功") > -1) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>changeNumber</strong> - 换号
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return AjaxJson
	 */
	@RequiresPermissions("aaa:aaa:changeNumber")
	@ResponseBody
	@RequestMapping(value = "changeNumber")
	public AjaxJson changeNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		Date operateStartTime = new Date();
		boolean operateSuccessFlg = true;
		String operateNumber = String.valueOf(paramsMap.get("operateNumber")).trim();
		String newNumber = String.valueOf(paramsMap.get("newNumber")).trim();
//		String result = aaaService.changeNumber(request, operateNumber, newNumber);
		String result = "成功";
		AjaxJson json = new AjaxJson();
		if (result.indexOf("成功") > -1) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>changeIMSI</strong> - 换卡
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView
	 */
	@RequiresPermissions("aaa:aaa:changeIMSI")
	@ResponseBody
	@RequestMapping("changeIMSI")
	public AjaxJson changeIMSI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		Date operateStartTime = new Date();
		boolean operateSuccessFlg = true;
		String operateNumber = String.valueOf(paramsMap.get("operateNumber")).trim();
		String imsiNumber = String.valueOf(paramsMap.get("imsiNumber")).trim();
		String newIMSINumber = String.valueOf(paramsMap.get("newIMSINumber")).trim();
//		String result = aaaService.changeIMSI(request, operateNumber, imsiNumber, newIMSINumber);
		String result = "成功";
		
		AjaxJson json = new AjaxJson();
		if (result.indexOf("成功") > -1) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>changeCGIMSI</strong> - CG国际换卡
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView
	 */
	@RequiresPermissions("aaa:aaa:changeIMSI")
	@ResponseBody
	@RequestMapping("changeCGIMSI")
	public AjaxJson changeCGIMSI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		Date operateStartTime = new Date();
		boolean operateSuccessFlg = true;
		String operateNumber = String.valueOf(paramsMap.get("operateNumber")).trim();
		String imsiCNumber = String.valueOf(paramsMap.get("imsiCNumber")).trim();
		String imsiGNumber = String.valueOf(paramsMap.get("imsiGNumber")).trim();
		String newIMSICNumber = String.valueOf(paramsMap.get("newIMSICNumber")).trim();
		String newIMSIGNumber = String.valueOf(paramsMap.get("newIMSIGNumber")).trim();
//		String result = aaaService.changeCGIMSI(request, operateNumber, newIMSICNumber, newIMSIGNumber);
		String result = "成功";
		String logDetails = "";
		
		AjaxJson json = new AjaxJson();
		if (result.indexOf("成功") > -1) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>businessModify</strong> - 业务更改
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView
	 */
	@ResponseBody
	@RequestMapping(value = "businessModify")
	public AjaxJson businessModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		String operateNumber = String.valueOf(paramsMap.get("operateNumber")).trim();
		String businessType = String.valueOf(paramsMap.get("businessType")).trim();
		String operateParams = String.valueOf(paramsMap.get("operateParams")).trim();
//		String result = aaaService.businessModify(request, operateNumber, businessType, operateParams);
		String result = "成功";
		
		AjaxJson json = new AjaxJson();
		if (result.indexOf("成功") > -1) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>addBlackList</strong> - 添加黑名单
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView
	 */
	@RequiresPermissions("aaa:aaa:addBlackList")
	@ResponseBody
	@RequestMapping(value = "addBlackList")
	public AjaxJson addBlackList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		// SimpleDateFormat formatDateTime1 = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> model = new HashMap<String, Object>();
		Date operateStartTime = new Date();
		boolean operateSuccessFlg = true;
		String numberType = String.valueOf(paramsMap.get("numberType")).trim();
		String operateNumber = String.valueOf(paramsMap.get("operateNumber")).trim();
		String startTime = String.valueOf(paramsMap.get("startTime")).trim();
		// String csDate = formatDateTime1.format(paramsMap.get("csDate")).trim(); // 下一版本才需要
		String endTime = String.valueOf(paramsMap.get("endTime")).trim();
//		String result = aaaService.addBlackList(request, numberType, operateNumber, startTime, null, endTime);
		String result = "成功";
		
		AjaxJson json = new AjaxJson();
		if (result.indexOf("成功") > -1) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>removeBlackList</strong> - 移除黑名单
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView
	 */
	@RequiresPermissions("aaa:aaa:removeBlackList")
	@ResponseBody
	@RequestMapping(value = "removeBlackList")
	public AjaxJson removeBlackList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		Date operateStartTime = new Date();
		boolean operateSuccessFlg = true;
		String numberType = String.valueOf(paramsMap.get("numberType")).trim();
		String operateNumber = String.valueOf(paramsMap.get("operateNumber")).trim();
//		String result = aaaService.removeBlackList(request, numberType, operateNumber);
		String result = "成功";

		AjaxJson json = new AjaxJson();
		if (result.indexOf("成功") > -1) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>getSoftwareCallTestSearchResult</strong> - 获取软拨测查询结果
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@RequiresPermissions("aaa:aaa:softwareCallTest")
	@ResponseBody
	@RequestMapping(value = "getSoftwareCallTestSearchResult")
	public AjaxJson getSoftwareCallTestSearchResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		String result = aaaService.getSoftwareCallTestSearchResult(request, paramsMap);
		
		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		json.setMsg(result);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>exportAuthenticationLog</strong> - 获取认证日志列表JSON格式
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@RequiresPermissions("aaa:aaa:authenticationLog")
	@ResponseBody
	@RequestMapping(value = "exportAuthenticationLog")
	public AjaxJson exportAuthenticationLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		List<?> alList = aaaService.getAuthenticationLogList(request, paramsMap);
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		
		for (int i = 0; i < alList.size(); i++) {
			Map<?, ?> alMap = (Map<?, ?>) alList.get(i);
			List<Object> line = new ArrayList<Object>();
			// naiusername|logtime|pdsnip|pcfip|nasport|nasportid|nasporttype|result
			line.add(String.valueOf(alMap.get("naiusername")));
			line.add(String.valueOf(alMap.get("logtime")));
			line.add(String.valueOf(alMap.get("pdsnip")));
			line.add(String.valueOf(alMap.get("pcfip")));
			line.add(String.valueOf(alMap.get("nasport")));
			line.add(String.valueOf(alMap.get("nasportid")));
			line.add(String.valueOf(alMap.get("nasporttype")));
			line.add(String.valueOf(alMap.get("result")));
			dataList.add(line);
		}
		
//		SimpleExcelView view = new SimpleExcelView("认证日志记录", "", new String[] { "NAIUSERNAME", "LOGTIME", "PDSNIP", "PCFIP", "NASPORT", "NASPORTID",
//				"NASPORTTYPE", "RESULT" }, dataList);
//		return new ModelAndView(view, new HashMap<String, Object>());
		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();//封装json的map
		body.put("title", "认证日志记录");
		body.put("subTitle", "");
		body.put("header", new String[] { "NAIUSERNAME", "LOGTIME", "PDSNIP", "PCFIP", "NASPORT", "NASPORTID", "NASPORTTYPE", "RESULT" });
		body.put("dataList", dataList);
		json.setBody(body);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>getAuthenticationLogToGridJSON</strong> - 获取认证日志列表JSON格式
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@RequiresPermissions("aaa:aaa:authenticationLog")
	@ResponseBody
	@RequestMapping(value = "getAuthenticationLogToGridJSON")
	public AjaxJson getAuthenticationLogToGridJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Date operateStartTime = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		String ismiNumber = String.valueOf(paramsMap.get("ismiNumber"));
		String startDate = formatDate.format(paramsMap.get("startDate"));
		String startTime = String.valueOf(paramsMap.get("startTime")).replaceAll(":", "-");
		String endDate = formatDate.format(paramsMap.get("endDate"));
		String endTime = String.valueOf(paramsMap.get("endTime")).replaceAll(":", "-");
		String queryParams = "IMSI号码：" + ismiNumber + "；开始时间：" + startDate + " " + startTime + "；结束时间：" + endDate + " " + endTime;
		List<?> alList = aaaService.getAuthenticationLogList(request, paramsMap);
		
		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();//封装json的map
		body.put("totalCount", alList.size());
		body.put("authenticationLog", ToolFunctions.getStringFromList(alList));
		json.setBody(body);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>exportAccountLog</strong> - 获取开销户日志列表JSON格式
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author zengqq
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@RequiresPermissions("aaa:aaa:accountLog")
	@ResponseBody
	@RequestMapping(value = "exportAccountLog")
	public AjaxJson exportAccountLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		List<?> alList = aaaService.getAccountLogList(request, paramsMap);
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		
		for (int i = 0; i < alList.size(); i++) {
			Map<?, ?> alMap = (Map<?, ?>) alList.get(i);
			List<Object> line = new ArrayList<Object>();
			// PROCTIME|IMSI|MDN|ACTION|RESCODE
			// 记录时间|IMSI主叫号码|MDN|操作|操作结果
			line.add(String.valueOf(alMap.get("procTime")));
			line.add(String.valueOf(alMap.get("imsi")));
			line.add(String.valueOf(alMap.get("action")));
			line.add(String.valueOf(alMap.get("mdn")));
			line.add(String.valueOf(alMap.get("rescode")));
			dataList.add(line);
		}
		
//		SimpleExcelView view = new SimpleExcelView("AAA开销户日志记录", "", new String[] { "记录时间", "IMSI", "操作", "MDN", "操作结果" }, dataList);
//		
//		return new ModelAndView(view, new HashMap<String, Object>());
		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();//封装json的map
		body.put("title", "开销户日志记录");
		body.put("subTitle", "");
		body.put("header", new String[] { "NAIUSERNAME", "LOGTIME", "PDSNIP", "PCFIP", "NASPORT", "NASPORTID", "NASPORTTYPE", "RESULT" });
		body.put("dataList", dataList);
		json.setBody(body);
		return json;
	}
	
	/**
	 * <p>
	 * <strong>getAccountLogToGridJSON</strong> - 获取开销户日志列表JSON格式
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author zengqq
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@RequiresPermissions("aaa:aaa:accountLog")
	@ResponseBody
	@RequestMapping(value = "getAccountLogToGridJSON")
	public AjaxJson getAccountLogToGridJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		String imsiNumber = String.valueOf(paramsMap.get("imsiNumber"));
		String startDate = formatDate.format(paramsMap.get("startDate"));
		String startTime = String.valueOf(paramsMap.get("startTime"));
		String endDate = formatDate.format(paramsMap.get("endDate"));
		String endTime = String.valueOf(paramsMap.get("endTime"));
		
		List<?> alList = aaaService.getAccountLogList(request, paramsMap);

		AjaxJson json = new AjaxJson();
		json.setSuccess(true);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();//封装json的map
		body.put("totalCount", alList.size());
		body.put("alList", ToolFunctions.getStringFromList(alList));
		json.setBody(body);
		return json;
	}
	
	/**
	 * 根据IMSI查询号码
	 * 
	 * @param userNo
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryUserNoByIMSI")
	public String queryUserNoByIMSI(String userNo, HttpSession session) {
		String IMSI = "";
		String cmd = "QUAAA|0|" + userNo + "|" + 0;
		try {
			String re = SocketTool.sendCmd(session, cmd);
			if (re.startsWith("QUAAA|0|")) {
				String[] tokens = re.split("\\|", -1);
				if (tokens.length >= 12) {
					IMSI = tokens[6];
				}
			}
		} catch (Exception e) {
			
		}
		return IMSI;
	}
	
	/**
	 * 根据操作类型operaterFlg取得不同的参数
	 * 
	 * @param sbb
	 * @param request
	 * @param operaterFlg
	 */
	private void getParamters(StringBuffer sbb, HttpServletRequest request, String operaterFlg) {
		if ("1".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append(request.getParameter("imsiNo")).append("|")
					.append(request.getParameter("userType")).append("|").append("").append("|").append("");
		} else if ("2".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append(request.getParameter("imsiNo")).append("|")
					.append(request.getParameter("userType")).append("|").append(request.getParameter("imsiGNo")).append("|").append("");
		} else if ("4".equals(operaterFlg)) {
			sbb.append("|").append(request.getParameter("userNo")).append("|").append("").append("|").append("").append("|").append("").append("|").append("");
		} else if ("5".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append("86" + request.getParameter("newNo")).append("|").append("")
					.append("|").append("").append("|").append("");
		} else if ("6".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append(request.getParameter("newIMSINo")).append("|").append("")
					.append("|").append("").append("|").append("");
		} else if ("7".equals(operaterFlg)) {
			sbb.append("|").append(request.getParameter("imsiNo")).append("|").append(request.getParameter("newIMSINo")).append("|")
					.append(request.getParameter("imsiGNo")).append("|").append(request.getParameter("newIMSIGNo")).append("|").append("");
		} else if ("10".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append(request.getParameter("userType")).append("|").append("")
					.append("|").append("").append("|").append("");
		} else if ("13".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append(request.getParameter("vpdnValue")).append("|").append("")
					.append("|").append("").append("|").append("");
		} else if ("25".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append(request.getParameter("startTime")).append("|")
					.append(request.getParameter("endTime")).append("|").append("");
		} else if ("26".equals(operaterFlg)) {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append("").append("|").append("").append("|").append("");
		} else {
			sbb.append("|").append("86" + request.getParameter("userNo")).append("|").append("").append("|").append("").append("|").append("").append("|")
					.append("");
		}
	}
	
	/**
	 * 根据翻译数字形式的操作类型
	 * 
	 * @param operatedType
	 * @return
	 */
	private String getLogStringByOperater(String operatedType, HttpServletRequest request) {
		String logString = "";
		if ("1".equals(operatedType)) {
			logString = "普通用户：" + request.getParameter("userNo") + " IMSI号:" + request.getParameter("imsiNo") + "开户";
		} else if ("2".equals(operatedType)) {
			logString = "CG国际漫游卡：" + request.getParameter("userNo") + " IMSI号:" + request.getParameter("imsiGNo") + "开户";
		} else if ("3".equals(operatedType)) {
			logString = "根据手机号码：" + request.getParameter("userNo") + "销卡";
		} else if ("4".equals(operatedType)) {
			logString = "根据IMSI号码：" + request.getParameter("userNo") + "销卡";
		} else if ("5".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "换号成：" + request.getParameter("newNo");
		} else if ("6".equals(operatedType)) {
			logString = "普通用户：" + request.getParameter("userNo") + "换卡：从IMSI号码：" + request.getParameter("imsiNo") + "换成新IMSI号码："
					+ request.getParameter("newIMSINo");
		} else if ("7".equals(operatedType)) {
			logString = "CG国际漫游用户：" + request.getParameter("userNo") + "换卡：从IMSI号码：" + request.getParameter("imsiNo") + "、G网IMSI号码："
					+ request.getParameter("imsiGNo") + "换成新IMSI号码：" + request.getParameter("newIMSINo") + "、新G网IMSI号码：" + request.getParameter("newIMSIGNo");
		} else if ("8".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "停机";
		} else if ("9".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "复机";
		} else if ("10".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "更改用户类型为：" + userTypeCh[Integer.parseInt(request.getParameter("userType"))];
		} else if ("11".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡为业务受限状态";
		} else if ("12".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "释放卡的业务绑定状态";
		} else if ("13".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡到VPDN域名：" + request.getParameter("vpdnValue");
		} else if ("14".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡到黑莓用户组";
		} else if ("15".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡到CTNET用户组";
		} else if ("16".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡到CTWAP用户组";
		} else if ("17".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡到BREW用户组";
		} else if ("18".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡到MAIL用户组";
		} else if ("19".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "绑定卡到JAVA用户组";
		} else if ("20".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "更改卡归属组为QCHAT业务组";
		} else if ("21".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "更改卡归属组为金牌业务组";
		} else if ("22".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "更改卡归属组为银牌业务组";
		} else if ("23".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "更改卡归属组为铜牌业务组";
		} else if ("24".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "更改卡归属组为VT业务组";
		} else if ("25".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "禁止本地用户在指定时间段内使用任何业务";
		} else if ("26".equals(operatedType)) {
			logString = "手机号码：" + request.getParameter("userNo") + "取消禁止本地用户在指定时间段内使用任何业务的限制";
		}
		return logString;
	}
	
	/**
	 * <p>
	 * <strong>batchOperate</strong> - 批量操作
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author xxx
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "batchOperate")
	public AjaxJson batchOperate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long beginTime = System.currentTimeMillis(), endTime = 0;
		String regMDN = "[1][0-9]{10}";
		String bat = request.getParameter("bat"); // 批处理文本
		String operateType = request.getParameter("operateType"); // 操作类型
		String operateName = "";
		String[] lines = bat.split("\n", -1); // 将文本以换行符拆分成数组行
		String result = "";
		
		if(lines.length<200){
			for (int i = 0; i < lines.length; i++) {
				Date operateStartTime = new Date();
				String logDetail = "";

				String line = String.valueOf(lines[i]).trim();
				
				if (line.equals("") == true || line.startsWith("#") == true) { // 空行和注释行不作任何处理
					continue;
				}
				
				String finalCmd = ""; // 最终协议指令
				String operateNumber = ""; // 号码
				
				try {
					String[] tokens = line.split("\\s+|,|\t", -1); // 通过空格及制表符拆分数组
					
					operateNumber = tokens[0];
					if (operateType.equals("queryByIMSI") == false) {
						if (operateNumber.matches(regMDN) == true && operateNumber.startsWith("86") == false) {
							operateNumber = "86" + operateNumber;
						}
					}
					if (operateType.equals("query")) { // 批量查询（用手机号）
						finalCmd = "QUAAA|102|" + operateNumber + "|1";
						logDetail = "AAA查询: " + operateNumber;
					} else if (operateType.equals("queryByIMSI")) { // 批量查询（用IMSI号）
						finalCmd = "QUAAA|102|" + operateNumber + "|0";
						logDetail = "AAA查询: " + operateNumber;
					} else if (operateType.equals("open")) {
						// MOAAA|00|MDN$C网IMSI|C网付费类型|C网停复机状态|C网归属组$G网IMSI|G网用户类型|G网停复机状态|G网归属组
						// MOAAA|00|8613000000000$460030000000000|0|0|gold_user$204040000000000|0|0|gold_user
						if (tokens.length == 3) {
							String imsiNumber = tokens[1];
							
							if (imsiNumber.startsWith("46003") == true) { // C网的
								finalCmd = "MOAAA|00|" + operateNumber + "$" + tokens[1] + "|" + tokens[2] + "||$|||";
							} else {
								finalCmd = "MOAAA|00|" + operateNumber + "$|||$" + tokens[1] + "|" + tokens[2] + "||";
							}
							logDetail = "AAA普通开户, MDN:" + operateNumber + ", IMSI: " + tokens[1];
						} else if (tokens.length == 5) {
							finalCmd = "MOAAA|00|" + operateNumber + "$" + tokens[1] + "|" + tokens[2] + "||$" + tokens[3] + "|" + tokens[4] + "||";
							logDetail = "AAACG国际漫游卡开户, MDN:" + operateNumber + ", C网IMSI：" + tokens[1] + ", G网IMSI：" + tokens[3];
						}
					} else if (operateType.equals("cancel")) { // 销户
						finalCmd = "MOAAA|99|" + operateNumber;
						logDetail = "AAA销卡：操作号码:" + operateNumber;
					} else if (operateType.equals("suspendresume")) { // 停复机
						String status = tokens[1];
						operateName = (status.equals("0") == true) ? "复机" : "停机";
						finalCmd = "MOAAA|05|" + operateNumber + "|" + status;
						logDetail = "AAA" + operateName + "：" + operateNumber;
					} else if (operateType.equals("lockunlock")) { // 受限加解锁
						String status = tokens[1];
						
						operateName = "卡业务受限" + ((status.equals("0") == true) ? "解锁" : "加锁");
						finalCmd = "MOAAA|04|" + operateNumber + "|" + status;
						logDetail = "AAA" + operateName + "：" + operateNumber;
					} else if (operateType.equals("bindVPDN")) { // 卡绑定VPDN域名
						finalCmd = "MOAAA|02|" + operateNumber + "|" + tokens[1];
						logDetail = "AAA卡绑定VPDN域名:" + operateNumber + " VPDN: " + tokens[1];
					} else if (operateType.equals("bindBusiness")) { // 卡绑定业务
						String business = tokens[1];
						
						finalCmd = "MOAAA|03|" + operateNumber + "|" + business;
						logDetail = "AAA卡绑定业务, 绑定卡到黑莓业务:" + operateNumber;
					} else if (operateType.equals("ownerGroup")) { // 归属组
						String ownerGroup = tokens[1];
						
						operateName = "更改卡归属组";
						finalCmd = "MOAAA|09|" + operateNumber + "|" + ownerGroup;
						logDetail = "AAA更改卡归属组：更改"
								+ operateNumber
								+ "的卡归属组为"
								+ AppUtil.initColumnValue(ownerGroup, -9999, -9999, 1, MemoryDataManager.getList("aaagszlxList"), "englishDescription",
										"chineseDescription");
					} else if (operateType.equals("fflx")) { // 修改付费类型
						int fflx = Integer.parseInt(String.valueOf(tokens[1]).trim());
						
						finalCmd = "MOAAA|10|" + operateNumber + "|" + tokens[1];
						logDetail = "AAA更改付费类型：更改" + operateNumber + "号的付费类型为"
								+ AppUtil.initColumnValue(null, -9999, fflx, 3, MemoryDataManager.getList("aaafflxList"), "intValue", "chineseDescription");
					} else if (operateType.equals("addBlackList")) { // 添加黑名单
						String startTime = tokens[1];
						// String csDate = tokens[2]; // 下一版本才需要
						String endTime2 = tokens[2];
						
						finalCmd = "MOAAA|11|" + operateNumber + "|" + startTime + "||" + endTime2;
						logDetail = "AAA加入黑名单:" + operateNumber;
					} else if (operateType.equals("removeBlackList")) { // 移除黑名单
						finalCmd = "MOAAA|12|" + operateNumber;
						logDetail = "AAA解除黑名单：" + operateNumber;
					} else if (operateType.equals("myqx")) { // 更改漫游权限
						int myqx = Integer.parseInt(String.valueOf(tokens[1]).trim());
						
						finalCmd = "MOAAA|01|" + operateNumber + "|" + String.valueOf(tokens[1]).trim();
						logDetail = "AAA更改漫游权限：更改" + operateNumber + "号的漫游权限为\""
								+ AppUtil.initColumnValue(null, -9999, myqx, 3, MemoryDataManager.getList("aaamyqxList"), "intValue", "chineseDescription") + "\"";
					} else if (operateType.equals("permitacctype")) { // 更改c网接入类型
						int permitacctype = Integer.parseInt(String.valueOf(tokens[1]).trim());
						
						finalCmd = "MOAAA|13|" + operateNumber + "|" + String.valueOf(tokens[1]).trim();
						logDetail = "AAA更改c网接入类型：更改" + operateNumber + "号的接入类型为\""
								+ AppUtil.initColumnValue(null, -9999, permitacctype, 3, MemoryDataManager.getList("aaapertypeList"), "intValue", "chineseDescription") + "\"";
					}
				} catch (Exception e) {
					result += operateNumber + "：操作失败， 输入参数有误！\n";
					continue;
				}
				
				String cmdResponse = SocketTool.sendCmd(request.getSession(), finalCmd.toString());
				boolean successFlag = false;
				
				if (operateType.equals("query") == true || operateType.equals("queryByIMSI") == true) { // 用户查询
					if (cmdResponse.startsWith("QUAAA|102|")) {
						
						String[] tokens = cmdResponse.split("\\$", -1); // 拆分后长度一定是3
						
						if (tokens.length < 2) {
							result += operateNumber + " \n";
						} else {
							// 手机号|C网IMSI号|C网状态|C网漫游权限值|C网用户类型|C网用户绑定选项|C网用户绑定NAI|C网用户绑定VPN|C网用户组$
							// 手机号|G网IMSI号|G网状态|G网漫游权限值|G网用户类型|G网用户绑定选项|G网用户绑定NAI|G网用户绑定VPN|G网用户组
							for (int n = 1; n < tokens.length; n++) {
								String listInfo = tokens[n];
								String[] liArray = listInfo.split("\\|", -1);
								String imsiNumber = (liArray.length > 1) ? liArray[1] : "";
								String tempAAAInfo = "";
								// AppUtil.initColumnValue(null, -9999, myqx, 3, MemoryDataManager.getList("aaamyqxList"),
								// "intValue", "chineseDescription")
								String imsi = liArray[1].trim();
								
								if (imsi.equals("") == true) {
									continue;
								}
								if (imsiNumber.startsWith("46003") == true) {
									tempAAAInfo += "手机号：" + liArray[0].trim();
									tempAAAInfo += ";IMSI号：" + liArray[1].trim();
									tempAAAInfo += ";停复机状态："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[2].trim().equals("") == true) ? "-9999" : liArray[2].trim()), 3,
													MemoryDataManager.getList("aaatfjList"), "intValue", "chineseDescription");
									tempAAAInfo += ";漫游权限："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[3].trim().equals("") == true) ? "-9999" : liArray[3].trim()), 3,
													MemoryDataManager.getList("aaamyqxList"), "intValue", "chineseDescription");
									tempAAAInfo += ";付费类型："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[4].trim().equals("") == true) ? "-9999" : liArray[4].trim()), 3,
													MemoryDataManager.getList("aaafflxList"), "intValue", "chineseDescription");
									tempAAAInfo += ";受限状态："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[5].trim().equals("") == true) ? "-9999" : liArray[5].trim()), 3,
													MemoryDataManager.getList("aaasxztList"), "intValue", "chineseDescription");
									tempAAAInfo += ";业务类型："
											+ AppUtil.initColumnValue(liArray[6].trim(), -9999, -9999, 1, MemoryDataManager.getList("aaaywlxList"), "intValue",
													"chineseDescription");
									tempAAAInfo += ";VPDN：" + liArray[7].trim();
									tempAAAInfo += ";归属组："
											+ AppUtil.initColumnValue(liArray[8].trim(), -9999, -9999, 1, MemoryDataManager.getList("aaagszlxList"),
													"englishDescription", "chineseDescription");
									if(n == 1 && liArray.length >9){
										tempAAAInfo += ";c网接入类型："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[9].trim().equals("") == true) ? "-9999" : liArray[9].trim()), 3,
													MemoryDataManager.getList("aaapertypeList"), "intValue", "chineseDescription")+ ";";
									}
									if(n == 2 && liArray.length >9){
										tempAAAInfo += ";g网接入类型："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[9].trim().equals("") == true) ? "-9999" : liArray[9].trim()), 3,
													MemoryDataManager.getList("aaapertypeList"), "intValue", "chineseDescription")+ ";";
									}
								} else {
									tempAAAInfo += "手机号：" + liArray[0].trim();
									tempAAAInfo += ";IMSI号：" + liArray[1].trim();
									tempAAAInfo += ";停复机状态："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[2].trim().equals("") == true) ? "-9999" : liArray[2].trim()), 3,
													MemoryDataManager.getList("aaatfjList"), "intValue", "chineseDescription");
									tempAAAInfo += ";漫游权限："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[3].trim().equals("") == true) ? "-9999" : liArray[3].trim()), 3,
													MemoryDataManager.getList("aaamyqxList"), "intValue", "chineseDescription");
									tempAAAInfo += ";付费类型："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[4].trim().equals("") == true) ? "-9999" : liArray[4].trim()), 3,
													MemoryDataManager.getList("aaafflxList"), "intValue", "chineseDescription");
									tempAAAInfo += ";受限状态："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[5].trim().equals("") == true) ? "-9999" : liArray[5].trim()), 3,
													MemoryDataManager.getList("aaasxztList"), "intValue", "chineseDescription");
									tempAAAInfo += ";业务类型："
											+ AppUtil.initColumnValue(liArray[6].trim(), -9999, -9999, 1, MemoryDataManager.getList("aaaywlxList"), "intValue",
													"chineseDescription");
									tempAAAInfo += ";VPDN：" + liArray[7].trim();
									tempAAAInfo += ";归属组："
											+ AppUtil.initColumnValue(liArray[8].trim(), -9999, -9999, 1, MemoryDataManager.getList("aaagszlxList"),
													"englishDescription", "chineseDescription");
									if(n == 1 && liArray.length >9){
										tempAAAInfo += ";c网接入类型："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[9].trim().equals("") == true) ? "-9999" : liArray[9].trim()), 3,
													MemoryDataManager.getList("aaapertypeList"), "intValue", "chineseDescription")+ ";";
									}
									if(n == 2 && liArray.length >9){
										tempAAAInfo += ";g网接入类型："
											+ AppUtil.initColumnValue(null, -9999,
													Integer.parseInt((liArray[9].trim().equals("") == true) ? "-9999" : liArray[9].trim()), 3,
													MemoryDataManager.getList("aaapertypeList"), "intValue", "chineseDescription")+ ";";
									}
								}
								result += tempAAAInfo + "\n";
							}
							successFlag = true;
						}
					} else {
						result += operateNumber + ": 操作失败\n";
					}
				} else {
					if (cmdResponse.equals("T") == true) {
						successFlag = true;
						result += operateNumber + "：操作成功\n";
					} else if (cmdResponse.startsWith("F") == true) {
						result += operateNumber + "：操作失败！原因：" + AppUtil.getAAAErrorCodeInfo(cmdResponse) + "\n";
					} else {
						result += operateNumber + "：操作失败！\n";
					}
				}
			}
		}else{
			result += "批量操作的数据量不能超过200条！\n";
		}
		AjaxJson json = new AjaxJson();
		json.setMsg(result);
		json.setSuccess(true);
		return json;
	}
	
	private String decodeUserType(String str) {
		if ("0".equals(str)) { return "后付费"; }
		
		if ("1".equals(str)) { return "预付费"; }
		
		return str;
	}
	
	private String decodeUserState(String str) {
		if ("0".equals(str)) { return "正常"; }
		
		if ("1".equals(str)) { return "停机"; }
		
		return str;
	}
	
	private String decodeUserLimitState(String str) {
		if ("0".equals(str)) { return "正常"; }
		
		if ("1".equals(str)) { return "受限"; }
		
		return str;
	}
	
	private String decodeUserGroup(String str) {
		
		if ("1000000000".equals(str)) { return "默认组"; }
		if ("1000003000".equals(str)) { return "金牌用户"; }
		if ("1000002000".equals(str)) { return "银牌用户"; }
		if ("1000001000".equals(str)) { return "铜牌用户"; }
		if ("1000003002".equals(str)) { return "VT业务组"; }
		if ("2011000".equals(str)) { return "Qchat 业务组"; }
		
		return str;
	}
	
	/**
	 * <p>
	 * <strong>queryPhoneStatus</strong> - 天翼蓝盾平台黑/灰名单查询
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author lilin
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "queryPhoneStatus")
	public AjaxJson queryPhoneStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Date operateStartTime = new Date();
		String userNoValue = String.valueOf(paramsMap.get("userNoValue")); // 接收来的号码参数：“IMSI号”或者“手机号”
		String resultJson = aaaService.queryPhoneStatus(request, paramsMap);
		
		AjaxJson json = new AjaxJson();
		if (resultJson == null) {
			json.setSuccess(false);
		} else {
			json.setSuccess(true);
			LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();//封装json的map
			body.put("resultJson", resultJson);
			json.setBody(body);
		}
		return json;
	}
	
	/**
	 * <p>
	 * <strong>queryPhoneStatus</strong> - 天翼蓝盾平台黑/灰名单设置
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author lilin
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "resetPhoneStatus")
	public AjaxJson resetPhoneStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<?, ?> paramsMap = AppUtil.reqToRandomMap(request);
		Map<String, Object> model = new HashMap<String, Object>();
		Date operateStartTime = new Date();
		SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Summittime=formatDateTime.format(operateStartTime);
		PriUserinfo userinfo = (PriUserinfo) request.getSession().getAttribute(SessionModel.USERINFO);
		String userNoValue = String.valueOf(paramsMap.get("userNoValue")); // 接收来的号码参数：“IMSI号”或者“手机号”
		PhoneStatusLog phoneStatusLog =new PhoneStatusLog();
		phoneStatusLog.setPhone(userNoValue);
		phoneStatusLog.setUserid(userinfo.getUserId());
		phoneStatusLog.setStatus("已入库");
		phoneStatusLog.setSummittime(Summittime);
		boolean blnAddFlag = aaaService.addPhoneStatusLog(phoneStatusLog);
		
		//List<?> infoList = aaaService.queryPhoneStatus(request, paramsMap);
		
		AjaxJson json = new AjaxJson();
		if (blnAddFlag) {
			json.setSuccess(true);
			json.setMsg("提交成功！");
		} else {
			json.setSuccess(false);
			json.setMsg("提交失败，该号码在2小时之内不能重复提交");
		}
		return json;

	}
	/**
	 * <p>
	 * <strong>queryPhoneStatus</strong> - 白名单查询
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author zc
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "queryWhitePhoneStatus")	
	public AjaxJson queryWhitePhoneStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date operateStartTime = new Date();
		PriUserinfo sessionUser = (PriUserinfo) request.getSession().getAttribute(SessionModel.USERINFO);
		String message="";
		String logString="";
        String cmd="PHONEST|2|";
		String ANO=request.getParameter("ANO");
		cmd+=ANO;
		String cmdResponse = SocketTool.sendCmd(request.getSession(),cmd);
		
		AjaxJson json = new AjaxJson();
		
		if (cmdResponse.equalsIgnoreCase("f")) {
			message = "没有查询到相关信息";
			logString ="用户id:"+ sessionUser.getUserId()+",号码:"+ANO + "-查询失败。";
			json.setMsg(message);
			json.setSuccess(false);
		} else if (cmdResponse.equalsIgnoreCase("NRM")) {
			message = "查询失败";
			logString = "用户id:"+ sessionUser.getUserId()+",号码:"+ANO + "-查询失败。";
			json.setMsg(message);
			json.setSuccess(false);
		} else if(cmdResponse.trim().equals(cmd))
			{
				message = "查询成功";
				json.setMsg(message);
				json.setSuccess(true);
			}else {
				message = "查询成功";
				if(cmdResponse.contains(cmd.trim()+"|SUC|"))
				{
				cmdResponse=cmdResponse.replace(cmd.trim()+"|SUC|","");	
				}else if(cmdResponse.contains(cmd.trim()+"$"))
				{
					cmdResponse=cmdResponse.replace(cmd.trim()+"$","");
				}
				
				
				String[]str=cmdResponse.split("\\|");
				Map<String, Object> withe = new HashMap<String, Object>();
				for(int i=0;i<WhiteNum.length;i++)
				{
					withe.put(WhiteNum[i], str[i]);
				}
				json.setMsg(message);
				json.setSuccess(true);
				LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();//封装json的map
				body.put("list", withe);
				json.setBody(body);
			}
		return json;
	}
	/**
	 * <p>
	 * <strong>queryPhoneStatus</strong> - 设置白名单
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author zc
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	@ResponseBody
	@RequestMapping("setWhitePhoneStatus")
	public AjaxJson setWhitePhoneStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date operateStartTime = new Date();
		PriUserinfo sessionUser = (PriUserinfo) request.getSession().getAttribute(SessionModel.USERINFO);
		String message="";
		String logString="";
        String cmd="PHONEST|3|";
		String ANO=request.getParameter("ANO");
		String CRMInfo=request.getParameter("CRMInfo");
		String UserProperty=request.getParameter("UserProperty");
		String Validtime=request.getParameter("Validtime");
		cmd+=ANO+"|"+CRMInfo+"|"+UserProperty+"|"+Validtime.replaceAll("-","");
		String cmdResponse = SocketTool.sendCmd(request.getSession(),cmd);
		
		AjaxJson json = new AjaxJson();
		
		if (cmdResponse.equalsIgnoreCase("f")) {
			message = "置白失败";
			logString ="用户id:"+ sessionUser.getUserId()+",号码:"+ANO+ "-置白失败。";
			json.setMsg(message);
			json.setSuccess(false);
		} else if (cmdResponse.equalsIgnoreCase("NRM")) {
			message = "置白失败";
			logString = "用户id:"+ sessionUser.getUserId()+",号码:"+ANO+ "-置白失败。";
			json.setMsg(message);
			json.setSuccess(false);
		} else if(cmdResponse.trim().equals(cmd))
		{
		message = "置白成功";
		json.setMsg(message);
		json.setSuccess(true);
		}else {
			message = "置白成功";
			cmdResponse=cmdResponse.replace(cmd.trim()+"|SUC|","");
			if(cmdResponse.contains("0"))
			{
				json.setMsg(message);
				json.setSuccess(true);
			}else
			{
				json.setMsg("置白失败");
				json.setSuccess(false);
			}
			
		}
		return json;
	}

}