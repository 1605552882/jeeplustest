package com.jeeplus.modules.volte.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jeeplus.common.socket.SocketTool;
import com.jeeplus.modules.volte.service.EnumService;

@Service
@Transactional(readOnly = true)
public class EnumServiceImpl implements EnumService{

	@Override
	public Map<String, Object> queryEnumLog(HttpServletRequest request, Map<?, ?> paramMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

		String number = (String) paramMap.get("number");
		String startDate = dateSdf.format((Date) paramMap.get("startDate"));
		String endDate = dateSdf.format((Date) paramMap.get("endDate"));
		String startTime = (String) paramMap.get("startTime");
		String endTime = (String) paramMap.get("endTime");
		// ENUM日志信息
		String cmd = "ENUM|05|" + number +"|"+ startDate +" "+ startTime +"|"+ endDate +" "+ endTime;
		String cmdResponse;
		

		try {
			//ENUM|05|number|startDate|endDate|JSON
			//JSON:{"success":true","msg":"..",logList":[{time,operate,user,source,result,cmd},..],"totalCount":..}
			cmdResponse = SocketTool.sendCmd(request.getSession(), cmd);
			String[] responses = cmdResponse.split("\\|", -1);
			JSONObject obj = new JSONObject();
			resultMap =  (Map<String, Object>) obj.parse(responses[5]);
//			resultMap =  (Map<String, Object>) obj.parse("{\"total\":1,\"logList\":[{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!1\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!2\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!3\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!4\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!5\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!6\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!7\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"},{\"result\":\"Operationsucceeded\",\"cmd\":\"MML:Add NAPTRRec:ZONENAME=8.7.8.1.0.3.1.6.8.e164.arpa,NAME=1.8.1.2.8.7.8.1.0.3.1.6.8.e164.arpa,ORDER=10,PREFERENCE=101,FLAGS=U,SERVICE=sip+E2U,REGEXP=!%5E.*%24!sip:+8613018782181@gd.ims.mnc011.mcc460.3gppnetwork.org!\",\"time\":\"2019-11-2912:05:37\",\"source\":\"10.142.252.196\",\"operate\":\"ADDNAPTRREC\",\"user\":\"BOSS\"}],\"success\":true,\"msg\":\"ok\"}");
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", "查询失败");
		}
		return resultMap;
	}

}
