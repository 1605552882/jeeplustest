package com.jeeplus.modules.sms.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.core.web.BaseController;

/**
 * 短信平台控制器
 * @author zh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sms/")
public class SMSPlatController extends BaseController{

	/**
	 * 短信页面
	 */
	@RequestMapping("/smsInfo")
	public String volteMmtelPage() {
		String page = "modules/sms/smsInfo";
		return page;
	}
	
	/**
	 * 根据号码查询黑名单页面
	 */
	@RequestMapping("/numBlackListQryWindow")
	public String numBlackListQryWindow() {
		String page = "modules/sms/numBlackListQryWindow";
		return page;
	}
	/**
	 * 根据号码解除黑名单页面
	 */
	@RequestMapping("/numBlackListDelWindow")
	public String numBlackListDelWindow() {
		String page = "modules/sms/numBlackListDelWindow";
		return page;
	}
	
	/**
	 * 根据区号查询黑名单页面
	 */
	@RequestMapping("/areaCodeBlackListWindow")
	public String areaCodeBlackListWindow() {
		String page = "modules/sms/areaCodeBlackListWindow";
		return page;
	}
	
	/**
	 * 查询用户状态
	 */
	@RequestMapping("/selectUserSatus")
	@ResponseBody
	public Map<String,Object> selectUserSatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		map.put("userQueryFormPanelMinNo", "6241123673");
		map.put("userQueryFormPanelUserPoriperty", "0");
		map.put("userQueryFormPanelLanguage", "1");
		map.put("userQueryFormPanelAuthData", "aaa");
		map.put("userQueryFormPanelOpenTime", "May 17 2015 10:59AM");
		map.put("userQueryFormPanelPayType", "0");
		map.put("userQueryFormPanelOSCNo", "123");
		list.add(map);
		//返回数据
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		retMap.put("message", "查询成功");
		retMap.put("list", list);
		retMap.put("total", 1);
		return retMap;
	}
	
	/**
	 * 根据号码查询黑名单
	 */
	@RequestMapping("/selectUserInfoByNumber")
	@ResponseBody
	public Map<String,Object> selectUserInfoByNumber() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNum", "18928849206");
		map.put("status", "1");
		map.put("areaCode", "020");
		map.put("deadline", "2019-12-01 18:00:00");
		map.put("reason", "举报");
		map.put("releaseTime", "2019-12-22 18:00:00");
		map.put("usefulLife", "2019-12-20 18:00:00");//解除黑名单时间
		map.put("releaseCount", "10");//本月解黑次数
		map.put("limit", "1");//是否限制解黑 0否 1是
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("phoneNum", "18078118091");
		map2.put("status", "2");
		map2.put("areaCode", "0755");
		map2.put("deadline", "2020-01-01 12:00:00");
		map2.put("reason", "欠费");
		map2.put("releaseTime", "2020-01-06 12:00:00");
		map2.put("usefulLife", "2019-12-15 12:00:00");//解除黑名单时间
		map2.put("releaseCount", "2");//本月解黑次数
		map2.put("limit", "0");//是否限制解黑 0否 1是
		list.add(map);
		list.add(map2);
		//返回数据
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		retMap.put("message", "查询成功");
		retMap.put("rows", list);
		retMap.put("total", 2);
		return retMap;
	}
	
	/**
	 * 根据区号和时间查询黑名单
	 */
	@RequestMapping("/selectUserInfoByAreacodeAndTime")
	@ResponseBody
	public Map<String,Object> selectUserInfoByAreacodeAndTime() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNum", "18928849206");
		map.put("status", "1");
		map.put("areaCode", "020");
		map.put("deadline", "2019-12-01 18:00:00");
		map.put("reason", "举报");
		map.put("releaseTime", "2019-12-22 18:00:00");
		map.put("usefulLife", "2019-12-20 18:00:00");//解除黑名单时间
		map.put("releaseCount", "10");//本月解黑次数
		map.put("limit", "1");//是否限制解黑 0否 1是
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("phoneNum", "18078118091");
		map2.put("status", "2");
		map2.put("areaCode", "0755");
		map2.put("deadline", "2020-01-01 12:00:00");
		map2.put("reason", "欠费");
		map2.put("releaseTime", "2020-01-06 12:00:00");
		map2.put("usefulLife", "2019-12-15 12:00:00");//解除黑名单时间
		map2.put("releaseCount", "2");//本月解黑次数
		map2.put("limit", "0");//是否限制解黑 0否 1是
		list.add(map);
		list.add(map2);
		//返回数据
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		retMap.put("message", "查询成功");
		retMap.put("rows", list);
		retMap.put("total", 2);
		return retMap;
	}
	
	/**
	 * 解除黑名单
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public Map<String,Object> modify() {
		//返回数据
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		retMap.put("message", "解除成功");
		return retMap;
	}
	
	
}
