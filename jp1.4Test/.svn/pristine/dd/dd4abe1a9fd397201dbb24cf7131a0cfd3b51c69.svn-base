package com.jeeplus.common.comfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 系统配置工具， 类加载时即读入配置
 * 
 * @author liws
 * 
 */
public class Config {
	
	private static Properties properties = null;
	
	static {
		properties = new Properties();
		InputStream in = Config.class.getResourceAsStream("/properties/telcomConfig.properties");
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static int getInt(String key) {
		return Integer.parseInt(getProperty(key));
	}
	
	public static double getDouble(String key) {
		return Double.parseDouble(getProperty(key));
	}
	
}
