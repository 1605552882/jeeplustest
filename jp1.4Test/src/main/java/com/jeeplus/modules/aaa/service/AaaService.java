/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.aaa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.modules.aaa.entity.AaaInfo;

/**
 * AAAService
 * @author zqb
 * @version 2019-10-25
 */
@Service
@Transactional(readOnly = true)
public class AaaService {
	
	/**
	 * <p>
	 * <strong>aaaInfoQuery</strong> - aaa信息查询
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param request, response
	 * @return ModelAndView throws Exception
	 */
	public List<?> aaaInfoQuery(HttpServletRequest request, Map<?, ?> paramsMap) throws Exception {
		String headCmd = "QUAAA|102|";
		String finalCmd = "";
		String cmdResponse = "";
		String searchType = String.valueOf(paramsMap.get("searchType"));
		String userNoValue = String.valueOf(paramsMap.get("userNoValue")); // 接收来的号码参数：“IMSI号”或者“手机号”
		
		System.out.println(searchType);
		if (searchType.equals("1") == true && userNoValue.startsWith("86") == false 
				&& !userNoValue.startsWith("02") && !userNoValue.startsWith("07") && !userNoValue.startsWith("06") && !userNoValue.startsWith("106")) {
			userNoValue = "86" + userNoValue;
		}
		finalCmd = headCmd + userNoValue + "|" + searchType;
		System.out.println(finalCmd);
//		cmdResponse = SocketTool.sendCmd(request.getSession(), finalCmd);
		cmdResponse = "QUAAA|102|18928849206|1$18928849206|460036261134524|0|2|0|0|||HA-CCG|7$18928849206|||||||||$18928849206||||||||$18928849206||||||||";
		if (cmdResponse.startsWith(headCmd) == true) {
			String[] creArray = cmdResponse.split("\\$", -1);
			List<AaaInfo> aaaInfoList = new ArrayList<AaaInfo>();
			
			// 手机号|C网IMSI号|C网状态|C网漫游权限值|C网用户类型|C网用户绑定选项|C网用户绑定NAI|C网用户绑定VPN|C网用户组$
			// 手机号|G网IMSI号|G网状态|G网漫游权限值|G网用户类型|G网用户绑定选项|G网用户绑定NAI|G网用户绑定VPN|G网用户组
			if (creArray.length >= 2) {
				for (int i = 1; i < creArray.length; i++) {
					String[] aiArray = creArray[i].split("\\|", -1);
					AaaInfo aaaInfo = new AaaInfo();
					
					aaaInfo.setAaaMDN(aiArray[0]);
					aaaInfo.setAaaIMSI(aiArray[1]);
					aaaInfo.setAaaStatus(aiArray[2]);
					aaaInfo.setAaaMYPermissions(aiArray[3]);
					aaaInfo.setAaaType(aiArray[4]);
					aaaInfo.setAaaBindOption(aiArray[5]);
					aaaInfo.setAaaBindNAI(aiArray[6]);
					aaaInfo.setAaaBindVPN(aiArray[7]);
					aaaInfo.setAaaOwnerGroup(aiArray[8]);
					if(i == 1 && aiArray.length >9){
						aaaInfo.setAaaPermitaccType(aiArray[9]);
					}
					if(i == 2 && aiArray.length >9){
						aaaInfo.setAaaGPermitaccType(aiArray[9]);
					}
					aaaInfoList.add(aaaInfo);
				}
				return aaaInfoList;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
}