package com.jeeplus.modules.volte.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author zh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/hss")
public class HSSController {
	
	/**
	 * hss受理日志查询页面
	 */
	@RequestMapping(value = {"hsslog", ""})
	public String hsslogList() {
		String page = "modules/volte/hsslog";
		return page;
	}
	
	/**
	 * hss受理日志查询列表
	 */
	@RequestMapping(value = {"queryHssLog", ""})
	@ResponseBody
	public Map<String,Object> queryHssLog() {
		Map<String,Object> retMap = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for(int i=1;i<=10;i++){
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("SERIAL_NO", i);
			map1.put("OPERATOR_NAME", "aa");
			map1.put("OPERATION_TIME", "2019-12-29 22:10:00");
			map1.put("HLR_INDEX", "123456");
			map1.put("IMSI_NO", "430000000000");
			map1.put("MSISDN_NO", "18078118091");
			map1.put("CMDRESULT", "成功");
			map1.put("MML_COMMAND", "aa");
			map1.put("MSG_TYPE", "aa");
			map1.put("NET_TYPE", "aa");
			map1.put("ERROR", "aa");
			map1.put("ERRORcoding", "2");
			map1.put("MML_COMMANDDETAIL", "bb");
			map1.put("COMMAND_NO", "bb");
			map1.put("ERRORCODE", "bb");
			dataList.add(map1);
		}
		System.out.println("list长度为： " + dataList.size());
		retMap.put("success", true);
		retMap.put("rows", dataList);
		retMap.put("total", 36);
		return retMap;
	}
	
	
}
