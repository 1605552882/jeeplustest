/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jeeplus.common.memory.MemoryDataManager;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.core.service.BaseService;
import com.jeeplus.core.web.Servlets;
import com.jeeplus.modules.pri_number_head.entity.pri_number_head.PriNumberHead;
import com.jeeplus.modules.sys.entity.Role;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.listener.PBSTrackManagerPostProcessor;
import com.jeeplus.modules.sys.utils.LOGunit;
import com.jeeplus.modules.sys.utils.LogUtils;
import com.jeeplus.modules.sys.utils.MysqlDateBasicUnit;
import com.jeeplus.modules.sys.utils.RequestHandleUtil;
import com.jeeplus.modules.sys.utils.RequestIp;
import com.jeeplus.modules.sys.utils.UserUtils;

/**
 * 日志拦截器
 * 
 * @author jeeplus
 * @version 2017-8-19
 */
public class LogInterceptor extends BaseService implements HandlerInterceptor {

	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
	// 定义一个线程域，存放登录用户
	public static final ThreadLocal<String> tl = new ThreadLocal<>();

	/**
	 * 发送请求的拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String reqinfo = request.getParameter("reqinfo");
		// 获得号码进行判断
		String number = request.getParameter("number");
		Map mapreq = new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					// System.out.println("参数：" + paramName + "=" + paramValue);
					mapreq.put(paramName, paramValue);
				}
			}
		}
		// 判断是否需要号码头判断
		if (reqinfo != null && number != null) {
			if (reqinfo.trim().contains("yes")) {
				// 调用方法对号码进行区域判断
				if (!(UserUtils.getIsNumberUser(number))) {
					Map<String, Object> ControJsonMap = new HashMap<String, Object>();
					ControJsonMap.put("codes", "10005");
					ControJsonMap.put("msg", "您没有该号码相关的区域权限");
					ControJsonMap.put("total", "");
					response.setCharacterEncoding("utf-8");
					PrintWriter out = response.getWriter();
					JSONObject json = new JSONObject();
					for (Entry<String, Object> map : ControJsonMap.entrySet()) {
						json.put(map.getKey(), map.getValue());
					}

					char[] buf = json.toString().toCharArray();
					if (buf.length > 0) {
						out.write(buf);// 发送
						out.flush();
						out.close();
						System.out.println("*******************数据发送到前台*******************");
						ControJsonMap.clear();
					}

					return false;
				}
			}
		}
		if (logger.isDebugEnabled()) {
			// 设置线程的参数
			User user = UserUtils.getUser();
			Integer connid = LOGunit.getConn_id();
			tl.set(connid.toString());
			InetAddress address = InetAddress.getLocalHost();

//			// 保存socket后台关联日志
//			LOGunit.web_log(user.getLoginName(), "LogInterceptor", "preHandle", mapreq.toString(),
//					address.getHostAddress(), "请求开始", number, new Date(), connid);
			long beginTime = System.currentTimeMillis();// 1、开始时间
			startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
			logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime),
					request.getRequestURI());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			// System.out.println("ViewName: " + modelAndView.getViewName());
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	/**
	 * 拦截请求结束后的操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String tt = tl.get();
		// 保存日志
		LogUtils.saveLog(Servlets.getRequest(), handler, ex, "", "", "", "", "", "0", "", "", Integer.valueOf(tt),"",0,"");
		// 打印JVM信息。
		if (logger.isDebugEnabled()) {
			long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
			HandlerMethod method = (HandlerMethod) handler;
			Object controller = method.getBean();
			long endTime = System.currentTimeMillis(); // 2、结束时间
			// 设置线程的参数
			User user = UserUtils.getUser();

			if (tt != null) {
				Integer connid = Integer.valueOf(tt);
				String controllerinfo = controller.getClass().getSimpleName().toString();
				int i = controllerinfo.indexOf("$$");// 首先获取字符的位置
				InetAddress address = InetAddress.getLocalHost();
				// System.out.println("请求参数：" + mapreq.toString());
				LOGunit.web_log(user.getLoginName(), controllerinfo.substring(0, i), method.getMethod().getName(), "",
						address.getHostAddress(), "请求成功", "", new Date(), connid);
				LogInterceptor.tl.remove();
			}

			logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
					new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime),
					request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024,
					Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
					(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
							+ Runtime.getRuntime().freeMemory()) / 1024 / 1024);
			// System.out.println("方法结束");
		}

	}

	public static String getConnId() {
		return tl.get();
	}
}
