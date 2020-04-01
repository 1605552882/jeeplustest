package com.jeeplus.modules.hsslogselete.web.hss;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeplus.modules.hsslogselete.service.hss.HssLogService;

@Controller
@RequestMapping(value = "${adminPath}/hsslog/log/hsslog")
public class HssLogController {
	@Autowired
	private HssLogService hsslogservice;
	/**
	 * HSS日志查询列表页面
	 */
	@RequiresPermissions("hsslog:log:hsslog:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/hsslog/log/hsslogList";
	}
	@RequiresPermissions("hsslog:log:hsslog:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String userNumber = String.valueOf(request.getParameter("usernumber"));
		String type = String.valueOf(request.getParameter("type"));
		String pageSize = String.valueOf(request.getParameter("pageSize"));
		int index = Integer.parseInt(request.getParameter("pageNo"));
		int limit = Integer.parseInt(request.getParameter("pageSize"));
		String startDate=request.getParameter("startDate");
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("userNumber", userNumber);
		paramsMap.put("type", type);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("index", index);
		paramsMap.put("limit", limit);
		paramsMap.put("startDate", startDate);
		String tt= hsslogservice.queryHssLog(request, paramsMap);
		System.out.println(tt);
		return null;
	}
}
