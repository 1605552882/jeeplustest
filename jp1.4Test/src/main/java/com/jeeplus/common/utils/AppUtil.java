package com.jeeplus.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.jeeplus.common.domain.groupBarrier;
import com.jeeplus.common.memory.MemoryDataManager;



public class AppUtil {
	
	public AppUtil() {
		
	}
	 
	public static String passwordAddSalt(String password, String salt) {
		if (isEmpty(password) == false && isEmpty(salt) == false) {
			password += "{" + salt + "}";
		}
		return password;
	}
	
	public static String passwordReplace(String password) {
		if (isEmpty(password) == false) {
			password = password.replaceAll(" ", "+");
		}
		return password;
	}
	
	public static String classAbsoluteRootPath(String packagePath) {
		String classPath = "";
		String path = "";
		Properties props = System.getProperties();
		String operateSystem = String.valueOf(props.get("os.name"));
		
		classPath = String.valueOf(AppUtil.class.getResource(packagePath));
		if (operateSystem.indexOf("Windows") > -1) {
			
			path = classPath.substring(6);
		} else {
			path = "/" + classPath.substring(6);
		}
		return path;
	}
	
	public static String getCitySymbol(String cityName) {
		if (cityName.equals("广州") == true) { return "GZ"; }
		if (cityName.equals("东莞") == true) { return "DG"; }
		if (cityName.equals("河源") == true) { return "HY"; }
		if (cityName.equals("江门") == true) { return "JM"; }
		if (cityName.equals("茂名") == true) { return "MM"; }
		if (cityName.equals("清远") == true) { return "QY"; }
		if (cityName.equals("汕头") == true) { return "ST"; }
		if (cityName.equals("深圳") == true) { return "SZ"; }
		if (cityName.equals("阳江") == true) { return "YJ"; }
		if (cityName.equals("湛江") == true) { return "ZJ"; }
		if (cityName.equals("中山") == true) { return "ZS"; }
		if (cityName.equals("潮州") == true) { return "CZ"; }
		if (cityName.equals("佛山") == true) { return "FS"; }
		if (cityName.equals("惠州") == true) { return "HZ"; }
		if (cityName.equals("揭阳") == true) { return "JY"; }
		if (cityName.equals("梅州") == true) { return "MZ"; }
		if (cityName.equals("汕尾") == true) { return "SW"; }
		if (cityName.equals("云浮") == true) { return "YF"; }
		if (cityName.equals("珠海") == true) { return "ZH"; }
		if (cityName.equals("肇庆") == true) { return "ZQ"; }
		if (cityName.equals("韶关") == true) { return "SG"; }
		return com.jeeplus.common.utils.skywin.util.DateUtils.getPNNDayDateAsString(0);
	}
	
	public static String rightPad(String source, int max, String ch) {
		int s = max - source.length();
		
		if (s > 0) {
			for (int i = 1; i <= s; i++) {
				source += ch;
			}
		}
		return source;
	}
	
	public static String getCellValue(HSSFCell cell) {
		if (cell == null) { return ""; }
		DecimalFormat dFormat=new DecimalFormat("0");
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				return dFormat.format(cell.getNumericCellValue());
			case HSSFCell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
		}
		return "";
	}
	
	public static String getAAAErrorCodeInfo(String cmdResponse) {
		String[] crArray = cmdResponse.split("\\|", -1);
		String result = AppUtil.initColumnValue(crArray[1], -9999, -9999, 1, MemoryDataManager.getList("aaacwmList"), "coding", "chineseDescription");
		
		if (AppUtil.isEmpty(result) == true) {
			if (crArray.length >= 3) {
				result = crArray[2];
			}
		}
		return result;
	}
	
	public static String formatNoSeparatorDateTime(String datetime) {
		if (isEmpty(datetime) == true) { return ""; }
		
		StringBuffer sbuf = new StringBuffer();
		
		sbuf.append(datetime.substring(0, 4)).append("-");
		sbuf.append(datetime.substring(4, 6)).append("-");
		sbuf.append(datetime.substring(6, 8)).append(" ");
		sbuf.append(datetime.substring(8, 10)).append(":");
		sbuf.append(datetime.substring(10, 12)).append(":");
		sbuf.append(datetime.substring(12, 14));
		return sbuf.toString();
	}
	
	/**
	 * <p>
	 * <strong>initColumnValue</strong> - 从编码表中初始化列值
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param val1, val2, val3, n, sourceList, compareFieldName, returnFieldName
	 * @return returnValue
	 */
	public static String initColumnValue(String val1, double val2, int val3, int n, List<?> sourceList, String compareFieldName, String returnFieldName) {
		boolean isHave = false;
		String returnValue = "";
		
		if (n == 1) { // 字符比较
			for (int i = 0; i < sourceList.size(); i++) {
				Map<?, ?> sourceMap = (Map<?, ?>) sourceList.get(i);
				String characterValue = String.valueOf(sourceMap.get(compareFieldName)).trim();
				
				if (val1.equals(characterValue) == true) {
					isHave = true;
					returnValue = String.valueOf(sourceMap.get(returnFieldName)).trim();
					break;
				}
			}
		} else if (n == 2) { // 浮点值比较
			for (int i = 0; i < sourceList.size(); i++) {
				Map<?, ?> sourceMap = (Map<?, ?>) sourceList.get(i);
				double floatValue = Double.parseDouble(String.valueOf(sourceMap.get("floatValue")).trim());
				
				if (val2 == floatValue) {
					isHave = true;
					returnValue = String.valueOf(sourceMap.get(returnFieldName)).trim();
					break;
				}
			}
		} else if (n == 3) { // 整型值比较
			for (int i = 0; i < sourceList.size(); i++) {
				Map<?, ?> sourceMap = (Map<?, ?>) sourceList.get(i);
				int intValue = Integer.parseInt(String.valueOf(sourceMap.get("intValue")).trim());
				
				if (val3 == intValue) {
					isHave = true;
					returnValue = String.valueOf(sourceMap.get(returnFieldName)).trim();
					break;
				}
			}
		}
		if (isHave) {
			return returnValue;
		} else {
			return "";
		}
	}
	
	public static boolean isEmpty(String s) {
		return (s == null) || (s.length() == 0) || s.equals("null");
	}
	
	public static String listSMSToExtJSTreeGridJSON(List<?> dataList, String parentId) throws Exception {
		String resultJSON = "[";
		
		for (int i = 0; i < dataList.size(); i++) {
			Map<?, ?> dataMap = (Map<?, ?>) dataList.get(i);
			String treeId = String.valueOf(dataMap.get("id"));
			String treeParentId = String.valueOf(dataMap.get("parentId"));
			
			if (treeParentId.equals(parentId) == true) {
				resultJSON += "{";
				resultJSON += "\"id\" : \"" + treeId + "\" , ";
				resultJSON += "\"parentId\" : \"" + String.valueOf(dataMap.get("parentId")) + "\" , ";
				resultJSON += "\"Region\" : \"" + String.valueOf(dataMap.get("Region")) + "\" , ";
				resultJSON += "\"BusinType\" : \"" + String.valueOf(dataMap.get("BusinType")) + "\" , ";
				resultJSON += "\"CallingNum\" : \"" + String.valueOf(dataMap.get("CallingNum")) + "\" , ";
				resultJSON += "\"CalledNum\" : \"" + String.valueOf(dataMap.get("CalledNum")) + "\" , ";
				resultJSON += "\"BeginTime\" : \"" + String.valueOf(dataMap.get("BeginTime")) + "\" , ";
				resultJSON += "\"EndTime\" : \"" + String.valueOf(dataMap.get("EndTime")) + "\" , ";
				resultJSON += "\"Result\" : \"" + String.valueOf(dataMap.get("Result")) + "\" , ";
				resultJSON += "\"Suggest\" : \"" + String.valueOf(dataMap.get("Suggest")) + "\" , ";
				resultJSON += "\"Cause\" : \"" + String.valueOf(dataMap.get("Cause")) + "\" , ";
				resultJSON += "\"AnswerTime\" : \"" + String.valueOf(dataMap.get("AnswerTime")) + "\" , ";
				resultJSON += "\"TraNumber\" : \"" + String.valueOf(dataMap.get("TraNumber")) + "\" , ";
				resultJSON += "\"BusinID\" : \"" + String.valueOf(dataMap.get("BusinID")) + "\" , ";
				//resultJSON += "\"ConDuration\" : \"" + String.valueOf(dataMap.get("ConDuration")) + "\" , ";
				//resultJSON += "\"AnswerResult\" : \"" + String.valueOf(dataMap.get("AnswerResult")) + "\" , ";
				//resultJSON += "iconCls : \"" + String.valueOf("phone_sound") + "\" , ";
				resultJSON += "\"uiProvider\" : \"" + String.valueOf("col") + "\" , ";
				
				boolean blnHaveChilds = false;
				
				for (int j = 0; j < dataList.size(); j++) {
						Map<?, ?> dataMap2 = (Map<?, ?>) dataList.get(j);
						String parentId2 = String.valueOf(dataMap2.get("parentId"));
						if (treeId.equals(parentId2) == true) {
							blnHaveChilds = true;
							break;
						}
				}
				
				if (blnHaveChilds == true) {
					resultJSON += "\"leaf\" : false , ";
					// resultJSON += "\"expanded\" : true , ";
					resultJSON += "\"children\" : " + listSMSToExtJSTreeGridJSON(dataList, treeId);
				} else {
					resultJSON += "\"leaf\" : true";
				}
				resultJSON += "},";
			}
		}
		if (resultJSON.length() > 1) {
			resultJSON = resultJSON.substring(0, resultJSON.length() - 1);
		}
		resultJSON += "]";
		System.out.print(resultJSON.toString());
		return resultJSON;
	}
	
	public static String listToExtJSTreeGridJSON(List<?> dataList, String parentId) throws Exception {
		String resultJSON = "[";
		
		for (int i = 0; i < dataList.size(); i++) {
			Map<?, ?> dataMap = (Map<?, ?>) dataList.get(i);
			String treeId = String.valueOf(dataMap.get("id"));
			String treeParentId = String.valueOf(dataMap.get("parentId"));
			
			if (treeParentId.equals(parentId) == true) {
				resultJSON += "{";
				resultJSON += "\"id\" : \"" + treeId + "\" , ";
				resultJSON += "\"parentId\" : \"" + String.valueOf(dataMap.get("parentId")) + "\" , ";
				resultJSON += "\"name\" : \"" + String.valueOf(dataMap.get("name")) + "\" , ";
				resultJSON += "\"iconCls\" : \"" + String.valueOf(dataMap.get("iconCls")) + "\" , ";
				resultJSON += "\"hrefs\" : \"" + String.valueOf(dataMap.get("href")) + "\" , ";
				resultJSON += "\"isSensitive\" : \"" + String.valueOf(dataMap.get("isSensitive")) + "\" , ";
				resultJSON += "\"uiProvider\" : \"" + String.valueOf("col") + "\" , ";
				resultJSON += "\"depth\" : \"" + String.valueOf(dataMap.get("depth")) + "\" , ";
				
				boolean blnHaveChilds = false;
				
				for (int j = 0; j < dataList.size(); j++) {
					Map<?, ?> dataMap2 = (Map<?, ?>) dataList.get(j);
					String parentId2 = String.valueOf(dataMap2.get("parentId"));
					if (treeId.equals(parentId2) == true) {
						blnHaveChilds = true;
						break;
					}
				}
				if (blnHaveChilds == true) {
					resultJSON += "\"leaf\" : false , ";
					// resultJSON += "\"expanded\" : true , ";
					resultJSON += "\"children\" : " + listToExtJSTreeGridJSON(dataList, treeId);
				} else {
					resultJSON += "\"leaf\" : true";
				}
				resultJSON += "},";
			}
		}
		if (resultJSON.length() > 1) {
			resultJSON = resultJSON.substring(0, resultJSON.length() - 1);
		}
		resultJSON += "]";
		// System.out.print(resultJSON.toString());
		return resultJSON;
	}
	
	/**
	 * @author kangzm
	 * @param dataList
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public static String listToExtJSTreeGridJSON2(List<?> dataList, String parentId) throws Exception {
		String resultJSON = "[";
		
		for (int i = 0; i < dataList.size(); i++) {
			Map<?, ?> dataMap = (Map<?, ?>) dataList.get(i);
			String treeId = String.valueOf(dataMap.get("id"));
			String treeParentId = String.valueOf(dataMap.get("parentId"));
			
			if (treeParentId.equals(parentId) == true) {
				resultJSON += "{";
				resultJSON += "id : \"" + treeId + "\" , ";
				resultJSON += "parentId : \"" + String.valueOf(dataMap.get("parentId")) + "\" , ";
				resultJSON += "name : \"" + String.valueOf(dataMap.get("name")) + "\" , ";
				resultJSON += "iconCls : \"" + String.valueOf(dataMap.get("iconCls")) + "\" , ";
				resultJSON += "hrefs : \"" + String.valueOf(dataMap.get("href")) + "\" , ";
				resultJSON += "isSensitive : \"" + String.valueOf(dataMap.get("isSensitive")) + "\" , ";
				resultJSON += "uiProvider : \"" + String.valueOf("col") + "\" , ";
				resultJSON += "depth : \"" + String.valueOf(dataMap.get("depth")) + "\" , ";
				
				boolean blnHaveChilds = false;
				
				for (int j = 0; j < dataList.size(); j++) {
					Map<?, ?> dataMap2 = (Map<?, ?>) dataList.get(j);
					String parentId2 = String.valueOf(dataMap2.get("parentId"));
					if (treeId.equals(parentId2) == true) {
						blnHaveChilds = true;
						break;
					}
				}
				if (blnHaveChilds == true) {
					resultJSON += "leaf : false , ";
					// resultJSON += "\"expanded\" : true , ";
					resultJSON += "children : " + listToExtJSTreeGridJSON2(dataList, treeId);
				} else {
					resultJSON += "leaf : true";
				}
				resultJSON += "},";
			}
		}
		if (resultJSON.length() > 1) {
			resultJSON = resultJSON.substring(0, resultJSON.length() - 1);
		}
		resultJSON += "]";
		// System.out.print(resultJSON.toString());
		return resultJSON;
	}
	
	/**
	 * <p>
	 * <strong>listToTreeJSON</strong> - 将list转成指定的JSON格式
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param dataList, parentId
	 * @return result
	 */
	public static String listToTreeJSON(List<?> dataList, String parentId) throws Exception {
		String result = "[";
		
		for (int i = 0; i < dataList.size(); i++) {
			Map<?, ?> dataMap = (Map<?, ?>) dataList.get(i);
			String treeId = String.valueOf(dataMap.get("id"));
			String treeParentId = String.valueOf(dataMap.get("parentId"));
			
			if (treeParentId.equals(parentId) == true) {
				result += "{";
				result += "id : \"" + treeId + "\",";
				result += "text : \"" + String.valueOf(dataMap.get("name")) + "\",";
				result += "iconCls : \"" + String.valueOf(dataMap.get("iconCls")) + "\",";
				result += "href : \"" + String.valueOf(dataMap.get("href")) + "\",";
				result += "isSensitive : \"" + String.valueOf(dataMap.get("isSensitive")) + "\",";
				result += "depth : \"" + String.valueOf(dataMap.get("depth")) + "\",";
				result += "parentId : \"" + String.valueOf(dataMap.get("parentId")) + "\",";
				
				boolean blnHaveChilds = false;
				
				for (int j = 0; j < dataList.size(); j++) {
					Map<?, ?> dataMap2 = (Map<?, ?>) dataList.get(j);
					String parentId2 = String.valueOf(dataMap2.get("parentId"));
					
					if (treeId.equals(parentId2) == true) {
						blnHaveChilds = true;
						break;
					}
				}
				if (blnHaveChilds == true) {
					result += "leaf : false,";
					result += "children : " + listToTreeJSON(dataList, treeId);
				} else {
					result += "leaf : true";
				}
				result += "},";
			}
		}
		if (result.length() > 1) {
			result = result.substring(0, result.length() - 1);
		}
		result += "]";
		return result;
	}
	
	/**
	 * 
	 * @author kangzm
	 * 
	 * @param dataList, parentId
	 * @return result
	 */
	public static String listToTreeJSONForWebsite(List<?> dataList, String parentId) throws Exception {
		String result = "[";
		
		for (int i = 0; i < dataList.size(); i++) {
			Map<?, ?> dataMap = (Map<?, ?>) dataList.get(i);
			String treeId = String.valueOf(dataMap.get("id"));
			String treeParentId = String.valueOf(dataMap.get("parentId"));
			
			if (treeParentId.equals(parentId) == true) {
				result += "{";
				result += "id : \"" + treeId + "\",";
				result += "text : \"" + String.valueOf(dataMap.get("name")) + "\",";
				result += "iconCls : \"" + String.valueOf(dataMap.get("iconCls")) + "\",";
				result += "href : \"" + String.valueOf(dataMap.get("href")) + "\",";
				result += "isSensitive : \"" + String.valueOf(dataMap.get("isSensitive")) + "\",";
				result += "depth : \"" + String.valueOf(dataMap.get("depth")) + "\",";
				result += "parentId : \"" + String.valueOf(dataMap.get("parentId")) + "\",";
				result += "functionPointName : " + String.valueOf(dataMap.get("functionPointName")) + ",";
				
				boolean blnHaveChilds = false;
				
				for (int j = 0; j < dataList.size(); j++) {
					Map<?, ?> dataMap2 = (Map<?, ?>) dataList.get(j);
					String parentId2 = String.valueOf(dataMap2.get("parentId"));
					
					if (treeId.equals(parentId2) == true) {
						blnHaveChilds = true;
						break;
					}
				}
				if (blnHaveChilds == true) {
					result += "leaf : false,";
					result += "children : " + listToTreeJSONForWebsite(dataList, treeId);
				} else {
					result += "leaf : true";
				}
				result += "},";
			}
		}
		if (result.length() > 1) {
			result = result.substring(0, result.length() - 1);
		}
		result += "]";
		return result;
	}
	
	public static String orgListToTreeJSON(List<?> dataList, String parentId) throws Exception {
		String result = "[";
		
		for (int i = 0; i < dataList.size(); i++) {
			Map<?, ?> dataMap = (Map<?, ?>) dataList.get(i);
			String treeId = String.valueOf(dataMap.get("nickId"));
			String treeParentId = String.valueOf(dataMap.get("parentId"));
			
			if (treeParentId.equals(parentId) == true) {
				result += "{";
				result += "id : \"" + treeId + "\",";
				result += "text : \"" + String.valueOf(dataMap.get("name")) + "\",";
				result += "notes : \"" + String.valueOf(dataMap.get("notes")) + "\",";
				result += "status : \"" + String.valueOf(dataMap.get("status")) + "\",";
				
				boolean blnHaveChilds = false;
				
				for (int j = 0; j < dataList.size(); j++) {
					Map<?, ?> dataMap2 = (Map<?, ?>) dataList.get(j);
					String parentId2 = String.valueOf(dataMap2.get("parentId"));
					
					if (treeId.equals(parentId2) == true) {
						blnHaveChilds = true;
						break;
					}
				}
				if (blnHaveChilds == true) {
					// result += "leaf : false,";
					result += "children : " + orgListToTreeJSON(dataList, treeId);
				} else {
					result += "leaf : true";
				}
				result += "},";
			}
		}
		if (result.length() > 1) {
			result = result.substring(0, result.length() - 1);
		}
		result += "]";
		return result;
	}
	
	/**
	 * <p>
	 * <strong>socketSendData</strong> - 将指定的消息通过TCP/IP协议发送给指定的IP
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param serverAddress, port, info
	 * @return void
	 */
	public static void socketSendData(String[] serverAddress, int port, String info) {
		Socket socket = null;
		
		try {
			for (int k = 0; k < serverAddress.length; k++) {
				try {
					SocketAddress socketAddress = new InetSocketAddress(serverAddress[k], port);
					
					socket = new Socket();
					socket.connect(socketAddress, 1000);
					System.out.println("第" + k + "个IP" + serverAddress[k] + "连接成功！");
				} catch (ConnectException ce) {
					System.out.println("第" + k + "个IP" + serverAddress[k] + "拒绝连接！");
				} catch (SocketTimeoutException ste) {
					System.out.println("第" + k + "个IP" + serverAddress[k] + "连接超时！");
				}
				if (socket.isConnected() && !socket.isClosed()) {
					break;
				}
			}
			
			OutputStream out = null;
			
			out = socket.getOutputStream();
			out.write(info.getBytes());
			System.out.println("发出去了：" + info);
			out.close();
			socket.close();
		} catch (IOException se) {
			
		}
	}
	
	/**
	 * <p>
	 * <strong>getKeyBySocket</strong> - 从指定的IP获取数据
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param
	 * @return key
	 */
	public static String getKeyBySocket() throws Exception {
		Socket socket = null;
		String key = "";
		
		try {
			for (int k = 0; k < MemoryDataManager.ipArray.length; k++) {
				try {
					SocketAddress socketAddress = new InetSocketAddress(MemoryDataManager.ipArray[k], 7878);
					
					socket = new Socket();
					socket.connect(socketAddress, 1000);
					System.out.println("第" + k + "个IP" + MemoryDataManager.ipArray[k] + "连接成功！");
				} catch (ConnectException ce) {
					System.out.println("第" + k + "个IP" + MemoryDataManager.ipArray[k] + "拒绝连接！");
				} catch (SocketTimeoutException ste) {
					System.out.println("第" + k + "个IP" + MemoryDataManager.ipArray[k] + "连接超时！");
				}
				if (socket.isConnected() && !socket.isClosed()) { // 其中一个服务器被连上，则跳出循环
					break;
				}
			}
			if (socket.isConnected() && socket.isClosed()) { throw new SocketException(); }
			
			OutputStream out = null;
			InputStream in = null;
			int i = 0;
			
			while (true) {
				if (i == 0) { // 连上密钥服务器后，打声招呼
					in = socket.getInputStream();
					byte[] buf = new byte[1024];
					int len = in.read(buf);
					String greeting = new String(buf, 0, len);
					
					if (greeting.equals("HELLO") == true) {
						out = socket.getOutputStream();
						out.write("HAPPYDAY".getBytes());
					}
				}
				if (i == 1) {
					in = socket.getInputStream();
					byte[] buf = new byte[1024];
					int len = in.read(buf);
					
					key = new String(buf, 0, len);
					break;
				}
				i++;
				Thread.sleep(500);
			}
			socket.close();
		} catch (SocketException s) {
			
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return key;
	}
	
	/**
	 * <p>
	 * <strong>writeFile</strong> - 将指定的内容写在指定路径的文件里，并返回操作成败结果
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author liaojg
	 * 
	 * @param filePath, content
	 * @return boolean
	 */
	public static boolean writeFile(String filePath, String content) {
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(filePath, false);
			fw.write(content);
			fw.close();
			return true;
		} catch (IOException e) {} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
		return false;
	}
	
	public static Map<Object, Object> mapAppendMap(Map<Object, Object> resultMap, Map<?, ?> sourceMap) throws Exception {
		for (Object key : sourceMap.keySet()) {
			resultMap.put(key, sourceMap.get(key));
		}
		return resultMap;
	}
	
	public static String uncToChr(String str) throws Exception {
		String result = "";
		
		if (str == null || str == "") { return ""; }
		if (str.indexOf("|") > -1) {
			String[] ss = str.split("\\|");
			
			for (String s : ss) {
				result = result + String.valueOf((char) Integer.parseInt(s));
			}
		} else {
			result = String.valueOf((char) Integer.parseInt(str));
		}
		return result;
	}
	
	public static String checkUnicode(String value) throws Exception {
		while (value.indexOf("Unicode:") > -1) {
			String temp0 = value.substring(0, value.indexOf("Unicode:"));
			String temp2 = value.substring(value.indexOf("Unicode:") + 8, value.length());
			String temp1 = "";
			
			if (temp2.indexOf("Unicode:") < 0) { // 後面沒有Unicode了
				temp1 = temp2.substring(0, temp2.indexOf("\"") > 0 ? temp2.indexOf("\"") : temp2.length());
				temp2 = temp2.indexOf("\"") > 0 ? temp2.substring(temp2.indexOf("\""), temp2.length()) : "";
				temp1 = uncToChr(temp1);
				value = temp0 + temp1 + temp2;
			} else {
				if (temp2.indexOf("\"") < temp2.indexOf("Unicode:")) {
					temp1 = temp2.substring(0, temp2.indexOf("\"") > 0 ? temp2.indexOf("\"") : temp2.length());
					temp2 = temp2.indexOf("\"") > 0 ? temp2.substring(temp2.indexOf("\""), temp2.length()) : "";
					temp1 = uncToChr(temp1);
					value = temp0 + temp1 + temp2;
				} else {
					temp1 = temp2.substring(0, temp2.indexOf("Unicode:") - 1);
					temp2 = temp2.substring(temp2.indexOf("Unicode:") - 1, temp2.length());
					temp1 = uncToChr(temp1);
					value = temp0 + temp1 + temp2;
				}
			}
		}
		return value;
	}
	
	public static Date strToDate(String sDate) throws Exception {
		Date result = null;
		
		if (sDate.length() < 20) {
			result = com.jeeplus.common.utils.skywin.util.DateUtils.stringToDate(sDate);
			if (result == null) {
				result = com.jeeplus.common.utils.skywin.util.DateUtils.stringToTimestamp(sDate);
			}
		}
		return result;
	}
	
	public static String getRemoteIPAddress(HttpServletRequest request) {
		String ipAddress = "";
		
		ipAddress = request.getHeader("X-Forwarded-For");
		System.out.println("X-Forwarded-For = " + ipAddress);
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress) == true) {
			ipAddress = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP = " + ipAddress);
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress) == true) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP = " + ipAddress);
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress) == true) {
			ipAddress = request.getRemoteAddr();
			System.out.println("remoteAddr = " + ipAddress);
		}
		return ipAddress;
	}
	
	public static Map<?, ?> reqToRandomMap(HttpServletRequest req) throws Exception {
		Map<?, ?> paramsMap = (Map<?, ?>) req.getParameterMap();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		for (Iterator<?> localIterator = paramsMap.keySet().iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
			Object value = ((String[]) paramsMap.get(key))[0];
			
			value = checkUnicode(String.valueOf(value));
			if (strToDate(String.valueOf(value)) != null) {
				value = strToDate(String.valueOf(value));
			} else {
				value = URLDecoder.decode(URLDecoder.decode(String.valueOf(value).toString(), "UTF-8"), "UTF-8");
				
			}
			resultMap.put(key, value);
			// System.out.println(key + " = " + value);
		}
		return (Map<?, ?>) resultMap;
	}
	
	public static Map<Object, Object> reqToObjectMap(HttpServletRequest req) throws Exception {
		Map<?, ?> paramsMap = (Map<?, ?>) req.getParameterMap();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		for (Iterator<?> localIterator = paramsMap.keySet().iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
			Object value = ((String[]) paramsMap.get(key))[0];
			
			value = checkUnicode((String) value);
			if (strToDate((String) value) != null) {
				value = strToDate((String) value);
			} else {
				value = (String) value;
			}
			resultMap.put(key, value);
			System.out.println(key + " = " + value);
		}
		return resultMap;
	}
	
	public static String formatString(String str) throws Exception {
		if (isEmpty(str) == true) { return ""; }
		str = str.trim();
		str = str.replace("'", "");
		str = str.replace("\"", "");
		str = str.replace("-", "");
		str = str.replace(";", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace("&", "");
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		return str;
	}
	
	public static boolean fileToZip(String sourceFilePath, String zipFilePath) throws Exception {
		File sourceFile = new File(sourceFilePath);
		
		if (sourceFile.exists() == false) {
			System.out.println("待压缩的文件：" + sourceFilePath + "不存在！");
			return false;
		} else {
			String sourceFileFullName = sourceFile.getName();
			String sourceFileName = sourceFileFullName.split("\\.")[0];
			String sourceFileExtendName = sourceFileFullName.split("\\.")[1];
			String sourceFileDirectory = sourceFilePath.substring(0, sourceFilePath.indexOf(sourceFileFullName));
			
			System.out.println("sourceFileFullName = " + sourceFileFullName);
			System.out.println("sourceFileName = " + sourceFileName);
			System.out.println("sourceFileExtendName = " + sourceFileExtendName);
			System.out.println("sourceFileDirectory = " + sourceFileDirectory);
			if (StringUtils.isEmpty(zipFilePath) == true) {
				zipFilePath = sourceFileDirectory;
			}
			
			File zipFile = new File(zipFilePath + sourceFileName + ".zip");
			
			if (zipFile.exists()) {
				System.out.println(zipFilePath + sourceFileName + ".zip已经存在！");
				zipFile.delete();
			}
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
			byte[] bufs = new byte[1024 * 10];
			ZipEntry zipEntry = new ZipEntry(sourceFileFullName);
			
			zos.putNextEntry(zipEntry);
			
			FileInputStream fis = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fis, 1024 * 10);
			int read = 0;
			
			while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
				zos.write(bufs, 0, read);
			}
			zos.close();
			fos.close();
			bis.close();
			fis.close();
		}
		return true;
	}

	public static String GetTableName(Object obj){

		String name=obj.getClass().getSimpleName();
		String res="";
	    for(int i=0;i<name.length();i++)
	    {
	    	String word=name.charAt(i)+"";
	    	if(i==0)
	    	{
	    		res+=word.toLowerCase();
	    	}else if(!word.toLowerCase().equals(word))
	    	{
	    		res+="_"+word.toLowerCase();
	    	}else{
	    		res+=word;
	    	}
	    	
	    }
	    return res;
	
	
}
	/**
	 * 
	 * @param tablename 表名字
	 * @param obj 封装的类型
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static String[] GetTableAll(String tablename,Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String []res=new String[2];
		String sql=" from "+tablename+" where 1=1";
		String Asql="select *";
	    String Csql="select count(*) as c";
		Method[] meths=obj.getClass().getDeclaredMethods();
		for(Method meth:meths)
		{
		  
			String Mname=meth.getName();
			String name="";
			if(Mname.startsWith("get")||Mname.startsWith("is"))
			{
				name=Mname.replace("get","");
			     if(name.equals(Mname))
			     {
			    	 name=Mname.replace("is","");
			     }
			     name=name.toLowerCase();
                String end=meth.invoke(obj)+"";
				if(!isEmpty(end.trim()))
				{
					if(meth.getReturnType()==Integer.class)
					  {
						sql+=" and "+name+" ="+end.trim();  
					  }else if(meth.getReturnType()==String.class)
					  {
						sql+=" and "+name+" like '%"+end.trim()+"%'";
					  }
					
				}

			}
			
		}
		Asql+=sql;
		Csql+=sql;
		res[0]=Asql;
		res[1]=Csql;
		return res;
		
		
		
	}
	/**
	 * 
	 * @param obj 封装的类型
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static String[] GetTableAll(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String []res=new String[2];
		String sql=" from "+GetTableName(obj)+" where 1=1";
		String Asql="select *";
	    String Csql="select count(*) as c";
		Method[] meths=obj.getClass().getDeclaredMethods();
		for(Method meth:meths)
		{
		  
			String Mname=meth.getName();
			String name="";
			if(Mname.startsWith("get")||Mname.startsWith("is"))
			{
				name=Mname.replace("get","");
			     if(name.equals(Mname))
			     {
			    	 name=Mname.replace("is","");
			     }
			     name=name.toLowerCase();
                String end=meth.invoke(obj)+"";
				if(!isEmpty(end.trim()))
				{
					if(meth.getReturnType()==Integer.class)
					  {
						sql+=" and "+name+" ="+end.trim();  
					  }else if(meth.getReturnType()==String.class)
					  {
						sql+=" and "+name+" like '%"+end.trim()+"%'";
					  }
					
				}

			}
			
		}
		Asql+=sql;
		Csql+=sql;
		res[0]=Asql;
		res[1]=Csql;
		return res;
		
		
		
		
	}
	

	public static Object GetToSet(HttpServletRequest request,Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ParseException{

		Method[] meths=obj.getClass().getDeclaredMethods();
		for(Method meth:meths)
		{
		  
			String Mname=meth.getName();
			String name="";
			if(Mname.startsWith("set"))
			{
				name=Mname.replace("set","");
			     name=name.toLowerCase();
                Object value=request.getParameter(name);
                if(value!=null&&meth.getParameterTypes()[0]==Integer.class)
                {
                	if((!value.toString().trim().equals("")))
                	{
                		value=Integer.parseInt(value.toString());
                	}else
                	{
                		value=null;
                	}
                	
                }
                if(value!=null&&meth.getParameterTypes()[0]==Date.class)
                {
                	if((!value.toString().trim().equals("")))
                	{
                	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	value=dateFormat.parse(value.toString());
                	}else
                	{
                		value=null;
                	}

                }
                meth.invoke(obj, value);
			}
			
		}
		return  obj;
		
		
		
		
		
		
	}
	public static Map<?, ?> reqToRandomMap2(HttpServletRequest req) throws Exception {
		Map<?, ?> paramsMap = (Map<?, ?>) req.getParameterMap();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		for (Iterator<?> localIterator = paramsMap.keySet().iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
			Object value = ((String[]) paramsMap.get(key))[0];
			
			value = checkUnicode(String.valueOf(value));
			if (strToDate(String.valueOf(value)) != null) {
				value = strToDate(String.valueOf(value));
			} else {
			String val="";	
    if(String.valueOf(value).toString().contains("%"))
    {
    	val = String.valueOf(value).toString().replaceAll("%(?![0-9a-fA-F]{2})", "%25");
    }else{
    	val=String.valueOf(value).toString();
    }
				
				value =URLDecoder.decode(val, "UTF-8");
				value =URLDecoder.decode((String)value, "UTF-8");
				
			}
			resultMap.put(key, value);
			// System.out.println(key + " = " + value);
		}
		return (Map<?, ?>) resultMap;
	}
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		groupBarrier barrier=new groupBarrier();
		barrier.setCreate_date(new Date());
		barrier.setUserid("zc");
		barrier.setId(1);
		Object[]objs=ToObject("select * from strict_tab where create_date>=? and end_date<=? and attr_city=? and malf_city=? and serv=?",barrier);
		for(int i=0;i<objs.length;i++)
		{
			System.out.println(objs[i]);
		}
	}
	
	public static Object[] ToObject(String sql,Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
        if(sql.charAt(sql.length()-1)!=' ')
        {
        	sql+=" ";
        }
		  if(sql.contains("where"))
	      {
	      	sql=sql.substring(sql.indexOf("where")+"where".length());
	      }
		  if(sql.contains("order"))
		  {
			  sql=sql.substring(0, sql.indexOf("order"));
		  }
		  if(sql.contains("group"))
		  {
			  sql=sql.substring(0, sql.indexOf("group"));
		  }
		  
		  sql=sql.replaceAll("[-:%']","").replaceAll("[<>]","=").replaceAll("( ){1,}[a-zA-Z]{1,}(\\.)","").replaceAll("(like)[ ]{0,}[0-9a-zA-Z?]{0,}[ ]{0,}","").replaceAll("(=){1,}( ){0,}[0-9a-zA-Z?]{0,}( ){0,}", "").replaceAll("(and)|(or)",",");
		String []sqls=sql.split(",");
		Method[] meths=obj.getClass().getDeclaredMethods();
		
		ArrayList<Object>lists=new ArrayList<Object>();
		
		for(int i=0;i<sqls.length;i++)
		{
			String sqlname=sqls[i].toLowerCase().trim();
			for(Method meth:meths)
			{
			  
				String Mname=meth.getName().toLowerCase();
				if(Mname.startsWith("get")||Mname.startsWith("is"))
				{
					
				    
				     if(Mname.contains(sqlname))
				     {
				    	 lists.add(meth.invoke(obj));
				         break;
				     }
				     
	               
				}
				
			}
			
		}
		return lists.toArray();
		
		

	
		
		
		
		
		
		
	}
	
}
