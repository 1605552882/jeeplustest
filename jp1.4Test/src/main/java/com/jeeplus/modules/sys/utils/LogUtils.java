/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.utils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.utils.CacheUtils;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.Exceptions;
import com.jeeplus.common.utils.SpringContextHolder;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.sys.entity.Log;
import com.jeeplus.modules.sys.entity.Menu;
import com.jeeplus.modules.sys.entity.Role;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.mapper.LogMapper;
import com.jeeplus.modules.sys.mapper.MenuMapper;

/**
 * 字典工具类
 * 
 * @author jeeplus
 * @version 2017-11-7
 */
public class LogUtils {

	public static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";
	public static final String QUERY = "查询";
	public static final String ADD = "新增";
	public static final String UPDATE = "修改";
	public static final String DELETE = "删除";
	public static final String LOGIN = "登入";
	public static final String SIGNOUT = "退出";

	private static LogMapper logMapper = SpringContextHolder.getBean(LogMapper.class);
	private static MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title, String module_name, String operate_name,
			String operate_type, String log_detail, String success_flag, String remarks, String operate_num,
			Integer connid,String opposite_ip,int backstage_port,String backstage_user) {
		saveLog(request, null, null, title, module_name, operate_name, operate_type, log_detail, success_flag, remarks,
				operate_num, connid,opposite_ip,backstage_port,backstage_user);
	}
	/**
	 * 
	 * @param request
	 * @param handler
	 * @param ex
	 * @param title 日志标题
	 * @param module_name 模块名称
	 * @param operate_name 操作页面名称
	 * @param operate_type 操作功能名称
	 * @param log_detail 操作内容
	 * @param success_flag 操作号码
	 * @param remarks 备注
	 * @param operate_num 操作号码
	 * @param connid 关联ID
	 * @param opposite_ip 要发送的Ip
	 * @param backstage_port 要发送的IP端口
	 * @param backstage_user 要发送的IP用户
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title,
			String module_name, String operate_name, String operate_type, String log_detail, String success_flag,
			String remarks, String operate_num, Integer connid,String opposite_ip,int backstage_port,String backstage_user) {
		User user = UserUtils.getUser();
		List<Role> roleList = UserUtils.getuserRolename(user.getId());

		String group_id = "";
		if (roleList != null) {
			for (int i = 0; i < roleList.size(); i++) {
				group_id += "," + roleList.get(i).getId();
			}
		}
		String regex = "^,*|,*$";
		if (user != null && user.getId() != null) {
			Log log = new Log();
			log.setSysTitle(title);
			if (module_name == null) {
				module_name = "";
			}
			log.setModuleName(module_name);
			if (operate_name == null) {
				operate_name = "";
			}
			log.setOperateName(operate_name);
			
			if (success_flag == null) {
				success_flag = "";
			}
			log.setSuccessFlag(success_flag);
			log.setRemarks(remarks);
			log.setUserId(user.getId());
			log.setUserName(user.getName());
			log.setGroupId(group_id.replaceAll(regex, ""));
			//log.setGroupName(group_name.replaceAll(regex, ""));
			//log.set(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
			try {
				InetAddress address = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.setRemoteAddr(StringUtils.getRemoteAddr(request));
			//log.setUserAgent(request.getHeader("user-agent"));
			log.setRequestUri(request.getRequestURI());
			log.setParams(request.getParameterMap());
			log.setSysMethod(request.getMethod());
			log.setConnId(connid);
			// 异步保存日志
			new SaveLogThread(log, handler, ex).start();
		}
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread {

		private Log log;
		private Object handler;
		private Exception ex;

		public SaveLogThread(Log log, Object handler, Exception ex) {
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}

		@Override
		public void run() {
			// 获取日志标题
			if (StringUtils.isBlank(log.getSysTitle())) {
				String permission = "";
				if (handler instanceof HandlerMethod) {
					Method m = ((HandlerMethod) handler).getMethod();
					RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
					permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
				}
				log.setSysTitle(getMenuNamePath(log.getRequestUri(), permission));
			}
			// 如果有异常，设置异常信息
			log.setException(Exceptions.getStackTraceAsString(ex));
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isBlank(log.getSysTitle())) {
				return;
			}
			String date=DateUtils.getDatelog();
			String taname=date.substring(2, 6);
			log.setTablename("sys_log"+taname);
			log.setCreateDate(new Date());
			// 保存日志信息
			log.preInsert();
			logMapper.insert(log);
		}
	}

	/**
	 * 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
	 */
	public static String getMenuNamePath(String requestUri, String permission) {
		String href = StringUtils.substringAfter(requestUri, Global.getAdminPath());
		@SuppressWarnings("unchecked")
		Map<String, String> menuMap = (Map<String, String>) CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);
		if (menuMap == null) {
			menuMap = Maps.newHashMap();
			List<Menu> menuList = menuMapper.findAllList(new Menu());
			for (Menu menu : menuList) {
				// 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
				String namePath = "";
				if (menu.getParentIds() != null) {
					List<String> namePathList = Lists.newArrayList();
					for (String id : StringUtils.split(menu.getParentIds(), ",")) {
						if (Menu.getRootId().equals(id)) {
							continue; // 过滤跟节点
						}
						for (Menu m : menuList) {
							if (m.getId().equals(id)) {
								namePathList.add(m.getName());
								break;
							}
						}
					}
					namePathList.add(menu.getName());
					namePath = StringUtils.join(namePathList, "-");
				}
				// 设置菜单名称路径
				if (StringUtils.isNotBlank(menu.getHref())) {
					menuMap.put(menu.getHref(), namePath);
				} else if (StringUtils.isNotBlank(menu.getPermission())) {
					for (String p : StringUtils.split(menu.getPermission())) {
						menuMap.put(p, namePath);
					}
				}

			}
			CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, menuMap);
		}
		String menuNamePath = menuMap.get(href);
		if (menuNamePath == null) {
			for (String p : StringUtils.split(permission)) {
				menuNamePath = menuMap.get(p);
				if (StringUtils.isNotBlank(menuNamePath)) {
					break;
				}
			}
			if (menuNamePath == null) {
				return "";
			}
		}
		return menuNamePath;
	}

}
