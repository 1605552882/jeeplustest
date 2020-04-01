 package com.jeeplus.modules.hsslogselete.service.hss;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.socket.SocketTool;
import com.jeeplus.common.utils.ToolFunctions;



@Service
@Transactional(readOnly = true)
public class HssLogService {
	/**
	 * HSS日志查询
	 */
	public String queryHssLog(HttpServletRequest request, Map<?, ?> paramsMap) throws Exception {
		String userNumber = "13302339001";
		String type = "1";
		String pageSize = String.valueOf(paramsMap.get("pageSize"));
		int index = Integer.parseInt(request.getParameter("pageNo"));
		int limit = Integer.parseInt(request.getParameter("pageSize"));
		String startDate = "";//paramsMap.get("startDate").toString().replaceAll("\\-", "") + "01000000";
		String endDate = "";//paramsMap.get("startDate").toString().replaceAll("\\-", "") + "31235959";

		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpledates = new SimpleDateFormat("yyyyMMddHHmmss");
		// String pageSize = "1000";
		String cmd = "QGLOG|01|";
		System.out.println("类型" + type);
		if (type.equals("1")) {
			cmd += "|" + userNumber + "|" + startDate + "|" + endDate + "|" + pageSize;
		} else {
			cmd += userNumber + "||" + startDate + "|" + endDate + "|" + pageSize;
		}
		System.out.println("ttttt" + cmd);
		String cmdResponse = SocketTool.sendCmd(request.getSession(), cmd);
		String[] responses = cmdResponse.split("\\$", -1);
		StringBuffer resultJson = new StringBuffer();
		if (cmdResponse.startsWith(cmd) && cmdResponse.indexOf("$") > -1 && responses[1].split("\\|", -1).length > 3) {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (int i = 1 + index; i < limit + index + 1 && i < responses.length; i++) {
				String[] data = responses[i].split("\\|");
				Map<String, String> map = new HashMap<String, String>();
				String order = getOrder(data[4]);
				map.put("SERIAL_NO", data[0]);
				map.put("HLR_INDEX", data[1]);
				map.put("OPERATOR_NAME", data[2]);
				map.put("OPERATION_TIME", simpledate.format(simpledates.parse(data[3])));
				map.put("MML_COMMAND", order);
				map.put("MML_COMMANDDETAIL", data[4]);
				map.put("CMDRESULT", data[5]);
				map.put("BATCH_TASK_ID", data[6]);
				map.put("COMMAND_NO", order);
				map.put("MSG_TYPE", data[8]);
				map.put("NET_TYPE", order);
				map.put("IMSI_NO", data[9]);
				map.put("MSISDN_NO", data[10]);
				map.put("ERROR", "1".equals(data[5]) ? "1" : data[11]);
				map.put("ERRORcoding", "1".equals(data[5]) ? "1" : data[11]);
				// String errorString
				// =data[11].substring(data[11].indexOf("ERR"),data[11].indexOf(":"));
				String errorString = "";
				if (data[11].indexOf("ERR") >= 0) {
					errorString = data[11].substring(data[11].indexOf("ERR") + 3, data[11].indexOf(":"));
					System.out.println("ttttt" + errorString);
					map.put("ERRORCODE", errorString);
				} else {
					map.put("ERRORCODE", data[11]);
				}

				list.add(map);
			}

			Map<String, Object> result = new HashMap<String, Object>();
			/*
			 * result.put("success", true); result.put("total", responses.length-1);
			 * result.put("list", list);
			 */
			resultJson.append("{success:true,total : " + (responses.length - 1) + " , list : "
					+ ToolFunctions.getStringFromList(list) + " }");
			return resultJson.toString();
		} else {
			return null;
		}
	}
	private String getOrder(String string) {
		String reString = "";
		Pattern p = Pattern.compile("(?i)([^:])*(?=:)");
		if(string.contains("*/")&&string.contains("/*")){
			p = Pattern.compile("(?i)(?<=\\*\\/)([^:])*(?=:)");
		}
		Matcher m = p.matcher(string);
		while (m.find()) {
			reString = m.group();
			break;
		}
		return reString;
	}
}
