package com.jeeplus.modules.ctog.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.core.web.BaseController;

/**
 * 双网模关Controller
 * @author 钟晖
 * @version 2019-12-5
 */
@Controller
@RequestMapping(value = "${adminPath}/ctog")
public class CtogController extends BaseController{
	
	/**
	 * hlr信息管理列表页面
	 */
	@RequiresPermissions("ctog:list")
	@RequestMapping("list")
	public String list() {
		String page = "modules/ctog/ctogInfo";
		return page;
	}
	
	/**
	 * 双网模关信息查询
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "select")
	public Map<String,Object> select() throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//封装数据
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("charge", 8);
		dataMap.put("esn", "80BD13F4");
		dataMap.put("gdualImsi", "789");
		dataMap.put("gimsi", "204046056626471");
		dataMap.put("imsi", "460036610356273");
		dataMap.put("isdn", "8618928849206");
		dataMap.put("msActStatus", "1");
		dataMap.put("mscNumber", "123");
		dataMap.put("nam", "0");
		dataMap.put("ocsitpl", "");
		dataMap.put("outerRoamAuth", "3");
		dataMap.put("sgsnAddress", "");
		dataMap.put("sgsnAddressLen", "");
		dataMap.put("sgsnAddressType", "");
		dataMap.put("sgsnNumber", "");
		dataMap.put("smsdpf", "0");
		dataMap.put("vlrNumber", "111");
		list.add(dataMap);
		Map<String,Object> retMap = new HashMap<String, Object>();
		//放入返回值中
		retMap.put("result", list);
		retMap.put("success", true);
		return retMap;
	}
	
	/**
	 * 业务更改操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "modify")
	public Map<String,Object> modify(){
		System.out.println("进来了");
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("message", "操作成功");
		retMap.put("success", true);
//		retMap.put("message", "操作失败");
//		retMap.put("success", false);
		return retMap;
	}
	
	/**
	 * HLR比对
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "selectFromHLR")
	public Map<String,Object> selectFromHLR() throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//封装数据
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("esn", "");
		dataMap.put("imsi", "460036610356273");
		dataMap.put("isdn", "8618928849206");
		dataMap.put("outerRoamAuth", "0");
		list.add(dataMap);
		Map<String,Object> retMap = new HashMap<String, Object>();
		//放入返回值中
		retMap.put("result", list);
		retMap.put("message", "");
		retMap.put("success", true);
		return retMap;
	}
}
