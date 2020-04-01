package com.jeeplus.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONArray;

/**
 * 
 * 提供各种特殊操作
 * 
 * @author lehua
 */
public class ToolFunctions {
	
	/**
	 * 记录分隔符
	 */
	static public String RECORDSPLITCHAR = "\\$";
	
	/**
	 * 字段分隔符
	 */
	static public String FIELDSPLITCHAR = "\\|";
	
	/**
	 * 字段分隔符
	 */
	static public String FIELDSPLITCHARSEMICOLON = ";";
	/**
	 * 字段分隔符
	 */
	static public String FIELDSPLITCHAR1 = "|";
	
	/**
	 * 记录分隔符
	 */
	static public String PARAMSPLITCHAR = "$";
	
	/**
	 * 记录分隔符
	 */
	static public String EXCLAMATIONSPLITCHAR = "!";
	
	/**
	 * 资源文件
	 */
	static public String RESOURCEFILENAME = "myConfiguration.properties";
	
	private static String[] areaCodesArr = { "0660", "0662", "0663", "0668", "0750", "0751", "0752", "0755", "0753", "0754", "0756", "0757", "0758", "0759",
			"0760", "0762", "0763", "0766", "0768", "0769", "020" };
	
	/**
	 * 检查areaCode是否是正常的区号
	 * 
	 * @param areaCode 要检查的区号
	 * @return resultFlg true:正常；false:不正常
	 */
	public static boolean checkAreaCode(String areaCode) {
		boolean resultFlg = false;
		
		// 检查areaCode是否是正常的区号
		for (int i = 0; i < areaCodesArr.length; i++) {
			if (areaCode.equals(areaCodesArr[i])) {
				resultFlg = true;
				break;
			}
		}
		return resultFlg;
	}
	
	/**
	 * 根据systemReturn取得message
	 * 
	 * @param systemReturn 接口返回值
	 * 
	 * @param heads 协议头
	 * 
	 * @return
	 */
	public static String getMessage(String systemReturn, String heads) {
		String message = "";
		
		if (systemReturn.equalsIgnoreCase("NOLOGIN")) {
			message = "登录超时，请重新登录！";
		} else if (systemReturn.equalsIgnoreCase("NHN")) {
			message = "该号码头没有配置信息。";
		} else if (systemReturn.trim().equalsIgnoreCase("NRM")) {
			message = "在平台内不存在符合条件的信息。";
		} else if (systemReturn.trim().equalsIgnoreCase("NPR")) {
			message = "您没有相应地市的查询权限。";
		} else if (systemReturn.trim().equalsIgnoreCase("INF")) {
			message = "错误的协议格式。";
		} else if (systemReturn.trim().equalsIgnoreCase("SB")) {
			message = "服务器忙，请稍候再试。";
		} else if (systemReturn.indexOf("ERRTEXT=") >= 0 || systemReturn.indexOf("ERROR=") >= 0 || systemReturn.indexOf("ERRNO=") >= 0) {
			try {
				String tmpSystemReturn = systemReturn.substring(systemReturn.indexOf("\"") + 1);
				String errText = tmpSystemReturn.substring(0, tmpSystemReturn.indexOf("\""));
				message = errText;
			} catch (Exception e) {
				e.printStackTrace();
				message = "在解析返回的异常信息时，程序出现异常，请联系管理员。";
			}
		} else if (!systemReturn.contains(heads) && !systemReturn.equals("T")) {
			message = "信息返回失败。";
		}
		return message;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized static List<Object> decodeFromString(String encodeString, Object ob) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		
		String tmpStr = encodeString.replaceAll(FIELDSPLITCHAR, FIELDSPLITCHAR + " ");
		
		Class classType = ob.getClass();
		ArrayList<Object> resultLst = new ArrayList<Object>();
		
		String[] recordStrings = tmpStr.split(RECORDSPLITCHAR);
		for (int i = 0; i < recordStrings.length; i++) {
			String[] fieldStrings = recordStrings[i].split(FIELDSPLITCHAR);
			Field[] fields = ob.getClass().getDeclaredFields();
			// 通过默认构造方法创建一个新的对象
			
			Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
			for (int j = 0; j < fieldStrings.length; j++) {
				Field field = fields[j];
				String fieldName = fields[j].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String setMethodName = "set" + firstLetter + fieldName.substring(1);
				Method setMethod = ob.getClass().getMethod(setMethodName, new Class[] { field.getType() });
				setMethod.invoke(objectCopy, new Object[] { fieldStrings[j].trim() });
			}
			resultLst.add(objectCopy);
		}
		return resultLst;
	}
	
	/**
	 * 解码函数，支持使用不同的记录分隔符分隔记录
	 * 
	 * @param encodeString 源码
	 * @param ob 记录bean
	 * @param splitChar 记录之间的分隔符
	 * @return 以记录bean为节点结构的list
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public synchronized static List<Object> decodeFromString(String encodeString, Object ob, String splitChar) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		
		String tmpStr = encodeString.replaceAll(FIELDSPLITCHAR, FIELDSPLITCHAR + " ");
		
		Class classType = ob.getClass();
		ArrayList<Object> resultLst = new ArrayList<Object>();
		
		String[] recordStrings = tmpStr.split(splitChar);
		for (int i = 0; i < recordStrings.length; i++) {
			String[] fieldStrings = recordStrings[i].split(FIELDSPLITCHAR);
			Field[] fields = ob.getClass().getDeclaredFields();
			// 通过默认构造方法创建一个新的对象
			
			Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
			for (int j = 0; j < fieldStrings.length; j++) {
				Field field = fields[j];
				String fieldName = fields[j].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String setMethodName = "set" + firstLetter + fieldName.substring(1);
				Method setMethod = ob.getClass().getMethod(setMethodName, new Class[] { field.getType() });
				setMethod.invoke(objectCopy, new Object[] { fieldStrings[j].trim() });
			}
			resultLst.add(objectCopy);
		}
		return resultLst;
	}
	
	/**
	 * 解码
	 * 
	 * @param encodeString 需要解码的字符串
	 * @param ob 数据容器（Bean）
	 * @return LIST 解码后的信息，以Bean为节点结构的链表
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public synchronized static List decodeFromStringWithQuotes(String encodeString, Object ob) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String tmpStr = encodeString.replace(FIELDSPLITCHAR, FIELDSPLITCHAR + " ");
		Class classType = ob.getClass();
		ArrayList<Object> resultLst = new ArrayList<Object>();
		String[] recordStrings = tmpStr.split(RECORDSPLITCHAR);
		for (int i = 0; i < recordStrings.length; i++) {
			String[] fieldStrings = recordStrings[i].split(FIELDSPLITCHAR);
			Field[] fields = ob.getClass().getDeclaredFields();
			Hashtable ht = new Hashtable();
			// 拆分字符串，建立名值映射
			
			for (int k = 0; k < fieldStrings.length; k++) {
				int start = 3;
				int end = 0;
				if (fieldStrings[k].indexOf("=\"") >= 0) {
					start = 2;
					end = 1;
				}
				// 检测出等号在字符串中的位置
				int equalMarkPosition = fieldStrings[k].indexOf("=");
				ht.put(fieldStrings[k].trim().substring(0, equalMarkPosition).toUpperCase(),
						fieldStrings[k].substring(equalMarkPosition + start, fieldStrings[k].length() - end));
			}
			// 通过默认构造方法创建一个新的对象
			
			Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				String fieldName = fields[j].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String setMethodName = "set" + firstLetter + fieldName.substring(1);
				Method setMethod = ob.getClass().getMethod(setMethodName, new Class[] { field.getType() });
				setMethod.invoke(objectCopy, new Object[] { ht.get(fieldName.toUpperCase()) });
			}
			resultLst.add(objectCopy);
		}
		return resultLst;
	}
	
	/**
	 * 解码功能
	 * 
	 * @param encodeString 需要解码的字符串
	 * @param ob 数据容器（Bean）
	 * @param splitChar encodeString中一条信息的分隔符
	 * @return LIST 解码后的信息，以Bean为节点结构的链表
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public synchronized static List decodeFromStringWithQuotes(String encodeString, Object ob, String splitChar) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String tmpStr = encodeString.replace(FIELDSPLITCHAR, FIELDSPLITCHAR + " ");
		Class classType = ob.getClass();
		ArrayList<Object> resultLst = new ArrayList<Object>();
		String[] recordStrings = tmpStr.split(splitChar);
		for (int i = 0; i < recordStrings.length; i++) {
			String[] fieldStrings = recordStrings[i].split(FIELDSPLITCHAR);
			Field[] fields = ob.getClass().getDeclaredFields();
			Hashtable ht = new Hashtable();
			// 拆分字符串，建立名值映射
			
			for (int k = 0; k < fieldStrings.length; k++) {
				int start = 3;
				int end = 0;
				if (fieldStrings[k].indexOf("=\"") >= 0) {
					start = 2;
					end = 1;
				}
				// 检测出等号在字符串中的位置
				int equalMarkPosition = fieldStrings[k].indexOf("=");
				ht.put(fieldStrings[k].substring(0, equalMarkPosition).toUpperCase(),
						fieldStrings[k].substring(equalMarkPosition + start, fieldStrings[k].length() - end));
			}
			// 通过默认构造方法创建一个新的对象
			
			Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				String fieldName = fields[j].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String setMethodName = "set" + firstLetter + fieldName.substring(1);
				Method setMethod = ob.getClass().getMethod(setMethodName, new Class[] { field.getType() });
				setMethod.invoke(objectCopy, new Object[] { ht.get(fieldName.toUpperCase()) });
			}
			resultLst.add(objectCopy);
		}
		return resultLst;
	}
	
	/**
	 * 
	 * @param systemReturn
	 * @return
	 */
	public static boolean getResultFlg(String systemReturn) {
		boolean resultFlg = true;
		String[] arrSystemReturn = systemReturn.split(ToolFunctions.RECORDSPLITCHAR);
		for (int i = 0; i < arrSystemReturn.length; i++) {
			if (arrSystemReturn[i].equalsIgnoreCase("A") || arrSystemReturn[i].equalsIgnoreCase("T")) {} else {
				resultFlg = false;
				break;
			}
		}
		return resultFlg;
	}
	
	/**
	 * 从给定的消息和list中获得相应字符串
	 * 
	 * @param message 消息
	 * @param resultList list
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void getResponseStrFromParams(HttpServletResponse response, String message, List resultList) throws IOException {
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		String tmp = "";
		if (!"".equals(message)) {
			tmp = "[{message:'" + message + "'}]";
		} else {
			tmp = ToolFunctions.getStringFromList(resultList);
		}
		sb.append("{totalProperty:").append(resultList == null ? 0 : resultList.size()).append(",result:").append(tmp).append("}");
		out.println(sb.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 从给定的消息和list中获得相应字符串
	 * 
	 * @param message 消息
	 * @param resultList list
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void getResponseStrFromParams(HttpServletResponse response, String message, List resultList, int totalCount) throws IOException {
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		String tmp = "";
		if (!"".equals(message)) {
			tmp = "[{message:'" + message + "'}]";
		} else {
			tmp = ToolFunctions.getStringFromList(resultList);
		}
		sb.append("{totalProperty:").append(totalCount).append(",result:").append(tmp).append("}");
		out.println(sb.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 从给定的消息和list中获得相应字符串
	 * 
	 * @param operateSuccessFlg 操作成功状态
	 * @param message 消息
	 * @param resultList list
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void getResponseStrFromParams(HttpServletResponse response, String message, List resultList, Boolean operateSuccessFlg) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{success:").append(operateSuccessFlg).append(",message:'").append(message).append("',result:")
				.append(ToolFunctions.getStringFromList(resultList)).append("}");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 从给定的消息和list中获得相应字符串
	 * 
	 * @param operateSuccessFlg 操作成功状态
	 * @param message 消息
	 * @param resultList list
	 * @return
	 * @throws IOException
	 */
	public static void getResponseStrFromParams(HttpServletResponse response, String message, Boolean operateSuccessFlg) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{success:").append(operateSuccessFlg).append(",message:'").append(message).append("'}");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 以JSON数据格式返回操作结果，该结果只有一个信息字段
	 * 
	 * @param response
	 * @param result 操作结果的信息字段
	 * @throws IOException
	 */
	public static void getResponseStrFromParams(HttpServletResponse response, String result) throws IOException {
		PrintWriter out = response.getWriter();
		StringBuffer sbuf = new StringBuffer();
		
		sbuf.append("{success:true,result:'").append(result).append("'}");
		out.println(sbuf.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 组装返回的json字符串
	 * 
	 * @param message 操作结果的中文信息
	 * @param opFlg 操作结果的状态码
	 * @param operateSuccessFlg 操作结果成功标志
	 * @return json字符串
	 * @throws IOException
	 */
	public static void getResponseStrFromParams(HttpServletResponse response, String message, String opFlg, Boolean operateSuccessFlg) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("{success:").append(operateSuccessFlg).append(",message:'").append(message).append("',result:'").append(opFlg).append("'}");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
	}
	
//	/**
//	 * 将list类型的数据以excel的形式导入response
//	 * 
//	 * @author lehua
//	 * @param response 响应
//	 * @param list 要生成excel的数据源
//	 * @param fileName 工作簿名
//	 * @param sheetName 工作表名
//	 * @return disposedResult 返回创建excel是否成功 true:成功；false:失败
//	 */
//	@SuppressWarnings("finally")
//	public static boolean createExcel(HttpServletResponse response, List<Object> list, String fileName, String sheetName) {
//		if (sheetName == null || "".equals(sheetName)) {
//			sheetName = fileName;
//		}
//		boolean disposedResult = true;
//		try {
//			// 设置响应流
//			
//			response.reset();
//			response.setCharacterEncoding("UTF-8");
//			response.setHeader("Content-Type", "application/force-download");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
//			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
//			HSSFRow row = null;
//			HSSFCell cell = null;
//			HSSFWorkbook wb = new HSSFWorkbook();
//			HSSFSheet sheet = wb.createSheet();
//			wb.setSheetName(0, sheetName, HSSFWorkbook.ENCODING_UTF_16);
//			
//			ArrayList<Object> lstTemp = (ArrayList<Object>) list;
//			for (int i = 0; i < lstTemp.size(); i++) {
//				row = sheet.createRow((short) i);
//				Object ob = lstTemp.get(i);
//				Field[] fields = ob.getClass().getDeclaredFields();
//				int count = 0;
//				// 该框架bean的第一个参数为serialVersionUID，导出excel时此参数可以忽略
//				for (int j = 0; j < fields.length; j++) {
//					String fieldName = fields[j].getName();
//					if ("serialVersionUID".equals(fieldName)) {
//						count++;
//						continue;
//					}
//					cell = row.createCell((short) (j - count));
//					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//					
//					String firstLetter = fieldName.substring(0, 1).toUpperCase();
//					String getMethodName = "get" + firstLetter + fieldName.substring(1);
//					Method getMethod = ob.getClass().getMethod(getMethodName, new Class[] {});
//					String tmp = getMethod.invoke(ob, new Object[] {}) == null ? "" : getMethod.invoke(ob, new Object[] {}).toString();
//					cell.setCellValue(tmp);
//				}
//			}
//			ServletOutputStream stream = response.getOutputStream();
//			wb.write(stream);
//			stream.flush();
//			stream.close();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			disposedResult = false;
//			throw e;
//		} finally {
//			return disposedResult;
//		}
//	}
	
	/**
	 * 把list类型的数据转换成字符串输出， 以"["开始，以"]"结尾，bean使用{}区分，bean之间使用","分隔 例子：[{"name1":"value1","name2":"value2"},
	 * {"name11":"value11","name12":"value12"}]
	 * 
	 * @param list
	 * @return
	 */
	public static String getStringFromList(List<?> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		return jsonArray.toString();
	}
	
	/**
	 * 文本文件导出
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public synchronized static String getContextFromRemoteTextFile(HttpServletRequest request) throws IOException {
		StringBuilder resultTemp = new StringBuilder();
		String line = "";
		boolean startflg = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((line = reader.readLine()) != null) {
			// 查找协议内容,http协议中Content-Type行以后协议内容开始，-----------------------------之前为协议内容
			
			if (line.contains("Content-Type: text/plain")) {
				startflg = true;
				continue;
			}
			if (startflg) {
				if (line.contains("-----------------------------")) {
					break;
				}
				if (!"".equals(line.trim())) {
					resultTemp.append(line);
					resultTemp.append("$");
				}
			}
		}
		return resultTemp.toString();
	}
	
	// /**
	// * 同步中间件服务的配置数据
	// *
	// * @return resultFlg T:同步成功；其他:同步失败
	// * @throws IOException
	// * @throws UnknownHostException
	// * @throws NumberFormatException
	// */
	// public static String synchronizationService() throws
	// NumberFormatException,
	// UnknownHostException, IOException {
	//
	//
	//
	// //String resultStr = "";
	// //RemoteClient client;
	//
	//
	// // 读取socket配置文件
	// //MyConfiguration config = new MyConfiguration(
	// // ToolFunctions.RESOURCEFILENAME);
	//
	// // 创建socket链接
	// //client = new RemoteClient(config.getValue("SocketService.IP"), Integer
	// // .parseInt(config.getValue("SocketService.PORT")));
	// // 发送socket指令
	// //resultStr = client.send("RESET");
	// // 关闭socket
	// //client.tearDownConnection();
	//
	// return resultStr;
	// }
	
	/**
	 * 同步中间件服务的配置数据
	 * 
	 * @return resultFlg T:同步成功；其他:同步失败
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws NumberFormatException
	 */
	// public static String synchronizationAction() {
	//
	// String message = "";
	// String resultStr = "";
	// try {
	//
	// // 执行同步命令
	// resultStr = ToolFunctions.synchronizationService();
	// } catch (Exception e) {
	// e.printStackTrace();
	// message = "同步HLR服务上的数据时发生异常，请联系管理员。";
	// }
	// if (!"T".equals(resultStr)) {
	// message = "同步HLR服务上的数据失败，请联系管理员。";
	// }
	// return message;
	// }
//	
//	/**
//	 * 将list类型的数据以excel的形式导入response,增加表头
//	 * 
//	 * @author zengqq
//	 * @param response 响应
//	 * @param list 要生成excel的数据源
//	 * @param fileName 工作簿名
//	 * @param sheetName 工作表名
//	 * @param header 表头
//	 * @return disposedResult 返回创建excel是否成功 true:成功；false:失败
//	 */
//	@SuppressWarnings("finally")
//	public static boolean createExcel(HttpServletResponse response, List<Object> list, String fileName, String sheetName, String[] header) {
//		if (sheetName == null || "".equals(sheetName)) {
//			sheetName = fileName;
//		}
//		boolean disposedResult = true;
//		try {
//			// 设置响应流
//			
//			response.reset();
//			response.setCharacterEncoding("UTF-8");
//			response.setHeader("Content-Type", "application/force-download");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
//			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
//			HSSFRow row = null;
//			HSSFCell cell = null;
//			HSSFWorkbook wb = new HSSFWorkbook();
//			HSSFSheet sheet = wb.createSheet();
//			wb.setSheetName(0, sheetName, HSSFWorkbook.ENCODING_UTF_16);
//			
//			// 创建标题样式
//			HSSFFont titleFont = wb.createFont();
//			titleFont.setFontHeightInPoints((short) 16);
//			titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//			HSSFCellStyle titleStyle = wb.createCellStyle();
//			titleStyle.setFont(titleFont);
//			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//			titleStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
//			
//			// 子标题样式
//			HSSFFont subTitleFont = wb.createFont();
//			subTitleFont.setFontHeightInPoints((short) 12);
//			subTitleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//			HSSFCellStyle subTitleStyle = wb.createCellStyle();
//			subTitleStyle.setFont(subTitleFont);
//			subTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//			subTitleStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
//			
//			// 表头样式
//			HSSFCellStyle headerStyle = subTitleStyle;
//			
//			row = sheet.createRow((short) 0);
//			for (int i = 0; i < header.length; i++) {
//				cell = row.createCell((short) i);
//				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//				cell.setCellStyle(headerStyle);
//				cell.setCellValue(header[i]);
//			}
//			
//			ArrayList<Object> lstTemp = (ArrayList<Object>) list;
//			for (int i = 0; i < lstTemp.size(); i++) {
//				row = sheet.createRow((short) i + 1);
//				Object ob = lstTemp.get(i);
//				Field[] fields = ob.getClass().getDeclaredFields();
//				int count = 0;
//				// 该框架bean的第一个参数为serialVersionUID，导出excel时此参数可以忽略
//				for (int j = 0; j < fields.length; j++) {
//					String fieldName = fields[j].getName();
//					if ("serialVersionUID".equals(fieldName)) {
//						count++;
//						continue;
//					}
//					cell = row.createCell((short) (j - count));
//					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//					
//					String firstLetter = fieldName.substring(0, 1).toUpperCase();
//					String getMethodName = "get" + firstLetter + fieldName.substring(1);
//					Method getMethod = ob.getClass().getMethod(getMethodName, new Class[] {});
//					String tmp = getMethod.invoke(ob, new Object[] {}) == null ? "" : getMethod.invoke(ob, new Object[] {}).toString();
//					cell.setCellValue(tmp);
//				}
//			}
//			ServletOutputStream stream = response.getOutputStream();
//			wb.write(stream);
//			stream.flush();
//			stream.close();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			disposedResult = false;
//			throw e;
//		} finally {
//			return disposedResult;
//		}
//	}
}
