package com.jeeplus.common.memory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.FilterConfig;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jeeplus.common.base.BaseService;
import com.jeeplus.common.utils.EncryptionFactory;


/**
 * <p>
 * <strong>MemoryDataManager</strong> - 编码数据内存管理
 * </p>
 * 
 * @author liaojg
 * @version 1.0
 * 
 * @date 2012-01-07 11:24:00
 * @description
 */
public class MemoryDataManager {
	
	private static FilterConfig filterConfig = null;
	
	private static BaseService baseService = null;
	
	public static BaseService getBaseService() {
		return baseService;
	}

	public static void setBaseService(BaseService baseService) {
		MemoryDataManager.baseService = baseService;
	}

	private static Map<String, List<?>> basisCodingMap = new Hashtable<String, List<?>>();
	
	public static String[] ipArray = { "132.96.189.85", "132.96.189.86" };
	
	public static String[] anotherSystemIPArray = { "132.96.189.86", "132.96.191.28" };
	
	private static String oldClientKey = null;
	
	private static String clientKey = null;
	
	private static boolean isKeyChange; // 是否密钥改变
	
	private static boolean isDecrypt; // 是否能解密
	
	private static boolean isNeedModify; // 是否需要修改配置文件
	
	private static boolean isInitHLR = false; // 是否初始化hlr_config表
	
	private static boolean isUpdateHLR = false; // 是否要更新hlr_config表
	
	private static String clientKeyEncryptionKey = "4474F617C03E2E3D04CB468B2B36ACD8"; // 客户密钥的加密解密密钥（密钥的转换码）
	
	public static FilterConfig getFilterConfig() {
		return filterConfig;
	}
	
	public static void setFilterConfig(FilterConfig filterConfig) {
		MemoryDataManager.filterConfig = filterConfig;
	}
	
	public static String getOldClientKey() {
		return oldClientKey;
	}
	
	public static void setOldClientKey(String oldClientKey) {
		MemoryDataManager.oldClientKey = oldClientKey;
	}
	
	public static String getClientKey() {
		return clientKey;
	}
	
	public static void setClientKey(String clientKey) {
		MemoryDataManager.oldClientKey = MemoryDataManager.clientKey;
		MemoryDataManager.clientKey = clientKey;
	}
	
	public static boolean isKeyChange() {
		return isKeyChange;
	}
	
	public static void setKeyChange(boolean isKeyChange) {
		MemoryDataManager.isKeyChange = isKeyChange;
	}
	
	public static boolean isDecrypt() {
		return isDecrypt;
	}
	
	public static void setDecrypt(boolean isDecrypt) {
		MemoryDataManager.isDecrypt = isDecrypt;
	}
	
	public static boolean isNeedModify() {
		return isNeedModify;
	}
	
	public static void setNeedModify(boolean isNeedModify) {
		MemoryDataManager.isNeedModify = isNeedModify;
	}
	
	public static boolean isInitHLR() {
		return isInitHLR;
	}
	
	public static void setInitHLR(boolean isInitHLR) {
		MemoryDataManager.isInitHLR = isInitHLR;
	}
	
	public static boolean isUpdateHLR() {
		return isUpdateHLR;
	}
	
	public static void setUpdateHLR(boolean isUpdateHLR) {
		MemoryDataManager.isUpdateHLR = isUpdateHLR;
	}
	
	public static String getClientKeyEncryptionKey() {
		return clientKeyEncryptionKey;
	}
	
	public static void setClientKeyEncryptionKey(String clientKeyEncryptionKey) {
		MemoryDataManager.clientKeyEncryptionKey = clientKeyEncryptionKey;
	}
	
	// *********************************************************************************************
	public MemoryDataManager() {
		
	}
	
	/**
	 * 初始化内存数据
	 * 
	 * @param void
	 * @return void
	 */
	public static void initBaseData() {
		try {
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
			
			baseService = (BaseService) ctx.getBean("baseService"); // baseService
			loadSystemParameter();
			loadBasisCoding();
			loadNumberHead(); // 广东省各地市的号码头数据，用于AN3A号码查询的权限判断
		} catch (Exception ex) {}
	}
	
	/**
	 * 刷新内存数据
	 * 
	 * @param void
	 * @return void
	 */
	public static void reloadBaseData() {
		basisCodingMap = new Hashtable<String, List<?>>();
		initBaseData();
	}
	
	/**
	 * 根据一个类型键，获取从编码表的列表数据
	 * 
	 * @param key 从编码表的类型，即主编码表的coding字段
	 * @return List<?>
	 */
	public static List<?> getList(String key) {
		Object obj = basisCodingMap.get(key);
		
		if (obj == null) {
			return new ArrayList<String>();
		} else {
			return (List<?>) obj;
		}
	}
	
	/**
	 * 在编码明细表里面，通过一个唯一的字段，获取其他指定的字段
	 * 
	 * @param field 明细表里拥有唯一值的字段名
	 * @return Object 取得字段的数据
	 */
	public static Object getValueByField(String key, String uniqueField, String uniqueValue, String needField) {
		List<?> dList = getList(key);
		Object resultValue = null;
		
		if (dList.size() > 0) {
			for (int i = 0; i < dList.size(); i++) {
				Map<?, ?> dMap = (Map<?, ?>) dList.get(i);
				String val = String.valueOf(dMap.get(uniqueField));
				
				if (uniqueValue.equals(val) == true) {
					resultValue = dMap.get(needField);
					break;
				}
			}
			return resultValue;
		}
		return null;
	}
	
	/**
	 * 加载系统参数
	 * 
	 * @param void
	 * @return boolean
	 */
	public static boolean loadSystemParameter() {
		boolean blnFlag = true;
		
		try {
			StringBuffer sbuf = new StringBuffer();
			
			sbuf.append("select new Map(smpr.code as code , smpr.coding as coding , smpr.name as name , ");
			sbuf.append("smpr.value as value , smpr.notes as notes , smpr.status as status) ");
			sbuf.append("from SystemParameter smpr ");
			sbuf.append("where smpr.status = 'N' ");
			sbuf.append("order by smpr.coding asc");
			
			basisCodingMap.put("smprList", baseService.getListByHql(sbuf.toString()));
		} catch (Exception ex) {
			blnFlag = false;
		}
		return blnFlag;
	}
	
	/**
	 * 加载广东省各地市的号码头数据
	 * 
	 * @param void
	 * @return void
	 */
	public static void loadNumberHead() {
		try {
			StringBuffer sbuf = new StringBuffer();
			
			sbuf.append("select new Map(pnh.locname as locname, pnh.numHead as numHead) ");
			sbuf.append("from PriNumberHead pnh ");
			sbuf.append("order by pnh.locname asc");
			
			basisCodingMap.put("numberHeadList", baseService.getListByHql(sbuf.toString()));
		} catch (Exception ex) {}
	}
	
	/**
	 * 加载IP地址黑名单
	 * 
	 * @author kangzm
	 */
	public static void loadIpBlackList() {
		try {
			String hql = "select new Map(ipbl.code as code,ipbl.ipAddress as ipAddress,ipbl.status as status) from IpBlacklist ipbl where status='N'";
			List<?> list = null;
			list = baseService.getListByHql(hql);
			basisCodingMap.put("ipBlackList", list);
			List<String> list2 = new ArrayList<String>();
			list2.add("already");
			basisCodingMap.put("alreadyLoadIpBlackList", list2);
		} catch (Exception e) {}
	}
	
	/**
	 * 加载基本编码数据
	 * 
	 * @param void
	 * @return void
	 */
	public static void loadBasisCoding() {
		try {
			StringBuffer sbuf = new StringBuffer();
			
			sbuf.append("select new Map(bcg.typeCoding as typeCoding) ");
			sbuf.append("from BasisCoding bcg ");
			sbuf.append("where bcg.status = 'N' ");
			sbuf.append("order by bcg.typeCoding");
			
			List<?> bcgList = baseService.getListByHql(sbuf.toString());
			
			for (int i = 0; i < bcgList.size(); i++) {
				Map<?, ?> bcgMap = (Map<?, ?>) bcgList.get(i);
				String typeCoding = String.valueOf(bcgMap.get("typeCoding"));
				
				sbuf = new StringBuffer();
				sbuf.append("select new Map(bcgds.code as code , bcgds.coding as coding , ");
				sbuf.append("bcgds.chineseDescription as chineseDescription , ");
				sbuf.append("bcgds.englishDescription as englishDescription , ");
				sbuf.append("bcgds.isDefault as isDefault , bcgds.characterValue as characterValue , ");
				sbuf.append("bcgds.floatValue as floatValue , bcgds.intValue as intValue) ");
				sbuf.append("from BasisCodingDetails bcgds ");
				sbuf.append("where bcgds.typeFK = '" + typeCoding + "' and bcgds.status = 'N' ");
				sbuf.append("order by bcgds.coding");
				
				basisCodingMap.put(typeCoding.toLowerCase() + "List", baseService.getListByHql(sbuf.toString()));
			}
		} catch (Exception ex) {}
	}
	
	/**
	 * 获取当前运行环境的绝对路径
	 * 
	 * @param void
	 * @return void
	 */
	public static String classAbsoluteRootPath() {
		String path = "";
		Properties props = System.getProperties();
		String operateSystem = String.valueOf(props.get("os.name"));
		
		if (operateSystem.indexOf("Windows") > -1) {
			path = String.valueOf(MemoryDataManager.class.getResource("")).substring(6);
		} else {
			path = "/" + String.valueOf(MemoryDataManager.class.getResource("")).substring(6);
		}
		return path;
	}
	
	/**
	 * 更新HLR帐号密码
	 * 
	 * @param void
	 * @return void
	 */
	public static void updateHLRAccount() {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(MemoryDataManager.getFilterConfig().getServletContext());
		BasicDataSource cnetBaseDataSource = (BasicDataSource) ctx.getBean("cnetDataSource"); // 加载本系统使用的数据库
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		jdbcTemplate.setDataSource(cnetBaseDataSource);
		try {
			StringBuffer sbuf = new StringBuffer();
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			
			sbuf.append("update hlr_config set passwd = ? where ip = ? and port = ?");
			PreparedStatement preStmt1 = conn.prepareStatement("select * from hlr_config order by ip asc , port asc", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			preStmt1.executeQuery(); // 查询远程的数据
			
			ResultSet rsQuery = preStmt1.getResultSet(); // 获得远程的所有记录
			int i = 0;
			
			while (rsQuery.next()) {
				if (String.valueOf(rsQuery.getObject("userid")).trim().equals("") == true) {
					continue;
				}
				String currentPassword = rsQuery.getString("passwd");
				// 解密
				String plaintextPassword = EncryptionFactory.decrypt(currentPassword,
						EncryptionFactory.encrypt(MemoryDataManager.getOldClientKey(), MemoryDataManager.getClientKeyEncryptionKey()));
				// 解不出来就跳到下一条记录
				if (plaintextPassword == null) {
					continue;
				}
				
				PreparedStatement preStmt2 = conn.prepareStatement(sbuf.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				preStmt2.setObject(
						1,
						EncryptionFactory.encrypt(plaintextPassword,
								EncryptionFactory.encrypt(MemoryDataManager.getClientKey(), MemoryDataManager.getClientKeyEncryptionKey())), Types.VARCHAR);
				preStmt2.setObject(2, rsQuery.getString("ip"), Types.VARCHAR);
				preStmt2.setObject(3, rsQuery.getInt("port"), Types.INTEGER);
				preStmt2.execute();
				preStmt2.close();
				++i;
			}
			rsQuery.close();
			preStmt1.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化HLR帐号密码
	 * 
	 * @param void
	 * @return void
	 */
	public static void initHLRAccount() {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(MemoryDataManager.getFilterConfig().getServletContext());
		BasicDataSource cnetBaseDataSource = (BasicDataSource) ctx.getBean("cnetDataSource"); // 加载本系统使用的数据库
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		jdbcTemplate.setDataSource(cnetBaseDataSource);
		try {
			StringBuffer sbuf = new StringBuffer();
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			
			sbuf.append("update hlr_config set passwd = ? where ip = ? and port = ?");
			PreparedStatement preStmt1 = conn.prepareStatement("select * from hlr_config order by ip asc , port asc", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			preStmt1.executeQuery(); // 查询远程的数据
			
			ResultSet rsQuery = preStmt1.getResultSet(); // 获得远程的所有记录
			int i = 0;
			
			while (rsQuery.next()) {
				if (String.valueOf(rsQuery.getObject("userid")).trim().equals("") == true || String.valueOf(rsQuery.getObject("passwd")).trim().length() >= 32) {
					continue;
				}
				
				String currentPassword = rsQuery.getString("passwd");
				PreparedStatement preStmt2 = conn.prepareStatement(sbuf.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				preStmt2.setObject(
						1,
						EncryptionFactory.encrypt(currentPassword,
								EncryptionFactory.encrypt(MemoryDataManager.getClientKey(), MemoryDataManager.getClientKeyEncryptionKey())), Types.VARCHAR);
				preStmt2.setObject(2, rsQuery.getString("ip"), Types.VARCHAR);
				preStmt2.setObject(3, rsQuery.getInt("port"), Types.INTEGER);
				preStmt2.execute();
				preStmt2.close();
				++i;
			}
			rsQuery.close();
			preStmt1.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
