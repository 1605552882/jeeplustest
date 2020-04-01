package com.jeeplus.modules.sys.utils;

import cn.com.venustech.caserver.api.Prop;
import cn.com.venustech.caserver.common.CaConfig;
import cn.com.venustech.caserver.model.APIAuth;

/**
 * 2015数据认证中心
 * @author surprise
 *
 */
public class VenustechUtils {
	private static APIAuth apiAuth = null;
	
	static {
		try {
			CaConfig.initConfig();
			apiAuth = new APIAuth();
			apiAuth.setAppCode(Prop.BUSINESS_CODE);
			apiAuth.setUserName(Prop.USERNAME);
			apiAuth.setPassword(Prop.PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static APIAuth getAPIAuth() {
		return apiAuth;
	}
}
