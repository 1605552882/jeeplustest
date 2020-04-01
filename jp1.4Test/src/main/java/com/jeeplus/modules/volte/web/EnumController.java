package com.jeeplus.modules.volte.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.utils.AppUtil;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.core.web.Servlets;
import com.jeeplus.modules.sys.interceptor.LogInterceptor;
import com.jeeplus.modules.sys.utils.LogUtils;
import com.jeeplus.modules.volte.service.EnumService;

/**
 * 
 * @author zh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/enum")
public class EnumController extends BaseController{
	
	@Autowired
	private EnumService enumService;
	
	/**
	 * ENUM网元信息
	 */
	@RequestMapping("/data")
	public String enumInfoPage() {
		String page = "modules/volte/enum";
		return page;
	}
	
	/**
	 * 查询ENUM网元信息
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String,Object> query() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("npcResultCode", "0");
		resultMap.put("ResultDescr", "success");
		resultMap.put("npvalidtime", "234");
		resultMap.put("portoutnetid", "567");
		resultMap.put("portinnetid", "555");
		resultMap.put("homenetid", "666");
		resultMap.put("porttype", "777");
		
		resultMap.put("success", true);
		resultMap.put("resultCode", "0");
		resultMap.put("CONTENT", "记录信息");
		return resultMap;
	}
	
	/**
	 * 增加enum资源信息
	 */
	@RequestMapping("/addEnum")
	@ResponseBody
	public Map<String,Object> addEnum() {
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("message", "添加成功！");
		return resultMap;
	}
	
	/**
	 * 删除enum资源信息
	 */
	@RequestMapping("/delEnum")
	@ResponseBody
	public Map<String,Object> delEnum() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("message", "删除成功！");
//		resultMap.put("success", false);
//		resultMap.put("message", "删除失败！");
		return resultMap;
	}
	
	
	/**
	 * 查询enum日志页面
	 */
	@RequiresPermissions("enum:queryEnumLogInfo")
	@RequestMapping(value = "/enumLogInfo")
	public String enumLogInfo() {
		return "modules/volte/enumLogInfo";
	}
	/**
	 * 查询enum日志
	 */
	@RequiresPermissions("enum:queryEnumLogInfo")
	@RequestMapping("/queryEnumLogInfo")
	@ResponseBody
	public Map<String,Object> queryEnumLog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<?, ?> paramMap = AppUtil.reqToRandomMap(request);
//		String number = String.valueOf(paramMap.get("number"));
		Map<String, Object> modelMap = enumService.queryEnumLog(request, paramMap);
		boolean successFlag = Boolean.parseBoolean(String.valueOf(modelMap.get("success")));
//		LogUtils.saveLog(Servlets.getRequest(), "ENUM日志查询","VoLTE","ENUM日志查询",LogUtils.QUERY,"ENUM日志查询:"+number,successFlag+"","",number,Integer.valueOf(LogInterceptor.getConnId()));
		return modelMap;
	}
	
}
