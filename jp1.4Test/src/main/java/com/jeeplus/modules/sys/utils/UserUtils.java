/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.google.common.collect.Lists;
import com.jeeplus.common.sms.SMSUtils;
import com.jeeplus.common.utils.CacheUtils;
import com.jeeplus.common.utils.SpringContextHolder;
import com.jeeplus.core.service.BaseService;
import com.jeeplus.modules.pri_number_head.entity.pri_number_head.PriNumberHead;
import com.jeeplus.modules.pri_number_head.mapper.pri_number_head.PriNumberHeadMapper;
import com.jeeplus.modules.sys.entity.Area;
import com.jeeplus.modules.sys.entity.DataRule;
import com.jeeplus.modules.sys.entity.Menu;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.Role;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.mapper.AreaMapper;
import com.jeeplus.modules.sys.mapper.MenuMapper;
import com.jeeplus.modules.sys.mapper.OfficeMapper;
import com.jeeplus.modules.sys.mapper.RoleMapper;
import com.jeeplus.modules.sys.mapper.UserMapper;
import com.jeeplus.modules.sys.mapper.DataRuleMapper;
import com.jeeplus.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.jeeplus.modules.sys.service.SystemService;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 用户工具类
 * 
 * @author jeeplus
 * @version 2016-12-05
 */
public class UserUtils {

	private static UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
	private static RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
	private static MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
	private static AreaMapper areaMapper = SpringContextHolder.getBean(AreaMapper.class);
	private static OfficeMapper officeMapper = SpringContextHolder.getBean(OfficeMapper.class);
	private static DataRuleMapper dataRuleMapper = SpringContextHolder.getBean(DataRuleMapper.class);
	private static PriNumberHeadMapper priNumberHeadMapper = SpringContextHolder.getBean(PriNumberHeadMapper.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_DATA_RULE_LIST = "dataRuleList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	public static List<PriNumberHead> prinumberlist = new ArrayList<>();

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userMapper.get(id);
			if (user == null) {
				return null;
			}
			user.setRoleList(roleMapper.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 加载号码头,匹配号码是否存在个人地区权限
	 */
	public static void getByNumberUser() {
		System.out.println("————————————————————加载号码头配置——————————————————");
		if (prinumberlist == null || prinumberlist.size() <= 0) {
			System.out.println("——————————————————————————————加载号码头配置成功————————————————————————————————");
			prinumberlist = priNumberHeadMapper.selectNumberList();
		}
	}

	/**
	 * 根据编号获得用户的用户组
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static List<Role> getuserRolename(String id) {
		List<Role> roleList = new ArrayList<>();
		// User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		// if (user == null) {
		roleList = userMapper.getuserRolename(id);
		// }
		return roleList;
	}

	/**
	 * 获得用户和用户组的区域权限
	 */
	public static String getUserCity() {

		// 获得当前用户
		User user = UserUtils.getUser();
		String city = "";
//		// 取出用户的区域权限
//		String[] citylist = user.getLocRight().split("\\|");
		if (user.getLocRight() != null && !user.getLocRight().equals("null")) {
			city = user.getLocRight();
		}

		// 取出用户的用户组区域权限
		List<Role> listrole = UserUtils.getuserRolename(user.getId());
		for (int j = 0; j < listrole.size(); j++) {
			city += listrole.get(j).getNetwork();
		}
		return city;

	}

	/**
	 * 判断号码是否数据个人的区域权限或者个人对应的用户组权限
	 */
	public static boolean getIsNumberUser(String number) {
		if (number != null) {
			if (UserUtils.getUserCity() != null) {
				String userGrpCity[] = UserUtils.getUserCity().split("\\|");
				for (int i = 0; i < userGrpCity.length; i++) {
					for (int j = 0; j < UserUtils.prinumberlist.size(); j++) {
						if (userGrpCity[i].contains(UserUtils.prinumberlist.get(j).getLocname())) {
							String numberlist[] = UserUtils.prinumberlist.get(j).getNumHead().split(",", -1);
							for (int k = 0; k < numberlist.length; k++) {
								if (number.contains(numberlist[k])) {
									System.out.println(
											number + "该号码可以查询，地市属于：" + UserUtils.prinumberlist.get(j).getLocname());
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null) {
			user = userMapper.getByLoginName(new User(null, loginName));
			if (user == null) {
				return null;
			}
			user.setRoleList(roleMapper.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_DATA_RULE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		UserUtils.clearCache(getUser());
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(User user) {
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		if (user.getOffice() != null && user.getOffice().getId() != null) {
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}

	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static User getUser() {
		Principal principal = getPrincipal();
		if (principal != null) {
			User user = get(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * 
	 * @return
	 */
	public static List<Role> getRoleList() {
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);
		if (roleList == null) {
			User user = getUser();
			if (user.getIsAdmin().contains("1")) {
				roleList = roleMapper.findAllList(new Role());
			} else {
				Role role = new Role();
				BaseService.dataRuleFilter(role);
				roleList = roleMapper.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}

	/**
	 * 获取当前用户和用户组（相关权限）的授权菜单
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList() {
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);

		if (menuList == null) {
			User user = getUser();
			if (user.getIsAdmin().equals("1")) {
				menuList = menuMapper.findAllList(new Menu());
			} else {
				Menu m = new Menu();
				m.setUserId(user.getId());
				// 获得用户组相关权限的菜单列表
				menuList = menuMapper.findByUserId(m);

				// 获得个人相关权限的菜单列表
				if (StringUtils.isNotBlank(user.getMenuId())) {
					String userstr=user.getMenuId();
					if(user.getMenu_ids()!=null) {
						userstr+=user.getMenu_ids();
					}
					String usermenu[] = userstr.split(",", -1);
					for (int i = 0; i < usermenu.length; i++) {
						Menu menu = new Menu();
						menu = menuMapper.getUsermenu(usermenu[i]);
						if(menu!=null) {
							menuList.add(menu);
						}
					}
				}

			}
			// 个人权限，跟用户组权限，相同的菜单进行去重
			for (int i = 0; i < menuList.size() - 1; i++) {
				for (int j = menuList.size() - 1; j > i; j--) {

					if (menuList.get(j).equals(menuList.get(i))) {
						menuList.remove(j);
					}

				}
			}

			menuList = sortListData(menuList);
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 传个人权限跟用户组权限获得的菜单，根据父级菜单编号来排序，降序
	 * 
	 * @param statisticsList
	 * @return
	 */
	private static List<Menu> sortListData(List<Menu> statisticsList) {
		Collections.sort(statisticsList, new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				// 父级菜单编号 ：o2.getParentIds()
				if (o2 != null && o1 != null) {
					if (o1.getSort() < o2.getSort()) {
						return -1;
					}
					// 父级菜单编号：o2.getParentIds()
					if (o1.getSort() == o2.getSort()) {
						return o2.getName().compareTo(o1.getName()); // 适用于英文，英文排序默认根据英文ASCII码进行排序
					}
				}
				return 1;
			}
		});
		return statisticsList;
	}

	/**
	 * 获取当前用户个人授权菜单
	 * 
	 * @return
	 */
	public static List<Menu> getUserMenuList() {
//		@SuppressWarnings("unchecked")
//		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
//		if (menuList == null){
//			User user = getUser();
//			if (user.isAdmin()){
//				menuList = menuMapper.findAllList(new Menu());
//			}else{
//				Menu m = new Menu();
//				m.setUserId(user.getId());
//				menuList = menuMapper.findByUserId(m);
//			}
//			putCache(CACHE_MENU_LIST, menuList);
//		}
		List<Menu> menuList = new ArrayList<>();
		User user = getUser();
		if (user.isAdmin()) {
			return menuList;
		}
		if (StringUtils.isNotBlank(user.getMenuId())) {
			String usermenu[] = user.getMenuId().split(",", -1);
			for (int i = 0; i < usermenu.length; i++) {
				Menu menu = new Menu();
				menu = menuMapper.getUsermenu(usermenu[i]);
				menuList.add(menu);
			}
		}

		return menuList;
	}

	/**
	 * 获取当前用户授权数据权限
	 * 
	 * @return
	 */
	public static List<DataRule> getDataRuleList() {
		@SuppressWarnings("unchecked")
		List<DataRule> dataRuleList = (List<DataRule>) getCache(CACHE_DATA_RULE_LIST);
		if (dataRuleList == null) {
			User user = getUser();
			if (user.isAdmin()) {
				dataRuleList = Lists.newArrayList();
			} else {
				dataRuleList = dataRuleMapper.findByUserId(user);
			}
			putCache(CACHE_DATA_RULE_LIST, dataRuleList);
		}
		return dataRuleList;
	}

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static Menu getTopMenu() {
		List<Menu> list = getMenuList();
		if (getMenuList().size() == 0) {
			return new Menu();
		} else {
			return getMenuList().get(0);
		}
	}

	/**
	 * 获取当前用户授权的区域
	 * 
	 * @return
	 */
	public static List<Area> getAreaList() {
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>) getCache(CACHE_AREA_LIST);
		if (areaList == null) {
			areaList = areaMapper.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * 
	 * @return
	 */
	public static List<Office> getOfficeList() {
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_LIST);
		if (officeList == null) {
			User user = getUser();
			if (user.isAdmin()) {
				officeList = officeMapper.findAllList(new Office());
			} else {
				Office office = new Office();
				BaseService.dataRuleFilter(office);
				officeList = officeMapper.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * 
	 * @return
	 */
	public static List<Office> getOfficeAllList() {
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null) {
			officeList = officeMapper.findAllList(new Office());
		}
		return officeList;
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
//			subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
//			subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	// ============== User Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

	public static String getTime(Date date) {
		StringBuffer time = new StringBuffer();
		Date date2 = new Date();
		long temp = date2.getTime() - date.getTime();
		long days = temp / 1000 / 3600 / 24; // 相差小时数
		if (days > 0) {
			time.append(days + "天");
		}
		long temp1 = temp % (1000 * 3600 * 24);
		long hours = temp1 / 1000 / 3600; // 相差小时数
		if (days > 0 || hours > 0) {
			time.append(hours + "小时");
		}
		long temp2 = temp1 % (1000 * 3600);
		long mins = temp2 / 1000 / 60; // 相差分钟数
		time.append(mins + "分钟");
		return time.toString();
	}

	// 发送注册码
	public static String sendRandomCode(String uid, String pwd, String tel, String randomCode) throws IOException {
		// 发送内容
		String content = "您的验证码是：" + randomCode + "，有效期30分钟，请在有效期内使用。";

		return SMSUtils.send(uid, pwd, tel, content);

	}

	// 注册用户重置密码
	public static String sendPass(String uid, String pwd, String tel, String password) throws IOException {
		// 发送内容
		String content = "您的新密码是：" + password + "，请登录系统，重新设置密码。";
		return SMSUtils.send(uid, pwd, tel, content);

	}

	/**
	 * 导出Excel调用,根据姓名转换为ID
	 */
	public static User getByUserName(String name) {
		User u = new User();
		u.setName(name);
		List<User> list = userMapper.findList(u);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new User();
		}
	}

	/**
	 * 导出Excel使用，根据名字转换为id
	 */
	public static Office getByOfficeName(String name) {
		Office o = new Office();
		o.setName(name);
		List<Office> list = officeMapper.findList(o);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Office();
		}
	}

	/**
	 * 导出Excel使用，根据名字转换为id
	 */
	public static Area getByAreaName(String name) {
		Area a = new Area();
		a.setName(name);
		List<Area> list = areaMapper.findList(a);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Area();
		}
	}

	public static boolean hasPermission(String permission) {
		return SecurityUtils.getSubject().isPermitted(permission);
	}

}
